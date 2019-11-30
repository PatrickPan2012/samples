package com.patrick.sso.service.face.impl.ms;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.Configuration;
import org.springframework.web.client.RestTemplate;

@Configuration
@ComponentScan(basePackages = { "com.patrick.sso.service", "com.patrick.sso.profileid" })
public class ApplicationContextConfigurationForTestingFaceLoginServiceImpl {
	@Bean
	public RestTemplate buildRestTemplate() {
		return new RestTemplate();
	}
}
