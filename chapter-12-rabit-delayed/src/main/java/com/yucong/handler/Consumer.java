package com.yucong.handler;

import java.io.IOException;
import java.time.LocalDateTime;

import org.springframework.amqp.core.Message;
import org.springframework.amqp.rabbit.annotation.RabbitListener;
import org.springframework.stereotype.Component;

import com.rabbitmq.client.Channel;
import com.yucong.config.RabbitConfig;
import com.yucong.entity.TestMessage;

import lombok.extern.slf4j.Slf4j;

/**
 * 消费者
 *
 * @author 喻聪
 * @date   2018-12-25
 */
@Component
@Slf4j
public class Consumer {

    @RabbitListener(queues = {RabbitConfig.DELAY_QUEUE})
    public void listenerDelayQueue(TestMessage book, Message message, Channel channel) {
        log.info("[listenerDelayQueue 监听的消息] - [消费时间] - [{}] - [{}]", LocalDateTime.now(), book.toString());
        try {
            // TODO 通知 MQ 消息已被成功消费,可以ACK了
            channel.basicAck(message.getMessageProperties().getDeliveryTag(), false);
        } catch (IOException e) {
            // TODO 如果报错了,那么我们可以进行容错处理,比如转移当前消息进入其它队列
        	e.printStackTrace();
        }
    }


}
