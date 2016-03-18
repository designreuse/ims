package com.ims.dto;

import com.ims.domain.enums.JobType;

public class JobTO {

	private Integer id;
	private String name;
	private String cronExpression;
	private boolean schedule;
	private JobType jobType; 
	
	public Integer getId() {
		return id;
	}
	public void setId(Integer id) {
		this.id = id;
	}	
	public String getName() {
		return name;
	}
	public void setName(String name) {
		this.name = name;
	}
	public String getCronExpression() {
		return cronExpression;
	}
	public void setCronExpression(String cronExpression) {
		this.cronExpression = cronExpression;
	}
	public boolean isSchedule() {
		return schedule;
	}
	public void setSchedule(boolean schedule) {
		this.schedule = schedule;
	}
	
	public JobType getJobType() {
		return jobType;
	}
	public void setJobType(JobType jobType) {
		this.jobType = jobType;
	}
	
}
