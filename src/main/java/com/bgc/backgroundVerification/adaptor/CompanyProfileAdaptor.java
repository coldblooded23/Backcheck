package com.bgc.backgroundVerification.adaptor;

import java.util.Arrays;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpEntity;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpMethod;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Component;
import org.springframework.web.client.RestTemplate;

import com.bgc.backgroundVerification.model.CompanyProfileInfo;
import com.bgc.backgroundVerification.util.AllUrls;
import com.bgc.backgroundVerification.util.BgcUIException;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.databind.ObjectWriter;

@Component
public class CompanyProfileAdaptor {
	
	
	@Autowired
	RestTemplate restTemplate;
	
	
	public CompanyProfileInfo getAProfile(String jwtToken,String id){
		
		
		String url=AllUrls.GetAprofile+id;
		CompanyProfileInfo companyInfo=new CompanyProfileInfo();	
		try {
			HttpHeaders headers = new HttpHeaders();
			headers.setAccept(Arrays.asList(MediaType.APPLICATION_JSON));
			headers.set("Accept", "application/json");
			headers.set("Content-Type", "application/json");
			headers.set("Authorization", "Bearer "+jwtToken);
			
	
			
			HttpEntity< String> entity = new HttpEntity<String>(null, headers);
			
			ResponseEntity<CompanyProfileInfo> company = restTemplate.exchange(url, HttpMethod.GET, entity, CompanyProfileInfo.class);
			
			if(company.getBody()!=null) {
				companyInfo.setCustomerId(company.getBody().getCustomerId());	
				companyInfo.setCompanyName(company.getBody().getCompanyName());
				companyInfo.setEmail(company.getBody().getEmail());
				companyInfo.setCompanyWebsite(company.getBody().getCompanyWebsite());
				companyInfo.setContactNumber(company.getBody().getContactNumber());
				companyInfo.setAddress(company.getBody().getAddress());
				companyInfo.setState(company.getBody().getState());
				companyInfo.setCountry(company.getBody().getCountry());
				companyInfo.setZipCode(company.getBody().getZipCode());		
			}
			
		}catch (Exception e) {
		
			throw new BgcUIException("Exception occered while accessing the data"+e.getMessage());
		}
		
		return companyInfo;
		
	}
	
	
	public ResponseEntity<String> updateProfile(CompanyProfileInfo companyProfileInfo, String jwtToken){
		ResponseEntity<String> response =null;
		String url="https://bgcwebservices.azurewebsites.net/UpdateAprofile";
	try {
		HttpHeaders headers = new HttpHeaders();
		headers.setAccept(Arrays.asList(MediaType.APPLICATION_JSON));
		headers.set("Accept", "application/json");
		headers.set("Content-Type", "application/json");
		headers.set("Authorization", "Bearer "+jwtToken);
		
		ObjectWriter ow = new ObjectMapper().writer().withDefaultPrettyPrinter();
		String json = ow.writeValueAsString(companyProfileInfo);
		
		HttpEntity<String> entity = new HttpEntity<String>(json,headers);
		response =restTemplate.exchange(url, HttpMethod.POST, entity, String.class);
		return response;
	}catch (Exception e) {
		System.out.println(e.getMessage());
	throw new BgcUIException("Exception occered while accessing the data"+e.getMessage());	
	}

}
}
