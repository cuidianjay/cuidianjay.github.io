package org.service.mq.service.impl;

import javax.jms.Destination;

import org.service.mq.service.ProducerService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jms.core.JmsMessagingTemplate;
import org.springframework.stereotype.Service;

@Service
public class ProducerServiceImpl implements ProducerService{

	@Autowired
	private JmsMessagingTemplate jmsMsgTem;

	@Override
	public void producer(Destination des, String msg) {
		jmsMsgTem.convertAndSend(des, msg);
	}
}
