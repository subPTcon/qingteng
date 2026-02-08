package com.qingteng.common.result;

import lombok.Data;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
public class Error {
    private String field;
    private Integer code;
    private String message;
}
