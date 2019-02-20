package org.service.mq.service;

import javax.jms.Destination;

public interface ProducerService {

	void producer(Destination des,String msg);
}
