package com.example.error.servlet;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.stereotype.Component;
import org.springframework.web.servlet.HandlerExceptionResolver;

import javax.servlet.Filter;
import javax.servlet.FilterChain;
import javax.servlet.FilterConfig;
import javax.servlet.ServletException;
import javax.servlet.ServletRequest;
import javax.servlet.ServletResponse;
import javax.servlet.annotation.WebFilter;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

/**
 * 利用 Spring MVC 的异常处理机制 手动捕获异常，并将异常 HandlerExceptionResolver 进行解析处理
 */
@WebFilter
@Component
public class PermissionFilter implements Filter {


    @Autowired
    @Qualifier("handlerExceptionResolver")
    private HandlerExceptionResolver resolver;

    @Override
    public void doFilter(ServletRequest request, ServletResponse response, FilterChain chain) throws IOException, ServletException {
        HttpServletRequest httpServletRequest = (HttpServletRequest) request;
        String token = httpServletRequest.getHeader("token");
        HttpServletResponse httpServletResponse = (HttpServletResponse) response;
        // 登录权限校验
//        if (!"111111".equals(token)) {
//            System.out.println("throw NotAllowException");
//            resolver.resolveException(httpServletRequest, httpServletResponse, null, new NotAllowException());
//            return;
//        }
        chain.doFilter(request, response);
    }


    @Override
    public void init(FilterConfig filterConfig) throws ServletException {
    }


    @Override
    public void destroy() {
    }
}