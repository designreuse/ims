package com.ims.controller;

import java.util.Iterator;


import javax.servlet.http.HttpServletResponse;

import org.apache.commons.beanutils.BeanUtilsBean;
import org.apache.commons.beanutils.ConvertUtilsBean;
import org.json.simple.JSONObject;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.http.MediaType;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.servlet.ModelAndView;

import com.github.dandelion.datatables.core.ajax.ColumnDef;
import com.github.dandelion.datatables.core.ajax.DataSet;
import com.github.dandelion.datatables.core.ajax.DatatablesCriterias;
import com.github.dandelion.datatables.core.ajax.DatatablesResponse;
import com.github.dandelion.datatables.extras.spring3.ajax.DatatablesParams;
import com.ims.dto.OrderSearchTO;
import com.ims.dto.OrderTO;
import com.ims.service.OrderService;
import com.ims.util.DtToPageableConvertor;

@Controller
@RequestMapping(value = "/admin/salesorder")
public class OrderManagement {
	@Autowired
	private HttpServletResponse res;
	
	@Autowired 
	private OrderService orderService;
	
	@RequestMapping(value = "/search", method = RequestMethod.GET)
	public ModelAndView homePage(Model model) {
		SimpleGrantedAuthority user = getUserRole();
		if (user == null || (user.getAuthority()).equals("ROLE_ANONYMOUS")) {
			return getRedirectPage();
		}
		model.addAttribute("updateStatus", "false");
		return new ModelAndView("sales/search");
	}

	@RequestMapping(value = "/updateQuantity", method = RequestMethod.GET)
	public ModelAndView updateProductQuantity(Model model) {
		SimpleGrantedAuthority user = getUserRole();
		if (user == null || (user.getAuthority()).equals("ROLE_ANONYMOUS")) {
			return getRedirectPage();
		}
		JSONObject update = orderService.updateQuantity();
		Boolean isUpdate = (Boolean)update.get("updateStatus");
		Integer totalProcess = (Integer)update.get("totalProcess");
		model.addAttribute("updateStatus", isUpdate);
		model.addAttribute("updateDetails", totalProcess);
		return new ModelAndView("sales/search");
	}
	
	@RequestMapping(value = "/{orderId}", method = RequestMethod.GET)
	public ModelAndView showProductDetails(Model model,
			@PathVariable("orderId") Integer orderId) {
		SimpleGrantedAuthority user = getUserRole();
		if (user == null || (user.getAuthority()).equals("ROLE_ANONYMOUS")) {
			return getRedirectPage();
		}
		
		OrderTO orderTO = orderService.getOrderInfo(orderId);
		model.addAttribute("orderTO", orderTO);
		return new ModelAndView("sales/showsalesorder");
	}

	
	
	@SuppressWarnings({ "unchecked", "rawtypes" })
	@RequestMapping(value = "/pagination", method = RequestMethod.GET, produces = MediaType.APPLICATION_JSON_VALUE)
	@ResponseBody
	public DatatablesResponse<OrderTO> showPagination(@DatatablesParams DatatablesCriterias criterias) {
		BeanUtilsBean beanUtilsBean = new BeanUtilsBean(new ConvertUtilsBean() {
			@Override
			public Object convert(String value, Class clazz) {
				if (clazz.isEnum()) {
					return Enum.valueOf(clazz, value);
				} else {
					return super.convert(value, clazz);
				}
			}
		});
		
		OrderSearchTO orderSearchTO = new OrderSearchTO();
		for(ColumnDef criteria : criterias.getColumnDefs()){
			if (criteria.getSearch() != null) {
				try{
						beanUtilsBean.setProperty(orderSearchTO,
								criteria.getName(), criteria.getSearch());
				} catch(Exception e){
					e.printStackTrace();
				}
			}
		}
		Pageable pageable = DtToPageableConvertor.convertToPageable(criterias);
		Page<OrderTO> page;
		page = orderService.dataTableSearch(orderSearchTO, pageable );
		DataSet dataset = new DataSet<OrderTO>(page.getContent(), new Long(
				page.getNumber()), new Long(page.getTotalElements()));
		return DatatablesResponse.build(dataset, criterias);

	}

	public SimpleGrantedAuthority getUserRole() {
		Iterator iterator = SecurityContextHolder.getContext()
				.getAuthentication().getAuthorities().iterator();
		SimpleGrantedAuthority user = null;
		while (iterator.hasNext()) {
			user = (SimpleGrantedAuthority) iterator.next();
		}
		return user;
	}
	
	public ModelAndView getRedirectPage() {
		try {
			res.sendRedirect("/ims/login");
		} catch (Exception e) {
			e.printStackTrace();
		}
		Login logIn = new Login();
		return logIn.showUserFrom();
	}
}
