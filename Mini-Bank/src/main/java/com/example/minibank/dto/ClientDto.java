package com.example.minibank.dto;

import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.Pattern;
import jakarta.validation.constraints.Size;
import lombok.Data;

@Data
public class ClientDto {

    @NotBlank(message = "Username is required")
    @Size(min = 2, max = 30, message = "Username must be between 2 and 30 characters")
    private String username;
    @NotBlank(message = "Password is required")
    @Pattern(
            regexp = "^(?=.*[A-Za-z])(?=.*\\d)[A-Za-z\\d]{8,}$",
            message = "Password must be at least 8 characters long and contain at least one letter and one number"
    )
    private String password;

    public ClientDto(@NotBlank(message = "Username is required") @Size(min = 2, max = 30, message = "Username must be between 2 and 30 characters") String username,
                     @NotBlank(message = "Password is required") @Pattern(
                             regexp = "^(?=.*[A-Za-z])(?=.*\\d)[A-Za-z\\d]{8,}$",
                             message = "Password must be at least 8 characters long and contain at least one letter and one number"
                     ) String password) {
        this.username = username;
        this.password = password;
    }
}
