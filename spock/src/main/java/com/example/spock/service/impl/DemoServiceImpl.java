package com.example.spock.service.impl;

import com.example.spock.entity.DemoEntity;
import com.example.spock.repository.DemoRepository;
import com.example.spock.service.BaseService;
import com.example.spock.service.DemoService;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;

@Service
public class DemoServiceImpl extends BaseService implements DemoService {


    @Resource
    private DemoRepository demoRepository;

    @Override
    public DemoEntity getDemo(Integer demoId) {
        return demoRepository.getDemo(demoId);
    }
}

