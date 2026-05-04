package com.tutormatch.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.mongodb.core.MongoTemplate;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.tutormatch.model.LessonModel;
import com.tutormatch.mongo.repository.LessonRepository;

@RestController
@RequestMapping("/tutor-match/lessons")
@CrossOrigin
public class LessonController {

	private LessonRepository lessonRepository;

	public LessonController(LessonRepository lessonRepository) {
		this.lessonRepository = lessonRepository;
	}

	@PostMapping
	public String createLesson(@RequestBody LessonModel lesson) {

		lessonRepository.save(lesson);

		return "Lesson Save Successfully";
	}
	
	@GetMapping
	public List<LessonModel> listLesson(){
		return lessonRepository.findAll();	
	}
	
	@GetMapping("/tutor-match/detail/{id}")
	public LessonModel getLesson(@PathVariable String id) {
		return lessonRepository.findById(id)
				.orElseThrow(() -> new RuntimeException("Error Not Found"));
	}
	

}
