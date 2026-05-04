package com.tutormatch.model;

import java.util.List;

import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.Document;

import com.tutormatch.dto.Availability;

@Document(collection ="lessons")
public class LessonModel {

	@Id
	private String id;
	
	private String lesson;
	private String description;
	private double rate;
	private int hours;
	private String sessionType;
	private List<Availability> availabilityDate;
	private String mode;
	private String tutorId;
	
	public LessonModel() {}

	public String getId() {
		return id;
	}

	public void setId(String id) {
		this.id = id;
	}

	public String getLesson() {
		return lesson;
	}

	public void setLesson(String lesson) {
		this.lesson = lesson;
	}

	public String getDescription() {
		return description;
	}

	public void setDescription(String description) {
		this.description = description;
	}

	public double getRate() {
		return rate;
	}

	public void setRate(double rate) {
		this.rate = rate;
	}

	public int getHours() {
		return hours;
	}

	public void setHours(int hours) {
		this.hours = hours;
	}

	public String getSessionType() {
		return sessionType;
	}

	public void setSessionType(String sessionType) {
		this.sessionType = sessionType;
	}

	public List<Availability> getAvailabilityDate() {
		return availabilityDate;
	}

	public void setAvailabilityDate(List<Availability> availabilityDate) {
		this.availabilityDate = availabilityDate;
	}

	public String getMode() {
		return mode;
	}

	public void setMode(String mode) {
		this.mode = mode;
	}

	public String getTutorId() {
		return tutorId;
	}

	public void setTutorId(String tutorId) {
		this.tutorId = tutorId;
	}
	
	
	

}
