package com.ims.service;

import java.util.List;

import com.ims.domain.entity.ProductOrder;
import com.ims.dto.ProductOrderTO;

public interface ProductOrderService {
	void saveProductOrder(ProductOrderTO productOrder);
	int getLastProductOrderId();
	List<ProductOrderTO> getProductOrdersInfo();
	void deleteProductOrder(Integer productId);
}
