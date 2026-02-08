package com.qingteng.common.result;

public enum ErrorCode {
    USER_USERNAME_EMPTY_ERROR(4001, "用户名不能为空"),
    USER_TEL_EMPTY_ERROR(4002, "用户手机号不能为空"),
    USER_COLLEGE_EMPTY_ERROR(4003, "用户学校名称不能为空"),
    USER_GENDER_EMPTY_ERROR(4004, "用户性别不能为空"),
    USER_PASSWORD_EMPTY_ERROR(4005, "用户密码不能为空"),

    USER_NOT_FOUND_WHEN_LOGIN(4021, "用户不存在，请先注册"),
    PASSWORD_INCORRECT_WHEN_LOGIN(4022, "用户手机号或密码错误"),

    TOKEN_MISSING_ERROR(4011, "请求头中缺少token"),
    TOKEN_INVALID_ERROR(4012, "token无效或已过期"),

    POST_CREATE_FAILED(4031, "帖子创建失败"),

    PARAMETER_INVALID_ERROR(400, "参数验证失败");

    private final int code;
    private final String message;

    ErrorCode(int code, String message) {
        this.code = code;
        this.message = message;
    }

    public int getCode() { return code; }
    public String getMessage() { return message; }
}
