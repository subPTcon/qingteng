package com.qingteng.user.vo;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class UserProfileVo {
    private String username;
    private String college;
    private String tel;
    private Long postCount;
    private Long postGetLikeCount;
    private String avatarUrl;
}
