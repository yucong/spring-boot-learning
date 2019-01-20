package com.yucong.config;

import java.util.HashMap;
import java.util.Map;

import org.springframework.amqp.core.Binding;
import org.springframework.amqp.core.BindingBuilder;
import org.springframework.amqp.core.DirectExchange;
import org.springframework.amqp.core.Queue;
import org.springframework.amqp.core.TopicExchange;
import org.springframework.amqp.rabbit.connection.CachingConnectionFactory;
import org.springframework.amqp.rabbit.core.RabbitTemplate;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

import lombok.extern.slf4j.Slf4j;

/**
 * RabbitMQ配置
 *
 * @author 喻聪
 * @date   2018-12-25
 */
@Configuration
@Slf4j
public class RabbitConfig {

    @Bean
    public RabbitTemplate rabbitTemplate(CachingConnectionFactory connectionFactory) {
        connectionFactory.setPublisherConfirms(true);
        connectionFactory.setPublisherReturns(true);
        RabbitTemplate rabbitTemplate = new RabbitTemplate(connectionFactory);
        rabbitTemplate.setMandatory(true);
        rabbitTemplate.setConfirmCallback((correlationData, ack, cause) -> log.info("消息发送成功:correlationData({}),ack({}),cause({})", correlationData, ack, cause));
        rabbitTemplate.setReturnCallback((message, replyCode, replyText, exchange, routingKey) -> log.info("消息丢失:exchange({}),route({}),replyCode({}),replyText({}),message:{}", exchange, routingKey, replyCode, replyText, message));
        return rabbitTemplate;
    }

    
    /**
     * DLX，dead letter发送到的 exchange
     * TODO 此处的 exchange 很重要,具体消息就是发送到该交换机的
     */
    public static final String EXCHANGE = "exchange";
    /**
     * routing key 名称
     * TODO 此处的 routingKey 很重要要,具体消息发送在该 routingKey 的
     */
    public static final String ROUTING_KEY = "routing-key";
    public static final String ALL_ROUTING_KEY = "all";

    //消息消费的队列
    public static final String DELAY_QUEUE = "delayy.queue";
    public static final String DELAY_EXCHANGE = "delay.exchange";
    
    /**
     * 延迟队列 TTL 名称
     */
    private static final String DELAY_WAIT_QUEUE = "delay.wait.queue";

    
    /**
     * step3:
     * 延时队列里面的消费无人消息，导致消息消费超时，
     * 会转发到DXL，并携带routing-key（all）
     * 
     * 延迟队列配置
     * <p>
     * 1、params.put("x-message-ttl", 5 * 1000);
     * TODO 第一种方式是直接设置 Queue 延迟时间 但如果直接给队列设置过期时间,这种做法不是很灵活,（当然二者是兼容的,默认是时间小的优先）
     * 2、rabbitTemplate.convertAndSend(book, message -> {
     * message.getMessageProperties().setExpiration(2 * 1000 + "");
     * return message;
     * });
     * TODO 第二种就是每次发送消息动态设置延迟时间,这样我们可以灵活控制
     **/
    @Bean
    public Queue delayWaitQueue() {
        Map<String, Object> params = new HashMap<>();
        // x-dead-letter-exchange 声明了队列里的死信转发到的DLX名称，
        params.put("x-dead-letter-exchange", DELAY_EXCHANGE);
        // x-dead-letter-routing-key 声明了这些死信在转发时携带的 routing-key 名称。
        params.put("x-dead-letter-routing-key", ALL_ROUTING_KEY);
        return new Queue(DELAY_WAIT_QUEUE, true, false, false, params);
    }

    /**
     * 需要将一个队列绑定到交换机上，要求该消息与一个特定的路由键完全匹配。
     * 这是一个完整的匹配。如果一个队列绑定到该交换机上要求路由键 “dog”，则只有被标记为“dog”的消息才被转发，不会转发dog.puppy，也不会转发dog.guard，只会转发dog。
     * TODO 它不像 TopicExchange 那样可以使用通配符适配多个
     *
     * @return DirectExchange
     */
    @Bean
    public DirectExchange exchange() {
        return new DirectExchange(EXCHANGE);
    }

    /**
     * step2:
     * exchange交换机根据Routing-Key(routing-key)来
     * 投递到DELAY_PROCESS_QUEUE(delay.process.queue)队列中
     */
    @Bean
    public Binding dlxBinding() {
        return BindingBuilder.bind(delayWaitQueue())
        		.to(exchange())
        		.with(ROUTING_KEY);
    }


    @Bean
    public Queue delayQueue() {
        return new Queue(DELAY_QUEUE, true);
    }

    /**
     * 将路由键和某模式进行匹配。此时队列需要绑定要一个模式上。
     * 符号“#”匹配一个或多个词，符号“*”匹配不多不少一个词。因此“audit.#”能够匹配到“audit.irs.corporate”，但是“audit.*” 只会匹配到“audit.irs”。
     **/
    @Bean
    public TopicExchange delayExchange() {
        return new TopicExchange(DELAY_EXCHANGE);
    }

    /**
     * step4：
     * 
     * delayExchange会根据ALL_ROUTING_KEY将消息投递到delayQueue,
     * 消息一投递到delayQueue中，就会被监听的消息者消费
     */
    @Bean
    public Binding delayBinding() {
        // TODO 如果要让延迟队列之间有关联,这里的 routingKey 和 绑定的交换机很关键
        return BindingBuilder.bind(delayQueue())
        		.to(delayExchange()).with(ALL_ROUTING_KEY);
    }


}
