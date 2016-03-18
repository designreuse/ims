package com.ims.job.bean;

public class SchedulerJob {
	int id;
	String cronExpression;
	String jobType;
	String jobName;
	boolean schedule;
	public int getId() {
		return id;
	}
	public void setId(int id) {
		this.id = id;
	}
	public String getCronExpression() {
		return cronExpression;
	}
	public void setCronExpression(String cronExpression) {
		this.cronExpression = cronExpression;
	}
	public String getJobType() {
		return jobType;
	}
	public void setJobType(String jobType) {
		this.jobType = jobType;
	}
	public String getJobName() {
		return jobName;
	}
	public void setJobName(String jobName) {
		this.jobName = jobName;
	}
	public boolean getSchedule() {
		return schedule;
	}
	public void setSchedule(boolean schedule) {
		this.schedule = schedule;
	}
}
