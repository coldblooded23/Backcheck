package com.bgc.backgroundVerification.controller;

import java.security.Principal;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.List;

import javax.servlet.http.HttpServletRequest;


import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.servlet.ModelAndView;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import com.bgc.backgroundVerification.model.CustomerInfo;
import com.bgc.backgroundVerification.model.EmployeeInfo;
import com.bgc.backgroundVerification.model.ResponseInfo;
import com.bgc.backgroundVerification.model.RolesInfo;
import com.bgc.backgroundVerification.service.AddAndRetriveCookieService;
import com.bgc.backgroundVerification.service.Constants;
import com.bgc.backgroundVerification.service.EmployeeService;
import com.bgc.backgroundVerification.service.RolesService;
import com.bgc.backgroundVerification.util.BgcUIException;

@Controller
@RequestMapping("/employee")
public class EmployeeController {
	
	@Autowired
	EmployeeService employeeService;
	
	
	@Autowired 
	RolesService rolesService;

	
	@GetMapping
	public ModelAndView getListOfEmployees(HttpServletRequest request) {
		Principal principal =request.getUserPrincipal();
		
		ModelAndView model = new ModelAndView("employee");
		String jwtToken=AddAndRetriveCookieService.readAdminServletCookie(request);
		CustomerInfo response=employeeService.getListOfEmployees(principal.getName(),jwtToken);
		System.out.println(response.getCustomerId());
		List<RolesInfo> roles=rolesService.listofRoles(response.getCustomerId(), jwtToken);
		model.addObject("listOfRoles", roles);
		model.addObject("customer", response);
		
		return model;
		
	}
	
	
	@PostMapping("/addEmployee")
	public ModelAndView addEmployee(EmployeeInfo employeeInfo,HttpServletRequest request) throws ParseException {
		Principal principal =request.getUserPrincipal();
		ModelAndView model = new ModelAndView("employee");
		EmployeeInfo info=employeeInfo;
			info.setdOB(info.getMdate());
			String jwtToken=AddAndRetriveCookieService.readAdminServletCookie(request);
		ResponseInfo response=employeeService.addEmployee(info, principal.getName(),jwtToken);
		
		  if(response.getMessage().equals(Constants.Success))
		  {
		  model.addObject("AddSuccess", "Success"); 
		  }else
		  {
		  model.addObject("Addfailed", "failed");
		  model.addObject("message",response.getAlertMessage());
		  }
		 
		
		model.addObject("customer", response.getCustomerInfo());
		return model;
		
	}
	
	
	@ModelAttribute("employee")
	public EmployeeInfo employeeInfo() {
		return new EmployeeInfo();
	}
	
	@PostMapping("/updateEmployee")
	public ModelAndView updateEmployee(@ModelAttribute("employee") EmployeeInfo employeeInfo,HttpServletRequest request,RedirectAttributes redirectAttributes)
	{
		Principal principal =request.getUserPrincipal();
	
		
		EmployeeInfo info=employeeInfo;
			info.setdOB(info.getMdate());
			String jwtToken=AddAndRetriveCookieService.readAdminServletCookie(request);
		ResponseInfo response=employeeService.updateEmployee(info, principal.getName(),jwtToken);
		
		  if(response.getMessage().equals(Constants.Success))
		  {
		ModelAndView model = new ModelAndView("employee");
		  model.addObject("updateSuccess", "Success"); 
		model.addObject("customer", response.getCustomerInfo());
		return model;
		  }else
		  {
				ModelAndView model = new ModelAndView("redirect:/employee/employeeDetails/"+employeeInfo.getEmail());

		  redirectAttributes.addFlashAttribute("updatefailed", "failed");
		  redirectAttributes.addFlashAttribute("message",response.getAlertMessage());
		 
		  return model;
		  }
		 
		
	
	}
	
	@GetMapping("/employeeDetails/{id}")
	public ModelAndView employeeDetails( @PathVariable String id,HttpServletRequest request) {

		
		ModelAndView model= new ModelAndView("updateemployee");
		Principal principal =request.getUserPrincipal();	
		try {
			String jwtToken=AddAndRetriveCookieService.readAdminServletCookie(request);
			EmployeeInfo response =   employeeService.getEmployeeDetailsForUpdate(id,jwtToken); 
			List<RolesInfo> roles=rolesService.listofRoles(response.getCustomerId(), jwtToken);
			model.addObject("listOfRoles", roles);
		   model.addObject("employee", response);
			
		}catch (Exception e) {
			throw new BgcUIException("exeception occered while accrssing the data");
		}
		
		return model;
		
	}
	
	
	@PostMapping("/deleteEmployee")
	public ModelAndView deleteEmployee( ResponseInfo responseInfo,HttpServletRequest request) throws ParseException {
		Principal principal =request.getUserPrincipal();
		ModelAndView model = new ModelAndView("employee");
	
		String jwtToken=AddAndRetriveCookieService.readAdminServletCookie(request);
		ResponseInfo response=employeeService.deleteEmployee(responseInfo.getEmailId(), principal.getName(),jwtToken);
		
		  if(response.getMessage().equals(Constants.Success))
		  {
		  model.addObject("DeleteSuccess", "Success"); 
		  }else
		  {
		  model.addObject("Addfailed", "failed");
		  model.addObject("message",response.getAlertMessage());
		  }
		 
		
		model.addObject("customer", response.getCustomerInfo());
		return model;
		
	}

	


}
