package com.example.classicspringmvc.model;

import jakarta.validation.constraints.*;
import lombok.Data;

@Data
public class UserForm {

    @NotBlank(message = "Name is required")
    private String name;

    @Email(message = "Email must be valid")
    @NotBlank(message = "Email is required")
    private String email;

    @Min(value = 1, message = "Age must be greater than 0")
    @Max(value = 120, message = "Age must be realistic")
    private int age;

}
