package org.service.mq.service.impl;

import org.service.mq.service.ConsumerService;
import org.springframework.jms.annotation.JmsListener;
import org.springframework.stereotype.Component;

@Component
public class ConsumerServiceImpl implements ConsumerService{

	@Override
	@JmsListener(destination = "mytest.queue")
	public void receiveQueue(String msg) {
		
	}

}
