package com.bgc.backgroundVerification.adaptor;

import java.util.Arrays;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpEntity;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpMethod;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Component;
import org.springframework.web.client.RestTemplate;

import com.bgc.backgroundVerification.model.CandidatesInfo;
import com.bgc.backgroundVerification.model.UserDetailsInfo;
import com.bgc.backgroundVerification.util.AllUrls;
import com.bgc.backgroundVerification.util.BgcUIException;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.databind.ObjectWriter;

@Component
public class PasswordAdaptor {
	
	
	@Autowired
	RestTemplate restTemplate;
	
	public ResponseEntity<String> changePassword(String jwtToken, UserDetailsInfo userInfo){
		ResponseEntity<String> response =null;
		String url="https://bgcwebservices.azurewebsites.net/changePassword";
		try {
			HttpHeaders headers = new HttpHeaders();
			headers.setAccept(Arrays.asList(MediaType.APPLICATION_JSON));
			headers.set("Accept", "application/json");
			headers.set("Content-Type", "application/json");
			headers.set("Authorization", "Bearer "+jwtToken);
			ObjectWriter ow = new ObjectMapper().writer().withDefaultPrettyPrinter();
			String json = ow.writeValueAsString(userInfo);
			HttpEntity<String> entity = new HttpEntity<String>(json,headers);
			response =restTemplate.exchange(url, HttpMethod.POST, entity, String.class);
			return response;
		}catch (Exception e) {
			System.out.println(e.getMessage());
		throw new BgcUIException("Exception occered while accessing the data"+e.getMessage());	
		}
		
	}
	
	public ResponseEntity<String> sendForgetPasswordlink(String id){
		ResponseEntity<String> response =null;
		//	String url="http://localhost:9091/forgetPassword/"+id;
		//String url="https://bgcwebservices.azurewebsites.net/forgetPassword/"+id;
	String url=AllUrls.ForgotPasswordLink+id;
		try {
			HttpHeaders headers = new HttpHeaders();
			headers.setAccept(Arrays.asList(MediaType.APPLICATION_JSON));
			headers.set("Accept", "application/json");
			headers.set("Content-Type", "application/json");
			HttpEntity<String> entity = new HttpEntity<String>(headers);
			response =restTemplate.exchange(url, HttpMethod.GET, entity, String.class);
			return response;
		}catch (Exception e) {
			System.out.println(e.getMessage());
		throw new BgcUIException("Exception occered while accessing the data"+e.getMessage());	
		}
		
	}
	
	
	public ResponseEntity<String> updateForgetPassword(UserDetailsInfo uerDetailsInfo){
		ResponseEntity<String> response =null;
		//	String url="http://localhost:9091/forgetPassword/verifylink";
		//String url="https://bgcwebservices.azurewebsites.net/forgetPassword/verifylink";
		String url=AllUrls.ForgotPassword;
		try {
			
//			String userdetail ="{\r\n"
//					+ "    \"vCode\":\""+uerDetailsInfo.getvCode()+"\",\r\n"
//					+ "    \"password\":\""+uerDetailsInfo.getPassword()+"\"\r\n"
//					+ "}";
			HttpHeaders headers = new HttpHeaders();
			headers.setAccept(Arrays.asList(MediaType.APPLICATION_JSON));
			headers.set("Accept", "application/json");
			headers.set("Content-Type", "application/json");
			
			
			  ObjectWriter ow = new ObjectMapper().writer().withDefaultPrettyPrinter();
			  String json = ow.writeValueAsString(uerDetailsInfo);
			 
			HttpEntity<String> entity = new HttpEntity<String>(json,headers);
			response =restTemplate.exchange(url, HttpMethod.POST, entity, String.class);
			return response;
		}catch (Exception e) {
			System.out.println(e.getMessage());
		throw new BgcUIException("Exception occered while accessing the data"+e.getMessage());	
		}
		
	}
	
