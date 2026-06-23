package com.application.auth.dto;

import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.Size;
import lombok.AllArgsConstructor;
import lombok.Data;

@Data
@AllArgsConstructor
public class RegisterRequest {


@NotBlank(message = "name is required")
    private String name;

@NotBlank(message = "email is required")
@Email(message = "Enter a valid emil")
    private String email;

@NotBlank(message = "password is required")
@Size(min = 6, message = "Password must be at least 6 characters")
    private String password;



}
