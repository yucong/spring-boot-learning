package com.yucong.controller;

import java.util.Date;

import javax.validation.Valid;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.yucong.annotation.ApiVersion;
import com.yucong.dto.LogDTO;
import com.yucong.dto.MySqlDTO;
import com.yucong.dto.RedisDTO;
import com.yucong.entity.User;
import com.yucong.manager.RedisManager;
import com.yucong.service.DemoService;
import com.yucong.vo.UserVO;
import com.yucong.vo.common.CommonVO;
import com.yucong.vo.common.DataTableVO;

import lombok.extern.slf4j.Slf4j;


@Slf4j
@RestController
@RequestMapping("{version}/demo")
public class DemoController {

	@Autowired
	private DemoService demoService;
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
    public CommonVO<DataTableVO<UserVO>> mysql(@Valid MySqlDTO dto,BindingResult result) {
    	return new CommonVO<>( demoService.list(dto.getUsername(),dto.getPage(),dto.getSize()) );
    }
	
	@ApiVersion(1)
	@GetMapping("mysql2")
    public CommonVO<User> mysql() {
    	return new CommonVO<>( demoService.test() );
    }
	
	@ApiVersion(1)
	@GetMapping("redis")
    public String redis(@Valid RedisDTO dto,BindingResult result) {
		redisManager.setObject("test", "redis:" + new Date().getTime());
		return redisManager.getObject("test");
    }


}
