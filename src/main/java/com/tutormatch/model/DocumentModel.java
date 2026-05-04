package com.tutormatch.model;


import com.fasterxml.jackson.annotation.JsonIgnore;
import com.tutormatch.dto.DocumentType;

import jakarta.persistence.*;

@Entity
@Table(name = "documents")
public class DocumentModel {
	

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(name = "fileName")
    private String fileName;
    
    @Column(name = "fileType")
    private String fileType;
    
    @Column(name = "filePath")
    private String filePath;
    
    @Enumerated(EnumType.STRING)
    @Column(name = "type")
    private DocumentType type; // ID, LICENSE, CERT

    @JsonIgnore
    @ManyToOne
    @JoinColumn(name = "tutor_id")
    private TutorModel tutor;
    
    public DocumentModel() {
    	
    }


	public Long getId() {
		return id;
	}

	public void setId(Long id) {
		this.id = id;
	}

	public String getFileName() {
		return fileName;
	}

	public void setFileName(String fileName) {
		this.fileName = fileName;
	}

	public String getFileType() {
		return fileType;
	}

	public void setFileType(String fileType) {
		this.fileType = fileType;
	}

	public String getFilePath() {
		return filePath;
	}

	public void setFilePath(String filePath) {
		this.filePath = filePath;
	}

	public DocumentType getType() {
		return type;
	}


	public void setType(DocumentType type) {
		this.type = type;
	}


	public TutorModel getTutor() {
		return tutor;
	}

	public void setTutor(TutorModel tutor) {
		this.tutor = tutor;
	}
    
    
    
}