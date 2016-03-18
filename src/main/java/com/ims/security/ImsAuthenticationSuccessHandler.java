package com.ims.security;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.util.Iterator;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.springframework.security.core.Authentication;
import org.springframework.security.web.DefaultRedirectStrategy;
import org.springframework.security.web.RedirectStrategy;
import org.springframework.security.web.authentication.AuthenticationSuccessHandler;

public class ImsAuthenticationSuccessHandler implements AuthenticationSuccessHandler{


		
	private RedirectStrategy redirectStrategy = new DefaultRedirectStrategy();
	@Override
	public void onAuthenticationSuccess(HttpServletRequest request,
			HttpServletResponse response, Authentication authentication)
			throws IOException, ServletException {
		Authentication auth = authentication;
		Integer seesionIntervalTime = getSessionInterValTime();
		if(seesionIntervalTime != null){
			request.getSession().setMaxInactiveInterval(seesionIntervalTime*60);
		}
		String role = null;
		String targetUrl = "";
		Object fpId = request.getParameter("fpId");
		System.out.println(fpId);
		
		if(auth.isAuthenticated()){
			Iterator iterator = auth.getAuthorities().iterator();
			while(iterator.hasNext()) {
				role = iterator.next().toString();
				break;
			}			
			if(role.equals("USER_MGMT_CRUD")) {
				targetUrl = "/admin/product/search";
			} else if(role.equals("PRODUCT_MGMT_CRUD")) {
				targetUrl = "/admin/product/search";
			} else if(role.equals("CRF_MGMT_CRUD")) {
				targetUrl = "/admin/protocol/protocol/search";
			} else if(role.equals("PURCHASE_ORDER_MGMT_CRUD")) {
				targetUrl = "/admin/purcahseorder/search";
			}
			
			if(fpId != null && fpId != ""){
				targetUrl = "/admin/volunteer/fingerPrintId?fpId="+fpId;
			}
		
			redirectStrategy.sendRedirect(request, response, targetUrl);
		}
	}

	
	public int getSessionInterValTime(){
		InputStream inputStream = getClass().getClassLoader().getResourceAsStream("application-dev.properties");
		BufferedReader reader = new BufferedReader(new InputStreamReader(inputStream));
		String output = null;
		String sessionTime = null;
		try{
			while((output=reader.readLine()) != null){
				String[] paths = output.split("=");
				if(paths[0].equals("session.timeout")) {
					sessionTime = paths[1];
				}
			}
		}catch(Exception e){
			e.printStackTrace();
		}

		if(sessionTime != null && sessionTime != ""){
			int intervalTime = Integer.parseInt(sessionTime);
			return intervalTime;
		}else{
			return 0;
		}
	}
}
