package org.service.mq.api;

import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/mq")
public class MQApi {
	
	@RequestMapping("/produce")
	public String produce() {
		
		return "hello";
	}

}
