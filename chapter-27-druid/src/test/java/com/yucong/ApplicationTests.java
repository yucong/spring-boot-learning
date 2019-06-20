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
	
	@Test
	public void contextLoads() throws SQLException {
		
		Connection connection = dataSource.getConnection();
        PreparedStatement prepareStatement = connection.prepareStatement("select * from t_user where id=-1");
        ResultSet resultSet = prepareStatement.executeQuery();
        while (resultSet.next()) {
            String cityName = resultSet.getString("nick_name");
            System.out.println(cityName);
        }

	}
}
