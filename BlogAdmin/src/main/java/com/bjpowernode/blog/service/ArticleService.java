package com.bjpowernode.blog.service;

import com.bjpowernode.blog.model.po.ArticlePO;

import java.util.List;

public interface ArticleService {
    //获取首页文章列表
    List<ArticlePO> queryTopArticle();
}
