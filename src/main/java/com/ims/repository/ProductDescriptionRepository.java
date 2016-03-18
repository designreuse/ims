package com.ims.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.JpaSpecificationExecutor;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import com.ims.domain.entity.ProductDescription;

public interface ProductDescriptionRepository extends JpaRepository<ProductDescription, Long>, JpaSpecificationExecutor<ProductDescription> {
	
	public final static String FIND_BY_PRODUCT_ID = "SELECT u FROM ProductDescription u where (u.product.productId = :productId)";
	
	@Query(FIND_BY_PRODUCT_ID)
	public ProductDescription findByProductId(@Param("productId") int productId);	

}
