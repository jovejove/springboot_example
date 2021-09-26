package com.example.es.controller;

import com.example.es.mapper.UniversityMapper;
import com.example.es.service.UniversityRestService;
import com.example.es.service.UniversityService;
import org.springframework.data.domain.PageRequest;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import javax.annotation.Resource;

/**
 * @ClassName: UniversityController.java
 * @Author: jjl
 * @Description:
 * @Date: 2021-09-02
 * @Version: 1.0
 */
@RestController
@RequestMapping(value = "/es")
public class UniversityController {

    @Resource
    UniversityMapper universityMapper;

    @Resource
    UniversityService universityService;
    @Resource
    UniversityRestService restService;

    @RequestMapping(value = "/getListTalentUniversity")
    public Object getListTalentUniversity(String name) {
        return universityMapper.getListTalentUniversity(name);
    }


    @RequestMapping(value = "/findTalentUniversityByNameIs")
    public Object findTalentUniversityByNameIs(String name, Integer page, Integer size) {
        return universityService.findTalentUniversityByNameIs(name, PageRequest.of(page, size));
    }

    @RequestMapping(value = "/findTalentUniversityByNameEquals")
    public Object findTalentUniversityByNameEquals(String name, Integer page, Integer size) {
        return universityService.findTalentUniversityByNameEquals(name, PageRequest.of(page, size));
    }

    @RequestMapping(value = "/findTalentUniversityByName")
    public Object findTalentUniversityByName(String name, Integer page, Integer size) {
        return universityService.findTalentUniversityByName(name, PageRequest.of(page, size));
    }

    @RequestMapping(value = "/findTalentUniversityByNameMatches")
    public Object findTalentUniversityByNameMatches(String name, Integer page, Integer size) {
        return universityService.findTalentUniversityByNameMatches(name, PageRequest.of(page, size));
    }

    @RequestMapping(value = "/getTermName")
    public Object getTermName(String name, Integer page, Integer size) throws Exception {
        return restService.getTermName(name);
    }

}
