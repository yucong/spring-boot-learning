package com.example.demo;

import com.example.demo.entity.User;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.core.RowMapper;
import org.springframework.test.context.junit4.SpringRunner;

import javax.annotation.Resource;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.List;

@RunWith(SpringRunner.class)
@SpringBootTest
public class SpringBootMysqlApplicationTests {

	@Resource
	private JdbcTemplate jdbcTemplate;

	@Test
	public void contextLoads() {
		String sql = "select id, name, age from user";
		List<User> userList = jdbcTemplate.query(sql, new RowMapper<User>() {
			@Override
			public User mapRow(ResultSet resultSet, int i) throws SQLException {
				User user = new User();
				user.setId(resultSet.getInt("id"));
				user.setName(resultSet.getString("name"));
				user.setAge(resultSet.getInt("age"));
				return user;
			}
		});
		System.out.println("----------查询结果如下：----------");
		//下面使用JDK8的新特性，如果没有安装JDK8的可以修改为普通的for循环
		userList.stream().forEach(user -> {System.out.println(user.toString());});
	}

}
