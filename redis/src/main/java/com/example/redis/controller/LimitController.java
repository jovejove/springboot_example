package com.example.redis.controller;

import com.example.redis.annotation.Limit;
import com.google.common.util.concurrent.RateLimiter;
import lombok.extern.slf4j.Slf4j;
import org.springframework.scheduling.annotation.Async;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.concurrent.TimeUnit;

@Slf4j
@RestController
@RequestMapping("/limit")
public class LimitController {
    /**
     * 限流策略 ： 1秒钟2个请求
     */
    private final RateLimiter limiter = RateLimiter.create(1);

    private DateTimeFormatter dtf = DateTimeFormatter.ofPattern("yyyy-MM-dd HH:mm:ss");

    @GetMapping("/test1")
    public String testLimiter() {

//        acquire() 获取一个令牌, 改方法会阻塞直到获取到这一个令牌, 返回值为获取到这个令牌花费的时间
//        acquire(int permits) 获取指定数量的令牌, 该方法也会阻塞, 返回值为获取到这 N 个令牌花费的时间
//        tryAcquire() 判断时候能获取到令牌, 如果不能获取立即返回 false
//        tryAcquire(int permits) 获取指定数量的令牌, 如果不能获取立即返回 false
//        tryAcquire(long timeout, TimeUnit unit) 判断能否在指定时间内获取到令牌, 如果不能获取立即返回 false
//        tryAcquire(int permits, long timeout, TimeUnit unit) 同上



        //500毫秒内，没拿到令牌，就直接进入服务降级
        boolean tryAcquire = limiter.tryAcquire(100, TimeUnit.MILLISECONDS);

        if (!tryAcquire) {
            log.warn("进入服务降级，时间{}", LocalDateTime.now().format(dtf));
            return "当前排队人数较多，请稍后再试！";
        }

        log.info("获取令牌成功，时间{}", LocalDateTime.now().format(dtf));
        return "请求成功";
    }
    @Limit(key = "limit2",permitsPerSecond = 10,timeout = 500)
    @GetMapping("/test2")
    public String testLimiter2() {
        return "请求成功";
    }
}
