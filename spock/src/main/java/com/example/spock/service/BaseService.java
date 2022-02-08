package com.example.spock.service;

import com.example.spock.repository.BaseRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.redis.core.RedisTemplate;

import javax.annotation.PostConstruct;
import javax.annotation.Resource;
import java.util.List;

public abstract class BaseService {

    //加一个init方法，模拟我们容器完全启动之前会去数据库里查询字典值等额外数据
    @PostConstruct
    public List<String> getDictionary(){
        return baseRepository.getDictionary();
    }

    @Resource
    protected RedisTemplate<String ,Object> redisTemplate;

    @Autowired
    private BaseRepository baseRepository;

}
