package com.ims.service.impl;

import java.util.ArrayList;
import java.util.List;

import org.dozer.DozerBeanMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageImpl;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.ims.domain.entity.Product;
import com.ims.domain.entity.PurchaseOrder;
import com.ims.dto.ProductDescriptionTO;
import com.ims.dto.PurchaseOrderSearchTO;
import com.ims.dto.PurchaseOrderTO;
import com.ims.repository.ProductDescriptionRepository;
import com.ims.repository.ProductRepository;
import com.ims.repository.PurchaseOrderRepository;
import com.ims.repository.PurchaseOrderSpecifications;
import com.ims.service.PurchaseOrderService;

@Service
@Transactional
public class PurchaseOrderServiceImpl implements PurchaseOrderService {

	@Autowired
	public ProductRepository productRepository;
	
	@Autowired
	public ProductDescriptionRepository productDescriptionRepository;

	@Autowired
	public PurchaseOrderRepository purchaseOrderRepository;
	
	@Autowired
	public DozerBeanMapper mapper;

	@Override
	public Page<PurchaseOrderTO> getAllPurchaseOrder(Pageable pageable) {
		Page<PurchaseOrder> purchaseOrders = purchaseOrderRepository
				.findAll(pageable);
		List<PurchaseOrderTO> purchaseOrderTOs = new ArrayList<PurchaseOrderTO>();
		PurchaseOrderTO purchaseOrderTO = null;
		for (PurchaseOrder purchaseOrder : purchaseOrders.getContent()) {
			purchaseOrderTO = mapper.map(purchaseOrder,PurchaseOrderTO.class);
			purchaseOrderTOs.add(purchaseOrderTO);
		}
		return new PageImpl<>(purchaseOrderTOs, new PageRequest(
				purchaseOrders.getNumber(), purchaseOrders.getSize(),
				purchaseOrders.getSort()), purchaseOrders.getTotalElements());
	}

	@Override
	public Page<PurchaseOrderTO> dataTableSearch(PurchaseOrderSearchTO purchaseOrderSearch, Pageable pageable) {
		Page<PurchaseOrder> purchaseOrders = purchaseOrderRepository.findAll(PurchaseOrderSpecifications.purchaseOrderSearch(purchaseOrderSearch), pageable);
		List<PurchaseOrderTO> purchaseOrderTOs = new ArrayList<PurchaseOrderTO>();
		PurchaseOrderTO purchaseOrderTO = null;
		System.out.println(purchaseOrders.getTotalPages());
		for (PurchaseOrder purchaseOrder : purchaseOrders.getContent()) {
			purchaseOrderTO = mapper.map(purchaseOrder,PurchaseOrderTO.class);
			purchaseOrderTOs.add(purchaseOrderTO);
		}
		return new PageImpl<>(purchaseOrderTOs, new PageRequest(
				purchaseOrders.getNumber(), purchaseOrders.getSize(),
				purchaseOrders.getSort()), purchaseOrders.getTotalElements());
	}

	@Override
	public PurchaseOrderTO getPurchaseOrderInfo(Long id) {
		PurchaseOrder purchaseOrder = purchaseOrderRepository.findOne(id);
		if (purchaseOrder == null)
			return null;

		PurchaseOrderTO purchaseOrderTO = mapper.map(purchaseOrder,
				PurchaseOrderTO.class);
		return purchaseOrderTO;
	}

	@Override
	public PurchaseOrderTO updatePurchaseOrderDetails(
			PurchaseOrderTO purchaseOrderTO) {
		PurchaseOrder purchaseOrder = purchaseOrderRepository
				.findByPurchaseOrderId(purchaseOrderTO.getPkey());
		
		if (purchaseOrder != null) {
			mapper.map(purchaseOrderTO, purchaseOrder);
			purchaseOrderRepository.save(purchaseOrder);
		}
		return purchaseOrderTO;
	}

	@Override
	@Transactional
	public void deleteOrder(Integer pkey){
		PurchaseOrder order = purchaseOrderRepository.findByPurchaseOrderId(Long.valueOf(pkey));		
		purchaseOrderRepository.delete(order);
	}

	
	@Override
	public PurchaseOrderTO register(PurchaseOrderTO orderTO) {
		// TODO Auto-generated method stub
		PurchaseOrder order = mapper.map(orderTO, PurchaseOrder.class);
		Product product = productRepository.findByProductId(Integer.valueOf(orderTO.getId()));
		if (product == null) {
			// TODO throw a valid exception			
		} else {
			order.setProduct(product);
			order.setName(product.getProductName());
			purchaseOrderRepository.save(order);
			int quantity = product.getQuantity();
			if(orderTO.getTotal() != null){
				product.setQuantity(quantity+Integer.valueOf(orderTO.getTotal().toString()));
			}
			
			productRepository.save(product);
		}
		return null;
	}

	@Override
	public List<Integer> getProductDeatails() {
		// TODO Auto-generated method stub
		List<Integer> ids = productRepository.getAllProductIds();
		return ids;
	}

}