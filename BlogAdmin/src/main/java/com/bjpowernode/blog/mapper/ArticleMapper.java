package com.bjpowernode.blog.mapper;

import com.bjpowernode.blog.model.po.ArticlePO;
import org.apache.ibatis.annotations.Result;
import org.apache.ibatis.annotations.Results;
import org.apache.ibatis.annotations.Select;

import java.util.List;

public interface ArticleMapper {

    //查询首页需要的文章列表
    @Select("""
    select id,user_id,title,summary,read_count,create_time,update_time
    from article where read_count>=#{lowRead}
    order by read_count desc
    limit #{topRead}
            """)
    @Results(id="ArticleBaseMap",value = {
            @Result(id=true,column = "id",property = "id"),
            @Result(column = "user_id",property = "userId"),
            @Result(column = "title",property = "title"),
            @Result(column = "summary",property = "summary"),
            @Result(column = "create_time",property = "createTime"),
            @Result(column = "update_time",property = "updateTime")
    })
    List<ArticlePO> topSortByReadCount(Integer lowRead, Integer topRead);
}
