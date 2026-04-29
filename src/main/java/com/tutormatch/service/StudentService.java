package com.tutormatch.service;


import org.springframework.stereotype.Service;

import com.tutormatch.model.StudentModel;
import com.tutormatch.repository.StudentRepository;



@Service
public class StudentService {

	private final StudentRepository studentRepository;

	public StudentService(StudentRepository studentRepository) {
		this.studentRepository = studentRepository;
	}
	
	public StudentModel createStudent(StudentModel user) {
		
		return studentRepository.save(user);
	}

	public boolean existsByUsername(String username) {
	    return studentRepository.findByUsername(username).isPresent();
	}

	public boolean existsByEmail(String email) {
	    return studentRepository.findByEmail(email).isPresent();
	}
	
	

}
