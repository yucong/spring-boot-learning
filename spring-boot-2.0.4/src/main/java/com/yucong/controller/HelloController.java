package com.yucong.controller;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

import lombok.extern.slf4j.Slf4j;


@Slf4j
@RestController
public class HelloController {

	@GetMapping("/test")
    public String hello() {
		String msg = "测试输出日志";
    	log.debug(msg);
    	log.info(msg);
    	log.warn(msg);
    	log.error(msg);
        return "Hello Spring Boot";
    }


}
