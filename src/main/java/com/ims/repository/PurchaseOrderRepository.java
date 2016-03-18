package com.ims.repository;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.JpaSpecificationExecutor;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import com.ims.domain.entity.PurchaseOrder;

public interface PurchaseOrderRepository extends JpaRepository<PurchaseOrder, Long>, JpaSpecificationExecutor<PurchaseOrder> {

	public final static String FIND_BY_Datatable_Filter = "SELECT u FROM PurchaseOrder u where (u.pkey = :purchaseOrderId)";
	
	public final static String FIND_BY_ID = "SELECT u FROM PurchaseOrder u where (u.pkey = :purchaseOrderId)";
	
	@Query(FIND_BY_Datatable_Filter)
	public Page<PurchaseOrder> findByDatatableFilter(@Param("purchaseOrderId") int purchaseOrderId, Pageable pageable);
	
	@Query(FIND_BY_ID)
	public PurchaseOrder findByPurchaseOrderId(@Param("purchaseOrderId") Long purchaseOrderId);

}
