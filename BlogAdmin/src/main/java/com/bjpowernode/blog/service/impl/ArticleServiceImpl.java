package com.bjpowernode.blog.service.impl;

import com.bjpowernode.blog.model.dto.ArticleDTO;
import com.bjpowernode.blog.model.map.ArticleAndDetailMap;
import com.bjpowernode.blog.model.po.ArticleDetailPO;
import com.bjpowernode.blog.settings.ArticleSettings;
import com.bjpowernode.blog.mapper.ArticleMapper;
import com.bjpowernode.blog.model.po.ArticlePO;
import com.bjpowernode.blog.service.ArticleService;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.time.LocalDateTime;
import java.util.List;
import java.util.Random;

@Service
@RequiredArgsConstructor    //给articleMapper进行构造注入
public class ArticleServiceImpl implements ArticleService {
    private final ArticleMapper articleMapper;
    private final ArticleSettings articleSettings;

    @Override
    public List<ArticlePO> queryTopArticle() {
        return articleMapper.topSortByReadCount(articleSettings.getLowRead(), articleSettings.getTopRead());
    }

    @Override
    @Transactional(rollbackFor = Exception.class)
    public Boolean addArticle(ArticleDTO articleDTO) {
        //文章article
        ArticlePO articlePO = new ArticlePO();
        articlePO.setTitle(articleDTO.getTitle());
        articlePO.setSummary(articleDTO.getSummary());
        articlePO.setCreateTime(LocalDateTime.now());
        articlePO.setUpdateTime(LocalDateTime.now());
        articlePO.setReadCount(new Random().nextInt(200));
        articlePO.setUserId(new Random().nextInt(5000));
        int addArticle = articleMapper.insertArticle(articlePO);

        //文章内容
        ArticleDetailPO articleDetailPO = new ArticleDetailPO();
        articleDetailPO.setArticleId(articlePO.getId());
        articleDetailPO.setContent(articleDTO.getContent());
        int addDetail = articleMapper.insertArticleDetail(articleDetailPO);
        return addArticle+addDetail==2;
    }

    @Override
    public ArticleDTO queryByArticleId(Integer id) {
        //文章的属性，内容
        ArticleAndDetailMap articleAndDetailMap = articleMapper.selectArticleAndDetail(id);
        //转为DTO
        ArticleDTO articleDTO = new ArticleDTO();
        articleDTO.setTitle(articleAndDetailMap.getTitle());
        articleDTO.setContent(articleAndDetailMap.getContent());
        articleDTO.setSummary(articleAndDetailMap.getSummary());
        articleDTO.setId(articleAndDetailMap.getId());


        return articleDTO;
    }

    @Override
    @Transactional(rollbackFor = Exception.class)
    public boolean modifyArticle(ArticleDTO articleDTO) {
        //修改文章属性
        ArticlePO articlePO = new ArticlePO();
        articlePO.setTitle(articleDTO.getTitle());
        articlePO.setSummary(articleDTO.getSummary());
        articlePO.setUpdateTime(LocalDateTime.now());
        articlePO.setId(articleDTO.getId());
        int article = articleMapper.updateArticle(articlePO);
        //修改文章内容
        ArticleDetailPO articleDetailPO=new ArticleDetailPO();
        articleDetailPO.setArticleId(articleDTO.getId());
        articleDetailPO.setContent(articleDTO.getContent());
        int detail = articleMapper.updateArticleDetail(articleDetailPO);
        return (article+detail)==2;
    }
}
