package com.ims.job;

import org.quartz.Job;
import org.quartz.JobExecutionContext;
import org.quartz.JobExecutionException;
import org.springframework.context.ApplicationContext;
import org.springframework.web.context.ContextLoaderListener;
import org.springframework.web.context.support.WebApplicationContextUtils;

import com.ims.service.ProductService;

public class ProductJobExecution implements Job{
	public void execute(JobExecutionContext context)
			throws JobExecutionException {
		ApplicationContext springContext = 
			    WebApplicationContextUtils.getWebApplicationContext(
			        ContextLoaderListener.getCurrentWebApplicationContext().getServletContext()
			    );
		ProductService productService = (ProductService)springContext.getBean(ProductService.class);
		productService.addProduct();
	} 
}
