package com.example.spock.repository;

import com.example.spock.entity.DemoEntity;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;
import org.springframework.stereotype.Repository;

@Mapper
@Repository
public interface DemoRepository {

    DemoEntity getDemo(@Param("demoId") Integer demoId);
    
    Integer createDemo(@Param("param")DemoEntity demoEntity);
}

