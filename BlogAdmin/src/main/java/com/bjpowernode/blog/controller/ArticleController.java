package com.bjpowernode.blog.controller;

import cn.hutool.core.bean.BeanUtil;
import com.bjpowernode.blog.formatter.IdType;
import com.bjpowernode.blog.handler.exp.IdTypeException;
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
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;

import java.util.Arrays;
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

    //查询文章内容
    @GetMapping("/article/get")
    public String queryById(Integer id,Model model){
        if(id!=null&&id>0){
            ArticleDTO articleDTO = articleService.queryByArticleId(id);
            //转为VO
            ArticleVO articleVO = BeanUtil.copyProperties(articleDTO, ArticleVO.class);
            //添加数据到域中
            model.addAttribute("article",articleVO);
            //视图
            return "/blog/editArticle";
        }
        return "/blog/error/error";
    }

    //更新文章
    @PostMapping("/article/edit")
    public String modifyArticle(@Validated(ArticleParam.EditArticle.class)ArticleParam articleParam){
        ArticleDTO articleDTO = new ArticleDTO();
        articleDTO.setId(articleParam.getId());
        articleDTO.setContent(articleParam.getContent());
        articleDTO.setTitle(articleParam.getTitle());
        articleDTO.setSummary(articleParam.getSummary());
        boolean edit = articleService.modifyArticle(articleDTO);
        return "redirect:/article/hot";
    }

    @PostMapping("/article/remove")
    public String removeArticle(@RequestParam("ids")IdType idType){
        if(idType==null)throw new IdTypeException("ID为空");
        int delete = articleService.removeArticle(idType.getIdList());
        return "redirect:/article/hot";
    }

    @GetMapping("/article/detail/overview")
    @ResponseBody
    public String queryDetail(Integer id){
        String top20Content = "无ID";
        if(id!=null&&id>0){
            top20Content = articleService.queryTop20Content(id);
        }
        return top20Content;
    }
}
