package com.qingteng.auth.service.impl;

import com.qingteng.auth.service.TokenService;
import com.qingteng.auth.util.JwtUtil;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.Map;

@Service
@AllArgsConstructor
public class TokenServiceImpl implements TokenService {

    private final JwtUtil jwtUtil;

    @Override
    public String generateAccessToken(Long userId) {
        return jwtUtil.generateToken(userId);
    }

    @Override
    public String generateAccessToken(Long userId, Map<String, Object> extraClaims) {
        return jwtUtil.generateToken(userId, extraClaims);
    }

    @Override
    public boolean validateToken(String token) {
        return jwtUtil.validateToken(token);
    }

    @Override
    public Long getUserIdFromToken(String token) {
        return jwtUtil.getUserIdFromToken(token);
    }
}
