package com.cy.store.service;

import com.cy.store.entity.User;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

@SpringBootTest
public class UserServiceTests {
    @Autowired
    IUserService iUserService;
    @Test
    void reg(){
//        User user = new User();
//        user.setUsername("anze2");
//        user.setPassword("123456");
//        iUserService.reg(user);
    }

    @Test
    void changePassword(){
        iUserService.changePassword(10,"anze","123","777");
    }
}
