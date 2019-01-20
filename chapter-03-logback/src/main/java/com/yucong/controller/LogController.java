package com.yucong.controller;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

/**
 * @author 喻聪
 * @date   2018-12-24
 */
@RestController
public class LogController {

    private static final Logger log = LoggerFactory.getLogger(LogController.class);

    @GetMapping("log")
    public String myProperties1() {
    	String msg = "测试输出日志";
    	log.debug(msg);
    	log.info(msg);
    	log.warn(msg);
    	log.error(msg);
    	return "success";
    }
    
}
