package com.ims.repository;

import javax.persistence.criteria.CriteriaBuilder;
import javax.persistence.criteria.CriteriaQuery;
import javax.persistence.criteria.Predicate;
import javax.persistence.criteria.Root;

import org.springframework.data.jpa.domain.Specification;

import com.ims.domain.entity.Stock;
import com.ims.dto.StockSearchTO;

public class StockSpecifications {
	public static Specification<Stock> stockSearch(
			final StockSearchTO stockInputSearchTO) {
		return new Specification<Stock>() {
			private String getLikePattern(final String searchTerm) {
				StringBuilder pattern = new StringBuilder();
				pattern.append(searchTerm.toLowerCase());
				pattern.append("%");
				return pattern.toString();
			}

			@Override
			public Predicate toPredicate(Root<Stock> root,
					CriteriaQuery<?> query, CriteriaBuilder cb) {
				// This array to be incremented if any new field is added in
				// Search screen
				Predicate[] predicates = new Predicate[8];
				int i = 0;
				
				if (stockInputSearchTO.getPkey() != null)
					predicates[i++] = cb.equal(root.get("pkey"),
							stockInputSearchTO.getPkey());
				
				if (stockInputSearchTO.getProductId()!=null)
					predicates[i++] = cb.equal(root.get("productId"),
							stockInputSearchTO.getProductId());
				
				if (stockInputSearchTO.getCurrentStock() != null )
					predicates[i++] = cb.equal(root.get("currentStock"),
							stockInputSearchTO.getCurrentStock());
				
				if (stockInputSearchTO.getProductName()!= null)
					predicates[i++] = cb.equal(root.get("productName"),
							stockInputSearchTO.getProductId());
				
				if (stockInputSearchTO.getStockValue() != null)
					predicates[i++] = cb.equal(root.get("stockValue"),
							stockInputSearchTO.getStockValue());
				
				
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
