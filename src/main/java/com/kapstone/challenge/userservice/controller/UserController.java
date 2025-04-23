package com.kapstone.challenge.userservice.controller;

import com.kapstone.challenge.userservice.dto.UserRequestDTO;
import com.kapstone.challenge.userservice.dto.response.ApiResponseDTO;
import com.kapstone.challenge.userservice.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import java.util.List;

@RestController
@RequestMapping("/api/v1/users")
public class UserController {

    @Autowired
    private UserService userService;

    @PostMapping
    public ResponseEntity<ApiResponseDTO<?>> createUsers(@Valid @RequestBody UserRequestDTO user) {
        return ResponseEntity.ok(userService.createUsers(user));
    }

    @GetMapping
    public ResponseEntity<ApiResponseDTO<?>> getAllUsers() {
        return ResponseEntity.ok(userService.getAllUsers());
    }

    @GetMapping("/{userId}")
    public ResponseEntity<ApiResponseDTO<?>> getUserById(@PathVariable Long userId) {
        return ResponseEntity.ok(userService.getUserById(userId));
    }

    @DeleteMapping("/{userId}")
    public ResponseEntity<ApiResponseDTO<?>> deleteUserById(@PathVariable Long userId) {
        return ResponseEntity.ok(userService.deleteUserById(userId));
    }


}
