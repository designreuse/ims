package com.ims.service.impl;

import java.util.ArrayList;
import java.util.List;

import org.dozer.DozerBeanMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageImpl;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.data.web.PageableDefault;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.ims.domain.entity.Product;
import com.ims.domain.entity.ProductDescription;
import com.ims.domain.entity.Stock;
import com.ims.dto.ProductTO;
import com.ims.dto.StockSearchTO;
import com.ims.dto.StockTO;
import com.ims.repository.StockRepository;
import com.ims.repository.StockSpecifications;
import com.ims.service.ProductService;
import com.ims.service.StockService;

@Service
@Transactional
public class StockServiceImpl implements StockService{

	
	@Autowired
	public ProductService productService;
	
	@Autowired
	public StockRepository stockRepository;

	@Autowired
	public DozerBeanMapper mapper;

	
	@Override
	public Page<StockTO> dataTableSearch(StockSearchTO stockSearchTO, @PageableDefault(page = 0, value = 10) Pageable pageable) {
		Page<Stock> stocks = stockRepository.findAll(StockSpecifications.stockSearch(stockSearchTO), pageable);
		List<StockTO> stockTOs = new ArrayList<StockTO>();
		List<StockTO> stockSearch = new ArrayList<StockTO>();
		for (Stock stock : stocks) {
			StockTO stockTO = mapper.map(stock, StockTO.class);
			if(stockTO.getProductId()!=null){
				ProductTO product = productService.getProductInfo(Integer.parseInt(stockTO.getProductId()));
				if(product != null){
					stockTO.setProductName(product.getProductName());
				}
			}
			stockTOs.add(stockTO);
		}
		
		return new PageImpl<>(stockTOs, new PageRequest(stocks.getNumber(), stocks.getSize(), stocks.getSort()),
			stocks.getTotalElements());
	}
	
	@Override
	public StockTO register(StockTO stockTO){
		try{
			ProductTO product = productService.getProductInfo(Integer.parseInt(stockTO.getProductId()));
			if(product != null){
				stockTO.setProductName(product.getProductName());
			}
			
			Stock stock=mapper.map(stockTO,Stock.class);
			stockRepository.save(stock);
		} catch(Exception e){
			e.printStackTrace();
		}
		return null;
	}

	@Override
	@Transactional
	public void deleteStock(Long pkey){
		stockRepository.deleteByPkey(pkey);
	}

	@Override
	public StockTO getStockInfo(Long stockId) {
		// TODO Auto-generated method stub
		Stock stock = stockRepository.findByStockId(stockId);
		if (stock == null)
			return null;
		
		StockTO stockTO = mapper.map(stock, StockTO.class);	
		return stockTO;
	}

	@Override
	public StockTO updateStockDetails(StockTO stockTO) {
		// TODO Auto-generated method stub
		Stock stock = stockRepository.findByStockId(stockTO.getPkey());
		if(stock == null)
			return null;
		mapper.map(stockTO, stock);
		stockRepository.save(stock);
		return null;
	}
	
	
	
}
