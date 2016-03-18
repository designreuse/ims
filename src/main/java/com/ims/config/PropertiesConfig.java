package com.ims.config;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.Profile;
import org.springframework.context.annotation.PropertySource;
import org.springframework.context.support.PropertySourcesPlaceholderConfigurer;

@Configuration
@PropertySource("classpath:application.properties")
public class PropertiesConfig {

	@Profile("dev")
	@Configuration
	@PropertySource("classpath:application-dev.properties")
	static class ApplicationProperties {
	}

	@Profile("integration-test")
	@Configuration
	@PropertySource("classpath:integration-test.properties")
	static class IntegrationTestProperties {
	}

	@Profile("test")
	@Configuration
	@PropertySource("file:${catalina.base}/properties/ims/application-test.properties")
	static class TestProperties {
	}

	@Profile("stage")
	@Configuration
	@PropertySource("file:${catalina.base}/properties/ims/application-stage.properties")
	static class StageProperties {
	}

	@Profile("prod")
	@Configuration
	@PropertySource("file:${catalina.base}/properties/ims/application-prod.properties")
	static class ProdProperties {
	}

	@Bean
	PropertySourcesPlaceholderConfigurer propertyPlaceHolderConfigurer() {
		return new PropertySourcesPlaceholderConfigurer();
	}
}
