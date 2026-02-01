package com.laxman.job.server.service;

import org.springframework.stereotype.Service;

import java.util.*;
import java.time.LocalDateTime;
@Service
public class OtpService {
    public String generateOtp() {
        return String.valueOf(100000 + new Random().nextInt(900000));
    }

    public LocalDateTime getExpiryTime() {
        return LocalDateTime.now().plusMinutes(5);
    }

    public boolean isOtpExpired(LocalDateTime expiryTime) {
        return expiryTime.isBefore(LocalDateTime.now());
    }
}
