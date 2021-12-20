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
import com.bgc.backgroundVerification.model.CandidatesInfo;
import com.bgc.backgroundVerification.model.ResponseInfo;
import com.bgc.backgroundVerification.service.AddAndRetriveCookieService;
import com.bgc.backgroundVerification.service.Candidate_EducationDetailsService;
import com.bgc.backgroundVerification.service.Constants;
import com.bgc.backgroundVerification.util.BgcUIException;

@Controller
public class Candidate_EducationDetailsController {
//edification	
	
	@Autowired
	Candidate_EducationDetailsService  educationDetailsService;
	
	
	/*
	 * @GetMapping("/user/education_info") public ModelAndView getEducationPage(
	 * HttpServletRequest request) { Principal principal
	 * =request.getUserPrincipal(); try { ModelAndView model = new
	 * ModelAndView("candidate/educationinfo"); String
	 * jwtToken=AddAndRetriveCookieService.readCandiddateServletCookie(request); CandidatesInfo
	 * educationInfo
	 * =educationDetailsService.getEducationDetails(principal.getName(),jwtToken);
	 * System.out.println(educationInfo.getCandidateId());
	 * model.addObject("education", educationInfo); return model; }catch (Exception
	 * e) { // TODO: handle exception throw new
	 * BgcUIException("exceotion occered while accessing the data"+e.getMessage());
	 * }
	 * 
	 * }
	 */
	
	
	@GetMapping("/user/edification")
	public ModelAndView getPage( HttpServletRequest request) {
		Principal principal =request.getUserPrincipal();
		try {
			ModelAndView model =  new ModelAndView("candidate/edification");
			String jwtToken=AddAndRetriveCookieService.readCandiddateServletCookie(request);
			CandidatesInfo educationInfo =educationDetailsService.getEducationDetails(principal.getName(), jwtToken);
			System.out.println(educationInfo.getCandidateId());
			model.addObject("education", educationInfo);
			return model;
		}catch (Exception e) {
			// TODO: handle exception
			throw new BgcUIException("exceotion occered while accessing the data"+e.getMessage());
		}
		
	}
	
	
	
	@GetMapping("/user/education_info/{id}")
	public ModelAndView getEducationEdit( @PathVariable Long id,HttpServletRequest request) {
		try {
			ModelAndView model =  new ModelAndView("candidate/editeducation");
			String jwtToken=AddAndRetriveCookieService.readCandiddateServletCookie(request);
			Candidate_EducationDetailsInfo educationInfo =educationDetailsService.getEducationInfoById(id,jwtToken);
			model.addObject("education", educationInfo);
			return model;
		}catch (Exception e) {
			// TODO: handle exception
			throw new BgcUIException("exceotion occered while accessing the data"+e.getMessage());
		}
		
	}
	
	
	
	
	@PostMapping("/user/education_info")
	public ModelAndView addEducationInfo(Candidate_EducationDetailsInfo candidate_EducationDetailsInfo,HttpServletRequest request) {
		Principal principal = request.getUserPrincipal();
		String userName =principal.getName();
		ModelAndView model =  new ModelAndView("candidate/edification");
		try {
			String jwtToken=AddAndRetriveCookieService.readCandiddateServletCookie(request);
			ResponseInfo ProfileInfo =educationDetailsService.addEducationDetailsInfo(candidate_EducationDetailsInfo, userName,jwtToken);
			if(ProfileInfo.getAlertMessage().equals(Constants.AuthenticationSuccess)) {
				model.addObject("AddSuccess", "Success");	
				
			}else {
				model.addObject("message", ProfileInfo.getMessage());
				model.addObject("Addfailed", "failed");	
			}
			
			model.addObject("education",ProfileInfo.getCandidatesInfo());
			return model;
		}catch (Exception e) {
			throw new BgcUIException("exception occered while accessing the data"+e.getMessage());
		}
		
	}
	
	@PostMapping("/user/updateeducation")
	public ModelAndView updateEducationInfo(Candidate_EducationDetailsInfo candidate_EducationDetailsInfo,HttpServletRequest request,RedirectAttributes redirectAttributes) {
		Principal principal = request.getUserPrincipal();
		String userName =principal.getName();
		
		try {
			String jwtToken=AddAndRetriveCookieService.readCandiddateServletCookie(request);
			ResponseInfo ProfileInfo =educationDetailsService.UpdateEducationDetailsInfo(candidate_EducationDetailsInfo, userName,jwtToken);
			if(ProfileInfo.getAlertMessage().equals(Constants.AuthenticationSuccess)) {
				ModelAndView model =  new ModelAndView("redirect:/user/edification");
				redirectAttributes.addFlashAttribute("updateSuccess", "Success");	
				return model;
				
			}else {
				ModelAndView model =  new ModelAndView("redirect:/user/education_info/"+candidate_EducationDetailsInfo.getEducationId());
				redirectAttributes.addFlashAttribute("message", ProfileInfo.getMessage());
				redirectAttributes.addFlashAttribute("updatefailed", "failed");	
				return model;
			}
			
			
		}catch (Exception e) {
			throw new BgcUIException("exception occered while accessing the data"+e.getMessage());
		}
		
	}
	
	
	@PostMapping("/user/education/delete")
	public ModelAndView deleteEducationInfo( ResponseInfo responseInfo,RedirectAttributes redirectAttributes,HttpServletRequest request) throws ParseException {
		String jwtToken=AddAndRetriveCookieService.readCandiddateServletCookie(request);
		ResponseInfo response=educationDetailsService.deleteEducationInfo(responseInfo.getEmailId(),jwtToken);
				ModelAndView model =  new ModelAndView("redirect:/user/edification");
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
