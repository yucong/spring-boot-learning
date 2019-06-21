package com.yucong.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

import com.github.pagehelper.PageHelper;
import com.yucong.entity.User;
import com.yucong.mapper.UserMapper;

import lombok.extern.slf4j.Slf4j;


@Slf4j
@RestController
public class TestController {

	@Autowired
	private UserMapper userMapper;
	
	@GetMapping("/test")
    public List<User> hello() {
		String msg = "测试输出日志";
    	log.debug(msg);
    	log.info(msg);
    	log.warn(msg);
    	log.error(msg);
    	
    	//获取第1页，页面大小为2
    	PageHelper.startPage(1,2);
    	List<User> users = userMapper.selectAll();
    	
    	return users;
    }


}
