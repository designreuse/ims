package com.ims.dao.impl;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.Statement;

import com.ims.dao.JobDao;
import com.ims.job.bean.SchedulerJob;

public class JobDaoImpl implements JobDao{
	public SchedulerJob getJobDetailsFromDb(String dbUrl,String userName,String pwd){
		try{ 
			Class.forName("com.mysql.jdbc.Driver").newInstance();
			
			Connection conn = DriverManager.getConnection(dbUrl, userName, pwd);
			Statement statement = conn.createStatement();
			
			String query = "select * from ims_job";
			
			ResultSet rs = statement.executeQuery(query);
			SchedulerJob job = null;
			while(rs.next()){
				job = new SchedulerJob();
				job.setId(rs.getInt("id"));
				job.setJobName(rs.getString("name"));
				job.setCronExpression(rs.getString("cron_expression"));
				job.setSchedule(rs.getBoolean("schedule"));
				job.setJobType(rs.getString("job_type"));
				if(rs.getBoolean("schedule")){
					break;
				}
			}
			return job;
		} catch(Exception e){
			e.printStackTrace();
		}
	    return new SchedulerJob();
	}
}
