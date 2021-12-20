package com.bgc.backgroundVerification.controller;

import java.security.Principal;

import javax.servlet.http.HttpServletRequest;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.servlet.ModelAndView;

import com.bgc.backgroundVerification.model.ResponseInfo;
import com.bgc.backgroundVerification.model.SetupModel;
import com.bgc.backgroundVerification.service.AddAndRetriveCookieService;
import com.bgc.backgroundVerification.service.Constants;
import com.bgc.backgroundVerification.service.SetupService;
import com.bgc.backgroundVerification.util.BgcUIException;

@Controller
@RequestMapping("/setup")
public class SetupController {
	
	@Autowired
	SetupService setupService;
	
	
	@GetMapping
	public ModelAndView getSetUpDetails(HttpServletRequest request) {
		Principal principal=request.getUserPrincipal();
		try {
			String jwtToken=AddAndRetriveCookieService.readAdminServletCookie(request);
			System.out.println(jwtToken);
	SetupModel setupModel=	setupService.getDetails(principal.getName(),jwtToken);
	ModelAndView model = new ModelAndView("setup");
	model.addObject("setup", setupModel);
		return model;
		}catch (Exception e) {
			throw new BgcUIException("exception occered while accessing the data"+e.getMessage());
		}
	}
	
	@PostMapping
	public ModelAndView UpdateSetUpDetails(SetupModel setupModel, HttpServletRequest request) {
		Principal principal=request.getUserPrincipal();
		ModelAndView model = new ModelAndView("setup");
		try {
			String jwtToken=AddAndRetriveCookieService.readAdminServletCookie(request);
		ResponseInfo responseInfo =setupService.updateSetup(principal.getName(), setupModel,jwtToken);
		if(responseInfo.getAlertMessage().equals(Constants.Success)) {
			model.addObject("Successfull", "success");	
			
		}else {
			model.addObject("message", responseInfo.getMessage());
				
		}
		model.addObject("setup", setupModel);
		
		return model;
		}catch (Exception e) {
			throw new BgcUIException("exception occered while accessing the data"+e.getMessage());
	
		}
		
	}

}
