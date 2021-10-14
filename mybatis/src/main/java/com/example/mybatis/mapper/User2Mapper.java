package com.example.mybatis.mapper;


import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import com.example.mybatis.entity.User2;
import org.apache.ibatis.annotations.Mapper;

/**
 * <p>
 * MP 支持不需要 UserMapper.xml 测试注解条件
 * </p>
 *
 * @author hubin
 * @since 2021-08-19
 */
@Mapper
public interface User2Mapper extends BaseMapper<User2> {

}
