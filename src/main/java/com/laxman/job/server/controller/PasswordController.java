package com.laxman.job.server.controller;

import com.laxman.job.server.dto.ErrorResponse;
import com.laxman.job.server.dto.ForgotPasswordRequest;
import com.laxman.job.server.dto.ResetPasswordRequest;
import com.laxman.job.server.exceptions.ErrorCode;
import com.laxman.job.server.model.User;
import com.laxman.job.server.repository.UserRepository;
import com.laxman.job.server.service.EmailService;
import com.laxman.job.server.service.OtpService;
import com.laxman.job.server.service.UserService;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.time.LocalDateTime;
import java.util.*;

@RestController
@RequestMapping("/api/auth")
public class PasswordController {

    @Autowired
    private UserService userService;

    @Autowired
    private OtpService otpService;

    @Autowired
    private EmailService emailService;

    @Autowired
    private UserRepository userRepository;

    @PostMapping("/forgot-password")
    public ResponseEntity<?> forgotPassword(@RequestBody ForgotPasswordRequest request) {

        String email = request.getEmail();
        User user = userService.findByEmail(email);

        if (user == null) {
            return ResponseEntity.badRequest().body(new ErrorResponse(
                    ErrorCode.USER_NOT_FOUND.name(),
                    "User not found with this email"
            ));
        }
        String otp = otpService.generateOtp();
        LocalDateTime expiry = otpService.getExpiryTime();

        userService.saveOtp(user, otp, expiry);
        emailService.sendOtp(user.getEmail(), otp);

        return ResponseEntity.ok("OTP sent successfully");
    }

    @PostMapping("/reset-password")
    public ResponseEntity<?> resetPassword(@RequestBody ResetPasswordRequest request) {

        String email = request.getEmail();
        User user = userService.findByEmail(email);

        if (user == null) {
            return ResponseEntity.badRequest().body("User not found with this email");
        }

        if (!request.getOtp().equals(user.getOtp())) {
            return ResponseEntity.badRequest().body("Invalid OTP");
        }

        if (otpService.isOtpExpired(user.getOtpExpiry())) {
            return ResponseEntity.badRequest().body("OTP expired");
        }

        userService.updatePassword(user, request.getNewPassword());

        return ResponseEntity.ok("Password updated");
    }
}
