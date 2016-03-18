package com.ims.migrationData;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;

import com.ims.domain.entity.Product;
import com.ims.dto.ProductDescriptionTO;
import com.ims.dto.ProductTO;

public class SyncProduct {
	
	public static void main(String[] args) {
		
		String dbUrl = "jdbc:mysql://localhost:3306/maligakadai?zeroDateTimeBehavior=convertToNull";
		String userName = "root";
		String passWord = "root";
		List<ProductTO> products = new SyncProduct().getAllProducts(dbUrl, userName, passWord);
		
		
		System.out.println(products);
	}
	public List<ProductTO> getAllProducts(String dbUrl,String userName,String passWord){
		try{ 
		  Class.forName("com.mysql.jdbc.Driver").newInstance();
		
	      Connection conn = DriverManager.getConnection(dbUrl, userName, passWord);
	      
	      Statement statement = conn.createStatement();
	      
	      ResultSet rs = statement.executeQuery("SELECT * FROM oc_product");
    	  List<ProductTO> products = new ArrayList<ProductTO>();
	      while(rs.next()){
	    	  ProductTO product = new ProductTO();
	    	  product.setModel(rs.getString("model"));
		      product.setSku(rs.getString("sku"));
		      product.setUpc(rs.getString("upc"));
		      product.setEan(rs.getString("ean"));
		      product.setJan(rs.getString("jan"));
		      product.setIsbn(rs.getString("isbn"));
		      product.setMpn(rs.getString("mpn"));
		      product.setLocation(rs.getString("location"));
		      product.setQuantity(rs.getInt("quantity"));
		      product.setStockStatusId(rs.getInt("stock_status_id"));
		      product.setImage(rs.getString("image"));
		      product.setManufacturerId(rs.getInt("manufacturer_id"));
		      product.setShipping(rs.getInt("shipping"));
		      product.setPrice(rs.getBigDecimal("price"));
		      product.setPoints(rs.getInt("points"));
		      product.setTaxClassId(rs.getInt("tax_class_id"));
		      product.setDateAvailable(rs.getDate("date_available"));
		      product.setWeight(rs.getBigDecimal("weight"));
		      product.setWeightClassId(rs.getInt("weight_class_id"));
		      product.setLength(rs.getBigDecimal("length"));
		      product.setWidth(rs.getBigDecimal("width"));
		      product.setHeight(rs.getBigDecimal("height"));
		      product.setLengthClassId(rs.getInt("length_class_id"));
		      product.setSubtract(rs.getInt("subtract"));
		      product.setMinimum(rs.getInt("minimum"));
		      product.setSortOrder(rs.getInt("sort_order"));
		      product.setStatus(rs.getInt("status"));
		      product.setDateAdded(rs.getDate("date_added"));
		      product.setDateModified(rs.getDate("date_modified"));
		      product.setViewed(rs.getInt("viewed"));
		      product.setSodexo(rs.getInt("sodexo"));
	    	  products.add(product);
	      }
	      conn.close();
	      return products;
		}catch(Exception e){
			e.printStackTrace();
		}
		return new ArrayList<ProductTO>();
	}
	
	public List<ProductDescriptionTO> getAllProductDescriptionTOs(String dbUrl,String userName,String passWord){
		try{ 
			  Class.forName("com.mysql.jdbc.Driver").newInstance();
			
		      Connection conn = DriverManager.getConnection(dbUrl, userName, passWord);
		      
		      Statement statement = conn.createStatement();
		      
		      ResultSet rs = statement.executeQuery("SELECT * FROM oc_product_description");
	    	  List<ProductDescriptionTO> productDescriptions = new ArrayList<ProductDescriptionTO>();
		      while(rs.next()){
		    	  ProductDescriptionTO productDescription = new ProductDescriptionTO(); 
		    	  productDescription.setName(rs.getString("name"));
		    	  productDescription.setLanguageId(rs.getInt("language_id"));
		    	  productDescription.setDescription(rs.getString("description"));
		    	  productDescription.setMetaDescription(rs.getString("meta_description"));
		    	  productDescription.setMetaKeyword(rs.getString("meta_keyword"));
		    	  productDescription.setTag(rs.getString("tag"));
		    	  productDescription.setId(rs.getInt("product_id"));
		    	  productDescriptions.add(productDescription);
		      }
		      conn.close();
		      return productDescriptions;
			}catch(Exception e){
				e.printStackTrace();
			}
			return new ArrayList<ProductDescriptionTO>();
	}
	
