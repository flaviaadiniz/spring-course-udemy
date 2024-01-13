package com.flavia.thymeleafdemo.controller;

import com.flavia.thymeleafdemo.model.Student;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;

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

}
