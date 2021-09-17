package com.panda.demo.controller;

import com.panda.demo.entity.Person;
import org.elasticsearch.action.get.GetRequest;
import org.elasticsearch.action.get.GetResponse;
import org.elasticsearch.action.index.IndexRequest;
import org.elasticsearch.action.index.IndexResponse;
import org.elasticsearch.client.RequestOptions;
import org.elasticsearch.client.RestHighLevelClient;
import org.elasticsearch.common.xcontent.XContentType;
import org.springframework.web.bind.annotation.*;

import javax.annotation.Resource;
import java.io.IOException;

@RestController
@RequestMapping("/")
public class TestController {

    @Resource
    RestHighLevelClient restHighLevelClient;

    @PostMapping("/person")
    public Object save(@RequestBody Person person) {

        IndexResponse index = null;
        try {
            index = restHighLevelClient.index(new IndexRequest("test-index").id("2").source(person, XContentType.JSON), RequestOptions.DEFAULT);
        } catch (IOException e) {
            e.printStackTrace();
        }
        return index;
    }

    @GetMapping("/person/{id}")
    public Object findById(@PathVariable("id") String id) {
        GetResponse documentFields = null;
        try {
            documentFields = restHighLevelClient.get(new GetRequest("test-index", id), RequestOptions.DEFAULT);
        } catch (IOException e) {
            e.printStackTrace();
        }
        return documentFields.getSource();
    }
}