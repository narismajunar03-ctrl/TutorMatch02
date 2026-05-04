package com.tutormatch.jpa.repository;

import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;

import com.tutormatch.model.TutorModel;

public interface TutorRepository extends JpaRepository<TutorModel,Long> {
	boolean existsByEmail(String email);
	boolean existsByUsername(String username);


}
