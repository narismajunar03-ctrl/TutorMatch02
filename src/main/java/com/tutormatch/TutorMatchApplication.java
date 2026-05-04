package com.tutormatch;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.data.jpa.repository.config.EnableJpaRepositories;
import org.springframework.data.mongodb.repository.config.EnableMongoRepositories;

@SpringBootApplication
@EnableMongoRepositories(basePackages = "com.tutormatch.mongo.repository")
@EnableJpaRepositories(basePackages = "com.tutormatch.jpa.repository")
public class TutorMatchApplication {

    public static void main(String[] args) {
        SpringApplication.run(TutorMatchApplication.class, args);
    }

}
