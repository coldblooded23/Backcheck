package com.bgc.backgroundVerification.controller;

import java.security.Principal;
import java.util.List;

import javax.servlet.http.Cookie;
import javax.servlet.http.HttpServletRequest;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.servlet.ModelAndView;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import com.bgc.backgroundVerification.model.CandidatesInfo;
import com.bgc.backgroundVerification.model.CustomerInfo;
import com.bgc.backgroundVerification.model.UserDetailsInfo;
import com.bgc.backgroundVerification.service.AddAndRetriveCookieService;
import com.bgc.backgroundVerification.service.Constants;
import com.bgc.backgroundVerification.service.CustomerService;
import com.bgc.backgroundVerification.service.PasswordService;
import com.bgc.backgroundVerification.util.BgcUIException;

import ch.qos.logback.core.util.SystemInfo;

@Controller
public class PasswordController {
	
	@Autowired
	CustomerService customerService;
	
	@Autowired
	PasswordService passwordService;
	
	@GetMapping("/changePassword")
	public ModelAndView changePasswordPage() {
		ModelAndView model= new ModelAndView("changepassword");
		return model;
		
	}
	
	@PostMapping("/changePassword")
	public ModelAndView changePassword(UserDetailsInfo  userDetailsInfo,HttpServletRequest request) {
		Principal principal = request.getUserPrincipal();
		String username =principal.getName();
		ModelAndView model = new ModelAndView("changepassword");
		try {
			String jwtToken=AddAndRetriveCookieService.readAdminServletCookie(request);
			String response =passwordService.changePassword(username, userDetailsInfo,jwtToken);
			if(response.equals(Constants.Success)) {
				model.addObject("Successfull", "success");
				return model;
			}else {
				model.addObject("message", response);
				model.addObject("failed", "fail");	
				return model;
			}	
		}catch (Exception e) {
			// TODO: handle exception
			throw new BgcUIException("exception occered while accessing the data");
		}		
	}
	
	@GetMapping("/forgetPassword")
	
	public String forgetPassword() {
		return "forgetpassword";
		
	}
	
	@PostMapping("/forgetPassword")
	public ModelAndView sendForgetPasswordLink(UserDetailsInfo userDetailsInfo) {
		ModelAndView model= new ModelAndView("forgetpassword");
		try {
			String response =passwordService.sendForgetPasswordLink(userDetailsInfo.getEmail());
			if(response.equals(Constants.Success)) {
				model.addObject("Successfull", "success");
				return model;
			}else {
				model.addObject("message", response);
				model.addObject("failed", "fail");	
				return model;
			}	
		}catch (Exception e) {
			throw new BgcUIException("exception occered while accessing the data");
		}
	
		
	}
	
	@GetMapping("/forgetPassword/verify/{id}")
	public ModelAndView getResetPassword(@PathVariable String id) {
		UserDetailsInfo userDetails= new UserDetailsInfo();
		userDetails.setvCode(id);
		ModelAndView model = new ModelAndView("forgetpassword");
		model.addObject("removeForgetPassword", "forgetPassword");
		model.addObject("userdetails", userDetails);
		return model;
		
	}
	
	@PostMapping("/forgetPassword/verify/")
	public ModelAndView updateResetPassword(UserDetailsInfo userDetailsInfo) {
		ModelAndView model = new ModelAndView("forgetpassword");
		model.addObject("removeForgetPassword", "forgetPassword");
		model.addObject("disableFormfields", "disable");
		
		System.out.println(userDetailsInfo.getvCode()+"        "+userDetailsInfo.getPassword());
		try {
			String response =passwordService.UpdateForgetPasswordLink(userDetailsInfo);
			if(response.equals(Constants.Success)) {
				model.addObject("Successfull", "success");
				return model;
			}else {
				model.addObject("message", response);
				model.addObject("failed", "fail");	
				return model;
			}	
		}catch (Exception e) {
			throw new BgcUIException("exception occered while accessing the data");
		}
		
	}
	
	@GetMapping("/user/forgotPassword")
	
	public String CandidateforgetPassword() {
		return "candidate/forgetpassword";
		
	}
	
