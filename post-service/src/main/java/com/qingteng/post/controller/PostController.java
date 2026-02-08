package com.qingteng.post.controller;

import com.qingteng.common.result.Result;
import com.qingteng.common.result.SuccessCode;
import com.qingteng.post.dto.PostCreateDto;
import com.qingteng.post.service.PostService;
import lombok.AllArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/api/post")
@AllArgsConstructor
@Slf4j
public class PostController {

    private final PostService postService;

    @PostMapping("/create")
    public Result<Object> create(@RequestBody PostCreateDto postCreateDto) {
        log.info("POST /api/post/create postCreateDto: {}", postCreateDto);
        postService.create(postCreateDto);
        return Result.success(SuccessCode.POST_CREATE_SUCCESS);
    }

    @GetMapping("/listByCreateTime")
    public Result<Object> listByCreateTime() {
        log.info("GET /api/post/listByCreateTime");

    }
}
