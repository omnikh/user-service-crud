package com.kapstone.challenge.userservice.entity;

import com.fasterxml.jackson.annotation.JsonFormat;
import lombok.*;

import javax.persistence.*;
import javax.validation.constraints.Email;
import javax.validation.constraints.NotBlank;
import java.time.LocalDate;
import java.time.LocalDateTime;

@Entity
@Getter
@Setter
@ToString
@NoArgsConstructor
@AllArgsConstructor
@Table(name = "user_master")
public class User {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long userId;

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

    @JsonFormat(pattern = "dd-MM-yyyy HH:mm:ss")
    @Column(name = "create_date", nullable = false)
    private LocalDateTime createDate;

    @JsonFormat(pattern = "dd-MM-yyyy HH:mm:ss")
    @Column(name = "updated_date", nullable = false)
    private LocalDateTime updatedDate;

    @Column(nullable = false)
    private boolean active;
}
