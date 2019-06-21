package com.example.demo;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

import javax.sql.DataSource;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;


/**
 * 使用jdbcTemplate通过，但是使用dataSource测试不通过
 *
 */
@RunWith(SpringRunner.class)
@SpringBootTest
public class SpringBootMysqlApplicationTests {

	/*@Resource
	private JdbcTemplate jdbcTemplate;
	
	@Test
	public void contextLoads() {
		String sql = "select id,nick_name from t_user";
		List<User> userList = jdbcTemplate.query(sql, new RowMapper<User>() {
			@Override
			public User mapRow(ResultSet resultSet, int i) throws SQLException {
				User user = new User();
				user.setId(resultSet.getInt("id"));
				user.setName(resultSet.getString("nick_name"));
				//user.setAge(resultSet.getInt("age"));
				return user;
			}
		});
		System.out.println("----------查询结果如下：----------");
		//下面使用JDK8的新特性，如果没有安装JDK8的可以修改为普通的for循环
		userList.stream().forEach(user -> {System.out.println(user.toString());});
	}*/
	
	
	@Autowired
	private DataSource dataSource;
	
	@Test
	public void contextLoads() throws SQLException {
		
		Connection connection = dataSource.getConnection();
        PreparedStatement prepareStatement = connection.prepareStatement("select * from login_user where id=3");
        ResultSet resultSet = prepareStatement.executeQuery();
        while (resultSet.next()) {
            String realName = resultSet.getString("real_name");
            System.out.println(realName);
        }

	}

}
