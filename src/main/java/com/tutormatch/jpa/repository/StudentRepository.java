package com.tutormatch.jpa.repository;

import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;

import com.tutormatch.model.StudentModel;

public interface StudentRepository extends JpaRepository<StudentModel, Long> {

	Optional<StudentModel> findByUsername(String username);

	Optional<StudentModel> findByEmail(String email);

}
