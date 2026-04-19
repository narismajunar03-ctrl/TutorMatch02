package com.tutormatch.dto;

public class Session {
    private String time;
    private String subject;
    private String title;
    private String mode;

    public Session(String time, String subject, String title, String mode) {
        this.time = time;
        this.subject = subject;
        this.title = title;
        this.mode = mode;
    }

    public String getTime() {
        return time;
    }

    public String getSubject() {
        return subject;
    }

    public String getTitle() {
        return title;
    }

    public String getMode() {
        return mode;
    }
}
