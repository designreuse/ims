package com.ims.job;

import org.quartz.CronTrigger;
import org.quartz.JobDataMap;
import org.quartz.JobDetail;
import org.quartz.Scheduler;
import org.quartz.impl.StdSchedulerFactory;

import com.ims.domain.enums.JobType;
import com.ims.dto.JobTO;
import com.ims.service.OrderService;
import com.ims.service.ProductService;

public class JobScheduleInfo {
	public static void main(String[] args) {
		JobDetail job = new JobDetail();
    	job.setName("dummyJobName");
    	job.setJobClass(JobExcecution.class);
    	try{
    		CronTrigger trigger = new CronTrigger();
    		trigger.setName("dummyTriggerName");
    		trigger.setCronExpression("0/30 * * * * ?");
    	
    		Scheduler scheduler = new StdSchedulerFactory().getScheduler();
    		scheduler.start();
    		scheduler.scheduleJob(job, trigger);
    	}catch(Exception e){
    		e.printStackTrace();
    	}
	}
	
	public void scheduleJob(JobTO jobTO,ProductService productService,OrderService orderService){
		try{
    		Scheduler scheduler = new StdSchedulerFactory().getScheduler();
    		JobDetail job = new JobDetail();
	    	//job.setName("dummyJobName");
	    	String cronExpression = jobTO.getCronExpression().trim();
	    	String name = jobTO.getName();
	    	if((jobTO.getJobType()).equals(JobType.SyncProduct)){
	    			job.setJobClass(ProductJobExecution.class);
	    		
			} else{
					job.setJobClass(OrderJobExecution.class);
			}
	    	if(jobTO.isSchedule()){
	    		CronTrigger trigger = new CronTrigger();
	    		job.setName(name);
	    		trigger.setName(name);
	    		trigger.setCronExpression(cronExpression);
    	
	    		scheduler.start();
	    		scheduler.scheduleJob(job, trigger);
	    	}else{
	    		//CronTrigger trigger = new CronTrigger();
	    		//trigger.setName(name);
	    		scheduler.deleteJob(name, null);
	    	}
		}
		catch(Exception e){
			e.printStackTrace();
		}
	}
}
