package com.ims.controller;

import com.github.dandelion.datatables.core.ajax.DataSet;
import com.github.dandelion.datatables.core.ajax.DatatablesCriterias;
import com.github.dandelion.datatables.core.ajax.DatatablesResponse;
import com.github.dandelion.datatables.extras.spring3.ajax.DatatablesParams;
import com.ims.dto.UserTO;
import com.ims.service.RepositoryService;
import com.ims.service.UserService;
import com.ims.util.AppUtil;
import com.ims.util.DtToPageableConvertor;
import com.ims.util.ImsException;

import org.dozer.DozerBeanMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.http.MediaType;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.multipart.MultipartFile;
import org.springframework.web.servlet.ModelAndView;

import java.io.IOException;

import javax.servlet.http.HttpServletResponse;

@Controller
@RequestMapping(value = "/admin/user")
public class UserManagement extends AbstractManagement {

	@Autowired
	private UserService userService;

	@Autowired
	private DozerBeanMapper mapper;

	@Autowired
	private RepositoryService repositoryService;
	
	@Autowired
	private HttpServletResponse res;


	@RequestMapping(value = "", method = RequestMethod.GET)
	public ModelAndView landingPage() {
		SimpleGrantedAuthority user = getUserRole();
		if(user == null || (user.getAuthority()).equals("ROLE_ANONYMOUS")){
			return getRedirectPage();
		}
		return new ModelAndView("user/home");
	}

	@RequestMapping(value = "/", method = RequestMethod.GET)
	public ModelAndView homePage() {
		SimpleGrantedAuthority user = getUserRole();
		if(user == null || (user.getAuthority()).equals("ROLE_ANONYMOUS")){
			return getRedirectPage();
		}
		return new ModelAndView("user/home");
	}

	@RequestMapping(value = "/add", method = RequestMethod.GET)
	public ModelAndView showUserFrom(Model model, UserTO userTO) {
		SimpleGrantedAuthority user = getUserRole();
		if(user == null || (user.getAuthority()).equals("ROLE_ANONYMOUS")){
			return getRedirectPage();
		}
		userTO = new UserTO();
		userTO.setAvailableRoles(repositoryService.getRolesTO());
		model.addAttribute(userTO);
		return new ModelAndView("user/register");
	}
	
	@RequestMapping(value = "/{pkey}", method = RequestMethod.GET)
	public ModelAndView viewUserDetails(Model model, @PathVariable("pkey") Long pkey) {
		SimpleGrantedAuthority user = getUserRole();
		if(user == null || (user.getAuthority()).equals("ROLE_ANONYMOUS")){
			return getRedirectPage();
		}
		UserTO userTO = userService.getUserInfo(pkey);
		userTO.setAvailableRoles(repositoryService.getRolesTO());
		model.addAttribute("userTO", userTO);
		return new ModelAndView("user/view");
	}

	@RequestMapping(value = "/delete/{pkey}", method = RequestMethod.GET)
	public ModelAndView deleteUser(Model model, @PathVariable("pkey") Long pkey) {
		SimpleGrantedAuthority user = getUserRole();
		if(user == null || (user.getAuthority()).equals("ROLE_ANONYMOUS")){
			return getRedirectPage();
		}
		UserTO userTO = userService.getUserInfo(pkey);
		if (!userTO.getUsername().equals("superadmin")) {
			userService.deleteUser(pkey);
		}
		return new ModelAndView("user/home");
	}

	@RequestMapping(value = "/update", method = RequestMethod.POST)
	public ModelAndView updateUser(Model model, UserTO userTO, BindingResult bindingResult,
			@RequestParam(value = "photo", required = false) MultipartFile image) {

		SimpleGrantedAuthority user = getUserRole();
		if(user == null || (user.getAuthority()).equals("ROLE_ANONYMOUS")){
			return getRedirectPage();
		}
		if (!image.isEmpty()) {
			try {
				AppUtil.validateImage(image);
				userTO.setActualImage(AppUtil.encodeToString(image.getBytes(), "jpg"));
				userTO.setThumbnailView(AppUtil.generateThumbnailViewString(image.getBytes()));
			} catch (RuntimeException | IOException re) {
				bindingResult.reject(re.getMessage());
				return new ModelAndView("user/view");
			}
		}
		try {
			userService.registerUser(userTO);
		} catch (ImsException e) {
			if (e instanceof ImsException) {
				model.addAttribute("error", ((ImsException) e).getError());
			} else {
				model.addAttribute("error", AppUtil.handleApplicationRuntimeError(e));
			}
			return new ModelAndView("user/register");
		}
		userTO = new UserTO();
		return new ModelAndView("user/home");
	}

	@RequestMapping(value = "/add", method = RequestMethod.POST)
	public ModelAndView addUser(Model model, UserTO userTO, BindingResult bindingResult,
			@RequestParam(value = "photo", required = false) MultipartFile image) {

		SimpleGrantedAuthority user = getUserRole();
		if(user == null || (user.getAuthority()).equals("ROLE_ANONYMOUS")){
			return getRedirectPage();
		}
		if (!image.isEmpty()) {
			try {
				AppUtil.validateImage(image);
				userTO.setActualImage(AppUtil.encodeToString(image.getBytes(), "jpg"));
				userTO.setThumbnailView(AppUtil.generateThumbnailViewString(image.getBytes()));
			} catch (RuntimeException | IOException re) {
				bindingResult.reject(re.getMessage());
				return new ModelAndView("user/register");
			}
		}
		try {
			userService.registerUser(userTO);
		} catch (Exception e) {
			if (e instanceof ImsException) {
				model.addAttribute("error", ((ImsException) e).getError());
			} else {
				model.addAttribute("error", AppUtil.handleApplicationRuntimeError(e));
			}
			return new ModelAndView("user/register");
		}
		userTO = new UserTO();
		return new ModelAndView("user/home");
	}

	@SuppressWarnings({ "unchecked", "rawtypes" })
	@RequestMapping(value = "/pagination", method = RequestMethod.GET, produces = MediaType.APPLICATION_JSON_VALUE)
	@ResponseBody
	public DatatablesResponse<UserTO> showPagination(@DatatablesParams DatatablesCriterias criterias) {
		System.out.println("REST");
		Pageable pageable = DtToPageableConvertor.convertToPageable(criterias);
		Page<UserTO> page;
		if (criterias.getSearch() != null && !criterias.getSearch().equals("")) {
			page = userService.dataTableSearch(criterias.getSearch(), pageable);

		} else {
			page = userService.getAllActiveUsers(pageable);
		}
		DataSet dataset = new DataSet<UserTO>(page.getContent(), new Long(page.getNumber()), new Long(page.getTotalElements()));
		return DatatablesResponse.build(dataset, criterias);
	}

	@RequestMapping(value = "/", method = RequestMethod.GET, produces = MediaType.APPLICATION_JSON_VALUE)
	@ResponseBody
	public ModelAndView showHomePageMVC(Model uiModel) {
		SimpleGrantedAuthority user = getUserRole();
		if(user == null || (user.getAuthority()).equals("ROLE_ANONYMOUS")){
			return getRedirectPage();
		}
		return new ModelAndView("user/home");
	}

}
