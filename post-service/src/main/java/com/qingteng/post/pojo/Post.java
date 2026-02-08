package com.qingteng.post.pojo;

import com.qingteng.post.enums.PostType;
import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.time.LocalDateTime;

@Data
@AllArgsConstructor
@NoArgsConstructor
@Entity
@Table(name = "post")
public class Post {
    @Column(nullable = false, unique = false, length = 500)
    private String content;

    @Column(nullable = false, unique = false)
    private String type;

    @Column(nullable = false, unique = false)
    private int anonymous;

    @Column(nullable = false, unique = false)
    private Long posterId;

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(nullable = false, unique = false)
    private LocalDateTime createTime;

    @Column(nullable = false, unique = false)
    private LocalDateTime updateTime;

    @Column(nullable = false, unique = false)
    private int isComment;

    @Column(nullable = false, unique = false)
    private Long likeCount;

    @Column(nullable = false, unique = false)
    private String posterUsername;

    @Column(nullable = false, unique = false)
    private Long mainPostId;
}
