package com.cy.store.controller;

import com.cy.store.service.ex.*;
import com.cy.store.util.JsonResult;
import jakarta.servlet.http.HttpSession;
import org.springframework.web.bind.annotation.ExceptionHandler;

//控制层类的基类
public class BaseController {
    public static final Integer OK = 200;
    @ExceptionHandler(ServiceException.class)
    public JsonResult<Void> handlerException(Throwable e){
        JsonResult<Void> result = new JsonResult<>(e);
        if(e instanceof UsernameDuplicatedException){
            result.setState(4000);
            result.setMessage("用户名已经被占用!");
        } else if (e instanceof InsertException) {
            result.setState(5000);
            result.setMessage("插入数据时产生未知的异常!");
        } else if (e instanceof UserNotFoundException) {
            result.setState(5001);
            result.setMessage(e.getMessage());
        } else if (e instanceof PasswordNotMatchException) {
            result.setState(5002);
            result.setMessage(e.getMessage());
        } else if (e instanceof UpdateException) {
            result.setState(5001);
            result.setMessage("更新数据时产生未知的异常!");
        }
        return result;
    }
    //获取session对象中的uid
    protected final Integer getUidFromSession(HttpSession session){
        String uid = session.getAttribute("uid").toString();
        return Integer.valueOf(uid);
    }

    protected final String getUsernameFromSession(HttpSession session){
        return session.getAttribute("username").toString();
    }
}
