package com.bgc.backgroundVerification.adaptor;

import java.util.Arrays;
import java.util.List;

import org.json.JSONObject;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.core.io.ClassPathResource;
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
import com.bgc.backgroundVerification.model.ResponseInfo;
import com.bgc.backgroundVerification.util.AllUrls;
import com.bgc.backgroundVerification.util.BgcUIException;
import com.bgc.backgroundVerification.util.MultipartInputStreamFileResource;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.databind.ObjectWriter;

@Component
public class Candidate_DocumentAdaptor {

	@Autowired
	RestTemplate restTemplate;

	public ResponseEntity<String> addDocument(MultipartFile file, Long CandidateId, String typeOfDoc,String jwtToken) {

		ResponseEntity<String> responseEntity = null;
		String url = AllUrls.url+"/addDocument";

		Candidate_DocumentInfo candidate_DocumentInfo = new Candidate_DocumentInfo();
		candidate_DocumentInfo.setCandidateId(CandidateId);
		candidate_DocumentInfo.setTypeofDoc(typeOfDoc);
		System.out.println(file.getContentType());
		try {
			HttpHeaders headers = new HttpHeaders();
			headers.setContentType(MediaType.MULTIPART_FORM_DATA);
			headers.set("Authorization", "Bearer " + jwtToken);
			ObjectWriter ow = new ObjectMapper().writer().withDefaultPrettyPrinter();
			String json = ow.writeValueAsString(candidate_DocumentInfo);
			MultiValueMap<String, Object> body = new LinkedMultiValueMap<>();
			body.add("document", file.getResource());

			body.add("typeOfDocument", json);
			HttpEntity<MultiValueMap<String, Object>> requestEntity = new HttpEntity<>(body, headers);
			responseEntity = restTemplate.exchange(url, HttpMethod.POST, requestEntity, String.class);

		} catch (Exception e) {
			throw new BgcUIException("exception occered while accessing the data" + e.getMessage());

		}
		return responseEntity;

	}
	
	
	public ResponseEntity<String> updateDocument(MultipartFile file, Long CandidateId, Long documentId,String typeOfDoc,String jwtToken) {

		ResponseEntity<String> responseEntity = null;
		String url = AllUrls.url+"/updateDocument";
		
		
		Candidate_DocumentInfo candidate_DocumentInfo = new Candidate_DocumentInfo();
		candidate_DocumentInfo.setCandidateId(CandidateId);
		candidate_DocumentInfo.setTypeofDoc(typeOfDoc);
		candidate_DocumentInfo.setDocumentId(documentId);
		System.out.println(file.getContentType());
		try {
			HttpHeaders headers = new HttpHeaders();
			headers.setContentType(MediaType.MULTIPART_FORM_DATA);
			headers.set("Authorization", "Bearer " + jwtToken);
			ObjectWriter ow = new ObjectMapper().writer().withDefaultPrettyPrinter();
			String json = ow.writeValueAsString(candidate_DocumentInfo);
			MultiValueMap<String, Object> body = new LinkedMultiValueMap<>();
			body.add("document", file.getResource());
			body.add("typeOfDocument", json);
			HttpEntity<MultiValueMap<String, Object>> requestEntity = new HttpEntity<>(body, headers);
			responseEntity = restTemplate.exchange(url, HttpMethod.POST, requestEntity, String.class);

		} catch (Exception e) {
			throw new BgcUIException("exception occered while accessing the data" + e.getMessage());

		}
		return responseEntity;

	}


	public CandidatesInfo getListOfDoc(String username, String jwtToken) {
		CandidatesInfo candidateInfo = new CandidatesInfo();
		String url = AllUrls.url+"/getDocumentList/"+username;
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

				List<Candidate_DocumentInfo> doc = response.getBody().getCandidate_DocumentInfo();

				candidateInfo.setCandidate_DocumentInfo(doc);

			}
			
		} catch (Exception e) {
			throw new BgcUIException("exception occered while accessing the data" + e.getMessage());
		}

		return candidateInfo;

	}
	
	

	
	
	
	
	public Candidate_DocumentInfo getADoc(String username, String jwtToken) {
		Candidate_DocumentInfo documentInfo = new Candidate_DocumentInfo();
		String url = AllUrls.url+"/getDocument/"+username;
		try {
			HttpHeaders headers = new HttpHeaders();
			headers.setAccept(Arrays.asList(MediaType.APPLICATION_JSON));
			headers.set("Accept", "application/json");
			headers.set("Content-Type", "application/json");
			headers.set("Authorization", "Bearer " + jwtToken);

			HttpEntity<String> entity = new HttpEntity<>(headers);

			ResponseEntity<Candidate_DocumentInfo> response = restTemplate.exchange(url, HttpMethod.GET, entity,
					Candidate_DocumentInfo.class);
			if (response != null) {
				documentInfo.setCandidateId(response.getBody().getCandidateId());
				documentInfo.setDocumentId(response.getBody().getDocumentId());
				documentInfo.setDocName(response.getBody().getDocName());
				documentInfo.setDocument(response.getBody().getDocument());
				documentInfo.setDocumenttype(response.getBody().getDocumenttype());
				documentInfo.setTypeofDoc(response.getBody().getTypeofDoc());
				

			}
			
		} catch (Exception e) {
			throw new BgcUIException("exception occered while accessing the data" + e.getMessage());
		}

		return documentInfo;

	}

	public ResponseEntity<String> deletedocument(String emailId, String jwtToken) {
		ResponseEntity<String> response = null;
		String url = AllUrls.url+"/deleteDocument/"+emailId;
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


			
		}
	}
	
	


}
	

