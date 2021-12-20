package com.bgc.backgroundVerification.service;

import java.util.Arrays;

import javax.servlet.http.Cookie;
import javax.servlet.http.HttpServletRequest;

public class AddAndRetriveCookieService {

public static String readCandiddateServletCookie(HttpServletRequest request){
		
		
	String cookieName="bgc_maxe";
	return Arrays.stream(request.getCookies())
	        .filter(c -> c.getName().equals(cookieName))
		     .findFirst()
		     .map(Cookie::getValue)
		     .orElse(null);
			  
			}

public static String readAdminServletCookie(HttpServletRequest request)
{	
	String cookieName="bgc_log";
	return Arrays.stream(request.getCookies())
	       .filter(c -> c.getName().equals(cookieName))
	       .findFirst()
	       .map(Cookie::getValue)
	       .orElse(null);
}

}
