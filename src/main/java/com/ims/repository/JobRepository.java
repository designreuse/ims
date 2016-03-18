package com.ims.repository;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.JpaSpecificationExecutor;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import com.ims.domain.entity.Job;
import com.ims.dto.JobTO;

public interface JobRepository extends JpaRepository<Job, Long>, JpaSpecificationExecutor<Job>  {
	
	public final static String FIND_BY_Datatable_Filter = "SELECT u FROM Job u where (u.name = :name)";
	
	public final static String FIND_BY_ID = "SELECT u FROM Job u where (u.id = :jobId)";
	
	public final static String FIND_BY_TYPE = "SELECT u FROM Job u where (u.jobType = :jobType)";
	
	@Query(FIND_BY_Datatable_Filter)
	public Page<Job> findByDatatableFilter(@Param("name") String name, Pageable pageable);

	@Query(FIND_BY_ID)
	public JobTO findByJobId(int jobId);

	@Query(FIND_BY_TYPE)
	public JobTO findByJobType(String jobType);

}
