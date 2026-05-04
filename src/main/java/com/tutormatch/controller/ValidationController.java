package com.tutormatch.controller;

import org.springframework.web.bind.annotation.*;

import com.tutormatch.jpa.repository.TutorRepository;

@RestController
@RequestMapping("/check")
public class ValidationController {

	private final TutorRepository tutorRepository;

	public ValidationController(TutorRepository tutorRepository) {
		this.tutorRepository = tutorRepository;
	}
	
	@GetMapping("/tutor-email")
	public boolean checkTutorEmail(@RequestParam String email) {
		return tutorRepository.existsByEmail(email);
	}

	@GetMapping("/tutor-username")
	public boolean checkTutorUsername(@RequestParam String username) {
		return tutorRepository.existsByUsername(username);
	}

}
