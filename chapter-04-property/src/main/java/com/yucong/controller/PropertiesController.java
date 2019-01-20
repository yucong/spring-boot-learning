package com.yucong.controller;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.yucong.properties.MyProperties1;
import com.yucong.properties.MyProperties2;

/**
 * @author 喻聪
 * @date   2018-12-24
 */
@RestController
@RequestMapping("properties")
public class PropertiesController {

    private static final Logger log = LoggerFactory.getLogger(PropertiesController.class);
    private final MyProperties1 myProperties1;
    private final MyProperties2 myProperties2;

    /**
     * TODO @Value 注解获取值方式,支持表达式，简单 eq and or 大于 小于 等对比操作
     */
    @Value("${my1.name}")
    private String my1Name;

    @Autowired
    public PropertiesController(MyProperties1 myProperties1, MyProperties2 myProperties2) {
        this.myProperties1 = myProperties1;
        this.myProperties2 = myProperties2;
    }

    @GetMapping("1")
    public MyProperties1 myProperties1() {
        log.info(myProperties1.toString());
        return myProperties1;
    }

    @GetMapping("2")
    public MyProperties2 myProperties2() {
        log.info(myProperties2.toString());
        return myProperties2;
    }
}
