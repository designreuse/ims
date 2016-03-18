package com.ims.repository;

import java.util.List;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.JpaSpecificationExecutor;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import com.ims.domain.entity.Product;

public interface ProductRepository extends JpaRepository<Product, Long>, JpaSpecificationExecutor<Product>  {

	public final static String FIND_BY_Datatable_Filter = "SELECT u FROM Product u where (u.productId = :productId)";
	
	public final static String FIND_ALL_Active = "SELECT u FROM Product u where u.status = 1";
	
	public final static String FIND_BY_ID = "SELECT u FROM Product u where (u.productId = :productId)";

	public final static String PRODUCT_IDS = "SELECT u.productId FROM Product u";
	
	public final static String PRODUCT_NAMES = "SELECT u.productName FROM Product u";
	
	public final static String FIND_BY_NAME = "SELECT u FROM Product u where (u.productDescription.name = :productName)";

	public final static String FIND_BY_PRODUCT_NAME = "SELECT u FROM Product u where (u.productName = :productName)";
	
	@Query(FIND_BY_Datatable_Filter)
	public Page<Product> findByDatatableFilter(@Param("productId") int productId, Pageable pageable);

	@Query(FIND_ALL_Active)
	public Page<Product> findAllActiveProducts(Pageable pageable);

	@Query(FIND_BY_ID)
	public Product findByProductId(@Param("productId") int productId);
	
	@Query(FIND_BY_NAME)
	public Product findByProductName(@Param("productName") String productName);
	
	@Query(PRODUCT_IDS)
	public List<Integer> getAllProductIds();
	
	@Query(PRODUCT_NAMES)
	public List<String> getAllProductNames();
	
	@Query(FIND_BY_PRODUCT_NAME)
	public Product findByName(@Param("productName") String productName);
	
}
