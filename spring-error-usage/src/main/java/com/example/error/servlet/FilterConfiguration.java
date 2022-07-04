package com.example.error.servlet;

import org.springframework.boot.web.servlet.FilterRegistrationBean;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

/**
 * filter根据其order值执行  值小先执行
 */
@Configuration
public class FilterConfiguration {
    @Bean
    public FilterRegistrationBean authFilter() {
        FilterRegistrationBean registration = new FilterRegistrationBean();
        registration.setFilter(new AuthFilter());
        registration.addUrlPatterns("/*");
        registration.setOrder(2);
        return registration;
    }

    @Bean
    public FilterRegistrationBean timeCostFilter() {
        FilterRegistrationBean registration = new FilterRegistrationBean();
        registration.setFilter(new TimeCostFilter());
        registration.addUrlPatterns("/*");
        registration.setOrder(1);
        return registration;
    }
}