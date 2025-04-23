package com.kapstone.challenge.userservice.dto;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.Setter;

import javax.persistence.Column;
import javax.validation.constraints.Email;
import javax.validation.constraints.NotBlank;

@Getter
@Setter
@AllArgsConstructor
public class UserRequestDTO {
    @NotBlank(message = "Name cannot be blank")
    @Column(nullable = false)
    private String name;

    @NotBlank(message = "Password cannot be blank")
    @Column(nullable = false)
    private String password; // Will be Base64 encoded

    @NotBlank(message = "Email cannot be blank")
    @Email(message = "Email must be a valid email address")
    @Column(nullable = false)
    private String email;
}