	@PostMapping("/user/forgotPassword")
	public ModelAndView sendCandidateForgetPasswordLink(UserDetailsInfo userDetailsInfo) {
		ModelAndView model= new ModelAndView("candidate/forgetpassword");
		try {
			System.out.println(userDetailsInfo.getEmail());
			String response =passwordService.sendCandidateForgetPasswordLink(userDetailsInfo.getEmail());
			if(response.equals(Constants.Success)) {
				model.addObject("Successfull", "success");
				return model;
			}else {
				model.addObject("message", response);
				model.addObject("failed", "fail");	
				return model;
			}	
		}catch (Exception e) {
			throw new BgcUIException("exception occered while accessing the data"+e.getMessage());
		}
	
		
	}
	
	@GetMapping("/user/forgotPassword/verify/{id}")
	public ModelAndView getCandidateResetPassword(@PathVariable String id) {
		UserDetailsInfo userDetails= new UserDetailsInfo();
		userDetails.setvCode(id);
		ModelAndView model = new ModelAndView("candidate/forgetpassword");
		model.addObject("removeForgetPassword", "forgetPassword");
		model.addObject("userdetails", userDetails);
		return model;
		
	}
	
	
	@PostMapping("/user/forgotPassword/verify")
	public ModelAndView updateCandidateResetPassword(UserDetailsInfo userDetailsInfo) {
		ModelAndView model = new ModelAndView("candidate/forgetpassword");
		model.addObject("removeForgetPassword", "forgetPassword");
		model.addObject("disableFormfields", "disable");
		
		System.out.println("sdsdsd"+userDetailsInfo.getvCode()+"        "+userDetailsInfo.getPassword());
		try {
			String response =passwordService.UpdateCandidateForgetPasswordLink(userDetailsInfo);
			if(response.equals(Constants.Success)) {
				model.addObject("Successfull", "success");
				return model;
			}else {
				model.addObject("message", response);
				model.addObject("failed", "fail");	
				return model;
			}	
		}catch (Exception e) {
			throw new BgcUIException("exception occered while accessing the data");
		}
	}
	
	@GetMapping("/user/changePassword")
	public ModelAndView changeCandidatePasswordPage(HttpServletRequest request) {
	
		String jwtToken=AddAndRetriveCookieService.readCandiddateServletCookie(request);		
		ModelAndView model= new ModelAndView("candidate/changepassword");
		return model;
		
	}
	
	@PostMapping("/user/changePassword")
	public ModelAndView changeCandidatePassword(CandidatesInfo  candidatesInfo,HttpServletRequest request) {
		Principal principal = request.getUserPrincipal();
		String username =principal.getName();
		ModelAndView model = new ModelAndView("candidate/changepassword");
		System.out.println(username);
		try {
			String jwtToken=AddAndRetriveCookieService.readCandiddateServletCookie(request);
			String response = passwordService.changeCandidatePassword(username, candidatesInfo,jwtToken);
			if (response.equals(Constants.Success)) {
				model.addObject("Successfull", "success");
				return model;
			} else {
				model.addObject("message", response);
				model.addObject("failed", "fail");
				return model;
			}
			 
		
		}catch (Exception e) {
			// TODO: handle exception
			throw new BgcUIException("exception occered while accessing the data");
		}		
	}
	
	
	@GetMapping("/user/forgotUsername")
	public ModelAndView forgetUserName() {
		ModelAndView model = new ModelAndView("candidate/forgetUserName");
		List<CustomerInfo> response=customerService.getAllCustomer();
		model.addObject("customer", response);
		return model;
	}
	
	@PostMapping("/user/forgotUsername")
	public ModelAndView getUserName(CandidatesInfo candidatesInfo,RedirectAttributes redirAttrs) {
		ModelAndView view = new ModelAndView("redirect:/user/forgotUsername");
		try {
			String response = passwordService.sendUserEmailName(candidatesInfo);
			System.out.println(response);
			if (response.equals(Constants.Success)) {
				redirAttrs.addFlashAttribute("success", "Username sended successfully to the mail");
				/* view.addObject("Successfull", "success"); */
				return view;
			} else {
				redirAttrs.addFlashAttribute("error", "Please enter correct mail or id");
				/*
				 * view.addObject("message", response); view.addObject("failed", "fail");
				 */
				return view;
			}
		} catch (Exception e) {
			throw new BgcUIException("exception occure while accessing the data"+e.getMessage());
		}
	}
	
}
