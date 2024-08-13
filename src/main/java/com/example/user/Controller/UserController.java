package com.example.user.Controller;

import com.example.user.Model.User;
import com.example.user.Service.UserService;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.Errors;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequiredArgsConstructor
@RequestMapping("/api/v1/user")
public class UserController {
    private final UserService userService;
    private @Valid User user;

    @GetMapping("/get")
    public ResponseEntity getAllUsers() {
        return ResponseEntity.ok(userService.getAllUsers());
    }

    @PostMapping("/add")
    public ResponseEntity addUser(@Valid @RequestBody User user, Errors errors) {
        if (errors.hasErrors()) {
            String message = errors.getAllErrors().get(0).getDefaultMessage();
            return ResponseEntity.badRequest().body(message);
        }
        userService.add(user);
        return ResponseEntity.status(200).body("user added successfully");
    }

    @PutMapping("/update/{id}")
    public ResponseEntity updateUser(@PathVariable Integer id, User user, Errors errors) {
        if (errors.hasErrors()) {
            String message = errors.getAllErrors().get(0).getDefaultMessage();
            return ResponseEntity.badRequest().body(message);
        }
        userService.update(id, user);
        return ResponseEntity.status(200).body("user updated successfully");
    }

    @DeleteMapping("/Delete/{id}")
    public ResponseEntity deleteUser(@PathVariable Integer id) {
        userService.delete(id);
        return ResponseEntity.status(200).body("user deleted successfully");
    }

    @GetMapping("/email/{email}")
    public ResponseEntity getUserByEmail(@PathVariable String email) {
        return ResponseEntity.ok(userService.getUserByEmail(email));
    }
    @GetMapping("/role/{role}")
    public ResponseEntity getUserByRole(@PathVariable String role) {
        List<User> users = userService.getUsersByRole(role);
        if (users.isEmpty()) {
            return ResponseEntity.status(404).body("No user found with role " + role);
        }
        return ResponseEntity.ok(users);
    }
    @GetMapping("/sign/{username}/{password}")
    public ResponseEntity getUserByUsername(@PathVariable String username, @PathVariable String password) {
        return ResponseEntity.status(200).body(userService.getUserByUserNameAndPassword(username, password));
    }
    @GetMapping("/age/{age}")
    public ResponseEntity getUserByAge(@PathVariable Integer age) {
        List<User> users = userService.getUserByAge(age);
        if (users.isEmpty()) {
            return ResponseEntity.status(404).body("No user found with age " + age);
        }
        return ResponseEntity.status(200).body(userService.getUserByAge(age));
    }
}

