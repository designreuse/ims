package com.ims.controller;

import java.util.List;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.apache.commons.beanutils.BeanUtilsBean;
import org.apache.commons.beanutils.ConvertUtilsBean;
import org.dozer.DozerBeanMapper;
import org.json.simple.JSONObject;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.http.MediaType;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.servlet.ModelAndView;

import com.github.dandelion.datatables.core.ajax.ColumnDef;
import com.github.dandelion.datatables.core.ajax.DataSet;
import com.github.dandelion.datatables.core.ajax.DatatablesCriterias;
import com.github.dandelion.datatables.core.ajax.DatatablesResponse;
import com.github.dandelion.datatables.extras.spring3.ajax.DatatablesParams;
import com.ims.dto.ProductDescriptionTO;
import com.ims.dto.ProductTO;
import com.ims.dto.PurchaseOrderSearchTO;
import com.ims.dto.PurchaseOrderTO;
import com.ims.service.ProductService;
import com.ims.service.PurchaseOrderService;
import com.ims.service.UserService;
import com.ims.util.DtToPageableConvertor;

@Controller
@RequestMapping(value = "/admin/purchaseorder")
public class PurchaseOrderManagement extends AbstractManagement {

	@Autowired
	private DozerBeanMapper mapper;

	@Autowired
	private HttpServletRequest context;

	@Autowired
	private HttpServletResponse res;

	@Autowired
	private PurchaseOrderService purchaseOrderService;
	
	@Autowired
	private ProductService productService;

	@Autowired
	private UserService userService;

	@RequestMapping(value = "/search", method = RequestMethod.GET)
	public ModelAndView homePage() {
		SimpleGrantedAuthority user = getUserRole();
		if (user == null || (user.getAuthority()).equals("ROLE_ANONYMOUS")) {
			return getRedirectPage();
		}
		return new ModelAndView("purchaseorder/search");
	}

	@SuppressWarnings({ "unchecked", "rawtypes" })
	@RequestMapping(value = "/pagination", method = RequestMethod.GET, produces = MediaType.APPLICATION_JSON_VALUE)
	@ResponseBody
	public DatatablesResponse<PurchaseOrderTO> showPagination(
			@DatatablesParams DatatablesCriterias criterias) {
		System.out.println("REST");
		Pageable pageable = DtToPageableConvertor.convertToPageable(criterias);
		Page<PurchaseOrderTO> page;
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
		PurchaseOrderSearchTO purchaseOrderSearch = new PurchaseOrderSearchTO();
		for(ColumnDef criteria:criterias.getColumnDefs()){
			if (criteria.getSearch() != null && !criteria.getSearch().equals("")) {
						try{
								beanUtilsBean.setProperty(purchaseOrderSearch,
										criteria.getName(), criteria.getSearch());
						} catch(Exception e){
							e.printStackTrace();
						}
			} 
	
		}
		
		page = purchaseOrderService.dataTableSearch(
				purchaseOrderSearch, pageable);DataSet dataset = new DataSet<PurchaseOrderTO>(page.getContent(),
		new Long(page.getNumber()), new Long(page.getTotalElements()));
		return DatatablesResponse.build(dataset, criterias);
	}

	@RequestMapping(value = "/{id}", method = RequestMethod.GET)
	public ModelAndView showPurchaseOrderDetails(Model model,
			@PathVariable("id") String purchaseOrderId) {
		SimpleGrantedAuthority user = getUserRole();
		if (user == null || (user.getAuthority()).equals("ROLE_ANONYMOUS")) {
			return getRedirectPage();
		}
		PurchaseOrderTO purchaseOrderTO = purchaseOrderService.getPurchaseOrderInfo(Long
				.valueOf(purchaseOrderId));
		model.addAttribute("purchaseOrderTO", purchaseOrderTO);
		if (purchaseOrderTO != null) {
			model.addAttribute("pkey", purchaseOrderId);
			model.addAttribute("name", purchaseOrderTO.getName());
			model.addAttribute("description", purchaseOrderTO.getDescription());
			model.addAttribute("vendorName", purchaseOrderTO.getVendorName());
			model.addAttribute("vendorSite", purchaseOrderTO.getVendorSite());
			model.addAttribute("status", purchaseOrderTO.getStatus());
			model.addAttribute("paymentMethod",
					purchaseOrderTO.getPaymentMethod());
			/*model.addAttribute("shippedDate", purchaseOrderTO.getShippedDate()
					.toString());*/
			model.addAttribute("total", purchaseOrderTO.getTotal());
			return new ModelAndView("purchaseorder/showpurchaseorder");
		} else {
			return new ModelAndView("/purchaseorder/search");
		}
	}

