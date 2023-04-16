package com.bjpowernode.blog.model.vo;

import lombok.Data;

import java.time.LocalDateTime;
@Data
public class ArticleVO {
    private Integer id;
    private Integer userId;
    private String title;
    private String summary;
    private Integer readCount;
    private LocalDateTime createTime;
    private LocalDateTime updateTime;
    private String content;
}
