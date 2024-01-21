package com.flavia.springdemo.mvc;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;

@Controller
public class CustomerController {

    @GetMapping("/")
    public String showForm(Model theModel) { //model allows us to share info between Controllers and view pages

        theModel.addAttribute("customer", new Customer());

        return "customer-form"; //will map to customer-form.html
    }

}
