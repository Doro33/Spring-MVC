package com.example.classicspringmvc.controller;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

@Controller
public class FormController {

    @GetMapping("/form")
    public String showForm() {
        return "form";
    }

    @PostMapping("/submit")
    public String handleForm(@RequestParam String name, Model model) {
        model.addAttribute("name", name);
        return "result";
    }
}