	public ProductTO getProduct(String dbUrl,String userName,String passWord,int productId){
		try{ 
		  Class.forName("com.mysql.jdbc.Driver").newInstance();
		
	      Connection conn = DriverManager.getConnection(dbUrl, userName, passWord);
	      
	      PreparedStatement ps = conn.prepareStatement("SELECT * FROM oc_product where product_id=?");
	      ps.setInt(1, productId);
	      
	      ResultSet rs = ps.executeQuery();
    	  ProductTO product = new ProductTO();
	      while(rs.next()){
	    	  product.setProductId(rs.getInt("product_id"));
	    	  product.setModel(rs.getString("model"));
		      product.setSku(rs.getString("sku"));
		      product.setUpc(rs.getString("upc"));
		      product.setEan(rs.getString("ean"));
		      product.setJan(rs.getString("jan"));
		      product.setIsbn(rs.getString("isbn"));
		      product.setMpn(rs.getString("mpn"));
		      product.setLocation(rs.getString("location"));
		      product.setQuantity(rs.getInt("quantity"));
		      product.setStockStatusId(rs.getInt("stock_status_id"));
		      product.setImage(rs.getString("image"));
		      product.setManufacturerId(rs.getInt("manufacturer_id"));
		      product.setShipping(rs.getInt("shipping"));
		      product.setPrice(rs.getBigDecimal("price"));
		      product.setPoints(rs.getInt("points"));
		      product.setTaxClassId(rs.getInt("tax_class_id"));
		      product.setDateAvailable(rs.getDate("date_available"));
		      product.setWeight(rs.getBigDecimal("weight"));
		      product.setWeightClassId(rs.getInt("weight_class_id"));
		      product.setLength(rs.getBigDecimal("length"));
		      product.setWidth(rs.getBigDecimal("width"));
		      product.setHeight(rs.getBigDecimal("height"));
		      product.setLengthClassId(rs.getInt("length_class_id"));
		      product.setSubtract(rs.getInt("subtract"));
		      product.setMinimum(rs.getInt("minimum"));
		      product.setSortOrder(rs.getInt("sort_order"));
		      product.setStatus(rs.getInt("status"));
		      //product.setDateAdded(rs.getDate("date_added"));
		      //product.setDateModified(rs.getDate("date_modified"));
		      product.setViewed(rs.getInt("viewed"));
		      product.setSodexo(rs.getInt("sodexo"));
	      }
	      conn.close();
	      return product;
		}catch(Exception e){
			e.printStackTrace();
		}
		return new ProductTO();
	}
	
	public ProductDescriptionTO getProductDescriptionInfo(String dbUrl,String userName,String passWord,int productId){
		try{ 
		  Class.forName("com.mysql.jdbc.Driver").newInstance();
		
	      Connection conn = DriverManager.getConnection(dbUrl, userName, passWord);
	      
	      PreparedStatement ps = conn.prepareStatement("SELECT * FROM oc_product_description where product_id=?");
	      ps.setInt(1, productId);
	      
	      ResultSet rs = ps.executeQuery();
    	  ProductDescriptionTO productDescription = new ProductDescriptionTO(); 
	      while(rs.next()){
	    	 // productDescription.setLanguageId(rs.getInt("language_id"));
	    	  productDescription.setName(rs.getString("name"));
	      }
	      conn.close();
	      return productDescription;
		}catch(Exception e){
			e.printStackTrace();
		}
		return new ProductDescriptionTO();
	}
	
}
