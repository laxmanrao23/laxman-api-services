package com.laxman.job.server.model;

import jakarta.persistence.*;
import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.Document;
import org.springframework.data.mongodb.core.index.Indexed;


import java.time.LocalDateTime;

//@Entity
//@Table(name = "users")
@Document(collection = "user")
public class User {

    @Id
    //@GeneratedValue(strategy = GenerationType.IDENTITY)
    private String id; //Must be string (or objectid)

    //@Column(unique = true, nullable = false)
    @Indexed(unique = true)
    private String username;

    //@Column(nullable = false)

    private String password;

    private String role;

    //@Column(length = 6)
    private String otp;

    private LocalDateTime otpExpiry;
    // 🔹 GETTERS & SETTERS

    @Indexed(unique = true)
    private String email;

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }


    public String getOtp() {
        return otp;
    }

    public void setOtp(String otp) {
        this.otp = otp;
    }

    public LocalDateTime getOtpExpiry() {
        return otpExpiry;
    }

    public void setOtpExpiry(LocalDateTime otpExpiry) {
        this.otpExpiry = otpExpiry;
    }

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public String getUsername() {
        return username;
    }

    public void setUsername(String username) {
        this.username = username;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public String getRole() {
        return role;
    }

    public void setRole(String role) {
        this.role = role;
    }
}
