package com.example.mybatis.mapper;

import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import com.example.mybatis.entity.User;
import com.example.mybatis.interceptor.FillAuthCondition;
import org.apache.ibatis.annotations.Mapper;

import java.util.List;


/**
 * @author Administrator
 */
@Mapper
public interface UserMapper extends BaseMapper<User> {

    @FillAuthCondition(filterField = "id")
    List<User> selectByUserId(Long id);

    @FillAuthCondition(tableName = "user",filterField = "id")
    List<User> selectByUserIds(List<Integer> ids);

    @FillAuthCondition(tableName = "user" , filterField = "id")
    List<User> selectAll();
}
