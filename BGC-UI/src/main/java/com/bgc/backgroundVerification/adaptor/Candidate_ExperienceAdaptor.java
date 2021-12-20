package com.bgc.backgroundVerification.adaptor;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpEntity;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpMethod;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Component;
import org.springframework.web.client.HttpClientErrorException;
import org.springframework.web.client.RestTemplate;

import com.bgc.backgroundVerification.model.Candidate_EducationDetailsInfo;
import com.bgc.backgroundVerification.model.Candidate_ExperienceInfo;
import com.bgc.backgroundVerification.model.CandidatesInfo;
import com.bgc.backgroundVerification.util.AllUrls;
import com.bgc.backgroundVerification.util.BgcUIException;
import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.databind.ObjectWriter;

@Component
public class Candidate_ExperienceAdaptor {
	
	@Autowired
	RestTemplate restTemplate;
	
	public CandidatesInfo getListOfExperience(String username,String jwtToken) {
		CandidatesInfo candidatesInfo = new CandidatesInfo();
		
		
		String url=AllUrls.GetExperienceList+username;
		
		try {
	HttpHeaders headers = new HttpHeaders();
	headers.setAccept(Arrays.asList(MediaType.APPLICATION_JSON));
	headers.set("Content-Type", "application/json");
	headers.set("Accept", "application/json");
	headers.set("Authorization", "Bearer "+jwtToken+"");
	
	HttpEntity< String> entity = new HttpEntity<>(headers);
	
	ResponseEntity<CandidatesInfo> responceEntity =restTemplate.exchange(url, HttpMethod.GET, entity, CandidatesInfo.class);
	
	candidatesInfo.setCandidateId(responceEntity.getBody().getCandidateId());
	
	List<Candidate_ExperienceInfo> info = new ArrayList<Candidate_ExperienceInfo>();
	
	List<Candidate_ExperienceInfo> experience=responceEntity.getBody().getCandidate_ExperienceInfo();
	
	for(Candidate_ExperienceInfo experienceInfo:experience) {
		
		Candidate_ExperienceInfo candidate_ExperienceInfo = new Candidate_ExperienceInfo();
		candidate_ExperienceInfo.setExperienceId(experienceInfo.getExperienceId());
		candidate_ExperienceInfo.setCandidateId(responceEntity.getBody().getCandidateId());
		candidate_ExperienceInfo.setCompanyName(experienceInfo.getCompanyName());
		candidate_ExperienceInfo.setDesignation(experienceInfo.getDesignation());
		;
		candidate_ExperienceInfo.setJoiningDate(ChangeFormat(experienceInfo.getJoiningDate()));
		candidate_ExperienceInfo.setLastWorkingDate(ChangeFormat(experienceInfo.getLastWorkingDate()));
		candidate_ExperienceInfo.setLocation(experienceInfo.getLocation());
		candidate_ExperienceInfo.setYears(experienceInfo.getYears());
		info.add(candidate_ExperienceInfo);
		
	}
	candidatesInfo.setCandidate_ExperienceInfo(info);
			
		}catch (HttpClientErrorException e) {
		throw new BgcUIException("exception occered while accessing the date"+e.getMessage());
			
		}
		
		return candidatesInfo;
		
	}
	

