package com.tutormatch.jpa.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import com.tutormatch.model.DocumentModel;

public interface DocumentRepository extends JpaRepository<DocumentModel, Long> {

}
