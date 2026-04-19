package com.tutormatch.dto;

public class Student {
    private String name;
    private String subject;
    private String progress;

    public Student(String name, String subject, String progress) {
        this.name = name;
        this.subject = subject;
        this.progress = progress;
    }

    public String getName() {
        return name;
    }

    public String getSubject() {
        return subject;
    }

    public String getProgress() {
        return progress;
    }
}