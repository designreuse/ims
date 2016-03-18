package com.ims.repository;

import com.ims.domain.entity.User;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

public interface UserRepository extends AbstractEntityRepository<User> {

	public final static String FIND_BY_Name = "SELECT u FROM User u where u.name = :name and u.deletedFlag <> 1";

	public final static String FIND_BY_Datatable_Filter = "SELECT u FROM User u where (u.name = :name or u.firstName = :name or u.lastName = :name or u.pkey = :name)and u.deletedFlag <> 1";

	@Query(FIND_BY_Name)
	public User findByName(@Param("name") String name);

	@Query(FIND_BY_Datatable_Filter)
	public Page<User> findByDatatableFilter(@Param("name") String name, Pageable pageable);
}
