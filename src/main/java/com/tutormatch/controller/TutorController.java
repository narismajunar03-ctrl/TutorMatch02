package com.tutormatch.controller;

import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;

import org.springframework.web.bind.annotation.*;

import com.tutormatch.dto.TutorRegistrationRequest;
import com.tutormatch.service.TutorService;


@RestController
@RequestMapping("/users")
public class TutorController {

	private final TutorService tutorService;

	public TutorController(TutorService tutorService) {
		this.tutorService = tutorService;
	}

	@PostMapping(value = "/tutor-registration", consumes = MediaType.MULTIPART_FORM_DATA_VALUE)
	public ResponseEntity<String> createTutor(@ModelAttribute TutorRegistrationRequest request) throws Exception {
		tutorService.createTutor(request);
		return ResponseEntity.ok("Successfully Create User Tutor");

	}


}