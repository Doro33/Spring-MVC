package com.example.classicspringmvc.controller;

import com.example.classicspringmvc.model.UserForm;
import jakarta.validation.Valid;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.*;

@Controller
public class FormObjectController {

    @GetMapping("/user-form")
    public String showForm(Model model) {
        model.addAttribute("userForm", new UserForm());
        return "user-form";
    }

    @PostMapping("/submit-user")
    public String handleSubmit(
            @ModelAttribute @Valid UserForm userForm,
            BindingResult result,
            Model model) {

        if (result.hasErrors()) {
            return "user-form"; // Return form with error messages
        }

        model.addAttribute("userForm", userForm);
        return "user-result";
    }
}

