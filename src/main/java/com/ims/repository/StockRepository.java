package com.ims.repository;

import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import com.ims.domain.entity.Product;
import com.ims.domain.entity.Stock;

public interface StockRepository extends AbstractEntityRepository<Stock>{

	public final static String FIND_BY_STOCK_ID = "SELECT u FROM Stock u where (u.pkey = :stockId)";
	
	@Query(FIND_BY_STOCK_ID)
	public Stock findByStockId(@Param("stockId") long stockId);

}
