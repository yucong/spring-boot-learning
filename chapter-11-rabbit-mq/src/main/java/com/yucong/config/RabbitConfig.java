package com.yucong.config;

import org.springframework.amqp.core.Queue;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

/**
 * RabbitMQ配置
 *
 * @author 喻聪
 * @since 2018-12-25
 */
@Configuration
public class RabbitConfig {

    public static final String DEFAULT_QUEUE = "defalut-queue";
    public static final String MANUAL_QUEUE = "manual-queue";

    @Bean
    public Queue defaultBookQueue() {
        // 第一个是 QUEUE 的名字,第二个是消息是否需要持久化处理
        return new Queue(DEFAULT_QUEUE, true);
    }

    @Bean
    public Queue manualBookQueue() {
        // 第一个是 QUEUE 的名字,第二个是消息是否需要持久化处理
        return new Queue(MANUAL_QUEUE, true);
    }

}
