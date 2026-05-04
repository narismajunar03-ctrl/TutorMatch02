package com.tutormatch.mongo.repository;

import org.springframework.data.mongodb.repository.MongoRepository;

import com.tutormatch.model.LessonModel;

public interface LessonRepository extends MongoRepository<LessonModel, String> {

}
