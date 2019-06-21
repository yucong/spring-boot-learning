package com.yucong.permission.producer;


import org.springframework.amqp.rabbit.core.RabbitTemplate;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import com.java.common.log.constant.LogConstants;
import com.java.common.log.model.BusinessAbnormalLog;
import com.java.common.log.model.HttpRequestLog;
import com.java.common.log.model.ServerExceptionLog;
import com.java.common.log.producer.LogAbstractProducer;
import com.yucong.permission.constants.Env;

@Component
public class LogProducer extends LogAbstractProducer {

	@Autowired
	private RabbitTemplate rabbitTemplate;
	
	@Override
	public void produceRequestLog(HttpRequestLog log) {
		log.setPlatform(LogConstants.Platform.PERMISSION);
		String queueName = Env.isProduct ? LogConstants.Routing.REQUEST : LogConstants.Routing.REQUEST + "_test";
		rabbitTemplate.convertAndSend(LogConstants.Routing.EXCHANGE, queueName, log);
	}
	
	@Override
	public void produceExceptionLog(ServerExceptionLog log) {
		log.setPlatform(LogConstants.Platform.PERMISSION);
		String queueName = Env.isProduct ? LogConstants.Routing.EXCEPTION : LogConstants.Routing.EXCEPTION + "_test";
		rabbitTemplate.convertAndSend(LogConstants.Routing.EXCHANGE, queueName, log);
	}

	public void produceAbnormalLog(BusinessAbnormalLog log) {
		log.setPlatform(LogConstants.Platform.PERMISSION);
		String queueName = Env.isProduct ? LogConstants.Routing.ABNORMAL : LogConstants.Routing.ABNORMAL + "_test";
		rabbitTemplate.convertAndSend(LogConstants.Routing.EXCHANGE, queueName, log);
	}
	

	
}

