package com.bgc.backgroundVerification.controller;


import javax.servlet.http.Cookie;
import javax.servlet.http.HttpServletResponse;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.servlet.ModelAndView;

import com.bgc.backgroundVerification.adaptor.GetJwtTokenAdapter;

@Controller
public class CandidateLoginController {
	
	
@Autowired
GetJwtTokenAdapter adaptor;
	
	@GetMapping("/user/login")
	public ModelAndView candidateLogin(){
	
		return new ModelAndView("candidate/login");
		
	}
	
	
	
	@GetMapping("/user/dashbord")
	public ModelAndView candidateIndexpage(HttpServletResponse response ) {
		
		
		String token=adaptor.getJwtToken(null);
		Cookie cookie = new Cookie("bgc_maxe", token);
		response.addCookie(cookie);
		return new ModelAndView("candidate/index");
		
	}

}
