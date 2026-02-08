package com.qingteng.common.result;

import lombok.*;

@Getter
@Setter
@AllArgsConstructor
public class Result<T> {
    private Integer code;
    private String message;
    private T data;

    public static <T> Result<T> success(SuccessCode code) {
        return new Result<>(code.getCode(), code.getMessage(), null);
    }

    public static <T> Result<T> success(SuccessCode code, T data) {
        return new Result<>(code.getCode(), code.getMessage(), data);
    }

    public static <T> Result<T> error(ErrorCode code, T data) {
        return new Result<>(code.getCode(), code.getMessage(), data);
    }
}
