package com.laxman.job.server.service;


import com.laxman.job.server.model.User;
import com.laxman.job.server.repository.UserRepository;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;


@Service
public class UserService {

    @Autowired
    private final UserRepository userRepository;

    @Autowired
    private PasswordEncoder passwordEncoder;

    public UserService(UserRepository  userRepository) {
        this.userRepository = userRepository;
    }

    public User findByUsername(String  username) {
        return userRepository.findByUsername(username).orElseThrow(() -> new RuntimeException("User not found"));
    }
    public User findByEmail(String email) {
        return userRepository.findByEmail(email)
                .orElseThrow(() -> new RuntimeException("User not found"));
    }

    public void updatePassword(User user, String newPassword) {
        user.setPassword(passwordEncoder.encode(newPassword));
        user.setOtp(null);
        user.setOtpExpiry(null);
        userRepository.save(user);
    }

    public void saveOtp(User user, String otp, LocalDateTime expiry) {
        user.setOtp(otp);
        user.setOtpExpiry(expiry);
        userRepository.save(user);
    }
}

