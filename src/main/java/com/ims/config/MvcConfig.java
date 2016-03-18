package com.ims.config;

import java.util.List;

import org.quartz.CronTrigger;
import org.quartz.JobDataMap;
import org.quartz.JobDetail;
import org.quartz.Scheduler;
import org.quartz.impl.StdSchedulerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.ApplicationContext;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.support.ReloadableResourceBundleMessageSource;
import org.springframework.core.env.Environment;
import org.springframework.data.web.PageableHandlerMethodArgumentResolver;
import org.springframework.data.web.SortHandlerMethodArgumentResolver;
import org.springframework.scheduling.quartz.JobDetailBean;
import org.springframework.scheduling.quartz.SchedulerFactoryBean;
import org.springframework.web.method.support.HandlerMethodArgumentResolver;
import org.springframework.web.multipart.commons.CommonsMultipartResolver;
import org.springframework.web.servlet.config.annotation.DefaultServletHandlerConfigurer;
import org.springframework.web.servlet.config.annotation.EnableWebMvc;
import org.springframework.web.servlet.config.annotation.ResourceHandlerRegistry;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurerAdapter;
import org.springframework.web.servlet.view.InternalResourceViewResolver;

import com.github.dandelion.datatables.extras.spring3.ajax.DatatablesCriteriasMethodArgumentResolver;
import com.ims.dao.JobDao;
import com.ims.dao.impl.JobDaoImpl;
import com.ims.job.AutowiringSpringBeanJobFactory;
import com.ims.job.OrderJobExecution;
import com.ims.job.ProductJobExecution;
import com.ims.job.bean.SchedulerJob;
import com.ims.service.OrderService;
import com.ims.service.ProductService;

@EnableWebMvc
@Configuration
@ComponentScan(basePackages = { "com.ims.controller" })
public class MvcConfig extends WebMvcConfigurerAdapter {

	@Autowired
	private Environment environment;

	@Autowired
	private ProductService productService;

	@Autowired
	private OrderService orderService;

	@Autowired
	ApplicationContext applicationContext;

	@Override
	public void addResourceHandlers(ResourceHandlerRegistry registry) {
		registry.addResourceHandler("/resources/**").addResourceLocations(
				"/resources/");
	}

	@Override
	public void configureDefaultServletHandling(
			DefaultServletHandlerConfigurer configurer) {
		configurer.enable();
	}

	@Bean
	public InternalResourceViewResolver jspViewResolver() {
		InternalResourceViewResolver bean = new InternalResourceViewResolver();
		bean.setPrefix("/pages");
		bean.setSuffix(".jsp");
		return bean;
	}

	@Bean(name = "multipartResolver")
	public CommonsMultipartResolver getMultipartResolver() {
		return new CommonsMultipartResolver();
	}

	@Bean(name = "messageSource")
	public ReloadableResourceBundleMessageSource getMessageSource() {
		ReloadableResourceBundleMessageSource resource = new ReloadableResourceBundleMessageSource();
		resource.setBasename("classpath:messages");
		resource.setDefaultEncoding("UTF-8");
		return resource;
	}

	@Override
	public void addArgumentResolvers(
			List<HandlerMethodArgumentResolver> argumentResolvers) {
		argumentResolvers.add(new PageableHandlerMethodArgumentResolver());
		argumentResolvers.add(new SortHandlerMethodArgumentResolver());
		argumentResolvers.add(new DatatablesCriteriasMethodArgumentResolver());
	}

	@Bean(name = "runJob")
	public JobDetail getRunJobBean() {
		JobDetail job = new JobDetailBean();
		return job;
	}

	@Bean(name = "cronTrigger")
	public CronTrigger getCronInformation() {
		JobDetail job = getRunJobBean();

		String jdbcUrl = environment.getRequiredProperty("persistence.jdbcurl");
		String userName = environment.getRequiredProperty("persistence.dbuser");
		String pwd = environment.getRequiredProperty("persistence.dbpass");
		try {
			JobDao jobInformation = new JobDaoImpl();
			SchedulerJob jobInfo = jobInformation.getJobDetailsFromDb(jdbcUrl,
					userName, pwd);
			if ((jobInfo.getJobType().trim()).equals("SyncProduct")
					|| (jobInfo.getJobType().trim()).equals("0")) {
				job.setJobClass(ProductJobExecution.class);
			} else if ((jobInfo.getJobType().trim()).equals("SyncOrder")
					|| (jobInfo.getJobType().trim()).equals("1")) {
				job.setJobClass(OrderJobExecution.class);
			}
			job.setName(jobInfo.getJobName());
			if (jobInfo.getSchedule()) {
				CronTrigger cron = new CronTrigger();
				cron.setName(jobInfo.getJobName());
				try {
					cron.setCronExpression(jobInfo.getCronExpression());
				} catch (Exception e) {
					e.printStackTrace();
				}
				return cron;
			} else {
				return null;
			}
		} catch (Exception e) {
			e.printStackTrace();
		}
		return null;
	}

	@Bean(name = "scheduler")
	public Scheduler getSchedulerInformation() {
		JobDetail jobDetail = getRunJobBean();
		// JobDetail[] jobDetails = {jobDetail};
		// schedulaer.setJobDetails(jobDetails);
		CronTrigger trigger = getCronInformation();
		Scheduler scheduler = null;
		try {
			if (trigger != null) {
				scheduler = new StdSchedulerFactory().getScheduler();
				scheduler.start();
				scheduler.scheduleJob(jobDetail, trigger);

			} else {
				scheduler = new StdSchedulerFactory().getScheduler();
				// scheduler.shutdown();
			}
		} catch (Exception e) {
			e.printStackTrace();
		}
		return scheduler;
	}

	@Bean
	public SchedulerFactoryBean quartzScheduler() {
		SchedulerFactoryBean quartzScheduler = new SchedulerFactoryBean();
		AutowiringSpringBeanJobFactory jobFactory = new AutowiringSpringBeanJobFactory();
		jobFactory.setApplicationContext(applicationContext);
		quartzScheduler.setJobFactory(jobFactory);
		return quartzScheduler;
	}
}