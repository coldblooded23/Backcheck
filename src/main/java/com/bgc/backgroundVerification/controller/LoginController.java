package com.bgc.backgroundVerification.controller;

import java.security.Principal;

import javax.servlet.http.Cookie;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.authentication.AnonymousAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;

import com.bgc.backgroundVerification.adaptor.GetJwtTokenAdapter;


@Controller
public class LoginController {
	
	@Autowired
	GetJwtTokenAdapter adaptor;
	
	@Autowired
	GetJwtTokenAdapter jwtToken;
	
	@GetMapping("/login")
	public String HomePage(HttpServletRequest request) {
Authentication authentication=SecurityContextHolder.getContext().getAuthentication();

if(authentication==null||authentication instanceof AnonymousAuthenticationToken) {
	return "login";
}

	return "redirect:/";	
	
	}


	@GetMapping("/")	
	public String homePage(HttpServletRequest request,HttpServletResponse response) {
		
		String token=adaptor.getJwtToken(null);
		Cookie cookie = new Cookie("bgc_log", token);
		response.addCookie(cookie);
		return "index";
		
	}

}
