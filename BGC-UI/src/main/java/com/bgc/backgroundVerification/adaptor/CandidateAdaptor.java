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
import org.springframework.util.LinkedMultiValueMap;
import org.springframework.util.MultiValueMap;
import org.springframework.web.client.RestTemplate;
import org.springframework.web.multipart.MultipartFile;

import com.bgc.backgroundVerification.model.Candidate_DocumentInfo;
import com.bgc.backgroundVerification.model.Candidate_EducationDetailsInfo;
import com.bgc.backgroundVerification.model.Candidate_ExperienceInfo;
import com.bgc.backgroundVerification.model.CandidatesInfo;
import com.bgc.backgroundVerification.model.CustomerInfo;
import com.bgc.backgroundVerification.util.AllUrls;
import com.bgc.backgroundVerification.util.BgcUIException;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.databind.ObjectWriter;

@Component
public class CandidateAdaptor {

	@Autowired
	RestTemplate restTemplate;

	public CustomerInfo getAllCandidatesInfo(String email, String jwtToken) {

		ResponseEntity<CustomerInfo> response = null;
		CustomerInfo customerInfo = new CustomerInfo();
		//String url = "http://localhost:9091/getListOfCanditates/" + email;
		String url=AllUrls.GetAllCandidates+email;
		try {
			HttpHeaders headers = new HttpHeaders();
			headers.setAccept(Arrays.asList(MediaType.APPLICATION_JSON));
			headers.set("Accept", "application/json");
			headers.set("Content-Type", "application/json");
			headers.set("Authorization", "Bearer " + jwtToken);

			HttpEntity<String> entity = new HttpEntity<String>(headers);

			response = restTemplate.exchange(url, HttpMethod.GET, entity, CustomerInfo.class);

			if (response != null) {
				customerInfo.setCustomerId(response.getBody().getCustomerId());
				List<CandidatesInfo> noOfCandidates = new ArrayList<CandidatesInfo>();

				Iterable<CandidatesInfo> info = response.getBody().getCandidatesInfo();

				for (CandidatesInfo candidates : info) {
					CandidatesInfo candidatesInfo = new CandidatesInfo();
					candidatesInfo.setCandidateId(candidates.getCandidateId());
					candidatesInfo.setFirstName(candidates.getFirstName());
					candidatesInfo.setLastName(candidates.getLastName());
					candidatesInfo.setEmail(candidates.getEmail());
					candidatesInfo.setdOB(candidates.getdOB().substring(0,10));
					candidatesInfo.setFiletype(candidates.getFiletype());

					candidatesInfo.setResume(candidates.getResume());
					candidatesInfo.setDocName(candidates.getDocName());
					candidatesInfo.setFiletype(candidates.getFiletype());

					candidatesInfo.setPhone(candidates.getPhone());
					noOfCandidates.add(candidatesInfo);
				}
				customerInfo.setCandidatesInfo(noOfCandidates);

			}

		} catch (Exception e) {
			throw new BgcUIException("exception occered while accessing the data" + e.getMessage());
		}

		return customerInfo;

	}
	
