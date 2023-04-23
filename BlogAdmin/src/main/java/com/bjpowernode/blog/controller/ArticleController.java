package com.bjpowernode.blog.controller;

import cn.hutool.core.bean.BeanUtil;
import com.bjpowernode.blog.model.dto.ArticleDTO;
import com.bjpowernode.blog.model.param.ArticleParam;
import com.bjpowernode.blog.model.po.ArticlePO;
import com.bjpowernode.blog.model.vo.ArticleVO;
import com.bjpowernode.blog.service.ArticleService;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;

import java.util.List;

@Controller
@RequiredArgsConstructor
public class ArticleController {
    private final ArticleService articleService;

    @GetMapping(value = {"/", "/article/hot"})
    public String showHotArticle(Model model){
        List<ArticlePO> articlePOS = articleService.queryTopArticle();
        //转为VO,使用.hutool
        List<ArticleVO> articleVOS = BeanUtil.copyToList(articlePOS, ArticleVO.class);
        //添加数据
        model.addAttribute("articleList",articleVOS);
        //视图
        return "/blog/articleList";
    }

    @PostMapping("/article/add")
    public String addArticle(@Validated(ArticleParam.AddArticle.class) ArticleParam param){
        //业务逻辑，调用Service
        ArticleDTO articleDTO = new ArticleDTO();
        articleDTO.setContent(param.getContent());
        articleDTO.setSummary(param.getSummary());
        articleDTO.setTitle(param.getTitle());
        boolean add=articleService.addArticle(articleDTO);
        return "redirect:/article/hot";
    }
}
