package com.qingteng.auth.service;

import java.util.Map;

public interface TokenService {

    /**
     * 生成访问令牌
     * @param userId 用户ID
     * @return JWT token
     */
    String generateAccessToken(Long userId);

    /**
     * 生成访问令牌（带额外信息）
     * @param userId 用户ID
     * @param extraClaims 额外的声明信息
     * @return JWT token
     */
    String generateAccessToken(Long userId, Map<String, Object> extraClaims);

    /**
     * 验证令牌
     * @param token JWT token
     * @return 是否有效
     */
    boolean validateToken(String token);

    /**
     * 从令牌中获取用户ID
     * @param token JWT token
     * @return 用户ID
     */
    Long getUserIdFromToken(String token);
}
