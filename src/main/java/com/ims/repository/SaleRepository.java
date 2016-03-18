package com.ims.repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.JpaSpecificationExecutor;
import org.springframework.data.jpa.repository.Query;

import com.ims.domain.entity.SaleDetail;

public interface SaleRepository extends JpaRepository<SaleDetail, Long>, JpaSpecificationExecutor<SaleDetail>{

	public final static String PRODUCT_IDS = "SELECT u.salesId FROM SaleDetail u";

	@Query(PRODUCT_IDS)
	public List<Integer> findSaleIds();
}
