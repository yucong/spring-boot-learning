package com.druid;

import org.mybatis.spring.annotation.MapperScan;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.scheduling.annotation.EnableScheduling;

@SpringBootApplication
@MapperScan("com.druid.mapper")
@EnableScheduling
public class DruidtestApplication {

	public static void main(String[] args) {
		SpringApplication.run(DruidtestApplication.class, args);
	}

}
