package com.yucong;

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


@RunWith(SpringRunner.class)
@SpringBootTest(webEnvironment = SpringBootTest.WebEnvironment.RANDOM_PORT)
public class ApplicationTests {

	@Autowired
	private DataSource dataSource;
	
	/**
	 * spring boot 2.0.6 测试可以通过
	 * spring boot 2.1.0 测试可以通过
	 */
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
