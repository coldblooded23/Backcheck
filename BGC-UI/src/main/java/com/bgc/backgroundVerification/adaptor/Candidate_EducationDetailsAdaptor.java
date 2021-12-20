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
import org.springframework.web.client.RestTemplate;

import com.bgc.backgroundVerification.model.Candidate_EducationDetailsInfo;
import com.bgc.backgroundVerification.model.CandidatesInfo;
import com.bgc.backgroundVerification.util.AllUrls;
import com.bgc.backgroundVerification.util.BgcUIException;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.databind.ObjectWriter;

@Component
public class Candidate_EducationDetailsAdaptor {

	@Autowired
	RestTemplate restTemplate;

	public CandidatesInfo getEducationDetails(String username, String jwtToken) {
		String url=AllUrls.EducationInfoList+username;
		CandidatesInfo candidatesInfo = new CandidatesInfo();
		try {
			HttpHeaders headers = new HttpHeaders();
			headers.setAccept(Arrays.asList(MediaType.APPLICATION_JSON));
			headers.set("Accept", "application/json");
			headers.set("Content-Type", "application/json");
			headers.set("Authorization", "Bearer " + jwtToken);

			HttpEntity<String> entity = new HttpEntity<>(headers);

			ResponseEntity<CandidatesInfo> response = restTemplate.exchange(url, HttpMethod.GET, entity,
					CandidatesInfo.class);
			if (response != null) {

				candidatesInfo.setCandidateId(response.getBody().getCandidateId());
				List<Candidate_EducationDetailsInfo> candidate_EducationDetailsInfo = new ArrayList<Candidate_EducationDetailsInfo>();
				List<Candidate_EducationDetailsInfo> info = response.getBody().getCandidate_EducationDetailsInfo();
				for (Candidate_EducationDetailsInfo education : info) {
					Candidate_EducationDetailsInfo educationDetailsInfo = new Candidate_EducationDetailsInfo();
					educationDetailsInfo.setEducationId(education.getEducationId());
					educationDetailsInfo.setDegree(education.getDegree());
					educationDetailsInfo.setPassingYear(education.getPassingYear());
					educationDetailsInfo.setSpecialization(education.getSpecialization());
					educationDetailsInfo.setUniversityName(education.getUniversityName());
					candidate_EducationDetailsInfo.add(educationDetailsInfo);

				}

				candidatesInfo.setCandidate_EducationDetailsInfo(candidate_EducationDetailsInfo);
				return candidatesInfo;
			}
			return candidatesInfo;
		} catch (Exception e) {
			throw new BgcUIException("Exception occered while accessing the data" + e.getMessage());
		}

	}
	
	public Candidate_EducationDetailsInfo getEducationInfoById(Long id,String jwtToken) {
		String url=AllUrls.EducationInfo+id;
		Candidate_EducationDetailsInfo educationInfo= new Candidate_EducationDetailsInfo();
		
		try {
			HttpHeaders headers = new HttpHeaders();
			headers.setAccept(Arrays.asList(MediaType.APPLICATION_JSON));
			headers.set("Accept", "application/json");
			headers.set("Content-Type", "application/json");
			headers.set("Authorization", "Bearer " + jwtToken);
			
			HttpEntity<String> entity = new HttpEntity<>(headers);
			
			ResponseEntity<Candidate_EducationDetailsInfo> response = restTemplate.exchange(url, HttpMethod.GET, entity,Candidate_EducationDetailsInfo.class);
			educationInfo.setEducationId(response.getBody().getEducationId());
			educationInfo.setCandidateId(response.getBody().getCandidateId());
			educationInfo.setDegree(response.getBody().getDegree());
			educationInfo.setPassingYear(response.getBody().getPassingYear());
			educationInfo.setUniversityName(response.getBody().getUniversityName());
			educationInfo.setSpecialization(response.getBody().getSpecialization());
			return educationInfo;
		}catch (Exception e) {
			// TODO: handle exception
			throw new BgcUIException("Exception occered while accessing the data" + e.getMessage());
			
		}
		
		
	}

	public ResponseEntity<String> addEducationInfo(Candidate_EducationDetailsInfo candidate_EducationDetailsInfo,
			String jwtToken) {
		ResponseEntity<String> response = null;
		String url = AllUrls.AddEducationInfo;
		try {
			HttpHeaders headers = new HttpHeaders();
			headers.setAccept(Arrays.asList(MediaType.APPLICATION_JSON));
			headers.set("Accept", "application/json");
			headers.set("Content-Type", "application/json");
			headers.set("Authorization", "Bearer " + jwtToken);
			ObjectWriter ow = new ObjectMapper().writer().withDefaultPrettyPrinter();
			String json = ow.writeValueAsString(candidate_EducationDetailsInfo);
			HttpEntity<String> entity = new HttpEntity<String>(json, headers);
			response = restTemplate.exchange(url, HttpMethod.POST, entity, String.class);
			return response;
		} catch (Exception e) {
			throw new BgcUIException("Exception occered while accessing the data" + e.getMessage());

		}

	}
	
	
	public ResponseEntity<String> updateEducationInfo(Candidate_EducationDetailsInfo candidate_EducationDetailsInfo,
			String jwtToken) {
		ResponseEntity<String> response = null;
		String url =AllUrls.UpdateEducationInfo;
		try {
			HttpHeaders headers = new HttpHeaders();
			headers.setAccept(Arrays.asList(MediaType.APPLICATION_JSON));
			headers.set("Accept", "application/json");
			headers.set("Content-Type", "application/json");
			headers.set("Authorization", "Bearer " + jwtToken);
			ObjectWriter ow = new ObjectMapper().writer().withDefaultPrettyPrinter();
			String json = ow.writeValueAsString(candidate_EducationDetailsInfo);
			HttpEntity<String> entity = new HttpEntity<String>(json, headers);
			response = restTemplate.exchange(url, HttpMethod.POST, entity, String.class);
			return response;
		} catch (Exception e) {
			throw new BgcUIException("Exception occered while accessing the data" + e.getMessage());

			// TODO: handle exception
		}

	}
	
	public ResponseEntity<String> deleteEducationInfo(String id,String jwtToken){
		ResponseEntity<String> response = null;
		String url = AllUrls.DeleteEducationInfo+id;
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

}
