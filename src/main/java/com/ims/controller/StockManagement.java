package com.ims.controller;

import java.util.Iterator;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.apache.commons.beanutils.BeanUtilsBean;
import org.apache.commons.beanutils.ConvertUtilsBean;
import org.dozer.DozerBeanMapper;
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
import com.ims.dto.ProductTO;
import com.ims.dto.StockSearchTO;
import com.ims.dto.StockTO;
import com.ims.service.StockService;
import com.ims.util.DtToPageableConvertor;

@Controller
@RequestMapping(value="/admin/stock")
public class StockManagement {
	
	@Autowired
	private DozerBeanMapper mapper;

	@Autowired
	private HttpServletRequest context;

	@Autowired
	private HttpServletResponse res;
	
	@Autowired
	private StockService stockService;
	
	
	@RequestMapping(value = "/search", method = RequestMethod.GET)
	public ModelAndView homePage() {
		SimpleGrantedAuthority user = getUserRole();
		if (user == null || (user.getAuthority()).equals("ROLE_ANONYMOUS")) {
			return getRedirectPage();
		}
		return new ModelAndView("stock/search");
	}
	
	@SuppressWarnings({ "unchecked", "rawtypes" })
	@RequestMapping(value = "/pagination", method = RequestMethod.GET, produces = MediaType.APPLICATION_JSON_VALUE)
	@ResponseBody
	public DatatablesResponse<StockTO> showPagination(@DatatablesParams DatatablesCriterias criterias) {

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
		
		StockSearchTO stockSearchTO = new StockSearchTO();
		for(ColumnDef criteria : criterias.getColumnDefs()){
			if (criteria.getSearch() != null) {
				try{
						beanUtilsBean.setProperty(stockSearchTO,
								criteria.getName(), criteria.getSearch());
				} catch(Exception e){
					e.printStackTrace();
				}
				
			}
		}
		
		Pageable pageable = DtToPageableConvertor.convertToPageable(criterias);
		Page<StockTO> page;
		page = stockService.dataTableSearch(stockSearchTO, pageable);
		DataSet dataset = new DataSet<StockTO>(page.getContent(), new Long(
				page.getNumber()), new Long(page.getTotalElements()));
		return DatatablesResponse.build(dataset, criterias);
	}
	
	@RequestMapping(value = "/add")
	public ModelAndView addStock(Model model){
		SimpleGrantedAuthority user = getUserRole();
		if (user == null || (user.getAuthority()).equals("ROLE_ANONYMOUS")) {
			return getRedirectPage();
		}
		model.addAttribute("stockTO",new StockTO());
		return new ModelAndView("stock/add");
	}
	
	@RequestMapping(value = "/edit/{stockId}")
	public ModelAndView editStock(Model model,@PathVariable("stockId") String stockId){
		SimpleGrantedAuthority user = getUserRole();
		if (user == null || (user.getAuthority()).equals("ROLE_ANONYMOUS")) {
			return getRedirectPage();
		}
		
		StockTO stockTO = stockService.getStockInfo(Long.valueOf(stockId));
		model.addAttribute("stockTO", stockTO);
		if (stockTO != null) {
			return new ModelAndView("stock/editStock");
		} else {
			return new ModelAndView("/stock/search");
		}
	}
	
	@RequestMapping(value = "/update", method = RequestMethod.POST)
	public ModelAndView updateProductDetails(StockTO stockTO) {
	
		SimpleGrantedAuthority user = getUserRole();
		if (user == null || (user.getAuthority()).equals("ROLE_ANONYMOUS")) {
			return getRedirectPage();
		}
		
		StockTO stock = stockService.updateStockDetails(stockTO);
			
		return new ModelAndView("/stock/search");
	}


	@RequestMapping(value="/register")
	public ModelAndView registerStock(StockTO stockTO){
		SimpleGrantedAuthority user = getUserRole();
		if (user == null || (user.getAuthority()).equals("ROLE_ANONYMOUS")) {
			return getRedirectPage();
		}
		stockService.register(stockTO);
		return new ModelAndView("stock/search");
	}
	
	@RequestMapping(value="/delete/{pkey}")
	public ModelAndView deleteStock(Model model, @PathVariable("pkey") Long pkey){
		SimpleGrantedAuthority user = getUserRole();
		if (user == null || (user.getAuthority()).equals("ROLE_ANONYMOUS")) {
			return getRedirectPage();
		}
		stockService.deleteStock(pkey);
		return new ModelAndView("stock/search");
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
