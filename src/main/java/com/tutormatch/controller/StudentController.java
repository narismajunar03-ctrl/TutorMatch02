package com.tutormatch.controller;


import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import com.tutormatch.model.StudentModel;
import com.tutormatch.service.StudentService;

@RestController
@RequestMapping("/users")
public class StudentController {

	private final StudentService studentService;

	public StudentController(StudentService userService) {
		this.studentService = userService;
	}

	@PostMapping("/student-registration")
	public ResponseEntity<String> createStudent(@RequestBody StudentModel studentUser) {

		if (studentService.existsByUsername(studentUser.getUsername())) {
			return ResponseEntity
					.badRequest()
					.body("Username already exists");
		}

		if (studentService.existsByEmail(studentUser.getEmail())) {
			return ResponseEntity
					.badRequest()
					.body("Email already exists");
		}

		studentService.createStudent(studentUser);

		return ResponseEntity.ok("User Created Sucessfully");

	}

}
