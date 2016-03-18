package com.ims.service.impl;

import java.util.ArrayList;
import java.util.Date;
import java.util.Iterator;
import java.util.List;

import org.dozer.DozerBeanMapper;
import org.json.simple.JSONObject;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.core.env.Environment;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageImpl;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.data.web.PageableDefault;
import org.springframework.stereotype.Service;

import com.ims.dao.OrderDao;
import com.ims.dao.impl.OrderDaoImpl;
import com.ims.domain.entity.Order;
import com.ims.domain.entity.SaleDetail;
import com.ims.dto.OrderSearchTO;
import com.ims.dto.OrderTO;
import com.ims.dto.ProductOrderTO;
import com.ims.dto.ProductTO;
import com.ims.dto.SaleDetailTO;
import com.ims.repository.OrderRepository;
import com.ims.repository.OrderSpecifications;
import com.ims.repository.SaleRepository;
import com.ims.service.OrderService;
import com.ims.service.ProductOrderService;
import com.ims.service.ProductService;

@Service
public class OrderServiceImpl implements OrderService{

	@Autowired
	OrderRepository orderRepository;
	
	@Autowired
	public DozerBeanMapper mapper;
	
	@Autowired
	ProductOrderService productOrderService;
	
	@Autowired
	ProductService productService;
	
	@Autowired
	SaleRepository saleRepository;
	
	@Autowired
	Environment env;
	
	private List<OrderTO> constructList(Iterable<Order> orders) {
 		List<OrderTO> orderTOs = new ArrayList<OrderTO>();
		for (Order order : orders) {
			orderTOs.add(mapper.map(order, OrderTO.class));
		}
		return orderTOs;
	}

	
	@Override
	public Page<OrderTO> dataTableSearch(OrderSearchTO orderSearch,@PageableDefault(page = 0, value = 10)Pageable pageable) {
		// TODO Auto-generated method stub
		Page<Order> orders = orderRepository.findAll(OrderSpecifications.orderSearch(orderSearch), pageable);
		
		List<OrderTO> order = constructList(orders);
		
		return new PageImpl<>(order, new PageRequest(orders.getNumber(), orders.getSize(), orders.getSort()),
					orders.getTotalElements());
	}


	@Override
	public JSONObject updateQuantity() {
		// TODO Auto-generated method stub
		int quantity = 0;
		boolean isUpdate = false;
		JSONObject obj = new JSONObject();
		try{
			List<ProductOrderTO> orderDetails = getShoppingCartProductOrder();
			ProductTO productTO = null;
			int i = 0;
			List<OrderTO> orderTOs = getFullOrderInfo();
			for(OrderTO orderTO:orderTOs){
				OrderTO localOrderTO = getOrderInfo(orderTO.getOrderId());
				for(ProductOrderTO productOrderTO:orderDetails){
					if(orderTO.getOrderId().equals(productOrderTO.getOrderId())){
						productTO = productService.getProductInfo(productOrderTO.getProductId());
						System.out.println(orderTO.getOrderId()+""+productOrderTO.getOrderId());
						if(localOrderTO == null && productTO !=null){
								quantity = productTO.getQuantity() - productOrderTO.getQuantity();
								productTO.setQuantity(quantity);
								productService.updateQuantity(productTO);
								localOrderTO = orderTO;
								Order order = mapper.map(localOrderTO, Order.class);
								order.setFlag("true");
								orderRepository.save(order);
								i++;
								//productOrderService.deleteProductOrder(productOrderTO.getOrderProductId());
						} else if(productTO !=null){
							if(localOrderTO.getFlag() == null){
								quantity = productTO.getQuantity() - productOrderTO.getQuantity();
								productTO.setQuantity(quantity);
								productTO.setQuantity(quantity);
								productService.updateQuantity(productTO);
								Order order = mapper.map(localOrderTO, Order.class);
								order.setFlag("true");
								orderRepository.save(order);
								i++;
							}
						}
					}
				}
			}
			
			if(i>0){
				isUpdate = true;
			}
			
			obj.put("updateStatus", isUpdate);
			obj.put("totalProcess", i);
			return obj;
		}catch(Exception e){
			e.printStackTrace();
		}
		return new JSONObject();
	}

	@Override
	public OrderTO getOrderInfo(Integer orderId) {
		// TODO Auto-generated method stub
		Order order = orderRepository.findByOrderId(orderId);
		if(order != null){
			OrderTO orderTO = mapper.map(order, OrderTO.class);
			return orderTO;
		}else{
			return null;
		}
	}


	@Override
	public OrderTO getSalesOrder(int orderId) {
		String driver = env.getRequiredProperty("order.driver");
		String dbUrl = env.getRequiredProperty("order.jdbcurl");
		String userName = env.getRequiredProperty("order.dbuser");
		String pwd = env.getRequiredProperty("order.dbpass");
		// TODO Auto-generated method stub
		OrderDao orderDao = new OrderDaoImpl();
		OrderTO orderDetail = orderDao.getOrderInfo(driver, dbUrl, userName, pwd,orderId);
		return orderDetail;
	}

	@Override
	public List<OrderTO> getFullOrderInfo() {
		String driver = env.getRequiredProperty("order.driver");
		String dbUrl = env.getRequiredProperty("order.jdbcurl");
		String userName = env.getRequiredProperty("order.dbuser");
		String pwd = env.getRequiredProperty("order.dbpass");
		OrderDao orderDao = new OrderDaoImpl();
		List<OrderTO> orderDetails = orderDao.getFullOrderInfo(driver, dbUrl, userName,pwd);
		return orderDetails;
	}

	@Override
	public List<ProductOrderTO> getShoppingCartProductOrder() {
		// TODO Auto-generated method stub
		String driver = env.getRequiredProperty("order.driver");
		String dbUrl = env.getRequiredProperty("order.jdbcurl");
		String userName = env.getRequiredProperty("order.dbuser");
		String pwd = env.getRequiredProperty("order.dbpass");
		OrderDao orderDao = new OrderDaoImpl();
		
		int lastId = 0;
		
		List<Integer> ids = saleRepository.findSaleIds();
		
		if(!ids.isEmpty()){
			lastId = ids.get(ids.size()-1);
		} 
		
		List<ProductOrderTO> orderDetails = orderDao.getSalesOrderInfo(driver, dbUrl, userName, pwd,lastId);
		
		SaleDetailTO saleDetail = new SaleDetailTO();
		if(lastId != 0){
			lastId = lastId+orderDetails.size();
		}else{
			lastId = orderDetails.size();
		}
		
		saleDetail.setSalesId(lastId);
		SaleDetail sale = mapper.map(saleDetail, SaleDetail.class);
	
		Iterator<ProductOrderTO> iter = orderDetails.iterator();
		
		saleRepository.save(sale);
		return orderDetails;
	}
	
	@Override
	public List<ProductOrderTO> getLocalProductOrder() {
		// TODO Auto-generated method stub
		int lastId = productOrderService.getLastProductOrderId();
		
		List<ProductOrderTO> orderDetails = productOrderService.getProductOrdersInfo();
		return orderDetails;
	}
}
