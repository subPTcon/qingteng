package com.qingteng.user.vo;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class UserCreateVo {
    private String username;
    private String tel;
    private String college;
    private Character gender;
}
