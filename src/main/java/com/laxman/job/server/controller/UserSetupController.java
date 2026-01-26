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

        if (user.geRole() != null) {
            user.setRole(user.getRole());
        } else {
            user.setRole("USER");
        }

        user.setOtp(user.getOtp());
        user.setOtpExpiry(null);

        userRepository.save(user);

        return "User created successfully";
    }
}
