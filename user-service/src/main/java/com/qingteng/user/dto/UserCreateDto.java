package com.qingteng.user.dto;

import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
public class UserCreateDto {
    private String username;
    private String tel;
    private String college;
    private Character gender;
    private String password;
    private String avatarUrl;
}