	public CandidatesInfo getCandidateDetailsByID(String username, String jwtToken) {
		CandidatesInfo candidateInfo = new CandidatesInfo();
		String url = AllUrls.url+"/getCandidateDetailsByID/"+username;
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
				candidateInfo.setCandidateId(response.getBody().getCandidateId());
				candidateInfo.setFirstName(response.getBody().getFirstName());
				candidateInfo.setLastName(response.getBody().getLastName());
				candidateInfo.setDefaultUserName(response.getBody().getDefaultUserName());
				candidateInfo.setdOB(response.getBody().getdOB().substring(0, 10) );
				candidateInfo.setEmail(response.getBody().getEmail());
				candidateInfo.setPhone(response.getBody().getPhone());
				candidateInfo.setCandidate_GeneralInfo(response.getBody().getCandidate_GeneralInfo());
				List<Candidate_EducationDetailsInfo> edu=response.getBody().getCandidate_EducationDetailsInfo();
				candidateInfo.setCandidate_EducationDetailsInfo(edu);
				List<Candidate_ExperienceInfo> exp=response.getBody().getCandidate_ExperienceInfo();
				candidateInfo.setCandidate_ExperienceInfo(exp);
				List<Candidate_DocumentInfo> doc = response.getBody().getCandidate_DocumentInfo();
				candidateInfo.setSsn_StatusInfo(response.getBody().getSsn_StatusInfo());
				candidateInfo.setCandidate_DocumentInfo(doc);

			}
			
		} catch (Exception e) {
			throw new BgcUIException("exception occered while accessing the data" + e.getMessage());
		}

		return candidateInfo;

	}

	public ResponseEntity<String> addCandidate(CandidatesInfo candidatesInfo, String jwtToken) {
		ResponseEntity<String> responseEntity = null;
		//String url = "http://localhost:9091/addCandidate";
		String url=AllUrls.AddCandidate;
		
		try {
			HttpHeaders headers = new HttpHeaders();
			headers.setAccept(Arrays.asList(MediaType.APPLICATION_JSON));
			headers.set("Accept", "application/json");
			headers.set("Content-Type", "application/json");
			headers.set("Authorization", "Bearer " + jwtToken);

			ObjectWriter ow = new ObjectMapper().writer().withDefaultPrettyPrinter();
			String json = ow.writeValueAsString(candidatesInfo);
			HttpEntity<String> entity = new HttpEntity<String>(json, headers);

			responseEntity = restTemplate.exchange(url, HttpMethod.POST, entity, String.class);

			return responseEntity;

		} catch (Exception e) {
			throw new BgcUIException("exception occered while accessing the data" + e.getMessage());

		}

	}
	
	
	public CandidatesInfo getACandidateInfo(Long id, String jwtToken) {
		
		ResponseEntity<CandidatesInfo> response=null;
				CandidatesInfo candidatesInfo = new CandidatesInfo();
				//String url="http://localhost:9091/getCanditatesDetails/"+id;
				String url=AllUrls.GetCandidateDetail+id;
			try {	
				HttpHeaders headers = new HttpHeaders();
				headers.setAccept(Arrays.asList(MediaType.APPLICATION_JSON));
				headers.set("Accept", "application/json");
				headers.set("Content-Type", "application/json");
				headers.set("Authorization", "Bearer " + jwtToken);

				HttpEntity<String> entity = new HttpEntity<String>(headers);

				response = restTemplate.exchange(url, HttpMethod.GET, entity, CandidatesInfo.class);
				candidatesInfo.setCustomerId(response.getBody().getCustomerId());
				candidatesInfo.setCandidateId(response.getBody().getCandidateId());
				candidatesInfo.setFirstName(response.getBody().getFirstName());
				candidatesInfo.setLastName(response.getBody().getLastName());
				candidatesInfo.setEmail(response.getBody().getEmail());
				candidatesInfo.setdOB(response.getBody().getdOB());
				candidatesInfo.setMdate(response.getBody().getdOB().substring(0,10));
				candidatesInfo.setFiletype(response.getBody().getFiletype());

				candidatesInfo.setResume(response.getBody().getResume());
				candidatesInfo.setDocName(response.getBody().getDocName());
				candidatesInfo.setFiletype(response.getBody().getFiletype());

				candidatesInfo.setPhone(response.getBody().getPhone());
				
			}catch (Exception e) {
				throw new BgcUIException("exception occered while accessing the data" + e.getMessage());
		}
		return candidatesInfo;
		
	}
	
	public ResponseEntity<String> updateCandidate(CandidatesInfo candidatesInfo, String jwtToken) {
		ResponseEntity<String> responseEntity = null;
		//String url = "http://localhost:9091/updateCandidate";
		String url=AllUrls.UpdateCandidate;
		try {
			HttpHeaders headers = new HttpHeaders();
			headers.setAccept(Arrays.asList(MediaType.APPLICATION_JSON));
			headers.set("Accept", "application/json");
			headers.set("Content-Type", "application/json");
			headers.set("Authorization", "Bearer " + jwtToken);

			ObjectWriter ow = new ObjectMapper().writer().withDefaultPrettyPrinter();
			String json = ow.writeValueAsString(candidatesInfo);
			HttpEntity<String> entity = new HttpEntity<String>(json, headers);

			responseEntity = restTemplate.exchange(url, HttpMethod.POST, entity, String.class);

			return responseEntity;

		} catch (Exception e) {
			throw new BgcUIException("exception occered while accessing the data" + e.getMessage());
	
	}
		
	}
	
	public ResponseEntity<String> uploadResume(MultipartFile file, String id, String email, String jwtToken){
		ResponseEntity<String> responseEntity = null;
		//String url = "http://localhost:9091/uplodeCandidateResume";
		String url=AllUrls.UplodeCandidateResume;
		System.out.println(file.getContentType());
		try {
			HttpHeaders headers = new HttpHeaders();
			headers.setContentType(MediaType.MULTIPART_FORM_DATA);
			headers.set("Authorization", "Bearer " + jwtToken);
			MultiValueMap<String, Object> body = new LinkedMultiValueMap<>();
			body.add("file", file.getResource());
			body.add("id",id );	
			HttpEntity<MultiValueMap<String, Object>> requestEntity = new HttpEntity<>(body, headers);
			 responseEntity = restTemplate.exchange(url, HttpMethod.POST, requestEntity, String.class);
			
		}catch (Exception e) {
			throw new BgcUIException("exception occered while accessing the data" + e.getMessage());
			
		}
		return responseEntity;
		
	}
	
	public ResponseEntity<String> deleteResume(String id,String jwtToken)	{
			ResponseEntity<String> responseEntity = null;
			//String url = "http://localhost:9091/deletecandidate/"+id;
			String url=AllUrls.DeleteCandidate+id;
		try {
			HttpHeaders headers = new HttpHeaders();
			headers.setAccept(Arrays.asList(MediaType.APPLICATION_JSON));
			headers.set("Accept", "application/json");
			headers.set("Content-Type", "application/json");
			headers.set("Authorization", "Bearer " + jwtToken);
			HttpEntity<String> entity = new HttpEntity<String>(headers);
			responseEntity = restTemplate.exchange(url, HttpMethod.DELETE, entity, String.class);
			return responseEntity;
			}
		catch (Exception e) {
			throw new BgcUIException("exception occered while accessing the data" + e.getMessage());
			}
		}
	
	

}
