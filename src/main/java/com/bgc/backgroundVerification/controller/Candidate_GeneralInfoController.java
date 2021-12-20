package com.bgc.backgroundVerification.controller;

import java.security.Principal;

import javax.servlet.http.Cookie;
import javax.servlet.http.HttpServletRequest;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.servlet.ModelAndView;

import com.bgc.backgroundVerification.model.CandidatesInfo;
import com.bgc.backgroundVerification.model.EmployeeInfo;
import com.bgc.backgroundVerification.model.ResponseInfo;
import com.bgc.backgroundVerification.service.AddAndRetriveCookieService;
import com.bgc.backgroundVerification.service.CandidateGeneralInfoService;
import com.bgc.backgroundVerification.service.Constants;
import com.bgc.backgroundVerification.util.BgcUIException;

@Controller
public class Candidate_GeneralInfoController {
	
	@Autowired
	CandidateGeneralInfoService candidateGeneralInfoService;
	
	
	@ModelAttribute("candidateInfo")
	public CandidatesInfo CandidatesInfo() {
		return new CandidatesInfo();
	}
	
	
	@GetMapping("/user/generalInfo")
	public ModelAndView getCandidategeneralInfo(HttpServletRequest request) {
		Principal principal = request.getUserPrincipal();
		
		String jwtToken=AddAndRetriveCookieService.readCandiddateServletCookie(request);
		ModelAndView model = new ModelAndView("candidate/generalinfo");
		CandidatesInfo candidatesInfo = candidateGeneralInfoService.getCandidateInfo(principal.getName(),jwtToken);
		model.addObject("candidateInfo", candidatesInfo);
		return model;
	}
	
	@PostMapping("/user/generalInfo")
	public ModelAndView updateCandidateInfo(@ModelAttribute("candidateInfo")CandidatesInfo candidatesInfo,HttpServletRequest request) {
		Principal principal = request.getUserPrincipal();
		String userName =principal.getName();
		ModelAndView model =  new ModelAndView("candidate/generalinfo");
		try {
			String jwtToken=AddAndRetriveCookieService.readCandiddateServletCookie(request);
			ResponseInfo ProfileInfo =candidateGeneralInfoService.updateCandidate(candidatesInfo, userName,jwtToken);
			if(ProfileInfo.getAlertMessage().equals(Constants.AuthenticationSuccess)) {
				model.addObject("Successfull", "success");	
				
			}else {
				model.addObject("message", ProfileInfo.getMessage());
				model.addObject("failed", "fail");	
			}
			
			model.addObject("candidateInfo",ProfileInfo.getCandidatesInfo());
			return model;
		}catch (Exception e) {
			throw new BgcUIException("exception occered while accessing the data");
		}
		
	}
	
	
}
