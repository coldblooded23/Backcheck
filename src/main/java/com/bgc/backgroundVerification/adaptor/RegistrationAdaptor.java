package com.bgc.backgroundVerification.adaptor;

import java.util.Arrays;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpEntity;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpMethod;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.security.web.header.Header;
import org.springframework.stereotype.Component;
import org.springframework.web.client.RestTemplate;

import com.bgc.backgroundVerification.model.CustomerInfo;
import com.bgc.backgroundVerification.util.BgcUIException;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.databind.ObjectWriter;

@Component
public class RegistrationAdaptor {
	
	
	
	@Autowired
	RestTemplate restTemplate;
	
	
	public ResponseEntity<String> addAUser(CustomerInfo customerInfo) {
		ResponseEntity<String> response =null;
		try {
			String url="https://bgcwebservices.azurewebsites.net/Register";
			HttpHeaders headers = new HttpHeaders();
			headers.setAccept(Arrays.asList(MediaType.APPLICATION_JSON));
			headers.set("Accept", "application/json");
			headers.set("Content-Type", "application/json");
		
			ObjectWriter ow = new ObjectMapper().writer().withDefaultPrettyPrinter();
			String json = ow.writeValueAsString(customerInfo);
			
			HttpEntity< String> entity = new HttpEntity<String>(json,headers);
			response =restTemplate.exchange(url, HttpMethod.POST, entity, String.class);
			
			return response;	
			
		}catch (Exception e) {
			throw new  BgcUIException("exception occered while accessing the data"+e.getMessage());
		}
		
	}
	
	
	public ResponseEntity<String> verifyAccount(String id){
		ResponseEntity<String> response =null;
		try {
			String url="https://bgcwebservices.azurewebsites.net/Register/verifyAccount/"+id;
			
			  HttpHeaders headers = new HttpHeaders();
			  headers.setAccept(Arrays.asList(MediaType.APPLICATION_JSON));
			  headers.set("Accept", "application/json"); headers.set("Content-Type",
			  "application/json");
			  
			  HttpEntity< String> entity =new HttpEntity<String>(null,headers);
			 
		response =restTemplate.exchange(url, HttpMethod.POST, entity, String.class);
		
		return response;	
		}catch (Exception e) {
			throw new  BgcUIException("exception occered while accessing the data"+e.getMessage());
				// TODO: handle exception
		}
		
		
	
		
	}
	
	
	
	
	

}
