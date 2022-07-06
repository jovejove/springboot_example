package com.panda.core.controller;

import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import javax.servlet.http.HttpServletRequest;
import java.util.ArrayList;
import java.util.List;

/**
 * @author: ljj
 * @date: 2022/7/5
 * @description:
 */
@RequestMapping("/performance")
@RestController
public class PerformanceController {

    @RequestMapping("/test")
    public String test1( HttpServletRequest request) {
        List temp = new ArrayList();
        Byte[] b = new Byte[1024 * 1024];
        temp.add(b);
        return "success";
    }

    public double myPow(double x, int n) {
        if (n == 0) {
            return 1;
        }
        if (n < 0) {
            // n为负数 -1是因为需要解决溢出， n<0此行代码执行一次
            return 1 / x * myPow(1 / x, -n - 1);
        }
        return n % 2 == 0 ? myPow(x * x, n / 2) : x * myPow(x * x, n / 2);
    }

    public static void main(String[] args) {
        System.out.println(new PerformanceController().myPow(2,-8));
    }
}
