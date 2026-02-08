package com.qingteng.common.result;

import lombok.Data;

public enum SuccessCode {
    USER_CREATE_SUCCESS(201, "注册成功"),
    USER_LOGIN_SUCCESS(202, "登录成功"),
    USER_GET_VERIFICATION_CODE_SUCCESS(203, "获取验证码成功"),
    USER_PROFILE_SUCCESS(204, "获取个人主页信息成功"),

    POST_CREATE_SUCCESS(2011, "帖子创建成功");


    private final int code;
    private final String message;

    SuccessCode(int code, String message) {
        this.code = code;
        this.message = message;
    }

    public int getCode() { return code; }

    public String getMessage() { return message; }
}
