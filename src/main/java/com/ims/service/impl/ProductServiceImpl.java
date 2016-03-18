package com.ims.service.impl;

import java.util.ArrayList;
import java.util.List;

import org.dozer.DozerBeanMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.core.env.Environment;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageImpl;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.data.web.PageableDefault;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.ims.domain.entity.Product;
import com.ims.domain.entity.ProductDescription;
import com.ims.dto.ProductDescriptionTO;
import com.ims.dto.ProductSearchTO;
import com.ims.dto.ProductTO;
import com.ims.migrationData.MigrationProductDescription;
import com.ims.migrationData.SyncProduct;
import com.ims.repository.ProductDescriptionRepository;
import com.ims.repository.ProductRepository;
import com.ims.repository.ProductSpecifications;
import com.ims.service.ProductService;

@Service
@Transactional
public class ProductServiceImpl implements ProductService {
	
	@Autowired
	public ProductRepository productRepository;

	@Autowired
	public ProductDescriptionRepository productDescriptionRepository;
	
	@Autowired
	public DozerBeanMapper mapper;
	
	@Autowired
	public Environment env;

	private List<ProductTO> constructList(Iterable<Product> products) {
 		List<ProductTO> productTOs = new ArrayList<ProductTO>();
		for (Product product : products) {
			productTOs.add(mapper.map(product, ProductTO.class));
		}
		return productTOs;
	}
	
	@Override
	public Page<ProductTO> dataTableSearch(ProductSearchTO productSearchTO, @PageableDefault(page = 0, value = 10) Pageable pageable) {
		Page<Product> products = productRepository.findAll(ProductSpecifications.productSearch(productSearchTO),pageable);
		
		List<ProductTO> productTOs = constructList(products);
		/*List<ProductTO> productSearch = new ArrayList<ProductTO>();
		List<ProductTO> pro = new ArrayList<ProductTO>();*/
		/*for(Product product : products.getContent()){
			ProductTO productTO = mapper.map(product, ProductTO.class);
			ProductDescriptionTO productDescription = getProductDescription(product.getProductId());
			if(productDescription != null && productDescription.getName() != null) {
				productTO.setProductName(productDescription.getName());
			}
			pro.add(productTO);
		}*/
		/*ProductTO searchProduct = null;
		List<ProductDescriptionTO> productdesc = getProductDescriptions(productSearchTO.getProductName());
		for(ProductDescriptionTO productDescriptionTO : productdesc) {
			Product product = productRepository.findByProductId(productDescriptionTO.getId());
			if(product != null){
				searchProduct = mapper.map(product, ProductTO.class);
				ProductDescriptionTO productDescription = getProductDescription(product.getProductId());
				if(productDescription != null && productDescription.getName() != null) {
					searchProduct.setProductName(productDescription.getName());			
				}
				productSearch.add(searchProduct);
			}
		}*/		
		//Page<ProductTO> productPage = null;
		/*if(searchProduct != null || productSearchTO.getProductName()!=null){
			productPage = new PageImpl<ProductTO>(productSearch,pageable,productSearch.size());
			return new PageImpl<>(productSearch, new PageRequest(productPage.getNumber(), productPage.getSize(), productPage.getSort()),
					productPage.getTotalElements());
		}else{*/
			//productPage = new PageImpl<ProductTO>(pro,pageable,productTOs.size());
			return new PageImpl<>(productTOs, new PageRequest(products.getNumber(), products.getSize(), products.getSort()),
					products.getTotalElements());
		//}
	}

	@Override
	public Page<ProductTO> getAllActiveProducts(@PageableDefault(page = 0, value = 10) Pageable pageable) {
		Page<Product> products = productRepository.findAllActiveProducts(pageable);
		List<ProductTO> productTOs = new ArrayList<ProductTO>();
		System.out.println(products.getTotalPages());
		for (Product product : products.getContent()) {
			ProductTO productTO = mapper.map(product, ProductTO.class);
			ProductDescription productDescription = productDescriptionRepository.findByProductId(product.getProductId());
			if(productDescription != null && productDescription.getName() != null) {
				productTO.setProductName(productDescription.getName());			
			}
			productTOs.add(productTO);
		}
		return new PageImpl<>(productTOs, new PageRequest(products.getNumber(), products.getSize(), products.getSort()),
				products.getTotalElements());
	}

