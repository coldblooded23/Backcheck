package com.bgc.backgroundVerification.controller;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;

@Controller
@RequestMapping("/home")
public class HomeController {

	@GetMapping
	public String homePage() {
		return "home";

	}
	
	@GetMapping("/aboutus")
	public String aboutusPage() {
		return "aboutus";

	}
	
	@GetMapping("/contact")
	public String contactPage() {
		return "contact";

	}
}
