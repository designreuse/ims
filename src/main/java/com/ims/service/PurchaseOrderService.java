package com.ims.service;

import java.util.List;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

import com.ims.dto.ProductDescriptionTO;
import com.ims.dto.PurchaseOrderSearchTO;
import com.ims.dto.PurchaseOrderTO;

@Service
public interface PurchaseOrderService {

	public Page<PurchaseOrderTO> getAllPurchaseOrder(Pageable pageable);

	public Page<PurchaseOrderTO> dataTableSearch(PurchaseOrderSearchTO purchaseOrderSearch, Pageable pageable);

	public PurchaseOrderTO getPurchaseOrderInfo(Long id);

	public PurchaseOrderTO updatePurchaseOrderDetails(PurchaseOrderTO purchaseOrderTO);

	public void deleteOrder(Integer pkey);
	
	public List<Integer> getProductDeatails();
	
	public PurchaseOrderTO register(PurchaseOrderTO orderTO);
}
