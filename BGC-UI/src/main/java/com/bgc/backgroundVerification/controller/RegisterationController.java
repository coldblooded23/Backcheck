package com.bgc.backgroundVerification.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.servlet.ModelAndView;

import com.bgc.backgroundVerification.model.CustomerInfo;
import com.bgc.backgroundVerification.service.Constants;
import com.bgc.backgroundVerification.service.RegistrationService;
import com.bgc.backgroundVerification.util.BgcUIException;

@Controller
@RequestMapping("/signup")
public class RegisterationController {

	@Autowired
	RegistrationService registrationService;

	@GetMapping()
	public String signUp() {

		return "register";
	}

	@ModelAttribute("customer")
	public CustomerInfo customerInfo() {
		return new CustomerInfo();
	}

	@PostMapping()
	public ModelAndView createACustomer(@ModelAttribute("customer") CustomerInfo customerInfo) {

		ModelAndView model = new ModelAndView("register");
		String response = null;
		try {
			response = registrationService.saveAUser(customerInfo);

			if (response.equals(Constants.Success)) {
				model.addObject("Successfull", "succcess");
				return model;
			} else {
				model.addObject("Failed", "fail");
				model.addObject("FailedMessage", response);
				return model;
			}
		} catch (Exception e) {
			throw new BgcUIException("Exeception occered while accessing the data");
		}
	}

	@GetMapping("/verifyAccount/{id}")
	public ModelAndView verifyAccount(@PathVariable String id) {

		ModelAndView model = new ModelAndView("verifyAccount");
		String response = null;
		try {
			response = registrationService.verifyAccount(id);

			if (response.equals(Constants.Success)) {
				model.addObject("Successfull", "succcess");
				return model;
			} else {
				model.addObject("Failed", "fail");
				model.addObject("FailedMessage", response);
				return model;
			}
		} catch (Exception e) {
			throw new BgcUIException("Exeception occered while accessing the data");
		}
	}

}
