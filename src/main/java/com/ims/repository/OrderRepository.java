package com.ims.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.JpaSpecificationExecutor;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import com.ims.domain.entity.Order;

public interface OrderRepository extends JpaRepository<Order, Long>, JpaSpecificationExecutor<Order>  {
	public final static String FIND_BY_ID = "SELECT u FROM Order u where (u.orderId = :orderId)";

	@Query(FIND_BY_ID)
	public Order findByOrderId(@Param("orderId") Integer orderId);
}
