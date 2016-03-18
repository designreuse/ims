package com.ims.repository;


import javax.persistence.criteria.CriteriaBuilder;
import javax.persistence.criteria.CriteriaQuery;
import javax.persistence.criteria.Predicate;
import javax.persistence.criteria.Root;

import org.springframework.data.jpa.domain.Specification;

import com.ims.domain.entity.Product;
import com.ims.dto.ProductSearchTO;
import com.ims.dto.ProductTO;

public class ProductSpecifications {
	public static Specification<Product> productSearch(
			final ProductSearchTO productInputSearchTO) {
		return new Specification<Product>() {
			private String getLikePattern(final String searchTerm) {
				StringBuilder pattern = new StringBuilder();
				pattern.append(searchTerm.toLowerCase());
				pattern.append("%");
				return pattern.toString();
			}

			@Override
			public Predicate toPredicate(Root<Product> root,
					CriteriaQuery<?> query, CriteriaBuilder cb) {
				// This array to be incremented if any new field is added in
				// Search screen
				Predicate[] predicates = new Predicate[8];
				int i = 0;
				
				if (productInputSearchTO.getProductId() != null)
					predicates[i++] = cb.equal(root.get("productId"),
							productInputSearchTO.getProductId());
				
				if (productInputSearchTO.getModel() != null && productInputSearchTO.getModel() != "")
					predicates[i++] = cb.equal(root.get("model"),
							productInputSearchTO.getModel());
				
				if (productInputSearchTO.getQuantity() != null)
					predicates[i++] = cb.equal(root.get("quantity"),
							productInputSearchTO.getQuantity());
				
				if (productInputSearchTO.getStatus() != null)
					predicates[i++] = cb.equal(root.get("status"),
							productInputSearchTO.getStatus());
				
				if (productInputSearchTO.getPrice() != null)
					predicates[i++] = cb.equal(root.get("price"),
							productInputSearchTO.getPrice());
				
				if (productInputSearchTO.getSku() != null && productInputSearchTO.getSku() != "")
					predicates[i++] = cb.equal(root.get("sku"),
							productInputSearchTO.getSku());
				
				if (productInputSearchTO.getReorderQuantity() != null )
					predicates[i++] = cb.equal(root.get("reorderQuantity"),
							productInputSearchTO.getReorderQuantity());
				
				if (productInputSearchTO.getProductName() != null )
					predicates[i++] = cb.like(root.<String>get("productName"),
							"%"+productInputSearchTO.getProductName()+"%");
				
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
