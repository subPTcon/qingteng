package com.qingteng.post.service.impl;

import com.qingteng.post.dto.PostCreateDto;
import com.qingteng.post.enums.PostType;
import com.qingteng.post.pojo.Post;
import com.qingteng.post.repository.PostRepository;
import com.qingteng.post.service.PostService;
import lombok.AllArgsConstructor;
import org.springframework.beans.BeanUtils;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;

@Service
@AllArgsConstructor
public class PostServiceImpl implements PostService {

    private final PostRepository postRepository;

    @Override
    public void create(PostCreateDto postCreateDto) {
        Post post = new Post();
        BeanUtils.copyProperties(postCreateDto, post);
        post.setCreateTime(LocalDateTime.now());
        post.setUpdateTime(LocalDateTime.now());
        post.setLikeCount(Long.valueOf(0));
        post.setMainPostId(Long.valueOf(-1));

        postRepository.save(post);
    }
}
