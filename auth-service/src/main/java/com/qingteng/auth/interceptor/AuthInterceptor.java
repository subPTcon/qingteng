package com.qingteng.auth.interceptor;

import com.qingteng.auth.service.TokenService;
import com.qingteng.common.exception.BusinessException;
import com.qingteng.common.result.ErrorCode;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import lombok.AllArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Component;
import org.springframework.web.servlet.HandlerInterceptor;

@Component
@AllArgsConstructor
@Slf4j
public class AuthInterceptor implements HandlerInterceptor {

    private final TokenService tokenService;

    @Override
    public boolean preHandle(HttpServletRequest request, HttpServletResponse response, Object handler) {
        // 从请求头中获取token
        String token = request.getHeader("Authorization");

        if (token == null || token.isEmpty()) {
            log.warn("Token is missing in request header");
            throw new BusinessException(ErrorCode.TOKEN_MISSING_ERROR);
        }

        // 如果token以"Bearer "开头，去掉前缀
        if (token.startsWith("Bearer ")) {
            token = token.substring(7);
        }

        // 验证token
        if (!tokenService.validateToken(token)) {
            log.warn("Invalid or expired token: {}", token);
            throw new BusinessException(ErrorCode.TOKEN_INVALID_ERROR);
        }

        // 从token中获取用户ID并存储到request中，方便后续使用
        Long userId = tokenService.getUserIdFromToken(token);
        request.setAttribute("userId", userId);

        log.info("Token validated successfully for userId: {}", userId);
        return true;
    }
}
