package com.laxman.job.server.security;

import io.jsonwebtoken.*;
import io.jsonwebtoken.security.Keys;
import org.springframework.stereotype.Component;

import java.security.Key;
import java.util.Date;

@Component
public class JwtUtil {

    // 🔐 MUST be at least 32 characters for HS256
    private static final String SECRET_KEY =
            "my-super-secret-key-my-super-secret-key";

    private static final long EXPIRATION_TIME = 1000 * 60 * 60; // 1 hour

    // ✅ Convert plain text → secure Key
    private final Key key = Keys.hmacShaKeyFor(SECRET_KEY.getBytes());

    public String generateToken(String username) {
        return Jwts.builder()
                .setSubject(username)
                .setIssuedAt(new Date())
                .setExpiration(new Date(System.currentTimeMillis() + EXPIRATION_TIME))
                .signWith(key, SignatureAlgorithm.HS256) // ✅ CORRECT
                .compact();
    }

    public String extractUsername(String token) {
        return Jwts.parserBuilder()
                .setSigningKey(key) // ✅ SAME KEY
                .build()
                .parseClaimsJws(token)
                .getBody()
                .getSubject();
    }
}
