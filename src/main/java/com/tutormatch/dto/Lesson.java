package com.tutormatch.dto;

public class Lesson {
    private String name;
    private String subject;
    private String status;

    public Lesson(String name, String subject, String status) {
        this.name = name;
        this.subject = subject;
        this.status = status;
    }

    public String getName() {
        return name;
    }

    public String getSubject() {
        return subject;
    }

    public String getStatus() {
        return status;
    }
}
