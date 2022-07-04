package com.example.error.servlet;

import lombok.extern.slf4j.Slf4j;
import org.springframework.core.annotation.Order;
import org.springframework.util.StopWatch;

import javax.servlet.Filter;
import javax.servlet.FilterChain;
import javax.servlet.ServletException;
import javax.servlet.ServletRequest;
import javax.servlet.ServletResponse;
import javax.servlet.annotation.WebFilter;
import java.io.IOException;

/**
 * 注入的类型是 FilterRegistrationBean 类型，而不是 TimeCostFilter 类型  @WebFilter 过滤器无法被自动注入
 *
 * 统计接口耗时
 * @ServletComponentScan + @WebFilter
 */
//@WebFilter
@Slf4j
//@Order(1)
public class TimeCostFilter implements Filter {
    public TimeCostFilter(){
        System.out.println("construct");
    }
    @Override
    public void doFilter(ServletRequest request, ServletResponse response, FilterChain chain) throws IOException, ServletException {
        log.info("开始计算接口耗时");
        final StopWatch watch = new StopWatch();
        watch.start();
        chain.doFilter(request, response);
        watch.stop();
        System.out.println("执行时间(ms)：" + watch.getTotalTimeMillis());
    }
}