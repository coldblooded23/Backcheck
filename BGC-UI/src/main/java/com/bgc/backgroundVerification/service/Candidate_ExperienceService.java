package com.bgc.backgroundVerification.service;

import org.json.JSONObject;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

import com.bgc.backgroundVerification.adaptor.Candidate_ExperienceAdaptor;
import com.bgc.backgroundVerification.adaptor.GetJwtTokenAdapter;
import com.bgc.backgroundVerification.model.Candidate_EducationDetailsInfo;
import com.bgc.backgroundVerification.model.Candidate_ExperienceInfo;
import com.bgc.backgroundVerification.model.CandidatesInfo;
import com.bgc.backgroundVerification.model.ResponseInfo;
import com.bgc.backgroundVerification.util.BgcUIException;

@Service
public class Candidate_ExperienceService {
	
	
	@Autowired
	Candidate_ExperienceAdaptor experienceAdaptor;
	
	
	@Autowired
	GetJwtTokenAdapter tokenAdapter;
	
	
	
	public CandidatesInfo listOfExperience(String username,String jwtToken) {
		
		try {
//			String jwtToken=tokenAdapter.getJwtToken(username);
			
			CandidatesInfo candidatesInfo= experienceAdaptor.getListOfExperience(username, jwtToken);
			return candidatesInfo;
			
			
		}catch (Exception e) {
			// TODO: handle exception
			throw new BgcUIException("exception occered while accessing the data"+e.getMessage());
		}
		
	}
	
	public ResponseInfo addEducationDetailsInfo(Candidate_ExperienceInfo candidate_ExperienceInfo,String id,String jwtToken) {
		JSONObject jsonObject = null;
		ResponseInfo responseInfo = new ResponseInfo();
		try {
//			String jwtToken = tokenAdapter.getJwtToken(id);

			ResponseEntity<String> response = experienceAdaptor
					.addExperience(candidate_ExperienceInfo, jwtToken);
			
			jsonObject = new JSONObject(response.getBody());
			String responsecode = (String) jsonObject.get("responseCode");
			String responseMessage = (String) jsonObject.get("responseMessage");
			String message = (String) jsonObject.get("response");
			System.out.println(responseMessage+"   "+message );
			CandidatesInfo candidates = experienceAdaptor.getListOfExperience(id, jwtToken);
			responseInfo.setAlertMessage(message);
			responseInfo.setMessage(message);
			responseInfo.setCandidatesInfo(candidates);
			return responseInfo;

		} catch (Exception e) {
			throw new BgcUIException("exception occered while accessing the data" + e.getMessage());

		}
		
	}
	
	public Candidate_ExperienceInfo getAExperienceInfo(String id,String jwtToken) {
		
		try {
//			String jwtToken=tokenAdapter.getJwtToken(id);
			
			Candidate_ExperienceInfo candidate_Experience= experienceAdaptor.getAExperienceExperinceInfo(id, jwtToken);
			return candidate_Experience;
		}catch (Exception e) {
			// TODO: handle exception
			throw new BgcUIException("exception occered while accessing the data"+e.getMessage());
		}
		
	}

	public ResponseInfo UpdateExperienceInfo(Candidate_ExperienceInfo candidate_ExperienceInfo,String jwtToken) {
		// TODO Auto-generated method stub
		JSONObject jsonObject = null;
		ResponseInfo responseInfo = new ResponseInfo();
		try {
//			String jwtToken = tokenAdapter.getJwtToken(null);

			ResponseEntity<String> response = experienceAdaptor
					.updateExperienceInfo(candidate_ExperienceInfo, jwtToken);

			jsonObject = new JSONObject(response.getBody());
			String responsecode = (String) jsonObject.get("responseCode");
			String responseMessage = (String) jsonObject.get("responseMessage");
		
			String message = (String) jsonObject.get("response");
			System.out.println(responseMessage   +"      "+message);
			responseInfo.setAlertMessage(message);
			responseInfo.setMessage(responseMessage);
		} catch (Exception e) {
			throw new BgcUIException("exception occered while accessing the data" + e.getMessage());

		}
		return responseInfo;
	}

	public ResponseInfo deleteExperirnceInfo(String emailId,String jwtToken) {
//		String jwtToken = tokenAdapter.getJwtToken(emailId);
		ResponseInfo responseInfo = new ResponseInfo();
		JSONObject jsonObject = null;
		try {

			ResponseEntity<String> response = experienceAdaptor.deleteExperirnceInfo(emailId, jwtToken);
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
