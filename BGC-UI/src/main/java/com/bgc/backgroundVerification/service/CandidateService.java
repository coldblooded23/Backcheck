package com.bgc.backgroundVerification.service;


import org.json.JSONObject;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

import com.bgc.backgroundVerification.adaptor.CandidateAdaptor;
import com.bgc.backgroundVerification.adaptor.GetJwtTokenAdapter;
import com.bgc.backgroundVerification.model.CandidatesInfo;
import com.bgc.backgroundVerification.model.CustomerInfo;
import com.bgc.backgroundVerification.model.EmployeeInfo;
import com.bgc.backgroundVerification.model.ResponseInfo;
import com.bgc.backgroundVerification.util.BgcUIException;

@Service
public class CandidateService {
	
	
	@Autowired
	CandidateAdaptor candidateAdaptor;
	
	@Autowired
	GetJwtTokenAdapter getJwtTokenAdapter;
	
	
	public CustomerInfo getAllCandidateInfo(String email,String jwtToken) {
		try {
//		String jwtToken = getJwtTokenAdapter.getJwtToken(email);
		
		CustomerInfo customerInfo = candidateAdaptor.getAllCandidatesInfo(email, jwtToken);
		
			return customerInfo;
			
		}catch (Exception e) {
			throw new BgcUIException("exception occered while accessing the data"+e.getMessage());
		}
	
	}
	
	public CandidatesInfo getCandidateDetailsByID(String username,String jwtToken) {

		try {
			/* String jwtToken = getJwtTokenAdapter.getJwtToken(username); */

			CandidatesInfo candidates = candidateAdaptor.getCandidateDetailsByID(username, jwtToken);
			return candidates;

		} catch (Exception e) {
			// TODO: handle exception
			throw new BgcUIException("exception occerred while accessing the data");
		}

	}
	
	
	public ResponseInfo addCandidate(CandidatesInfo candidatesInfo, String email,String jwtToken) {
		JSONObject jsonObject = null;
		ResponseInfo responseInfo = new ResponseInfo();
		try {
			
//			String jwtToken=getJwtTokenAdapter.getJwtToken(email);
			CandidatesInfo candidates= new CandidatesInfo();
			
			candidates.setCustomerId(candidatesInfo.getCustomerId());
			candidates.setFirstName(candidatesInfo.getFirstName());
			candidates.setLastName(candidatesInfo.getLastName());
			candidates.setEmail(candidatesInfo.getEmail());
			candidates.setPhone(candidatesInfo.getPhone());
			candidates.setdOB(candidatesInfo.getMdate());
		
			ResponseEntity<String> response=candidateAdaptor.addCandidate(candidates, jwtToken);
			  jsonObject = new JSONObject(response.getBody()); 
			  String responsecode = (String) jsonObject.get("responseCode"); 
			  String responseMessage = (String) jsonObject.get("responseMessage"); 
			  String message  = (String) jsonObject.get("response");
			  
	CustomerInfo customerInfo = candidateAdaptor.getAllCandidatesInfo(email, jwtToken);
	  responseInfo.setAlertMessage(message );
	  responseInfo.setMessage(responseMessage);
	  responseInfo.setCustomerInfo(customerInfo);
				
		}catch (Exception e) {
			throw new BgcUIException("exception occered while accessing the data"+e.getMessage());
			
		}
		return responseInfo;
		
	}
	
	
	
	public CandidatesInfo getACandidateInfo(Long id,String email,String jwtToken) {
		try {
//			String jwtToken = getJwtTokenAdapter.getJwtToken(email);
			
			CandidatesInfo candidatesInfo = candidateAdaptor.getACandidateInfo(id, jwtToken);
			return candidatesInfo;
		}catch (Exception e) {
			throw new BgcUIException("exception occered while accessing the data"+e.getMessage());
			
			
		}
		
		
	}
	
	public ResponseInfo updateCandidate(CandidatesInfo candidatesInfo, String email,String jwtToken) {
		JSONObject jsonObject = null;
		ResponseInfo responseInfo = new ResponseInfo();
		try {
			
//			String jwtToken=getJwtTokenAdapter.getJwtToken(email);
			CandidatesInfo candidates= new CandidatesInfo();
			
			candidates.setCustomerId(candidatesInfo.getCustomerId());
			candidates.setCandidateId(candidatesInfo.getCandidateId());
			candidates.setFirstName(candidatesInfo.getFirstName());
			candidates.setLastName(candidatesInfo.getLastName());
			candidates.setEmail(candidatesInfo.getEmail());
			candidates.setPhone(candidatesInfo.getPhone());
			candidates.setdOB(candidatesInfo.getMdate());
		
			ResponseEntity<String> response=candidateAdaptor.updateCandidate(candidates, jwtToken);
			  jsonObject = new JSONObject(response.getBody()); 
			  String responsecode = (String) jsonObject.get("responseCode"); 
			  String responseMessage = (String) jsonObject.get("responseMessage"); 
			  String message  = (String) jsonObject.get("response");
			  
	CustomerInfo customerInfo = candidateAdaptor.getAllCandidatesInfo(email, jwtToken);
	  responseInfo.setAlertMessage(message );
	  responseInfo.setMessage(responseMessage);
	  responseInfo.setCustomerInfo(customerInfo);
				
		}catch (Exception e) {
			throw new BgcUIException("exception occered while accessing the data"+e.getMessage());
			
		}
		return responseInfo;
		
	}
	
	public ResponseInfo updateResume(MultipartFile file , String id,String email,String jwtToken) {
		JSONObject jsonObject = null;
		ResponseInfo responseInfo = new ResponseInfo();
		try {
			
//			String jwtToken=getJwtTokenAdapter.getJwtToken(email);
	
			ResponseEntity<String> response=candidateAdaptor.uploadResume(file, id, email, jwtToken);
			  jsonObject = new JSONObject(response.getBody()); 
			  String responsecode = (String) jsonObject.get("responseCode"); 
			  String responseMessage = (String) jsonObject.get("responseMessage"); 
			  String message  = (String) jsonObject.get("response");
			  
	CustomerInfo customerInfo = candidateAdaptor.getAllCandidatesInfo(email, jwtToken);
	  responseInfo.setAlertMessage(message );
	  responseInfo.setMessage(responseMessage);
	  responseInfo.setCustomerInfo(customerInfo);
				
		}catch (Exception e) {
			throw new BgcUIException("exception occered while accessing the data"+e.getMessage());
			
		}
		return responseInfo;
		
	}
	
	
	public ResponseInfo deleteCandidate(String id, String email,String jwtToken) {
		JSONObject jsonObject = null;
		ResponseInfo responseInfo = new ResponseInfo();
		try {
			
//			String jwtToken=getJwtTokenAdapter.getJwtToken(email);
			ResponseEntity<String> response=candidateAdaptor.deleteResume(id, jwtToken);
			jsonObject = new JSONObject(response.getBody()); 
			String responsecode = (String) jsonObject.get("responseCode"); 
			String responseMessage = (String) jsonObject.get("responseMessage"); 
			String message  = (String) jsonObject.get("response");
			  
	CustomerInfo customerInfo = candidateAdaptor.getAllCandidatesInfo(email, jwtToken);
	responseInfo.setAlertMessage(message );
	responseInfo.setMessage(responseMessage);
	responseInfo.setCustomerInfo(customerInfo);
				
		}catch (Exception e) {
			throw new BgcUIException("exception occered while accessing the data"+e.getMessage());
			
		}
		return responseInfo;
		
	}
	
}
