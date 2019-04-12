package com.yucong.controller;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

import com.yucong.model.User;

import lombok.extern.slf4j.Slf4j;

/**
 * 加上Slf4j后，就可以直接使用log对象了
 * 
 * @author 喻聪
 * 
 * @date   2018-12-24
 */
@RestController
@Slf4j
public class TestController {

    @GetMapping("test")
    public User test() {
    	User user = new User();
    	user.setId(1);
    	user.setName("喻聪");
    	log.info("用户信息：" + user);
    	return user;
    }

}
