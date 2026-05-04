package com.tutormatch.controller;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;

@Controller
public class PageController {

	@GetMapping("/")
	public String showMainPage() {
		return "main";
	}
	
	@GetMapping("/login")
	public String showLogin() {
		return "login";
	}

	@GetMapping("/dashboard")
	public String showDashBoard() {
		return "dashboard";
	}

	@GetMapping("/lesson-list")
	public String showSubjectList() {
		return "lesson-list";
	}
	
	@GetMapping("/student-registration")
	public String showStudentRegistration() {
		return "student-registration";
	}
	
	@GetMapping("/tutor-registration")
	public String showTutorRegistration() {
		return "tutor-registration";
	}
	
	@GetMapping("/lesson-registration")
	public String showLessonRegistration() {
		return "lesson-registration";
	}

}
