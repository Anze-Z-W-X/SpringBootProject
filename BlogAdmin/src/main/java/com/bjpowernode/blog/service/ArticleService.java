package com.bjpowernode.blog.service;

import com.bjpowernode.blog.model.dto.ArticleDTO;
import com.bjpowernode.blog.model.po.ArticlePO;

import java.util.List;

public interface ArticleService {
    //获取首页文章列表
    List<ArticlePO> queryTopArticle();

    //发布新文章: article和article_detail表
    Boolean addArticle(ArticleDTO articleDTO);

    //根据主键查询文章
    ArticleDTO queryByArticleId(Integer id);

    boolean modifyArticle(ArticleDTO articleDTO);

    int removeArticle(List<Integer> idList);

    String queryTop20Content(Integer id);
}
