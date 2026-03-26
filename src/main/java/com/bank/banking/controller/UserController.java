package com.bank.banking.controller;

import com.bank.banking.dto.UserResponseDTO;
import com.bank.banking.entity.User;
import com.bank.banking.repository.UserRepository;
import com.bank.banking.service.UserService;
import jakarta.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/users")
public class UserController {

    @Autowired
    private UserRepository userRepository;

    @Autowired
    private UserService userService;

    @PostMapping("/register")
    public User register(@Valid @RequestBody User user) {
        return userService.register(user);
    }
    @PostMapping("/login")
    public String login(@RequestBody User user) {
        return userService.login(user.getEmail(), user.getPassword());
    }

    @GetMapping("/admin/all-users")
    public List<UserResponseDTO> getAllUsers() {
        return userService.getAllUsers();
    }
}