	public ResponseEntity<String> sendCandidateForgetPasswordlink(String id){
		ResponseEntity<String> response =null;
		//	String url="http://localhost:9091/forgetPassword/"+id;
	String url=AllUrls.CandidateForgotPasswordLink+id;
		try {
			HttpHeaders headers = new HttpHeaders();
			headers.setAccept(Arrays.asList(MediaType.APPLICATION_JSON));
			headers.set("Accept", "application/json");
			headers.set("Content-Type", "application/json");
			HttpEntity<String> entity = new HttpEntity<String>(headers);
			response =restTemplate.exchange(url, HttpMethod.GET, entity, String.class);
			return response;
		}catch (Exception e) {
			System.out.println(e.getMessage());
		throw new BgcUIException("Exception occered while accessing the data"+e.getMessage());	
		}
		
	}
	
	public ResponseEntity<String> updateCandidateForgetPassword(CandidatesInfo candidatesInfo){
		ResponseEntity<String> response =null;
		String url=AllUrls.CandidateForgotPassword;
		try {

			HttpHeaders headers = new HttpHeaders();
			headers.setAccept(Arrays.asList(MediaType.APPLICATION_JSON));
			headers.set("Accept", "application/json");
			headers.set("Content-Type", "application/json");
			
			
			  ObjectWriter ow = new ObjectMapper().writer().withDefaultPrettyPrinter();
			  String json = ow.writeValueAsString(candidatesInfo);
			 
			HttpEntity<String> entity = new HttpEntity<String>(json,headers);
			response =restTemplate.exchange(url, HttpMethod.POST, entity, String.class);
			return response;
		}catch (Exception e) {
			System.out.println(e.getMessage());
		throw new BgcUIException("Exception occered while accessing the data"+e.getMessage());	
		}
		
	}
	
	
	
	public ResponseEntity<String> changeCandidatePassword(String jwtToken, CandidatesInfo candidatesInfo){
		ResponseEntity<String> response =null;
		String url="https://bgcwebservices.azurewebsites.net/candidate/changepasword";
		//	String url="http://localhost:9091/candidate/changepasword";
		try {
			HttpHeaders headers = new HttpHeaders();
			headers.setAccept(Arrays.asList(MediaType.APPLICATION_JSON));
			headers.set("Accept", "application/json");
			headers.set("Content-Type", "application/json");
			headers.set("Authorization", "Bearer "+jwtToken);
			ObjectWriter ow = new ObjectMapper().writer().withDefaultPrettyPrinter();
			String json = ow.writeValueAsString(candidatesInfo);
			HttpEntity<String> entity = new HttpEntity<String>(json,headers);
			response =restTemplate.exchange(url, HttpMethod.POST, entity, String.class);
			return response;
		}catch (Exception e) {
			System.out.println(e.getMessage());
		throw new BgcUIException("Exception occered while accessing the data"+e.getMessage());	
		}
		
	}
	
	public ResponseEntity<String> sendForgetUserNameLink(CandidatesInfo candidatesInfo){
		ResponseEntity<String> response=null;
		String url= AllUrls.ForgotUserNameLink;
		System.out.println(url);
		try {
				HttpHeaders headers = new HttpHeaders();
				headers.setAccept(Arrays.asList(MediaType.APPLICATION_JSON));
				headers.set("Accept", "application/json");
				headers.set("Content-Type", "application/json");
				
				  ObjectWriter ow = new ObjectMapper().writer().withDefaultPrettyPrinter();
				  String json = ow.writeValueAsString(candidatesInfo);
				HttpEntity<String> entity = new HttpEntity<String>(json,headers);
				System.out.println(entity);
				response =restTemplate.exchange(url, HttpMethod.POST, entity, String.class);
				System.out.println("response ="+response);
				return response;
		}catch (Exception e) {
			System.out.println(e.getMessage()); 
		throw new BgcUIException("Exception occered while accessing the data"+e.getMessage());
		}
	}

}
