package com.ims.repository;
import javax.persistence.criteria.CriteriaBuilder;
import javax.persistence.criteria.CriteriaQuery;
import javax.persistence.criteria.Predicate;
import javax.persistence.criteria.Root;

import org.springframework.data.jpa.domain.Specification;

import com.ims.domain.entity.Order;
import com.ims.dto.OrderSearchTO;


public class OrderSpecifications {
	public static Specification<Order> orderSearch(
			final OrderSearchTO orderInputSearchTO) {
		return new Specification<Order>() {
			private String getLikePattern(final String searchTerm) {
				StringBuilder pattern = new StringBuilder();
				pattern.append(searchTerm.toLowerCase());
				pattern.append("%");
				return pattern.toString();
			}
		
			@Override
			public Predicate toPredicate(Root<Order> root,
					CriteriaQuery<?> query, CriteriaBuilder cb) {
				// TODO Auto-generated method stub
				Predicate[] predicates = new Predicate[8];
				int i = 0;
				if (orderInputSearchTO.getOrderId() != null)
					predicates[i++] = cb.equal(root.get("orderId"),
							orderInputSearchTO.getOrderId());
				
				if (orderInputSearchTO.getCustomerId() != null)
					predicates[i++] = cb.equal(root.get("customerId"),
							orderInputSearchTO.getCustomerId());
				
				if (orderInputSearchTO.getFirstName() != null)
					predicates[i++] = cb.equal(root.get("firstName"),
							orderInputSearchTO.getFirstName());
				
				if (orderInputSearchTO.getFirstName() != null)
					predicates[i++] = cb.equal(root.get("firstName"),
							orderInputSearchTO.getFirstName());

				if(orderInputSearchTO.getTotal() != null){
					predicates[i++] = cb.equal(root.get("total"),
							orderInputSearchTO.getTotal());
				}
				
				if(orderInputSearchTO.getShippingFirstName() != null){
					predicates[i++] = cb.equal(root.get("shippingFirstName"),
							orderInputSearchTO.getShippingFirstName());
				}
				
				if(orderInputSearchTO.getStoreName() != null){
					predicates[i++] = cb.equal(root.get("storeName"),
							orderInputSearchTO.getStoreName());
				}
				
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
