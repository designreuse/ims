package com.ims.repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.JpaSpecificationExecutor;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Service;

import com.ims.domain.entity.Order;
import com.ims.domain.entity.Product;
import com.ims.domain.entity.ProductOrder;

@Service
public interface ProductOrderRepository extends JpaRepository<ProductOrder, Long>, JpaSpecificationExecutor<ProductOrder>{
	
	public final static String PRODUCT_IDS = "SELECT u FROM ProductOrder u";
	public final static String FIND_BY_ID = "SELECT u FROM ProductOrder u where (u.orderProductId = :orderProductId)";

	@Query(PRODUCT_IDS)
	public List<ProductOrder> getAllOrderDetails();
	
	@Query(FIND_BY_ID)
	public ProductOrder findByProductOrderId(@Param("orderProductId") int orderProductId);

}
