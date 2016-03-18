package com.ims.service.impl;

import java.util.ArrayList;
import java.util.List;

import org.dozer.DozerBeanMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageImpl;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.ims.domain.entity.Job;
import com.ims.dto.JobTO;
import com.ims.job.JobScheduleInfo;
import com.ims.repository.JobRepository;
import com.ims.service.JobService;
import com.ims.service.OrderService;
import com.ims.service.ProductService;
import com.ims.util.ImsException;

@Service
@Transactional
public class JobServiceImpl implements JobService {

	@Autowired
	private DozerBeanMapper mapper;
	
	@Autowired
	private JobRepository jobRepository;
	
	@Autowired
	private ProductService productService;
	
	@Autowired
	private OrderService orderService;
	
	@Override
	public JobTO saveOrUpdateJob(JobTO jobTO) throws ImsException {
		Job job = null;
		JobScheduleInfo schedule = new JobScheduleInfo();
		schedule.scheduleJob(jobTO,productService,orderService);
		if (jobTO.getId() == null)
			job = mapper.map(jobTO, Job.class);
		else {
			job = jobRepository.findOne(Long.valueOf(jobTO.getId()));
			if (job == null)
				return null;
			mapper.map(jobTO, job);
		}
		jobRepository.save(job);
		return null;
	}

	@Override
	public Page<JobTO> getAllJobs(Pageable pageable) {
		Page<Job> jobs = jobRepository.findAll(pageable);
		List<JobTO> jobTOs = new ArrayList<JobTO>();
		for (Job job : jobs.getContent()) {
			JobTO jobTo = mapper.map(job, JobTO.class);
			jobTOs.add(jobTo);
		}
		return new PageImpl<>(jobTOs, new PageRequest(jobs.getNumber(), jobs.getSize(), jobs.getSort()),
				jobs.getTotalElements());
	}

	@Override
	public Page<JobTO> dataTableSearch(String name, Pageable pageable) {
		Page<Job> jobs = jobRepository.findByDatatableFilter(name, pageable);
		List<JobTO> jobTOs = new ArrayList<JobTO>();
		for (Job job : jobs.getContent()) {
			jobTOs.add(mapper.map(job, JobTO.class));
		}
		return new PageImpl<>(jobTOs, new PageRequest(jobs.getNumber(), jobs.getSize(), jobs.getSort()),
				jobs.getTotalElements());
	}

	@Override
	public JobTO getJobInfo(int jobId) {
		return jobRepository.findByJobId(jobId);
	}

	@Override
	public JobTO getJobInfo(String jobType) {
		return jobRepository.findByJobType(jobType);
	}

}
