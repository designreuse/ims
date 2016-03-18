package com.ims.service;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

import com.ims.dto.JobTO;
import com.ims.util.ImsException;

@Service
public interface JobService {
	
	public JobTO getJobInfo(String jobType);
	
	public JobTO getJobInfo(int jobId);
	
	public JobTO saveOrUpdateJob(JobTO jobTO) throws ImsException;
	
	public Page<JobTO> getAllJobs(Pageable pageable);

	public Page<JobTO> dataTableSearch(String name, Pageable pageable);
	
}
