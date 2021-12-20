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

import com.bgc.backgroundVerification.model.Candidate_GeneralInfo;
import com.bgc.backgroundVerification.model.CandidatesInfo;
import com.bgc.backgroundVerification.model.CustomerInfo;
import com.bgc.backgroundVerification.util.AllUrls;
import com.bgc.backgroundVerification.util.BgcUIException;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.databind.ObjectWriter;

@Component
public class Candidate_GeneralInfoAdaptor {
	
	@Autowired
	RestTemplate restTemplate;
	
	
	public CandidatesInfo getCandidateGeneralInfo(String id, String jwtToken) {
		
		ResponseEntity<CandidatesInfo> response=null;
		CandidatesInfo candidatesInfo = new CandidatesInfo();
		//String url="http://localhost:9091/candidategeneralInfo/"+id;
		String url=AllUrls.CandidateGeneralInfo+id;
	try {	
		HttpHeaders headers = new HttpHeaders();
		headers.setAccept(Arrays.asList(MediaType.APPLICATION_JSON));
		headers.set("Accept", "application/json");
		headers.set("Content-Type", "application/json");
		headers.set("Authorization", "Bearer " + jwtToken);
		
		HttpEntity<String> entity = new HttpEntity<String>(headers);
		response = restTemplate.exchange(url, HttpMethod.GET, entity, CandidatesInfo.class);
		candidatesInfo.setCandidateId(response.getBody().getCandidateId());
		Candidate_GeneralInfo candidateGeneralInfo= response.getBody().getCandidate_GeneralInfo();
		Candidate_GeneralInfo info = new Candidate_GeneralInfo();
		info.setMaritalStatus(candidateGeneralInfo.getMaritalStatus());
		info.setGender(candidateGeneralInfo.getGender());
		info.setAddress(candidateGeneralInfo.getAddress());
		info.setCity(candidateGeneralInfo.getCity());
		info.setState(candidateGeneralInfo.getState());
		info.setCountry(candidateGeneralInfo.getCountry());
		info.setPincode(candidateGeneralInfo.getPincode());
		info.setSsn(candidateGeneralInfo.getSsn());
		candidatesInfo.setCandidate_GeneralInfo(info);
	}catch (Exception e) {
		throw new BgcUIException("exception occered while accessing the data" + e.getMessage());
	}
		return candidatesInfo;
	}
	
	
	public ResponseEntity<String> updateCandidateInfo(String jwtToken,CandidatesInfo candidatesInfo){
		ResponseEntity<String> response=null;
		//String url="http://localhost:9091/updateCandidategeneralInfo";
		String url=AllUrls.UpdateCandidateGeneralInfo;
		try {
			HttpHeaders headers = new HttpHeaders();
			headers.setAccept(Arrays.asList(MediaType.APPLICATION_JSON));
			headers.set("Accept", "application/json");
			headers.set("Content-Type", "application/json");
			headers.set("Authorization", "Bearer " + jwtToken);
			ObjectWriter ow = new ObjectMapper().writer().withDefaultPrettyPrinter();
			String json = ow.writeValueAsString(candidatesInfo);
			HttpEntity<String> entity = new HttpEntity<String>(json, headers);
			response = restTemplate.exchange(url, HttpMethod.POST, entity, String.class);

			return response;
		} catch (Exception e) {
			throw new BgcUIException("exception occered while accessing the data" + e.getMessage());
		}
		
	}

}
