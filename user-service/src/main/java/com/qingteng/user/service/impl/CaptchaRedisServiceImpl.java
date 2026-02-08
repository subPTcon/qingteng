package com.qingteng.user.service.impl;

import com.qingteng.user.service.CaptchaRedisService;
import lombok.AllArgsConstructor;
import org.springframework.data.redis.core.RedisTemplate;
import org.springframework.data.redis.core.StringRedisTemplate;
import org.springframework.stereotype.Service;

import java.util.concurrent.TimeUnit;

@Service
@AllArgsConstructor
public class CaptchaRedisServiceImpl implements CaptchaRedisService {

    private final StringRedisTemplate redisTemplate;


    @Override
    public String buildKey(String tel) {
        return "captcha:tel:" + tel;
    }

    @Override
    public void save(String tel, String code) {
        redisTemplate.opsForValue().set(buildKey(tel), code, 1, TimeUnit.MINUTES);
    }

    @Override
    public String get(String tel) {
        return redisTemplate.opsForValue().get(buildKey(tel));
    }

    @Override
    public void delete(String tel) {
        redisTemplate.delete(buildKey(tel));
    }
}
