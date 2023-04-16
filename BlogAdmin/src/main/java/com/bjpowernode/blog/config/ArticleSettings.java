package com.bjpowernode.blog.config;

import lombok.Data;
import org.mybatis.spring.annotation.MapperScan;
import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.boot.context.properties.EnableConfigurationProperties;

@Data
@MapperScan(basePackages = "com.bjpowernode.blog.mapper")
@EnableConfigurationProperties(ArticleSettings.class)
@ConfigurationProperties(prefix = "article")
public class ArticleSettings {
    private Integer lowRead;
    private Integer topRead;
}
