package com.ims.dao;

import java.util.List;

import com.ims.dto.OrderSearchTO;
import com.ims.dto.OrderTO;
import com.ims.dto.ProductOrderTO;

public interface OrderDao {
	OrderTO getOrderInfo(String driver,String dbUrl,String userName,String passWord,int orderId);
	List<ProductOrderTO> getSalesOrderInfo(String driver,String dbUrl,String userName,String passWord,int size);
	List<OrderTO> getFullOrderInfo(String driver,String dbUrl,String userName,String passWord);
}
