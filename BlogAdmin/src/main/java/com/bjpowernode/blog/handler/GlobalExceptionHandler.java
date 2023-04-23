package com.bjpowernode.blog.handler;

import org.springframework.ui.Model;
import org.springframework.validation.BindException;
import org.springframework.validation.BindingResult;
import org.springframework.validation.FieldError;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;

import java.util.List;

@ControllerAdvice
public class GlobalExceptionHandler {
    @ExceptionHandler({BindException.class})
    public String handlerBindingException(BindException bindException, Model model){
        BindingResult bindingResult=bindException.getBindingResult();
        List<FieldError> fieldErrors=bindingResult.getFieldErrors();
        model.addAttribute("errors",fieldErrors);
        return "/blog/error/bind";
    }
}
