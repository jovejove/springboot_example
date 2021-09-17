package com.panda.demo.service;

import com.panda.demo.entity.TUniUser;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.core.RowMapper;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.List;

@Service
public class UserService {

    @Resource    // 自动注入，spring boot会帮我们实例化一个对象
    private JdbcTemplate jdbcTemplate;      // 一个通过JDBC连接数据库的工具类，可以通过这个工具类对数据库进行增删改查


    public List<TUniUser> queryAllUser() {

        String sql = "select * from t_uni_user";

        List<TUniUser> list = jdbcTemplate.query(sql, new RowMapper<TUniUser>() {
            @Override
            public TUniUser mapRow(ResultSet resultSet, int i) throws SQLException {
                TUniUser student = new TUniUser();

                student.setUserId(resultSet.getString("user_id"));
                student.setUserName(resultSet.getString("user_name"));
                student.setPhone(resultSet.getString("phone"));
                student.setEmail(resultSet.getString("email"));
                student.setPassword(resultSet.getString("password"));
                student.setEmpName(resultSet.getString("emp_name"));
                return student;
            }
        });

        return list;
    }

    public TUniUser queryUserByName(String empName) {

        String sql = "select * from t_uni_user where emp_name = ?";

        return jdbcTemplate.queryForObject(sql, new RowMapper<TUniUser>() {
            @Override
            public TUniUser mapRow(ResultSet resultSet, int i) throws SQLException {
                TUniUser student = new TUniUser();

                student.setUserId(resultSet.getString("user_id"));
                student.setUserName(resultSet.getString("user_name"));
                student.setPhone(resultSet.getString("phone"));
                student.setEmail(resultSet.getString("email"));
                student.setPassword(resultSet.getString("password"));
                student.setEmpName(resultSet.getString("emp_name"));
                return student;
            }
        }, empName);

    }

    public TUniUser queryUserByName2(String empName) {

        String sql = "select * from t_uni_user where emp_name ='" + empName + "'";

        System.out.println(sql);

        return jdbcTemplate.queryForObject(sql, new RowMapper<TUniUser>() {
            @Override
            public TUniUser mapRow(ResultSet resultSet, int i) throws SQLException {
                TUniUser student = new TUniUser();

                student.setUserId(resultSet.getString("user_id"));
                student.setUserName(resultSet.getString("user_name"));
                student.setPhone(resultSet.getString("phone"));
                student.setEmail(resultSet.getString("email"));
                student.setPassword(resultSet.getString("password"));
                student.setEmpName(resultSet.getString("emp_name"));
                return student;
            }
        });

    }

    public String getUserById(Integer id) {
        System.out.println("get...");
//        int i = 1/0;
        return "user";
    }

    public void deleteUserById(Integer id) {
        System.out.println("delete...");
    }


}
