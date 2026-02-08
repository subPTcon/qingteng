package com.qingteng.post.dto;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class PostCreateDto {
    private Long posterId;
    private String posterUsername;
    private int anonymous;
    private String content;
    private String type;
    private int isComment;
}
