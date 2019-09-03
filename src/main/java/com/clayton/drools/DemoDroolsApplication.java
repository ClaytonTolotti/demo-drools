package com.clayton.drools;

import com.clayton.drools.drools.KieServerConfiguration;
import org.kie.server.client.KieServicesClient;
import org.modelmapper.ModelMapper;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.Bean;

@SpringBootApplication
public class DemoDroolsApplication {

	public static void main(String[] args) {
		SpringApplication.run(DemoDroolsApplication.class, args);
	}

	@Bean
	public KieServicesClient initialize() {
		return new KieServerConfiguration().initialize();
	}

	@Bean
	public ModelMapper getModelMapper() {
		return new ModelMapper();
	}
}
