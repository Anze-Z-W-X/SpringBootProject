package com.bjpowernode.blog.service.impl;

import com.bjpowernode.blog.config.ArticleSettings;
import com.bjpowernode.blog.mapper.ArticleMapper;
import com.bjpowernode.blog.model.po.ArticlePO;
import com.bjpowernode.blog.service.ArticleService;
import jakarta.annotation.Resource;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;
@Service
@RequiredArgsConstructor    //给articleMapper进行构造注入
public class ArticleServiceImpl implements ArticleService {
    private final ArticleMapper articleMapper;
    private final ArticleSettings articleSettings;

    @Override
    public List<ArticlePO> queryTopArticle() {
        return articleMapper.topSortByReadCount(articleSettings.getLowRead(), articleSettings.getTopRead());
    }
}
