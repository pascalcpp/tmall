package com.xpcf.tmall.exception;

import org.springframework.web.HttpRequestHandler;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestController;

import javax.servlet.http.HttpServletRequest;

/**
 * @program: tmall_springboot
 * @description:
 * @author: "xpcf"
 * @create: 2020-03-26 11:16
 **/
@RestController
@ControllerAdvice
public class GlobalExceptionHandler {
    @ExceptionHandler(value = Exception.class)
    public String defaultErrorHandler(HttpServletRequest req, Exception e) throws Exception{
        e.printStackTrace();
        Class constrainViolationException
                = Class.forName("org.hibernate.exception.ConstraintViolationException");
        if(e.getCause() != null && constrainViolationException==e.getCause().getClass()){
            return "违反了约束，maybe外键约束";
        }
        return e.getMessage();
    }
}
