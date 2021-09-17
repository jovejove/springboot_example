package com.panda.springboot_demo;

import org.junit.jupiter.api.Test;
import org.springframework.boot.SpringBootConfiguration;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.jdbc.core.JdbcTemplate;

import javax.annotation.Resource;

@SpringBootTest
@SpringBootConfiguration
class SpringbootDemoApplicationTests {

	@Test
	void contextLoads() {
		System.out.println(111);
	}

	@Resource    // 自动注入，spring boot会帮我们实例化一个对象
	private JdbcTemplate jdbcTemplate;   // 一个通过JDBC连接数据库的工具类，可以通过这个工具类对数据库进行增删改查

	@Test
	public void mySqlTest() {
//		String sql = "select * from t_uni_user";
//		List<TUniUser> students = jdbcTemplate.query(sql, new RowMapper<TUniUser>() {
//			@Override
//			public TUniUser mapRow(ResultSet resultSet, int i) throws SQLException {
//				TUniUser student = new TUniUser();
//				student.setUserId(resultSet.getString(1));
//				student.setPhone(resultSet.getString(2));
//				student.setPassword(resultSet.getString(3));
//				return student;
//			}
//		});
//
//		System.out.println("查询成功");
//		for (TUniUser s : students) {
//			System.out.println(s);
//		}
	}


}
