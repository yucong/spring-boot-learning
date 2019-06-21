package com.yucong.producer;

import org.springframework.amqp.rabbit.core.RabbitTemplate;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import com.java.common.log.constant.LogConstants;
import com.java.common.log.model.BusinessAbnormalLog;
import com.java.common.log.model.HttpRequestLog;
import com.java.common.log.model.ServerExceptionLog;
import com.java.common.log.producer.LogAbstractProducer;

@Component
public class LogDirectProducer extends LogAbstractProducer {

    @Autowired
	private RabbitTemplate rabbitTemplate;
	
	@Override
	public void produceRequestLog(HttpRequestLog log) {
		log.setPlatform(LogConstants.Platform.APP);
		rabbitTemplate.convertAndSend(LogConstants.Routing.EXCHANGE, LogConstants.Routing.REQUEST, log);
	}
	
	@Override
	public void produceExceptionLog(ServerExceptionLog log) {
		log.setPlatform(LogConstants.Platform.APP);
		rabbitTemplate.convertAndSend(LogConstants.Routing.EXCHANGE, LogConstants.Routing.EXCEPTION, log);
	}

	public void produceAbnormalLog(BusinessAbnormalLog log) {
		rabbitTemplate.convertAndSend(LogConstants.Routing.EXCHANGE, LogConstants.Routing.ABNORMAL, log);
	}


}
