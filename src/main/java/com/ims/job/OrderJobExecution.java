package com.ims.job;

import org.quartz.Job;
import org.quartz.JobExecutionContext;
import org.quartz.JobExecutionException;
import org.springframework.context.ApplicationContext;
import org.springframework.web.context.ContextLoaderListener;
import org.springframework.web.context.support.WebApplicationContextUtils;

import com.ims.service.OrderService;
public class OrderJobExecution implements Job{
	public void execute(JobExecutionContext context)
			throws JobExecutionException {
		try {
			ApplicationContext springContext = 
				    WebApplicationContextUtils.getWebApplicationContext(
				        ContextLoaderListener.getCurrentWebApplicationContext().getServletContext()
				    );
			OrderService orderService = (OrderService)springContext.getBean(OrderService.class);
			orderService.updateQuantity();
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
        
	} 
}
