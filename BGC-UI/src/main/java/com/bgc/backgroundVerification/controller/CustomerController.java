package com.bgc.backgroundVerification.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.servlet.ModelAndView;

import com.bgc.backgroundVerification.model.CustomerInfo;
import com.bgc.backgroundVerification.service.CustomerService;

@Controller
public class CustomerController {

	@Autowired
	CustomerService customerService;

	@GetMapping("/Customers")
	public ModelAndView getListOfEmployees() {
		ModelAndView model = new ModelAndView("customer");
		List<CustomerInfo> response=customerService.getAllCustomer();
		model.addObject("customers", response);
		return model;
		
	}
}
