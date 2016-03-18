package com.ims.service;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

import com.ims.dto.ProductTO;
import com.ims.dto.StockSearchTO;
import com.ims.dto.StockTO;

@Service
public interface StockService {
	public Page<StockTO> dataTableSearch(StockSearchTO stockSearchTO,
			Pageable pageable);

	public StockTO register(StockTO stockTO);

	public void deleteStock(Long pkey);

	public StockTO getStockInfo(Long stockId);
	
	public StockTO updateStockDetails(StockTO stockTO);
}
