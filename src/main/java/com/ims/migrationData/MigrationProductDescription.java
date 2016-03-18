package com.ims.migrationData;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;

import com.ims.dto.ProductDescriptionTO;

public class MigrationProductDescription {
	public ProductDescriptionTO getProductDescriptionInfo(String dbUrl,String userName,String passWord,int productId){
		try{ 
		  Class.forName("com.mysql.jdbc.Driver").newInstance();
		
	      Connection conn = DriverManager.getConnection(dbUrl, userName, passWord);
	      
	      PreparedStatement ps = conn.prepareStatement("SELECT * FROM ims_product_description where product_id=?");
	      ps.setInt(1, productId);
	      
	      ResultSet rs = ps.executeQuery();
    	  ProductDescriptionTO productDescription = new ProductDescriptionTO(); 
	      while(rs.next()){
	    	  productDescription.setId(rs.getInt("product_id"));
	    	  productDescription.setLanguageId(rs.getInt("language_id"));
	    	  productDescription.setName(rs.getString("name"));
	    	  break;
	      }
	      conn.close();
	      return productDescription;
		}catch(Exception e){
			e.printStackTrace();
		}
		return new ProductDescriptionTO();
	}
	
	public List<String> getAllProductNames(String dbUrl,String userName,String passWord){
		try{ 
			  Class.forName("com.mysql.jdbc.Driver").newInstance();
			
		      Connection conn = DriverManager.getConnection(dbUrl, userName, passWord);
		      
		      String sql = "SELECT * FROM ims_product_description";
		      Statement ps = conn.createStatement();
		      
		      ResultSet rs = ps.executeQuery(sql);
	    	  List<String> names = new ArrayList<String>();
		      while(rs.next()){
		    	names.add(rs.getString("name").trim()+"^");
		      }
		      conn.close();
		      return names;
		   }catch(Exception e){
				e.printStackTrace();
			}
		return new ArrayList<String>();	
	}
	
	public ProductDescriptionTO getProductDescriptionInfo(String dbUrl,String userName,String passWord,String productName){
		try{ 
		  Class.forName("com.mysql.jdbc.Driver").newInstance();
		
	      Connection conn = DriverManager.getConnection(dbUrl, userName, passWord);
	      
	      PreparedStatement ps = conn.prepareStatement("SELECT * FROM ims_product_description where name=?");
	      ps.setString(1, productName);
	      
	      ResultSet rs = ps.executeQuery();
    	  ProductDescriptionTO productDescription = new ProductDescriptionTO(); 
	      while(rs.next()){
	    	  /*productDescription.setId(rs.getInt("product_id"));
	    	  productDescription.setLanguageId(rs.getInt("language_id"));*/
	    	  productDescription.setName(rs.getString("name"));
	    	  break;
	      }
	      conn.close();
	      return productDescription;
		}catch(Exception e){
			e.printStackTrace();
		}
		return new ProductDescriptionTO();
	}

	public List<ProductDescriptionTO> getProductDescriptionInfos(String dbUrl,String userName,String passWord,String productName){
	    List<ProductDescriptionTO> productDescriptions = new ArrayList<ProductDescriptionTO>();
		try{ 
		  Class.forName("com.mysql.jdbc.Driver").newInstance();
		
	      Connection conn = DriverManager.getConnection(dbUrl, userName, passWord);
	      
	      System.out.println("Product Name is" + productName);
	      PreparedStatement ps = conn.prepareStatement("SELECT * FROM ims_product_description where name like ?");
	      ps.setString(1, "%"+ productName + "%");
	      
	      ResultSet rs = ps.executeQuery();
	      while(rs.next()){
	    	  ProductDescriptionTO productDescription = new ProductDescriptionTO(); 
	    	  productDescription.setId(rs.getInt("product_id"));
	    	  productDescription.setLanguageId(rs.getInt("language_id"));
	    	  productDescription.setName(rs.getString("name"));
	    	  productDescriptions.add(productDescription);
	      }
	      conn.close();
	      return productDescriptions;
		}catch(Exception e){
			e.printStackTrace();
		}
		return productDescriptions;
	}
}
