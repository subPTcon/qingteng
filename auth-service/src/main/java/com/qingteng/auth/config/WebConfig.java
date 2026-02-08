package com.qingteng.auth.config;

import com.qingteng.auth.interceptor.AuthInterceptor;
import lombok.AllArgsConstructor;
import org.springframework.context.annotation.Configuration;
import org.springframework.web.servlet.config.annotation.InterceptorRegistry;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurer;

@Configuration
@AllArgsConstructor
public class WebConfig implements WebMvcConfigurer {

    private final AuthInterceptor authInterceptor;

    @Override
    public void addInterceptors(InterceptorRegistry registry) {
        registry.addInterceptor(authInterceptor)
                .addPathPatterns("/api/user/profile/**",
                                "/api/post/create"
                        )  // 需要验证token的路径
                .excludePathPatterns(
                        "/api/user/create",              // 注册接口不需要验证
                        "/api/user/loginByPassword",     // 登录接口不需要验证
                        "/api/user/getVerificationCode/**" // 获取验证码不需要验证
                );
    }
}
