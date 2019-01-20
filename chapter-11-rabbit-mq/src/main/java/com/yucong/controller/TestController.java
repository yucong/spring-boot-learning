package com.yucong.controller;

import org.springframework.amqp.rabbit.core.RabbitTemplate;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.yucong.config.RabbitConfig;
import com.yucong.entity.TestMessage;

@RestController
@RequestMapping(value = "/test")
public class TestController {

	@Autowired
    private RabbitTemplate rabbitTemplate;

    @GetMapping
    public String defaultMessage() {
        TestMessage msg = new TestMessage();
        msg.setId("1");
        msg.setName("一起来学Spring Boot");
        this.rabbitTemplate.convertAndSend(RabbitConfig.DEFAULT_QUEUE, msg);
        this.rabbitTemplate.convertAndSend(RabbitConfig.MANUAL_QUEUE, msg);
        return "success";
    }


}
