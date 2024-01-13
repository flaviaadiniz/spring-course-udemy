package com.flavia.thymeleafdemo.controller;

import com.flavia.thymeleafdemo.model.Student;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;

@Controller
public class StudentController {


    @GetMapping("/showStudentForm")
    public String showForm(Model model) {

        //create a new student object
        Student theStudent = new Student();

        //add student object to the model
        model.addAttribute("student", theStudent);

        return "student-form";
    }

    @PostMapping("/processStudentForm")
    public String processForm(@ModelAttribute("student") Student theStudent) {

        //log the input data
        System.out.println("The student: " + theStudent.getFirstName() + " " + theStudent.getLastName());

        return "student-confirmation";
    }


}
