package com.ims.dao;

import com.ims.job.bean.SchedulerJob;

public interface JobDao {
	SchedulerJob getJobDetailsFromDb(String dbUrl,String userName,String pwd);
}
