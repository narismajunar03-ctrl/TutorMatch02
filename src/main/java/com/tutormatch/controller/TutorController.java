package com.tutormatch.controller;

import com.tutormatch.dto.Session;
import com.tutormatch.dto.Lesson;
import com.tutormatch.dto.Student;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;

import java.time.LocalDate;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.LinkedHashMap;
import java.util.List;
import java.util.Map;

@Controller
public class TutorController {

    @GetMapping("/tutor-dashboard")
    public String tutorDashboard(Model model) {
        List<Lesson> lessons = new ArrayList<>();
        lessons.add(new Lesson("Math Basics", "Mathematics", "Active"));
        lessons.add(new Lesson("English Grammar", "English", "Active"));

        List<Student> students = new ArrayList<>();
        students.add(new Student("John Doe", "Mathematics", "75%"));
        students.add(new Student("Jane Smith", "English", "82%"));

        Map<LocalDate, List<Session>> sessionsByDate = new LinkedHashMap<>();

        sessionsByDate.put(
                LocalDate.of(2025, 4, 15),
                Arrays.asList(
                        new Session("10:00 AM", "English", "Reading Comprehension", "ONLINE"),
                        new Session("5:00 PM", "Math", "Algebra Practice", "FACE_TO_FACE")
                )
        );

        sessionsByDate.put(
                LocalDate.of(2025, 4, 16),
                Arrays.asList(
                        new Session("2:00 PM", "Science", "Basic Physics", "ONLINE")
                )
        );

        model.addAttribute("lessons", lessons);
        model.addAttribute("students", students);
        model.addAttribute("sessionsByDate", sessionsByDate);

        return "tutor-dashboard";
    }
}