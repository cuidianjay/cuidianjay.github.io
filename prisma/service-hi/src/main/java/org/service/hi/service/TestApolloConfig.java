package org.service.hi.service;

import org.springframework.beans.factory.annotation.Value;

import com.ctrip.framework.apollo.Config;
import com.ctrip.framework.apollo.ConfigService;
import com.ctrip.framework.apollo.spring.annotation.ApolloConfig;

public class TestApolloConfig {
	
	private Config config;
	
	@ApolloConfig
	private Config configSpring;
	
	@Value("${name:jay}")
	private String name;
	
	@Value("${age:18}")
	private String age;

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public String getAge() {
		return age;
	}

	public void setAge(String age) {
		this.age = age;
	}
	
	public String toStringByJavaApi() {
		config = ConfigService.getAppConfig();
		name = config.getProperty("name", "");
		age = config.getProperty("age", "");
		return ("姓名："+name+",年龄："+age);
	}
	
	public String toStringByJSpring() {
		name = configSpring.getProperty("name", "");
		age = configSpring.getProperty("age", "");
		return ("姓名："+name+",年龄："+age);
	}
	
	public String toStringByValue() {
		return ("姓名："+name+",年龄："+age);
	}
}
