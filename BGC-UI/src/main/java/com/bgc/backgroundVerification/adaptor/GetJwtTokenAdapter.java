package com.bgc.backgroundVerification.adaptor;

import java.util.Arrays;

import org.json.JSONObject;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.http.HttpEntity;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpMethod;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Component;
import org.springframework.web.client.RestTemplate;

import com.bgc.backgroundVerification.model.LoginInfo;
import com.bgc.backgroundVerification.service.Constants;
import com.bgc.backgroundVerification.util.AllUrls;
import com.bgc.backgroundVerification.util.BgcUIException;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.databind.ObjectWriter;

@Component
public class GetJwtTokenAdapter {
	
	
	@Autowired
	RestTemplate restTemplate;
	
	
	@Autowired
	CallLoginUserDetails CallLoginUserDetails;
	
	@Value("${aesSecretKey}")
	private String secretKey;

	public String getJwtToken(String Email) {
		
		
		
		/*
		 * UserDetailsInfo userDetailsInfo = CallLoginUserDetails.getUserDetails(Email);
		 */
		ResponseEntity<String> response = null;
		JSONObject jsonObject = null;
		try {
			String url=AllUrls.GenerateToken;
			LoginInfo login = new LoginInfo();
			login.setUsername(Constants.username);
			login.setPassword(Constants.password);
			HttpHeaders headers = new HttpHeaders();
			headers.setAccept(Arrays.asList(MediaType.APPLICATION_JSON));
			headers.set("Accept", "application/json");
			headers.set("Content-Type", "application/json");
			ObjectWriter ow = new ObjectMapper().writer().withDefaultPrettyPrinter();
			String json = ow.writeValueAsString(login);
			HttpEntity<String> entity = new HttpEntity<>(json, headers);
			response =restTemplate.exchange(url, HttpMethod.POST, entity, String.class);
			jsonObject = new JSONObject(response.getBody());
			String accessToken = (String) jsonObject.get("response");
			return accessToken;
		}catch (Exception e) {
			throw new BgcUIException("exception occered while accessing the data"+e.getMessage());
		}
		
	
		
	}

}
