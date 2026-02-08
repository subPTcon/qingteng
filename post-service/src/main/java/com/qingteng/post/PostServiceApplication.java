package com.qingteng.post;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.persistence.autoconfigure.EntityScan;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.data.jpa.repository.config.EnableJpaRepositories;

@SpringBootApplication
@ComponentScan(basePackages = {"com.qingteng.post", "com.qingteng.common", "com.qingteng.auth"})
@EnableJpaRepositories(basePackages = "com.qingteng.post.repository")
@EntityScan(basePackages = "com.qingteng.post.pojo")
public class PostServiceApplication {
    public static void main(String[] args) {
        SpringApplication.run(PostServiceApplication.class, args);
    }
}
