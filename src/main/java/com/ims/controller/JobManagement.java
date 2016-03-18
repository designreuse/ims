package com.ims.controller;

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
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.servlet.ModelAndView;

import com.github.dandelion.datatables.core.ajax.DataSet;
import com.github.dandelion.datatables.core.ajax.DatatablesCriterias;
import com.github.dandelion.datatables.core.ajax.DatatablesResponse;
import com.github.dandelion.datatables.extras.spring3.ajax.DatatablesParams;
import com.ims.domain.entity.Job;
import com.ims.dto.JobTO;
import com.ims.repository.JobRepository;
import com.ims.service.JobService;
import com.ims.service.OrderService;
import com.ims.service.ProductService;
import com.ims.service.RepositoryService;
import com.ims.util.AppUtil;
import com.ims.util.DtToPageableConvertor;
import com.ims.util.ImsException;

@Controller
@RequestMapping(value = "/admin/job")
public class JobManagement extends AbstractManagement {

	@Autowired
	private RepositoryService repositoryService;
	
	@Autowired
	private JobService jobService;
	
	@Autowired
	private JobRepository jobRepository;
	
	@Autowired
	private DozerBeanMapper mapper;
	
	@Autowired
	private ProductService productService;
	
	@Autowired
	private OrderService orderService;

	@RequestMapping(value = "", method = RequestMethod.GET)
	public ModelAndView landingPage() {
		SimpleGrantedAuthority user = getUserRole();
		if(user == null || (user.getAuthority()).equals("ROLE_ANONYMOUS")){
			return getRedirectPage();
		}
		return new ModelAndView("job/home");
	}

	@RequestMapping(value = "/", method = RequestMethod.GET)
	public ModelAndView homePage() {
		SimpleGrantedAuthority user = getUserRole();
		if(user == null || (user.getAuthority()).equals("ROLE_ANONYMOUS")){
			return getRedirectPage();
		}
		return new ModelAndView("job/home");
	}
	
	@RequestMapping(value = "/{id}", method = RequestMethod.GET)
	public ModelAndView viewJobDetails(Model model, @PathVariable("id") Long id) {
		SimpleGrantedAuthority user = getUserRole();
		if(user == null || (user.getAuthority()).equals("ROLE_ANONYMOUS")){
			return getRedirectPage();
		}
		Job job = jobRepository.findOne(id);
		JobTO jobTO = mapper.map(job, JobTO.class);
		model.addAttribute("jobTO", jobTO);
		return new ModelAndView("job/view");
	}
	
	@RequestMapping(value = "/add", method = RequestMethod.GET)
	public ModelAndView addJob(Model model){
		SimpleGrantedAuthority user = getUserRole();
		if (user == null || (user.getAuthority()).equals("ROLE_ANONYMOUS")) {
			return getRedirectPage();
		}
		model.addAttribute("jobTO",new JobTO());
		return new ModelAndView("job/save");
	}	
	
	@SuppressWarnings({ "unchecked", "rawtypes" })
	@RequestMapping(value = "/pagination", method = RequestMethod.GET, produces = MediaType.APPLICATION_JSON_VALUE)
	@ResponseBody
	public DatatablesResponse<JobTO> showPagination(@DatatablesParams DatatablesCriterias criterias) {
		System.out.println("Job Management");
		Pageable pageable = DtToPageableConvertor.convertToPageable(criterias);
		Page<JobTO> page;
		if (criterias.getSearch() != null && !criterias.getSearch().equals("")) {
			page = jobService.dataTableSearch(criterias.getSearch(), pageable);

		} else {
			page = jobService.getAllJobs(pageable);
		}
		DataSet dataset = new DataSet<JobTO>(page.getContent(), new Long(page.getNumber()), new Long(page.getTotalElements()));
		return DatatablesResponse.build(dataset, criterias);
	}
	
	@RequestMapping(value = "/add", method = RequestMethod.POST)
	public ModelAndView addJob(Model model, JobTO jobTO, BindingResult bindingResult){
		SimpleGrantedAuthority user = getUserRole();
		if (user == null || (user.getAuthority()).equals("ROLE_ANONYMOUS")) {
			return getRedirectPage();
		}
		try {
			jobService.saveOrUpdateJob(jobTO);
		} catch (ImsException e) {
			if (e instanceof ImsException) {
				model.addAttribute("error", ((ImsException) e).getError());
			} else {
				model.addAttribute("error", AppUtil.handleApplicationRuntimeError(e));
			}
			return new ModelAndView("job/save");
		}
		jobTO = new JobTO();
		return new ModelAndView("job/home");
	}		
	
	@RequestMapping(value = "/delete/{id}", method = RequestMethod.GET)
	public ModelAndView deleteUser(Model model, @PathVariable("id") Long id) {
		SimpleGrantedAuthority user = getUserRole();
		if(user == null || (user.getAuthority()).equals("ROLE_ANONYMOUS")){
			return getRedirectPage();
		}
		jobRepository.delete(id);
		return new ModelAndView("job/home");
	}
	
	@RequestMapping(value = "/update", method = RequestMethod.POST)
	public ModelAndView updateJob(Model model, JobTO jobTO, BindingResult bindingResult) {
		SimpleGrantedAuthority user = getUserRole();
		if(user == null || (user.getAuthority()).equals("ROLE_ANONYMOUS")){
			return getRedirectPage();
		}
		try {
			jobService.saveOrUpdateJob(jobTO);
		} catch (ImsException e) {
			if (e instanceof ImsException) {
				model.addAttribute("error", ((ImsException) e).getError());
			} else {
				model.addAttribute("error", AppUtil.handleApplicationRuntimeError(e));
			}
			return new ModelAndView("job/save");
		}
		jobTO = new JobTO();
		return new ModelAndView("job/home");
	}	
	
	@RequestMapping(value = "/execute/{id}", method = RequestMethod.GET)
	public ModelAndView viewJobDetails(Model model, @PathVariable("id") int id) {
		SimpleGrantedAuthority user = getUserRole();
		if(user == null || (user.getAuthority()).equals("ROLE_ANONYMOUS")){
			return getRedirectPage();
		}
		if(id == 1){
			productService.addProduct();
		} else if(id == 2){
			orderService.updateQuantity();
		}
		return new ModelAndView("job/home");
	}
	
	@RequestMapping(value = "/help", method = RequestMethod.GET)
	public ModelAndView showHelp(){
		SimpleGrantedAuthority user = getUserRole();
		if (user == null || (user.getAuthority()).equals("ROLE_ANONYMOUS")) {
			return getRedirectPage();
		}
		return new ModelAndView("help/cronexpression");
	}	
}
