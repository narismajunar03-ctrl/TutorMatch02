package com.tutormatch.service;

import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.ArrayList;
import java.util.List;

import org.springframework.http.ResponseEntity;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.*;
import org.springframework.web.multipart.MultipartFile;

import com.tutormatch.config.SecurityConfig;
import com.tutormatch.dto.DocumentType;
import com.tutormatch.dto.TutorRegistrationRequest;
import com.tutormatch.jpa.repository.DocumentRepository;
import com.tutormatch.jpa.repository.TutorRepository;
import com.tutormatch.model.DocumentModel;
import com.tutormatch.model.TutorModel;

@Service
public class TutorService {


	private final TutorRepository tutorRepository;
	private final DocumentRepository documentRepository;
	private final PasswordEncoder passwordEncoder;

	public TutorService(TutorRepository tutorRepository
			,DocumentRepository documentRepository,	PasswordEncoder passwordEncoder) {

		this.tutorRepository = tutorRepository;
		this.documentRepository = documentRepository;
		this.passwordEncoder = passwordEncoder;
	}

	public TutorModel createTutor(TutorRegistrationRequest request)  throws Exception {

		TutorModel tutor = new TutorModel();
		tutor.setFname(request.getFname());
		tutor.setLname(request.getLname());
		tutor.setEmail(request.getEmail());
		tutor.setContactNumber(request.getContactNumber());
		tutor.setStreetAddress(request.getStreetAddress());
		tutor.setBarangay(request.getBarangay());
		tutor.setCity(request.getCity());
		tutor.setProvince(request.getProvince());
		tutor.setZipCode(request.getZipCode());
		tutor.setUsername(request.getUsername());
		tutor.setPassword(passwordEncoder.encode(request.getPassword()));
		
		tutor.setDocuments(new ArrayList<>());

		addDocuments(request.getCertificate(),tutor,DocumentType.CERT,"certificate");
		addDocuments(request.getLicense(),tutor,DocumentType.LICENSE,"license");
		addDocuments(request.getAdditionalDocs(),tutor,DocumentType.ID,"valid-id");

		return tutorRepository.save(tutor);

	}

	private void addDocuments(List<MultipartFile> files, TutorModel tutor, DocumentType cert, String string) throws Exception {
		if(files != null) {

			for(MultipartFile file : files) {
				DocumentModel doc = createDocument(file, DocumentType.CERT, "certificate");
				doc.setTutor(tutor);
				tutor.getDocuments().add(doc);
			}

		}
		
	}

	private DocumentModel createDocument(MultipartFile file, DocumentType type, String folder) throws Exception{

		DocumentModel doc = new DocumentModel();

		doc.setFileName(file.getOriginalFilename());
		doc.setFileType(file.getContentType());
		doc.setType(type);
		doc.setFilePath(saveFile(file,folder));

		return doc;

	}

	private String saveFile(MultipartFile file, String folder)  throws Exception {
		String uploadDir = "uploads/" + folder +"/";
		Files.createDirectories(Paths.get(uploadDir));
		
		String fileName = System.currentTimeMillis() + "_" + file.getOriginalFilename();
		Path path = Paths.get(uploadDir + fileName);
		
		Files.write(path,file.getBytes());
		
		return uploadDir + fileName;

	}

}
