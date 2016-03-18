package com.ims.service;

import java.util.List;

import org.json.simple.JSONObject;
import org.springframework.core.env.Environment;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

import com.ims.dto.OrderSearchTO;
import com.ims.dto.OrderTO;
import com.ims.dto.ProductOrderTO;

@Service
public interface OrderService {
	Page<OrderTO> dataTableSearch(OrderSearchTO orderSearch,Pageable pageble);
	JSONObject updateQuantity();
	OrderTO getOrderInfo(Integer orderId);
	OrderTO getSalesOrder(int orderId);
	List<ProductOrderTO> getShoppingCartProductOrder();
	List<OrderTO> getFullOrderInfo();
	List<ProductOrderTO> getLocalProductOrder();
}
