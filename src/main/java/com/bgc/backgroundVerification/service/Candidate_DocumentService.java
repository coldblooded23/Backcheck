package com.bgc.backgroundVerification.service;

import org.json.JSONObject;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

import com.bgc.backgroundVerification.adaptor.Candidate_DocumentAdaptor;
import com.bgc.backgroundVerification.adaptor.GetJwtTokenAdapter;
import com.bgc.backgroundVerification.model.Candidate_DocumentInfo;
import com.bgc.backgroundVerification.model.CandidatesInfo;
import com.bgc.backgroundVerification.model.ResponseInfo;
import com.bgc.backgroundVerification.util.BgcUIException;

@Service
public class Candidate_DocumentService {

	@Autowired
	Candidate_DocumentAdaptor documentAdaptor;

	@Autowired
	GetJwtTokenAdapter jwtTokenAdapter;

	public CandidatesInfo getListOfDocuments(String username,String jwtToken) {

		try {
//			String jwtToken = jwtTokenAdapter.getJwtToken(username);

			CandidatesInfo candidates = documentAdaptor.getListOfDoc(username, jwtToken);
			return candidates;

		} catch (Exception e) {
			// TODO: handle exception
			throw new BgcUIException("exception occerred while accessing the data");
		}

	}
	
	
	public Candidate_DocumentInfo getADoc(String id,String jwtToken) {
		try {
//			String jwtToken = jwtTokenAdapter.getJwtToken(id);

			Candidate_DocumentInfo candidates = documentAdaptor.getADoc(id, jwtToken);
			return candidates;

		} catch (Exception e) {
			// TODO: handle exception
			throw new BgcUIException("exception occerred while accessing the data");
		}

	}
	
	public ResponseInfo addADocument(MultipartFile file,Long Candidate_id,String typeOfDoc,String jwtToken) {
		JSONObject jsonObject=null;
		ResponseInfo responseInfo = new ResponseInfo();
		try {
//			String jwtToken=jwtTokenAdapter.getJwtToken(typeOfDoc);
			
			ResponseEntity<String> response=documentAdaptor.addDocument(file, Candidate_id, typeOfDoc, jwtToken);
			  jsonObject = new JSONObject(response.getBody()); 
			  String responsecode = (String) jsonObject.get("responseCode"); 
			  String responseMessage = (String) jsonObject.get("responseMessage"); 
			  String message  = (String) jsonObject.get("response");
			  System.out.println(responsecode+"  "+responseMessage+" "+message);
			  responseInfo.setAlertMessage(message );
			  responseInfo.setMessage(responseMessage);
		}catch (Exception e) {
			// TODO: handle exception
			throw new BgcUIException("exception occerred while accessing the data"+ e.getMessage());
		}
		return responseInfo;
		
		
	
	}
	
	public ResponseInfo updateDocument(MultipartFile file,Long Candidate_id,Long documentId,String typeOfDoc,String jwtToken) {
		JSONObject jsonObject=null;
		ResponseInfo responseInfo = new ResponseInfo();
		try {
//			String jwtToken=jwtTokenAdapter.getJwtToken(typeOfDoc);
			
			ResponseEntity<String> response=documentAdaptor.updateDocument(file, Candidate_id, documentId, typeOfDoc, jwtToken);
			  jsonObject = new JSONObject(response.getBody()); 
			  String responsecode = (String) jsonObject.get("responseCode"); 
			  String responseMessage = (String) jsonObject.get("responseMessage"); 
			  String message  = (String) jsonObject.get("response");
			  System.out.println(responsecode+"  "+responseMessage+" "+message);
			  responseInfo.setAlertMessage(message );
			  responseInfo.setMessage(responseMessage);
		}catch (Exception e) {
			// TODO: handle exception
			throw new BgcUIException("exception occerred while accessing the data"+ e.getMessage());
		}
		return responseInfo;
		
		
	
	}
		
	
	public ResponseInfo deleteDocument(String emailId,String jwtToken) {
//		String jwtToken = jwtTokenAdapter.getJwtToken(emailId);
		ResponseInfo responseInfo = new ResponseInfo();
		JSONObject jsonObject = null;
		try {

			ResponseEntity<String> response = documentAdaptor.deletedocument(emailId, jwtToken);
			jsonObject = new JSONObject(response.getBody());
			String responsecode = (String) jsonObject.get("responseCode");
			String responseMessage = (String) jsonObject.get("responseMessage");
			String message = (String) jsonObject.get("response");
			System.out.println(responsecode + "    " + responseMessage + "   " + message);
			responseInfo.setAlertMessage(message);
			responseInfo.setMessage(responseMessage);
		} catch (Exception e) {
			throw new BgcUIException("Exeception occered while accessing the data" + e.getMessage());
		}
		return responseInfo;
	}
	

	

	
	
}
