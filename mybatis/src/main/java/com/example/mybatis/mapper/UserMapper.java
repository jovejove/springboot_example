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

    @InterceptAnnotation(authField = "id")
    List<User> selectByUserId(Long id);

    @InterceptAnnotation(authField = "id")
    List<User> selectByUserIds(List<Integer> ids);

    @InterceptAnnotation(tableName = "user" , authField = "id")
    List<User> selectAll();
}