	@Override
	public ProductTO getProductInfo(int productId) {
		Product product = productRepository.findByProductId(productId);
		if (product == null)
			return null;
		
		ProductTO productTO = mapper.map(product, ProductTO.class);	
		/*ProductDescriptionTO productDescription = getProductDescription(product.getProductId());
		if(productDescription!=null)
			productTO.setProductName(productDescription.getName());*/
		return productTO;
	}
	
	@Override
	public ProductTO updateProductDetails(ProductTO productTO){
		productTO.setStatus(1);
		Product product = productRepository.findByProductId(productTO.getProductId());
		if(product == null)
			return null;
		mapper.map(productTO, product);
		productRepository.save(product);
		return null;
	}

	@Override
	public ProductTO register(ProductTO productTO) {
		// TODO Auto-generated method stub
		Product product=mapper.map(productTO,Product.class);
		
		ProductDescription productDescription = new ProductDescription();
		productDescription.setProduct(product);
		productDescription.setName(productTO.getProductName());
		product.setProductDescription(productDescription);
		productRepository.save(product);
		return null;
	}

	@Override
	@Transactional
	public void deleteProduct(Integer productId) {
		// TODO Auto-generated method stub
		Product product = productRepository.findByProductId(productId);
		productRepository.delete(product);
	}

	@Override
	public ProductTO getProductByName(String name) {
		// TODO Auto-generated method stub
		//Product product = productRepository.findByProductName(name);
		Product product = productRepository.findByName(name);
		if (product == null)
			return null;
		ProductTO productTO = mapper.map(product, ProductTO.class);	
		return productTO;
	}

	@Override
	public void updateQuantity(ProductTO productTO) {
		// TODO Auto-generated method stub
		Product product=mapper.map(productTO, Product.class);
		productRepository.save(product);
	}
	
	public ProductDescriptionTO getProductDescription(int productId){
		String dbUrl = env.getRequiredProperty("persistence.jdbcurl");
		String userName = env.getRequiredProperty("persistence.dbuser");
		String pwd = env.getRequiredProperty("persistence.dbpass");
		
		MigrationProductDescription migration = new MigrationProductDescription();
		return migration.getProductDescriptionInfo(dbUrl, userName, pwd, productId);
	}
	
	@Override
	public ProductDescriptionTO getProductDescription(String productName){
		String dbUrl = env.getRequiredProperty("persistence.jdbcurl");
		String userName = env.getRequiredProperty("persistence.dbuser");
		String pwd = env.getRequiredProperty("persistence.dbpass");
		
		MigrationProductDescription migration = new MigrationProductDescription();
		return migration.getProductDescriptionInfo(dbUrl, userName, pwd, productName);
	}
	
	@Override
	public List<ProductDescriptionTO> getProductDescriptions(String productName){
		String dbUrl = env.getRequiredProperty("persistence.jdbcurl");
		String userName = env.getRequiredProperty("persistence.dbuser");
		String pwd = env.getRequiredProperty("persistence.dbpass");
		
		MigrationProductDescription migration = new MigrationProductDescription();
		return migration.getProductDescriptionInfos(dbUrl, userName, pwd, productName);
	}
	
	@Override
	public List<String> getAllProductNames(){
		List<String> productNames = productRepository.getAllProductNames();
		List<String> names = new ArrayList<String>();
		for(String name:productNames){
			names.add((name+"^"));
		}
		return names;
	}

	@Override
	public ProductTO addProduct() {
		// TODO Auto-generated method stub
		String dbUrl = env.getRequiredProperty("order.jdbcurl");
		String userName = env.getRequiredProperty("order.dbuser");
		String pwd = env.getRequiredProperty("order.dbpass");
		
		//String localDbUrl = env.getRequiredProperty("persistence.jdbcurl");
		//String localUserName = env.getRequiredProperty("persistence.dbuser");
		//String localPwd = env.getRequiredProperty("persistence.dbpass");
		
		SyncProduct productSync = new SyncProduct();
		List<ProductDescriptionTO> productDescriptions = productSync.getAllProductDescriptionTOs(dbUrl, userName, pwd);
		//MigrationProductDescription migration = new MigrationProductDescription();
		for(ProductDescriptionTO productDescriptionTO:productDescriptions){
			Product localProductInfo = productRepository.findByName(productDescriptionTO.getName()); 
			if(localProductInfo == null){
				ProductTO productTO = productSync.getProduct(dbUrl, userName, pwd, productDescriptionTO.getId());
				productTO.setProductName(productDescriptionTO.getName());
				Product product = mapper.map(productTO, Product.class);
				productRepository.save(product);
			}
		}
		
		return null;
	}
}
