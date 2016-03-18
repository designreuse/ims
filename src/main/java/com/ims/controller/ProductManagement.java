package com.ims.controller;

import java.util.Iterator;

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
import com.ims.dto.ProductSearchTO;
import com.ims.dto.ProductTO;
import com.ims.service.ProductService;
import com.ims.service.UserService;
import com.ims.util.DtToPageableConvertor;

@Controller
@RequestMapping(value = "/admin/product")
public class ProductManagement {

	@Autowired
	private DozerBeanMapper mapper;

	/*@Autowired
	private HttpServletRequest context;*/

	@Autowired
	private HttpServletResponse res;
	
	@Autowired
	private ProductService productService;
	
	@Autowired
	private UserService userService;	

	@RequestMapping(value = "/search", method = RequestMethod.GET)
	public ModelAndView homePage(Model model) {
		SimpleGrantedAuthority user = getUserRole();
		if (user == null || (user.getAuthority()).equals("ROLE_ANONYMOUS")) {
			return getRedirectPage();
		}
		return new ModelAndView("product/search");
	}
	
	@SuppressWarnings({ "unchecked", "rawtypes" })
	@RequestMapping(value = "/pagination", method = RequestMethod.GET, produces = MediaType.APPLICATION_JSON_VALUE)
	@ResponseBody
	public DatatablesResponse<ProductTO> showPagination(@DatatablesParams DatatablesCriterias criterias) {
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
		
		ProductSearchTO productSearchTO = new ProductSearchTO();
		for(ColumnDef criteria : criterias.getColumnDefs()){
			if (criteria.getSearch() != null) {
				try{
						beanUtilsBean.setProperty(productSearchTO,
								criteria.getName(), criteria.getSearch());
				} catch(Exception e){
					e.printStackTrace();
				}
			}
		}
		Pageable pageable = DtToPageableConvertor.convertToPageable(criterias);
		Page<ProductTO> page;
		page = productService.dataTableSearch(productSearchTO, pageable);
		DataSet dataset = new DataSet<ProductTO>(page.getContent(), new Long(
				page.getNumber()), new Long(page.getTotalElements()));
		return DatatablesResponse.build(dataset, criterias);
	}
	
	@RequestMapping(value = "/add", method = RequestMethod.GET)
	public ModelAndView addProduct(Model model){
		SimpleGrantedAuthority user = getUserRole();
		if (user == null || (user.getAuthority()).equals("ROLE_ANONYMOUS")) {
			return getRedirectPage();
		}
		productService.addProduct();
		//model.addAttribute("productTO",new ProductTO());
		return new ModelAndView("product/search");
	}
	
	@RequestMapping(value="/register")
	public ModelAndView registerProduct(ProductTO productTO){
		SimpleGrantedAuthority user = getUserRole();
		if (user == null || (user.getAuthority()).equals("ROLE_ANONYMOUS")) {
			return getRedirectPage();
		}
		productService.register(productTO);
		return new ModelAndView("product/search");
	}

	
	@RequestMapping(value = "/{productId}", method = RequestMethod.GET)
	public ModelAndView showProductDetails(Model model,
			@PathVariable("productId") String productId) {
		SimpleGrantedAuthority user = getUserRole();
		if (user == null || (user.getAuthority()).equals("ROLE_ANONYMOUS")) {
			return getRedirectPage();
		}
		
		ProductTO productSearchTO = productService.getProductInfo(Integer.valueOf(productId));
		model.addAttribute("productSearchTO", productSearchTO);
		if (productSearchTO != null) {
			model.addAttribute("productId", productId);
			model.addAttribute("productName", productSearchTO.getProductName());
			model.addAttribute("model", productSearchTO.getModel());
			model.addAttribute("price", productSearchTO.getPrice());
			model.addAttribute("quantity", productSearchTO.getQuantity());
			model.addAttribute("points", productSearchTO.getPoints());
			model.addAttribute("viewed", productSearchTO.getViewed());
			String role = userService.getUserRole();
			if (role.equalsIgnoreCase("admin")) {
				return new ModelAndView("product/showproduct");
			} else{
				return new ModelAndView("product/showproduct");
			}
		} else {
			return new ModelAndView("/product/search");
		}
	}
	
	@RequestMapping(value = "/edit/{productId}", method = RequestMethod.GET)
	public ModelAndView editProductDetails(Model model,
			@PathVariable("productId") String productId) {
		SimpleGrantedAuthority user = getUserRole();
		if (user == null || (user.getAuthority()).equals("ROLE_ANONYMOUS")) {
			return getRedirectPage();
		}
		ProductTO productTO = productService.getProductInfo(Integer.valueOf(productId));
		model.addAttribute("productTO", productTO);
		if (productTO != null) {
			model.addAttribute("productId", productId);
			model.addAttribute("model", productTO.getModel());
			model.addAttribute("price", productTO.getPrice());
			model.addAttribute("quantity", productTO.getQuantity());
			model.addAttribute("points", productTO.getPoints());
			model.addAttribute("viewed", productTO.getViewed());
			String role = userService.getUserRole();
			 
			return new ModelAndView("product/editProduct");
		} else {
			return new ModelAndView("/product/search");
		}
	}
	
	@RequestMapping(value = "/update", method = RequestMethod.POST)
	public ModelAndView updateProductDetails(ProductTO productTO) {
	
		SimpleGrantedAuthority user = getUserRole();
		if (user == null || (user.getAuthority()).equals("ROLE_ANONYMOUS")) {
			return getRedirectPage();
		}
		
		ProductTO product = productService.updateProductDetails(productTO);
			
		return new ModelAndView("/product/search");
	}
	
	@RequestMapping(value = "/delete/{productId}")
	public ModelAndView deleteProductDetails(Model model, @PathVariable("productId") int productId) {
		try{
			productService.deleteProduct(productId);
		} catch(Exception e){
			e.printStackTrace();
		}
		return new ModelAndView("/product/search");
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
