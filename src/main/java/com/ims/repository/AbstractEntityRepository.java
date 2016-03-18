package com.ims.repository;

import com.ims.domain.entity.AbstractEntity;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.JpaSpecificationExecutor;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.NoRepositoryBean;
import org.springframework.data.repository.query.Param;
import org.springframework.transaction.annotation.EnableTransactionManagement;
import org.springframework.transaction.annotation.Transactional;

@NoRepositoryBean
@EnableTransactionManagement
@Transactional(readOnly=true)
public interface AbstractEntityRepository<T extends AbstractEntity> extends JpaRepository<T, Long>, JpaSpecificationExecutor<T> {

	@Query("select ae from #{#entityName} ae where ae.deletedFlag = 0")
	Page<T> findAllActive(Pageable pageable);
	
	@Query("select ae from #{#entityName} ae where ae.deletedFlag = 0 and ae.pkey = :pkey")
	T findByPkey(@Param("pkey") Long pkey);
	
	@Modifying
	@Query("update #{#entityName} ae set ae.deletedFlag = 1 where ae.pkey = :pkey")
	@Transactional(readOnly=false)
	void deleteSoftByPkey(@Param("pkey") Long pkey);
	
	@Modifying
	@Query("delete from #{#entityName} ae where ae.pkey = :pkey")
	@Transactional(readOnly=false)
	void deleteByPkey(@Param("pkey") Long pkey);

}
