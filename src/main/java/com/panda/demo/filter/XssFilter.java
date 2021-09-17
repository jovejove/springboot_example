package com.panda.demo.filter;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.databind.module.SimpleModule;
import com.panda.demo.config.XssAndSqlHttpServletRequestWrapper;
import com.panda.demo.config.XssStringJsonDeserializer;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Primary;
import org.springframework.http.converter.json.Jackson2ObjectMapperBuilder;
import org.springframework.stereotype.Component;

import javax.servlet.*;
import javax.servlet.annotation.WebFilter;
import javax.servlet.http.HttpServletRequest;
import java.io.IOException;

/**
 * @ClassName: XssFilter.java
 * @Author: jjl
 * @Description:
 * @Date: 2021-07-23
 * @Version: 1.0
 */

@WebFilter(filterName = "XssFilter", urlPatterns = "/*", asyncSupported = true)
@Component
public class XssFilter implements Filter {
    @Override
    public void init(FilterConfig filterConfig) throws ServletException {

    }

    @Override
    public void doFilter(ServletRequest servletRequest, ServletResponse servletResponse, FilterChain filterChain) throws IOException, ServletException {
        HttpServletRequest req = (HttpServletRequest) servletRequest;
        XssAndSqlHttpServletRequestWrapper xssRequestWrapper = new XssAndSqlHttpServletRequestWrapper(req);
        filterChain.doFilter(xssRequestWrapper, servletResponse);
    }

    @Override
    public void destroy() {

    }


    /**
     * 过滤json类型的
     */
    @Bean
    @Primary
    public ObjectMapper xssObjectMapper(Jackson2ObjectMapperBuilder builder) {
        //解析器
        ObjectMapper objectMapper = builder.createXmlMapper(false).build();
        //注册xss解析器
        SimpleModule xssModule = new SimpleModule("XssStringJsonDeserializer");

        //入参和出参过滤选一个就好了，没必要两个都加
        //这里为了和XssHttpServletRequestWrapper统一,建议对入参进行处理
        //注册入参转义
        xssModule.addDeserializer(String.class, new XssStringJsonDeserializer());

        //注册出参转义
        //xssModule.addSerializer(new XssStringJsonSerializer());
        objectMapper.registerModule(xssModule);
        //返回
        return objectMapper;
    }

}
