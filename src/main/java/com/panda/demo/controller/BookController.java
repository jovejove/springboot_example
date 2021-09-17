package com.panda.demo.controller;

import com.panda.demo.entity.BookVO;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.RestController;

import java.util.Date;

//@Controller
@RestController
public class BookController {
    @GetMapping("/book")
//    @ResponseBody
    public BookVO book() {
        BookVO bookVO = new BookVO();
        bookVO.setAuthor("罗贯中");
        bookVO.setName("三国演义");
        bookVO.setPrice(30f);
        bookVO.setPublicationDate(new Date());
        System.out.println(bookVO.toString());
        return bookVO;
    }

    @GetMapping("/hello")
    @ResponseBody
    public void hello(Model model) {

//        Map<String, Object> map = model.asMap();
//        Set<String> keySet = map.keySet();
//        Iterator<String> iterator = keySet.iterator();
//        while (iterator.hasNext()) {
//            String key = iterator.next();
//            Object value = map.get(key);
//            System.out.println(key + ">>>>>" + value);
//        }

        System.out.println("say hello!");
    }
}