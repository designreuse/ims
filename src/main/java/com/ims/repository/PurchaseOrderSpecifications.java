package com.ims.repository;


import javax.persistence.criteria.CriteriaBuilder;
import javax.persistence.criteria.CriteriaQuery;
import javax.persistence.criteria.ParameterExpression;
import javax.persistence.criteria.Predicate;
import javax.persistence.criteria.Root;

import org.springframework.data.jpa.domain.Specification;

import com.ims.domain.entity.PurchaseOrder;
import com.ims.dto.PurchaseOrderSearchTO;

public class PurchaseOrderSpecifications {
	public static Specification<PurchaseOrder> purchaseOrderSearch(
			final PurchaseOrderSearchTO purchaseOrderInputSearchTO) {
		return new Specification<PurchaseOrder>() {
			private String getLikePattern(final String searchTerm) {
				StringBuilder pattern = new StringBuilder();
				pattern.append(searchTerm.toLowerCase());
				pattern.append("%");
				return pattern.toString();
			}

			@Override
			public Predicate toPredicate(Root<PurchaseOrder> root,
					CriteriaQuery<?> query, CriteriaBuilder cb) {
				// This array to be incremented if any new field is added in
				// Search screen
				Predicate[] predicates = new Predicate[8];
				int i = 0;
				
				if (purchaseOrderInputSearchTO.getTotal() != null)
					predicates[i++] = cb.equal(root.get("total"),
							purchaseOrderInputSearchTO.getTotal());
				if (purchaseOrderInputSearchTO.getDescription() != null)
					predicates[i++] = cb.equal(root.get("description"),
							purchaseOrderInputSearchTO.getDescription());
				
				if (purchaseOrderInputSearchTO.getName() != null){
					predicates[i++] = cb.like(root.<String>get("name"),"%"+purchaseOrderInputSearchTO.getName()+"%");
				}
				
				if (purchaseOrderInputSearchTO.getPkey() != null)
					predicates[i++] = cb.equal(root.get("pkey"),
							purchaseOrderInputSearchTO.getPkey());
			
				if (purchaseOrderInputSearchTO.getVendorName() != null)
					predicates[i++] = cb.equal(root.get("vendorName"),
							purchaseOrderInputSearchTO.getVendorName());
			
				
				if (i != predicates.length) {
					for (int j = i; j < predicates.length; j++) {
						predicates[j] = cb.conjunction();
					}
				}
				return cb.and(predicates);
			}
		};
	}

}
