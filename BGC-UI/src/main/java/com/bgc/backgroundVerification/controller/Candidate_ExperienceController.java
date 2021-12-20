package com.bgc.backgroundVerification.controller;

import java.security.Principal;
import java.text.ParseException;

import javax.servlet.http.HttpServletRequest;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.servlet.ModelAndView;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import com.bgc.backgroundVerification.model.Candidate_EducationDetailsInfo;
import com.bgc.backgroundVerification.model.Candidate_ExperienceInfo;
import com.bgc.backgroundVerification.model.CandidatesInfo;
import com.bgc.backgroundVerification.model.ResponseInfo;
import com.bgc.backgroundVerification.service.AddAndRetriveCookieService;
import com.bgc.backgroundVerification.service.Candidate_ExperienceService;
import com.bgc.backgroundVerification.service.Constants;
import com.bgc.backgroundVerification.util.BgcUIException;

@Controller
public class Candidate_ExperienceController {
	
	
	@Autowired
	Candidate_ExperienceService candidate_ExperienceService;
	
	
	@GetMapping("/user/experience")
	public ModelAndView getexperiencePage( HttpServletRequest request) {
		Principal principal =request.getUserPrincipal();
		try {
			ModelAndView model =  new ModelAndView("candidate/experience");
			String jwtToken=AddAndRetriveCookieService.readCandiddateServletCookie(request);
			CandidatesInfo experienceInfo =candidate_ExperienceService.listOfExperience(principal.getName(),jwtToken);
			System.out.println(experienceInfo.getCandidateId());
			model.addObject("candidate", experienceInfo);
			return model;
		}catch (Exception e) {
			// TODO: handle exception
			throw new BgcUIException("exceotion occered while accessing the data"+e.getMessage());
		}
			
	}
	
	
	@PostMapping("/user/experience")
	public ModelAndView addExperience( Candidate_ExperienceInfo candidate_ExperienceInfo,HttpServletRequest request) {
	
		Principal principal = request.getUserPrincipal();
		String userName =principal.getName();
		ModelAndView model =  new ModelAndView("candidate/experience");
		try {
			String jwtToken=AddAndRetriveCookieService.readCandiddateServletCookie(request);
			ResponseInfo ProfileInfo =candidate_ExperienceService.addEducationDetailsInfo(candidate_ExperienceInfo, userName,jwtToken);
			if(ProfileInfo.getAlertMessage().equals(Constants.AuthenticationSuccess)) {
				model.addObject("AddSuccess", "Success");	
				
			}else {
				model.addObject("message", ProfileInfo.getMessage());
				model.addObject("Addfailed", "failed");	
			}
			
			model.addObject("candidate",ProfileInfo.getCandidatesInfo());
			return model;
		}catch (Exception e) {
			throw new BgcUIException("exception occered while accessing the data"+e.getMessage());
		}
			
	}
	
	@GetMapping("/user/experience/{id}")
	public ModelAndView getexperienceInfo( @PathVariable String id,HttpServletRequest request) {
		try {
			ModelAndView model =  new ModelAndView("candidate/editexperience");
			String jwtToken=AddAndRetriveCookieService.readCandiddateServletCookie(request);
			Candidate_ExperienceInfo experienceInfo =candidate_ExperienceService.getAExperienceInfo(id,jwtToken);
			System.out.println(experienceInfo.getCandidateId());
			model.addObject("experience", experienceInfo);
			return model;
		}catch (Exception e) {
			// TODO: handle exception
			throw new BgcUIException("exceotion occered while accessing the data"+e.getMessage());
		}
			
	}
	

	
	@PostMapping("/user/updateExperience")
	public ModelAndView updateExperienceInfo(HttpServletRequest request,Candidate_ExperienceInfo candidate_ExperienceInfo,RedirectAttributes redirectAttributes) {
	
		try {
			String jwtToken=AddAndRetriveCookieService.readCandiddateServletCookie(request);
			ResponseInfo ProfileInfo =candidate_ExperienceService.UpdateExperienceInfo(candidate_ExperienceInfo,jwtToken);
			if(ProfileInfo.getAlertMessage().equals(Constants.AuthenticationSuccess)) {
				ModelAndView model =  new ModelAndView("redirect:/user/experience");
				redirectAttributes.addFlashAttribute("updateSuccess", "Success");	
				return model;
				
			}else {
				ModelAndView model =  new ModelAndView("redirect:/user/experience/"+candidate_ExperienceInfo.getExperienceId());
				redirectAttributes.addFlashAttribute("message", ProfileInfo.getAlertMessage());
				redirectAttributes.addFlashAttribute("updatefailed", "failed");	
				return model;
			}
			
			
		}catch (Exception e) {
			throw new BgcUIException("exception occered while accessing the data"+e.getMessage());
		}
		
	}
	
	@PostMapping("/user/experience/delete")
	public ModelAndView deleteExperienceInfo( ResponseInfo responseInfo,HttpServletRequest request,RedirectAttributes redirectAttributes) throws ParseException {
		String jwtToken=AddAndRetriveCookieService.readCandiddateServletCookie(request);
		ResponseInfo response=candidate_ExperienceService.deleteExperirnceInfo(responseInfo.getEmailId(),jwtToken);
		ModelAndView model =  new ModelAndView("redirect:/user/experience");
		  if(response.getAlertMessage().equals(Constants.AuthenticationSuccess))
		  {
			  redirectAttributes.addFlashAttribute("DeleteSuccess", "Success");
		   System.out.println("11111111");
		  }else {
			  redirectAttributes.addFlashAttribute("Addfailed", "failed");
			  redirectAttributes.addFlashAttribute("message",response.getAlertMessage());
		  }
		 
		return model;
		
	}


	}
	
	