	@SuppressWarnings("unused")
	@RequestMapping(value = "/edit/{pkey}", method = RequestMethod.GET)
	public ModelAndView editPurchaseOrderDetails(Model model,
			@PathVariable("pkey") String purchaseOrderId) {
		SimpleGrantedAuthority user = getUserRole();
		if (user == null || (user.getAuthority()).equals("ROLE_ANONYMOUS")) {
			return getRedirectPage();
		}
		List<Integer> ids = purchaseOrderService.getProductDeatails();
	    List<String> names = productService.getAllProductNames();
	    PurchaseOrderTO purchaseOrderTO = purchaseOrderService.getPurchaseOrderInfo(Long
				.valueOf(purchaseOrderId));
	    purchaseOrderTO.setNames(names);
	    model.addAttribute("purchaseOrderTO", purchaseOrderTO);
		if (purchaseOrderTO != null) {
			model.addAttribute("pkey", purchaseOrderId);
			model.addAttribute("name", purchaseOrderTO.getName());
			model.addAttribute("description", purchaseOrderTO.getDescription());
			model.addAttribute("vendorName", purchaseOrderTO.getVendorName());
			model.addAttribute("vendorSite", purchaseOrderTO.getVendorSite());
			model.addAttribute("status", purchaseOrderTO.getStatus());
			model.addAttribute("paymentMethod",
					purchaseOrderTO.getPaymentMethod());
			model.addAttribute("shippedDate", purchaseOrderTO.getShippedDate());
			model.addAttribute("total", purchaseOrderTO.getTotal());
			return new ModelAndView("purchaseorder/editPurchaseOrder");
		} 
		return new ModelAndView("purchaseorder/search");
	}

	@RequestMapping(value = "/update", method = RequestMethod.POST)
	public ModelAndView updatePurchaseOrderDetails(PurchaseOrderTO purchaseOrderTO) {
		SimpleGrantedAuthority user = getUserRole();
		if (user == null || (user.getAuthority()).equals("ROLE_ANONYMOUS")) {
			return getRedirectPage();
		}
		purchaseOrderService.updatePurchaseOrderDetails(purchaseOrderTO);
		return new ModelAndView("/purchaseorder/search");
	}
	
	@RequestMapping(value = "/add", method = RequestMethod.GET)
	public ModelAndView add(Model model) {
		SimpleGrantedAuthority user = getUserRole();
		if (user == null || (user.getAuthority()).equals("ROLE_ANONYMOUS")) {
			return getRedirectPage();
		}
		List<Integer> ids = purchaseOrderService.getProductDeatails();
	    List<String> names = productService.getAllProductNames();
	    PurchaseOrderTO purchaseOrderTO = new PurchaseOrderTO();
	    purchaseOrderTO.setIds(ids);
	    purchaseOrderTO.setNames(names);
	    model.addAttribute("purchaseOrderTO",purchaseOrderTO);
	    return new ModelAndView("/purchaseorder/add");
	}

	@RequestMapping(value="/delete/{pkey}")
	public ModelAndView deleteOrder(Model model, @PathVariable("pkey") Integer pkey){
		SimpleGrantedAuthority user = getUserRole();
		if (user == null || (user.getAuthority()).equals("ROLE_ANONYMOUS")) {
			return getRedirectPage();
		}
		purchaseOrderService.deleteOrder(pkey);
		return new ModelAndView("/purchaseorder/search");
	}
	
	@RequestMapping(value="/register")
	public ModelAndView registerOrder(PurchaseOrderTO purchaseOrderTO){
		SimpleGrantedAuthority user = getUserRole();
		if (user == null || (user.getAuthority()).equals("ROLE_ANONYMOUS")) {
			return getRedirectPage();
		}
		//ProductDescriptionTO productDescription = productService.getProductDescription(purchaseOrderTO.getName().trim());
		try{
			ProductTO product = productService.getProductByName((purchaseOrderTO.getName().trim()));
			purchaseOrderTO.setId(product.getProductId());
			purchaseOrderService.register(purchaseOrderTO);
		} catch(Exception e){
			e.printStackTrace();
		}
		return new ModelAndView("/purchaseorder/search");
	}

	@RequestMapping(value="/getProductname", method = RequestMethod.GET)
	public @ResponseBody JSONObject getProductName(@RequestParam("id") Integer id){
		ProductTO product = productService.getProductInfo(id);
		JSONObject json = new JSONObject();
		json.put("productName", product.getProductName());
		return json;
	}
	
	@RequestMapping(value="/getProductId", method = RequestMethod.GET)
	public @ResponseBody JSONObject getProductId(@RequestParam("name") String name){
		ProductTO product = productService.getProductByName(name.trim());
		JSONObject json = new JSONObject();
		json.put("productId", product.getProductId());
		return json;
	}


}
