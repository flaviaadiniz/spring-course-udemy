package com.flavia.thymeleafdemo.controller;

import com.flavia.thymeleafdemo.model.Student;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;

import java.util.List;

@Controller
public class StudentController {

    @Value("${countries}")
    private List<String> countries;

    @Value("${languages}")
    private List<String> languages;

    @GetMapping("/showStudentForm")
    public String showForm(Model model) {

        //create a new student object
        Student theStudent = new Student();

        //add student object to the model
        model.addAttribute("student", theStudent);

        //add the list of countries to the model
        model.addAttribute("countries", countries);

        //add the list of languages to the model
        model.addAttribute("languages", languages);

        return "student-form";
    }

    @PostMapping("/processStudentForm")
    public String processForm(@ModelAttribute("student") Student theStudent) {

        //log the input data
        System.out.println("The student: " + theStudent.getFirstName() + " " + theStudent.getLastName());

        return "student-confirmation";
    }


}
