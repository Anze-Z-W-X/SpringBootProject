package com.bjpowernode.blog.mapper;

import com.bjpowernode.blog.model.dto.ArticleDTO;
import com.bjpowernode.blog.model.map.ArticleAndDetailMap;
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

    @Select("""
        select m.id as article_id,title,summary,ad.content
        from article m inner join article_detail ad
        on m.id=ad.article_id
        where m.id=#{id};
            """)
    @Results(id="ArticleAndDetailMapperr",value = {
            @Result(id=true,column = "article_id",property = "id"),
            @Result(column = "title",property = "title"),
            @Result(column = "summary",property = "summary"),
            @Result(column = "content",property = "content")
    })
    ArticleAndDetailMap selectArticleAndDetail(Integer id);

    //修改文章属性
    @Update("""
        update article set title=#{title},summary=#{summary},
        update_time=#{updateTime}
        where id=#{id}
            """)
    int updateArticle(ArticlePO articlePO);

    //更新文章内容
    @Update("""
        update article_detail set content=#{content}
        where article_id=#{articleId}
            """)
    int updateArticleDetail(ArticleDetailPO articleDetailPO);

    //删除文章属性
    @Delete("""
        <script>
            delete from article where id in 
            <foreach item="id" collection="list" open="(" separator="," close=")">
                #{id}
            </foreach>
        </script>
            """)
    void deleteArticle(List<Integer> idList);

    //删除文章内容
    @Delete("""
        <script>
        delete from article_detail where article_id in 
        <foreach item="id" collection="list" open="(" separator="," close=")">
            #{id}
        </foreach>
        </script>
            """)
    void deleteDetail(List<Integer> idList);


    @Select("""
        select id,article_id,content from article_detail
        where article_id=#{articleId}
            """)
    @Results(id="articleDetailMapper",value = {
            @Result(id = true,column = "id",property = "id"),
            @Result(column = "article_id",property = "articleId"),
            @Result(column = "content",property = "content")
    })
    ArticleDetailPO selectArticleDetailByArticleId(Integer articleId);
}
