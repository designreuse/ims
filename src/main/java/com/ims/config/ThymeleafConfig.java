package com.ims.config;

import com.github.dandelion.datatables.thymeleaf.dialect.DataTablesDialect;

import nz.net.ultraq.thymeleaf.LayoutDialect;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.core.env.Environment;
import org.thymeleaf.extras.springsecurity3.dialect.SpringSecurityDialect;
import org.thymeleaf.spring4.SpringTemplateEngine;
import org.thymeleaf.spring4.view.ThymeleafViewResolver;
import org.thymeleaf.templateresolver.ServletContextTemplateResolver;
import org.thymeleaf.templateresolver.TemplateResolver;

@Configuration
public class ThymeleafConfig {

	@Autowired
	private Environment environment;

	@Bean
	public TemplateResolver templateResolver() {
		ServletContextTemplateResolver templateResolver = new ServletContextTemplateResolver();
		templateResolver.setPrefix("/pages/");
		templateResolver.setSuffix(".html");
		templateResolver.setTemplateMode("HTML5");
		templateResolver.setCacheable(environment.getRequiredProperty("thymeleaf.cache", Boolean.class));
		return templateResolver;
	}

	@Bean
	public SpringTemplateEngine templateEngine() {
		SpringTemplateEngine templateEngine = new SpringTemplateEngine();
		templateEngine.setTemplateResolver(templateResolver());
		templateEngine.addDialect(new LayoutDialect());
		templateEngine.addDialect(new SpringSecurityDialect());
		templateEngine.addDialect(new DataTablesDialect());
		return templateEngine;
	}

	@Bean
	public ThymeleafViewResolver thymeleafViewResolver() {
		ThymeleafViewResolver resolver = new ThymeleafViewResolver();
		resolver.setTemplateEngine(templateEngine());
		return resolver;
	}
}