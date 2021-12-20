package com.bgc.backgroundVerification.adaptor;

import java.util.ArrayList;
import java.util.Arrays;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpEntity;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpMethod;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Component;
import org.springframework.web.client.RestTemplate;

import com.bgc.backgroundVerification.model.SetupModel;
import com.bgc.backgroundVerification.util.AllUrls;
import com.bgc.backgroundVerification.util.BgcUIException;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.databind.ObjectWriter;


@Component
public class SetupAdaptor {
	
	
	@Autowired
	RestTemplate restTemplate;
	
	public SetupModel getSetupDetails(String jwtToken,String id) {
		ResponseEntity<SetupModel> response=null;
		String url=AllUrls.Setup+id;
		try {
			HttpHeaders headers = new HttpHeaders();
			headers.setAccept(Arrays.asList(MediaType.APPLICATION_JSON));
			headers.set("Accept", "application/json");
			headers.set("Content-Type", "application/json");
			headers.set("Authorization", "Bearer "+jwtToken);
			
			HttpEntity<String> entity = new HttpEntity<>(headers);
			
			response=restTemplate.exchange(url, HttpMethod.GET, entity, SetupModel.class);
			SetupModel setup = new SetupModel();	
			
			setup.setCustomerId(response.getBody().getCustomerId());
			setup.setEmailId(response.getBody().getEmailId());
			setup.setPassword(response.getBody().getPassword());
			setup.setHost(response.getBody().getHost());
			setup.setPort(response.getBody().getPort());
			return setup;
		}catch (Exception e) {
			System.out.println(e.getMessage());
			throw new BgcUIException("exception occered while accessing the data"+e.getMessage());
		}
	
		
	}
	
	public ResponseEntity<String> updateSetup(String jwtToken,SetupModel setupModel){
		ResponseEntity<String> response=null;
		String url="http://bgcwebservices.azurewebsites.net/setup";
		try {
			HttpHeaders headers = new HttpHeaders();
			headers.setAccept(Arrays.asList(MediaType.APPLICATION_JSON));
			headers.set("Accept", "application/json");
			headers.set("Content-Type", "application/json");
			headers.set("Authorization", "Bearer "+jwtToken);
			ObjectWriter ow = new ObjectMapper().writer().withDefaultPrettyPrinter();
			String json=ow.writeValueAsString(setupModel);
			HttpEntity< String> entity = new HttpEntity<String>(json, headers);
			response=restTemplate.exchange(url, HttpMethod.POST, entity, String.class);
			return response;
		}catch (Exception e) {
		
			throw new BgcUIException("exception occered while accessing the data"+e.getMessage());
		}

		
	}
	

}
