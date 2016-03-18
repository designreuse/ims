package com.ims.service.impl;

import java.util.ArrayList;
import java.util.List;

import org.dozer.DozerBeanMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.ims.domain.entity.ProductOrder;
import com.ims.dto.ProductOrderTO;
import com.ims.repository.ProductOrderRepository;
import com.ims.service.ProductOrderService;

@Service
public class ProductOrderServiceImpl implements ProductOrderService{
	
	@Autowired
	ProductOrderRepository productOrderRepository;
	
	@Autowired
	public DozerBeanMapper mapper;
	
	@Override
	public void saveProductOrder(ProductOrderTO productOrderTO){
		ProductOrder productOrder=mapper.map(productOrderTO, ProductOrder.class);
		productOrderRepository.save(productOrder);
	}

	@Override
	public int getLastProductOrderId() {
		// TODO Auto-generated method stub
		List<ProductOrder> productOrder = productOrderRepository.getAllOrderDetails();
		int id = 0;
		if(!productOrder.isEmpty()){
			ProductOrder product = productOrder.get((productOrder.size()-1));
			ProductOrderTO productOrderTO = mapper.map(product,ProductOrderTO.class);
			id = productOrderTO.getOrderProductId();
		}
		return id;
	}

	@Override
	public void deleteProductOrder(Integer productOrderId){
		ProductOrder productOrder = productOrderRepository.findByProductOrderId(productOrderId);
		productOrderRepository.delete(productOrder);
	}
	
	@Override
	public List<ProductOrderTO> getProductOrdersInfo() {
		// TODO Auto-generated method stub
		List<ProductOrder> productOrders = productOrderRepository.getAllOrderDetails();
		List<ProductOrderTO> productOrderTOList = new ArrayList<ProductOrderTO>();
		for(ProductOrder productOrder:productOrders){
			ProductOrderTO productOrderTO = mapper.map(productOrder, ProductOrderTO.class);
			productOrderTOList.add(productOrderTO);
		}
		return productOrderTOList;
	}
}
