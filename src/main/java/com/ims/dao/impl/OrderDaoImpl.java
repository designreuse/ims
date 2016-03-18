package com.ims.dao.impl;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.Statement;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.Date;
import java.util.List;

import com.ims.dao.OrderDao;
import com.ims.dto.OrderTO;
import com.ims.dto.ProductOrderTO;


public class OrderDaoImpl implements OrderDao{
	
	public List getFullOrderInfo(String driver,String dbUrl,String userName,String passWord){
		try{ 
		  Class.forName("com.mysql.jdbc.Driver").newInstance();
		
	      Connection conn = DriverManager.getConnection(dbUrl, userName, passWord);
	      
	      Date startDate = new Date();
	      SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd");
	      String end = sdf.format(startDate);
	      String start = null;
	      String[] dateInfo = end.split("-");
	      int month = 0;
	      if(dateInfo.length > 2){
	    	  month = Integer.parseInt(dateInfo[1]);
	      }
	      if(month != 0)
	    	  start = dateInfo[0]+"-"+(month-1)+"-"+dateInfo[2];
	      
	      PreparedStatement ps = conn.prepareStatement("SELECT * FROM oc_order where (date_added between ? and ?) and (order_status_id=5)");
	      ps.setString(1, start);
	      ps.setString(2, end);
	      
	      ResultSet rs = ps.executeQuery();
	      List<OrderTO> orderList = new ArrayList<OrderTO>();
	      
	      while(rs.next()){
	    	  OrderTO order = new OrderTO();
	    	  order.setOrderId(rs.getInt("order_id"));
	    	  order.setStoreId(rs.getInt("store_id"));
	    	  order.setStoreName(rs.getString("store_name"));
	    	  order.setCustomerId(rs.getInt("customer_id"));
	    	  order.setCustomerGroupId(rs.getInt("customer_group_id"));
	    	  order.setFirstName(rs.getString("firstname"));
	    	  order.setEmail(rs.getString("email"));
	    	  order.setTelephone( rs.getString("telephone"));
	    	  order.setPaymentFirstName(rs.getString("payment_firstname"));
	    	  order.setShippingFirstName(rs.getString("shipping_firstname"));
	    	  order.setShippingCompany(rs.getString("shipping_company"));
	    	  order.setOrderStatusId(rs.getInt("order_status_id"));
	    	  order.setInvoicePrefix(rs.getString("invoice_prefix"));
	    	  order.setPaymentMethod(rs.getString("payment_method"));
	    	  order.setShippingCountry(rs.getString("shipping_country"));
	    	  order.setShippingZone(rs.getString("shipping_zone"));
	    	  order.setShippingAddressFormat(rs.getString("shipping_address_format"));
	    	  order.setShippingMethod(rs.getString("shipping_method"));
	    	  order.setCurrencyCode(rs.getString("currency_code"));
	    	  order.setIp(rs.getString("ip"));
	    	  order.setUserAgent(rs.getString("user_agent"));
	    	  order.setAcceptLanguage(rs.getString("accept_language"));
	    	  order.setDateAdded(rs.getDate("date_added"));
	    	  orderList.add(order);
	      }
	      conn.close();
	      return orderList;
		}catch(Exception e){
			e.printStackTrace();
		}
		return null;
	}
	
	public OrderTO getOrderInfo(String driver,String dbUrl,String userName,String passWord,int orderId){
		try{ 
		  Class.forName("com.mysql.jdbc.Driver").newInstance();
		
	      Connection conn = DriverManager.getConnection(dbUrl, userName, passWord);
	      PreparedStatement stmt = conn.prepareStatement("SELECT * FROM oc_order where order_id=?");
	      stmt.setInt(1,orderId);
	      ResultSet rs = stmt.executeQuery();
	      OrderTO order = new OrderTO();
	      while(rs.next()){
	    	  order.setOrderId(rs.getInt("order_id"));
	    	  order.setStoreId(rs.getInt("store_id"));
	    	  order.setStoreName(rs.getString("store_name"));
	    	  //order.setStoteUrl(rs.getString("store_url"));
	    	  order.setCustomerId(rs.getInt("customer_id"));
	    	  order.setCustomerGroupId(rs.getInt("customer_group_id"));
	    	  order.setFirstName(rs.getString("firstname"));
	    	  order.setEmail(rs.getString("email"));
	    	  order.setTelephone( rs.getString("telephone"));
	    	  order.setPaymentFirstName(rs.getString("payment_firstname"));
	    	  order.setShippingFirstName(rs.getString("shipping_firstname"));
	    	  order.setShippingCompany(rs.getString("shipping_company"));
	    	  order.setOrderStatusId(rs.getInt("order_status_id"));
	    	  order.setInvoicePrefix(rs.getString("invoice_prefix"));
	    	  order.setPaymentMethod(rs.getString("payment_method"));
	    	  order.setShippingCountry(rs.getString("shipping_country"));
	    	  order.setShippingZone(rs.getString("shipping_zone"));
	    	  order.setShippingAddressFormat(rs.getString("shipping_address_format"));
	    	  order.setShippingMethod(rs.getString("shipping_method"));
	    	  order.setCurrencyCode(rs.getString("currency_code"));
	    	  order.setIp(rs.getString("ip"));
	    	  order.setUserAgent(rs.getString("user_agent"));
	    	  order.setAcceptLanguage(rs.getString("accept_language"));
	    	  break;
	      }
	      conn.close();
	      return order;
		}catch(Exception e){
			e.printStackTrace();
		}
		return null;
	}

	@Override
	public List<ProductOrderTO> getSalesOrderInfo(String driver, String dbUrl,
			String userName, String passWord,int id) {
		// TODO Auto-generated method stub
		try{ 
			  Class.forName("com.mysql.jdbc.Driver").newInstance();
			  
		      Connection conn = DriverManager.getConnection(dbUrl, userName, passWord);
		      String sql = null;
		      ResultSet rs = null;
		      PreparedStatement ps = conn.prepareStatement("SELECT * FROM oc_order_product where order_product_id > ?");
		      ps.setInt(1, id);
		      rs = ps.executeQuery();
		      List<ProductOrderTO> productOrderTOs = new ArrayList<ProductOrderTO>();
		      while(rs.next()){
		    	  ProductOrderTO order = new ProductOrderTO();
		    	  order.setOrderProductId(rs.getInt("order_product_id"));
		    	  order.setOrderId(rs.getInt("order_id"));
		    	  order.setProductId(rs.getInt("product_id"));
		    	  order.setName(rs.getString("name"));
		    	  order.setModel(rs.getString("model"));
		    	  order.setQuantity(rs.getInt("quantity"));
		    	  order.setPrice(rs.getBigDecimal("price"));
		    	  order.setTotal(rs.getBigDecimal("total"));
		    	  order.setTax(rs.getBigDecimal("tax"));
		    	  order.setReward(rs.getInt("reward"));
		    	  productOrderTOs.add(order);
		    	  rs.getBigDecimal("yousave");
		    	  rs.getBigDecimal("yousavetot");
		      }
		      conn.close();
		      return productOrderTOs;
			}catch(Exception e){
				e.printStackTrace();
			}
			return null;
	}
}
