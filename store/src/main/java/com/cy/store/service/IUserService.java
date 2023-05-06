package com.cy.store.service;

import com.cy.store.entity.User;

public interface IUserService {
    //用户注册方法
    void reg(User user);

    //用户登录方法
    User login(String username,String password);

    void changePassword(Integer uid,String username,String oldPassword,String newPassword);
}
