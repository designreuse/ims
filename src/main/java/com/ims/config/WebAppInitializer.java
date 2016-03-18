package com.ims.config;

import com.github.dandelion.core.web.DandelionFilter;
import com.github.dandelion.core.web.DandelionServlet;

import org.springframework.web.WebApplicationInitializer;
import org.springframework.web.context.ContextLoaderListener;
import org.springframework.web.context.support.AnnotationConfigWebApplicationContext;
import org.springframework.web.servlet.DispatcherServlet;

import javax.servlet.FilterRegistration;
import javax.servlet.ServletContext;
import javax.servlet.ServletRegistration;

public class WebAppInitializer implements WebApplicationInitializer {

	public void onStartup(ServletContext container) {
		// Create the 'root' Spring application context
		AnnotationConfigWebApplicationContext rootContext = new AnnotationConfigWebApplicationContext();
		rootContext.register(PropertiesConfig.class, SecurityConfig.class, ServiceConfig.class, JPAConfig.class);

		// Manage the lifecycle of the root application context
		container.addListener(new ContextLoaderListener(rootContext));

		// Create the dispatcher servlet's Spring application context
		AnnotationConfigWebApplicationContext dispatcherServlet = new AnnotationConfigWebApplicationContext();
		dispatcherServlet.register(ThymeleafConfig.class, MvcConfig.class);

		// Register and map the dispatcher servlet
		ServletRegistration.Dynamic dispatcher = container.addServlet("dispatcher", new DispatcherServlet(dispatcherServlet));
		dispatcher.setLoadOnStartup(1);
		dispatcher.addMapping("/");

		// Register the Dandelion filter
		FilterRegistration.Dynamic dandelionFilter = container.addFilter("dandelionFilter", new DandelionFilter());
		dandelionFilter.addMappingForUrlPatterns(null, false, "/*");

		// Register the Dandelion servlet
		ServletRegistration.Dynamic dandelionServlet = container.addServlet("dandelionServlet", new DandelionServlet());
		dandelionServlet.setLoadOnStartup(2);
		dandelionServlet.addMapping("/dandelion-assets/*");
	}

}