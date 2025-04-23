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
    public ResponseEntity<ApiResponseDTO<?>> createUsers(@Valid @RequestBody List<UserRequestDTO> users) {
        return ResponseEntity.ok(userService.createUsers(users));
    }

    @GetMapping
    public ResponseEntity<List<User>> getAllUsers(@RequestParam(name = "active", required = false) Optional<Boolean> active) {
        return ResponseEntity.ok(userService.getAllUsers(active));
    }

    @GetMapping("/{userId}")
    public ResponseEntity<User> getUserById(@PathVariable Long userId) {
        return ResponseEntity.ok(userService.getUserById(userId));
    }

    @GetMapping(params = "name")
    public ResponseEntity<User> getUserByName(@RequestParam String name) {
        return ResponseEntity.ok(userService.getUserByName(name));
    }

    @GetMapping(params = "email")
    public ResponseEntity<User> getUserByEmail(@RequestParam String email) {
        return ResponseEntity.ok(userService.getUserByEmail(email));
    }

    @PutMapping("/{userId}")
    public ResponseEntity<User> updateUser(@PathVariable Long userId, @Valid @RequestBody User user) {
        return ResponseEntity.ok(userService.updateUser(userId, user));
    }

    @PutMapping(params = "name")
    public ResponseEntity<User> updateUserByName(@RequestParam String name, @Valid @RequestBody User user) {
        User existingUser = userService.getUserByName(name);
        return ResponseEntity.ok(userService.updateUser(existingUser.getUserId(), user));
    }

    @PutMapping(params = "email")
    public ResponseEntity<User> updateUserByEmail(@RequestParam String email, @Valid @RequestBody User user) {
        User existingUser = userService.getUserByEmail(email);
        return ResponseEntity.ok(userService.updateUser(existingUser.getUserId(), user));
    }

    @DeleteMapping("/{userId}")
    public ResponseEntity<Void> deleteUserById(@PathVariable Long userId) {
        userService.deleteUserById(userId);
        return ResponseEntity.noContent().build();
    }

    @DeleteMapping(params = "name")
    public ResponseEntity<Void> deleteUserByName(@RequestParam String name) {
        userService.deleteUserByName(name);
        return ResponseEntity.noContent().build();
    }

    @DeleteMapping(params = "email")
    public ResponseEntity<Void> deleteUserByEmail(@RequestParam String email) {
        userService.deleteUserByEmail(email);
        return ResponseEntity.noContent().build();
    }

    @DeleteMapping
    public ResponseEntity<Void> deleteAllUsers(@RequestParam(name = "active", required = false) Optional<Boolean> active) {
        userService.deleteAllUsers(active);
        return ResponseEntity.noContent().build();
    }
}
