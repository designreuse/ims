package com.ims.service;

import java.util.List;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

import com.ims.dto.ProductDescriptionTO;
import com.ims.dto.ProductSearchTO;
import com.ims.dto.ProductTO;

@Service
public interface ProductService {

	public Page<ProductTO> getAllActiveProducts(Pageable pageable);

	public Page<ProductTO> dataTableSearch(ProductSearchTO productSearchTO, Pageable pageable);

	public ProductTO getProductInfo(int productId);

	public ProductTO updateProductDetails(ProductTO productTO);
	
	public ProductTO register(ProductTO productTO);
	
	public void deleteProduct(Integer productId);
	
	public ProductTO getProductByName(String name);
	
	public void updateQuantity(ProductTO productTO);

	public List<String> getAllProductNames();
	
	public ProductDescriptionTO getProductDescription(String productName);
	
	public List<ProductDescriptionTO> getProductDescriptions(String productName);
	
	public ProductTO addProduct();
}