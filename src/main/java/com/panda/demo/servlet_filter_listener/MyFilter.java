package com.panda.demo.servlet_filter_listener;

import javax.servlet.*;
import javax.servlet.annotation.WebFilter;
import java.io.IOException;

@WebFilter("/*")
public class MyFilter implements Filter {
    @Override
    public void init(FilterConfig filterConfig) {
        System.out.println("MyFilter>>>init");
    }

    @Override
    public void doFilter(ServletRequest req, ServletResponse resp, FilterChain chain) throws IOException, ServletException {
        System.out.println("MyFilter>>>doFilter");
        chain.doFilter(req, resp);
    }

    @Override
    public void destroy() {
        System.out.println("MyFilter>>>destroy");
    }
}