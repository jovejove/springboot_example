package com.example.es.mapper;

import com.example.es.entity.TalentUniversity;
import org.apache.ibatis.annotations.Result;
import org.apache.ibatis.annotations.Results;
import org.apache.ibatis.annotations.Select;

import java.util.List;

/**
 * @ClassName: UniversityMapper.java
 * @Author: jjl
 * @Description:
 * @Date: 2021-09-02
 * @Version: 1.0
 */

public interface UniversityMapper {

    @Results({
            @Result(property = "validStart", column = "valid_Start"),
            @Result(property = "createTime", column = "create_Time")
    })
    @Select("select * from TALENT_UNIVERSITY where NAME=#{name}")
    public List<TalentUniversity> getListTalentUniversity(String name);
}
