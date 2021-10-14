package com.example.mybatis.mapper;

import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import com.example.mybatis.entity.User;
import com.example.mybatis.interceptor.InterceptAnnotation;
import org.apache.ibatis.annotations.Mapper;

import java.util.List;


/**
 * @author Administrator
 */
@Mapper
public interface UserMapper extends BaseMapper<User> {

    @InterceptAnnotation(filterField = "id")
    List<User> selectByUserId(Long id);

    @InterceptAnnotation(tableName = "user",filterField = "id")
    List<User> selectByUserIds(List<Integer> ids);

    @InterceptAnnotation(tableName = "user" , filterField = "id")
    List<User> selectAll();
}
