package com.bjpowernode.blog.mapper;

import com.bjpowernode.blog.model.dto.ArticleDTO;
import com.bjpowernode.blog.model.po.ArticleDetailPO;
import com.bjpowernode.blog.model.po.ArticlePO;
import org.apache.ibatis.annotations.*;

import java.util.List;

public interface ArticleMapper {

    //查询首页需要的文章列表
    @Select("""
      select id,user_id ,title,summary, read_count , create_time, update_time
      from article
      where read_count >= #{lowRead}
      order by read_count desc 
      limit #{topRead}
      """)
    @Results(id = "ArticleBaseMap", value = {@Result(id = true, column = "id", property = "id"),
            @Result(column = "user_id", property = "userId"),
            @Result(column = "title", property = "title"),
            @Result(column = "summary", property = "summary"),
            @Result(column = "read_count", property = "readCount"),
            @Result(column = "create_time", property = "createTime"),
            @Result(column = "update_time", property = "updateTime"),})
    List<ArticlePO> topSortByReadCount(Integer lowRead, Integer topRead);


    //添加article
    @Insert("""
      insert into article(user_id ,title,summary, read_count , create_time, update_time)
      values(#{userId},#{title},#{summary} ,#{readCount},#{createTime},#{updateTime})
      """)
    @Options(useGeneratedKeys = true, keyProperty = "id", keyColumn = "id")
    int insertArticle(ArticlePO articlePO);


    //文章内容
    @Insert("""
      insert into article_detail(article_id,content)
      values(#{articleId},#{content})
      """)
    int insertArticleDetail(ArticleDetailPO articleDetailPO);
}
