package com.ims.domain.entity;

import javax.persistence.Entity;
import javax.persistence.EnumType;
import javax.persistence.Enumerated;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.Table;

import com.ims.domain.enums.JobType;

@Entity
@Table(name="job")
public class Job {

	@Id
	@GeneratedValue
	private Long id;
	private String name;
	private String cronExpression;
	private boolean schedule;
	private JobType jobType; 
	
	public Long getId() {
		return id;
	}
	public void setOrderId(Long id) {
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
	
	@Enumerated(EnumType.STRING)
	public JobType getJobType() {
		return jobType;
	}
	
	public void setJobType(JobType jobType) {
		this.jobType = jobType;
	}
	
}
