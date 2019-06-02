package org.service.hi.service;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

import com.ctrip.framework.apollo.spring.annotation.EnableApolloConfig;

@Configuration
@EnableApolloConfig(value = "application")
public class TestApollo {
	
	@Bean
	public TestApolloConfig getApolloConfig() {
		return new TestApolloConfig();
	}
}