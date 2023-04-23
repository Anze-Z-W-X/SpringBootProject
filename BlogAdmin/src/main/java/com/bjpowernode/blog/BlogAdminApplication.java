package com.bjpowernode.blog;

import com.bjpowernode.blog.settings.ArticleSettings;
import org.mybatis.spring.annotation.MapperScan;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.context.properties.EnableConfigurationProperties;
import org.springframework.transaction.annotation.EnableTransactionManagement;

@SpringBootApplication
@EnableTransactionManagement    //开启事务管理器
@MapperScan(basePackages = "com.bjpowernode.blog.mapper")
@EnableConfigurationProperties({ArticleSettings.class})
public class BlogAdminApplication {

    public static void main(String[] args) {
        SpringApplication.run(BlogAdminApplication.class, args);
    }

}