	public ResponseEntity<String> addExperience(Candidate_ExperienceInfo candidate_ExperienceInfo, String jwtToken)
			throws JsonProcessingException {

		String url =AllUrls.AddExperience;

		try {
			HttpHeaders headers = new HttpHeaders();
			headers.setAccept(Arrays.asList(MediaType.APPLICATION_JSON));
			headers.set("Content-Type", "application/json");
			headers.set("Accept", "application/json");
			headers.set("Authorization", "Bearer " + jwtToken + "");

			ObjectWriter ow = new ObjectMapper().writer().withDefaultPrettyPrinter();
			String json = ow.writeValueAsString(candidate_ExperienceInfo);

			HttpEntity<String> entity = new HttpEntity<>(json, headers);

			ResponseEntity<String> response = restTemplate.exchange(url, HttpMethod.POST, entity, String.class);

			return response;
		} catch (HttpClientErrorException e) {
		
			throw new BgcUIException("exception occered while accessing the date" + e.getMessage());

		}
	}
	
	
	public Candidate_ExperienceInfo getAExperienceExperinceInfo(String id,String jwtToken) {
		Candidate_ExperienceInfo candidate_ExperienceInfo = new Candidate_ExperienceInfo();
		
		
		String url=AllUrls.GetExperienceInfo+id;
		
		try {
	HttpHeaders headers = new HttpHeaders();
	headers.setAccept(Arrays.asList(MediaType.APPLICATION_JSON));
	headers.set("Content-Type", "application/json");
	headers.set("Accept", "application/json");
	headers.set("Authorization", "Bearer "+jwtToken+"");
	
	HttpEntity< String> entity = new HttpEntity<>(headers);
	
	ResponseEntity<Candidate_ExperienceInfo> responceEntity =restTemplate.exchange(url, HttpMethod.GET, entity, Candidate_ExperienceInfo.class);
	
	
		candidate_ExperienceInfo.setExperienceId(responceEntity.getBody().getExperienceId());
		candidate_ExperienceInfo.setCandidateId(responceEntity.getBody().getCandidateId());
		System.out.println(responceEntity.getBody().getExperienceId());
		candidate_ExperienceInfo.setCompanyName(responceEntity.getBody().getCompanyName());
		candidate_ExperienceInfo.setDesignation(responceEntity.getBody().getDesignation());
		candidate_ExperienceInfo.setJoiningDate(responceEntity.getBody().getJoiningDate().substring(0, 10));
		candidate_ExperienceInfo.setLastWorkingDate(responceEntity.getBody().getLastWorkingDate().substring(0, 10));
		candidate_ExperienceInfo.setLocation(responceEntity.getBody().getLocation());
		candidate_ExperienceInfo.setYears(responceEntity.getBody().getYears());
		
		
		}catch (HttpClientErrorException e) {
		throw new BgcUIException("exception occered while accessing the date"+e.getMessage());
			// TODO: handle exception
		}
		
		return candidate_ExperienceInfo;
		
	}
	
	
	public ResponseEntity<String> updateExperienceInfo(Candidate_ExperienceInfo candidate_ExperienceInfo,
			String jwtToken) {
		ResponseEntity<String> response = null;
		String url =AllUrls.UpdateExperience;
		try {
			HttpHeaders headers = new HttpHeaders();
			headers.setAccept(Arrays.asList(MediaType.APPLICATION_JSON));
			headers.set("Accept", "application/json");
			headers.set("Content-Type", "application/json");
			headers.set("Authorization", "Bearer " + jwtToken);
			ObjectWriter ow = new ObjectMapper().writer().withDefaultPrettyPrinter();
			String json = ow.writeValueAsString(candidate_ExperienceInfo);
			HttpEntity<String> entity = new HttpEntity<String>(json, headers);
			response = restTemplate.exchange(url, HttpMethod.POST, entity, String.class);
			return response;
		} catch (Exception e) {
			throw new BgcUIException("Exception occered while accessing the data" + e.getMessage());

			// TODO: handle exception
		}

	}


	public ResponseEntity<String> deleteExperirnceInfo(String emailId, String jwtToken) {
		ResponseEntity<String> response = null;
		String url = AllUrls.DeleteExperience+emailId;
		try {
			HttpHeaders headers = new HttpHeaders();
			headers.setAccept(Arrays.asList(MediaType.APPLICATION_JSON));
			headers.set("Accept", "application/json");
			headers.set("Content-Type", "application/json");
			headers.set("Authorization", "Bearer " + jwtToken);
		
			HttpEntity<String> entity = new HttpEntity<String>(headers);
			response = restTemplate.exchange(url, HttpMethod.DELETE, entity, String.class);
			return response;
		} catch (Exception e) {
			throw new BgcUIException("Exception occered while accessing the data" + e.getMessage());

			// TODO: handle exception
		}
	}
	
	
	public String ChangeFormat(String date) {
		String[] date1=date.split("-");
		return date1[2] +"/"+date1[1]+"/"+date1[0];
		
	}

}
