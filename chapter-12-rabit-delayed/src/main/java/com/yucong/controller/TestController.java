package com.yucong.controller;

import java.time.LocalDateTime;

import org.springframework.amqp.rabbit.core.RabbitTemplate;
import org.springframework.amqp.support.converter.AbstractJavaTypeMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.yucong.config.RabbitConfig;
import com.yucong.entity.TestMessage;

import lombok.extern.slf4j.Slf4j;

@RestController
@RequestMapping(value = "test")
@Slf4j
public class TestController {

    private final RabbitTemplate rabbitTemplate;

    @Autowired
    public TestController(RabbitTemplate rabbitTemplate) {
        this.rabbitTemplate = rabbitTemplate;
    }


    /**
     * step 1
     * 
     * 将消息发送到EXCHANGE(exchange)交换机下的某个队列中（ROUTING_KEY为“routing-key”），
     * 但是没有对应的消费者消费，消息在5s后消费超时
     */
    @GetMapping
    public String defaultMessage() {
        TestMessage book = new TestMessage();
        book.setId("1");
        book.setName("一起来学Spring Boot");
        // 添加延时队列
        this.rabbitTemplate.convertAndSend(RabbitConfig.EXCHANGE, RabbitConfig.ROUTING_KEY, book, message -> {
            // TODO 第一句是可要可不要,根据自己需要自行处理
            message.getMessageProperties().setHeader(AbstractJavaTypeMapper.DEFAULT_CONTENT_CLASSID_FIELD_NAME, TestMessage.class.getName());
            // TODO 如果配置了 params.put("x-message-ttl", 5 * 1000); 那么这一句也可以省略,具体根据业务需要是声明 Queue 的时候就指定好延迟时间还是在发送自己控制时间
            message.getMessageProperties().setExpiration(5 * 1000 + "");
            return message;
        });
        log.info("[发送时间] - [{}]", LocalDateTime.now());
        return "send success";
    }
}
