package com.example.error.valid;

import lombok.extern.slf4j.Slf4j;
import org.springframework.http.HttpHeaders;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestHeader;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import javax.servlet.http.HttpServletResponse;
import javax.validation.Valid;

/**
 * @author: ljj
 * @date: 2022/7/1
 * @description:
 */
@Slf4j
@RestController
@RequestMapping("/web")
public class WebController {

    @RequestMapping(path = "/hi", method = RequestMethod.GET)
    public String hi(@RequestHeader("myHeaderName") String name) {
        //省略 body 处理
        return name;
    }

    /**
     * 案例 1：接受 Header 使用错 Map 类型
     * correct HttpHeaders
     *
     * @param map
     * @return
     */
    @RequestMapping(path = "/hi1", method = RequestMethod.GET)
    public String hi1(@RequestHeader() HttpHeaders map) {
        return map.toString();
    }

    /**
     * 在实际使用时，虽然 HTTP 协议规范可以忽略大小写，但是不是所有框架提供的接口方法都是可以忽略大小写的
     *
     * @param myHeader
     * @param map
     * @return
     */
    @RequestMapping(path = "/hi2", method = RequestMethod.GET)
    public String hi2(@RequestHeader("MyHeader") String myHeader, @RequestHeader HttpHeaders map) {
        return myHeader + " compare with : " + map.get("MyHeader");
    }

    /**
     * 案例 3：试图在 Controller 中随意自定义 CONTENT_TYPE 等
     *
     * @param httpServletResponse
     * @return
     */
    @RequestMapping(path = "/hi3", method = RequestMethod.GET)
    public String hi3(HttpServletResponse httpServletResponse) {
        httpServletResponse.addHeader("myheader", "myheadervalue");
        httpServletResponse.addHeader(HttpHeaders.CONTENT_TYPE, "application/json");
        return "ok";
    }

    @RequestMapping(path = "/students", method = RequestMethod.POST)
    public Object addStudent(@Valid @RequestBody Student student) {
        log.info("add new student: {}", student.toString());
        return student;
    }
}
