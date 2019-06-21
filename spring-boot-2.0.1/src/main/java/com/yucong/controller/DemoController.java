package com.yucong.controller;

import java.util.Date;
import java.util.List;

import javax.validation.Valid;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.github.pagehelper.PageHelper;
import com.yucong.annotation.ApiVersion;
import com.yucong.dto.LogDTO;
import com.yucong.entity.User;
import com.yucong.manager.RedisManager;
import com.yucong.mapper.UserMapper;

import lombok.extern.slf4j.Slf4j;


@Slf4j
@RestController
@RequestMapping("{version}/demo")
public class DemoController {

	@Autowired
	private UserMapper userMapper;
	@Autowired
	private RedisManager redisManager;
	
	@ApiVersion(1)
	@GetMapping("log")
    public String log(@Valid LogDTO dto,BindingResult result) {
		String msg = "测试输出日志";
    	log.debug(msg);
    	log.info(msg);
    	log.warn(msg);
    	log.error(msg);
    	return msg;
    }
	
	@ApiVersion(1)
	@GetMapping("mysql")
    public List<User> mysql() {
    	//获取第1页，页面大小为2
    	PageHelper.startPage(1,2);
    	List<User> users = userMapper.selectAll();
    	return users;
    }
	
	@ApiVersion(1)
	@GetMapping("redis")
    public String redis() {
		redisManager.setObject("test", "redis:" + new Date().getTime());
		return redisManager.getObject("test");
    }


}
