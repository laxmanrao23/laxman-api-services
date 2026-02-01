package com.laxman.job.server.controller;

import org.springframework.web.bind.annotation.*;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.beans.factory.annotation.Autowired;

import com.laxman.job.server.model.User;
import com.laxman.job.server.repository.UserRepository;

@RestController
@RequestMapping("/api/setup")
public class UserSetupController {

    @Autowired
    private UserRepository userRepository;

    @Autowired
    private PasswordEncoder passwordEncoder;

    @PostMapping("/create-user")
    public String createUser(@RequestBody User user) {

        user.setUsername(user.getUsername());
        user.setPassword(passwordEncoder.encode(user.getPassword()));

        user.setRole("USER");   // default role
        user.setOtp(null);
        user.setOtpExpiry(null);
        user.setEmail(user.getEmail());
        userRepository.save(user);

        return "User created successfully";
    }
}
