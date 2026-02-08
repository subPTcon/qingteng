package com.qingteng.user.pojo;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.time.LocalDateTime;

@Data
@Entity
@Table(name = "user")
@NoArgsConstructor
@AllArgsConstructor
public class User {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(nullable = false, unique = false, length = 20)
    private String username;

    @Column(nullable = false, unique = true, length = 11)
    private String tel;

    @Column(nullable = false, unique = false, length = 20)
    private String college;

    @Column(nullable = false, unique = false, length = 255)
    private String avatarUrl;

    @Column(nullable = false, unique = false)
    private LocalDateTime createTime;

    @Column(nullable = false, unique = false)
    private LocalDateTime updateTime;

    @Column(nullable = false, unique = false, length = 1)
    private Character gender;

    @Column(nullable = true, unique = false)
    private String wechatId;

    @Column(nullable = true, unique = false)
    private String nickname;

    @Column(nullable = false, unique = false)
    private String password;


}
