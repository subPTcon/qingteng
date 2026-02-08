package com.qingteng.user.service;

public interface CaptchaRedisService {

    String buildKey(String tel);

    void save(String tel, String code);

    String get(String tel);

    void delete(String tel);
}
