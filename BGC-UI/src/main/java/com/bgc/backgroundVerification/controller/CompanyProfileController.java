package com.bgc.backgroundVerification.controller;

import java.security.Principal;

import javax.servlet.http.HttpServletRequest;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.servlet.ModelAndView;

import com.bgc.backgroundVerification.model.CompanyProfileInfo;
import com.bgc.backgroundVerification.model.ResponseInfo;
import com.bgc.backgroundVerification.service.AddAndRetriveCookieService;
import com.bgc.backgroundVerification.service.CompanyProfileService;
import com.bgc.backgroundVerification.service.Constants;
import com.bgc.backgroundVerification.util.BgcUIException;

@Controller
@RequestMapping("/companyProfile")
public class CompanyProfileController {
	
	@Autowired
	CompanyProfileService companyProfileService;
	
	
	@GetMapping
	public ModelAndView getAprofile(HttpServletRequest request) {
		
		Principal principal = request.getUserPrincipal();
		String userName =principal.getName();
		ModelAndView model =  new ModelAndView("companyProfile");
		try {
			String jwtToken=AddAndRetriveCookieService.readAdminServletCookie(request);
			CompanyProfileInfo companyProfileInfo =companyProfileService.getAprofile(userName,jwtToken);
			model.addObject("companyProfile",companyProfileInfo);
			return model;
		}catch (Exception e) {
			throw new BgcUIException("exception occered while accessing the data");
		}
			
	}
	
	
	@PostMapping
	public ModelAndView updateProfile(CompanyProfileInfo companyProfileInfo,HttpServletRequest request) {
		Principal principal = request.getUserPrincipal();
		String userName =principal.getName();
		ModelAndView model =  new ModelAndView("companyProfile");
		try {
			String jwtToken=AddAndRetriveCookieService.readAdminServletCookie(request);
			ResponseInfo ProfileInfo =companyProfileService.updateProfile(companyProfileInfo, userName,jwtToken);
			if(ProfileInfo.getAlertMessage().equals(Constants.Success)) {
				model.addObject("Successfull", "success");	
				
			}else {
				model.addObject("message", ProfileInfo.getMessage());
				model.addObject("failed", "fail");	
			}
			
			model.addObject("companyProfile",ProfileInfo.getCompanyProfileInfo());
			return model;
		}catch (Exception e) {
			throw new BgcUIException("exception occered while accessing the data");
		}
	}

}
