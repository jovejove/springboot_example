package com.example.error.servlet;

import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.RestControllerAdvice;

@RestControllerAdvice
public class NotAllowExceptionHandler {
    @ExceptionHandler(NotAllowException.class)
    @ResponseBody
    public String handle() {
        System.out.println("403");
        return "{\"resultCode\": 403}";
    }
}