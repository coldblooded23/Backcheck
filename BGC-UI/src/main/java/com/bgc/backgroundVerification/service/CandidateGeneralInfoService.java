package com.bgc.backgroundVerification.service;

import org.json.JSONObject;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
import com.bgc.backgroundVerification.adaptor.Candidate_GeneralInfoAdaptor;
import com.bgc.backgroundVerification.adaptor.GetJwtTokenAdapter;
import com.bgc.backgroundVerification.model.CandidatesInfo;
import com.bgc.backgroundVerification.model.CustomerInfo;
import com.bgc.backgroundVerification.model.ResponseInfo;
import com.bgc.backgroundVerification.util.BgcUIException;

@Service
public class CandidateGeneralInfoService {
	
	@Autowired
	Candidate_GeneralInfoAdaptor candidate_GeneralInfoAdaptor;
	
	@Autowired
	GetJwtTokenAdapter getJwtTokenAdapter;
	
	public CandidatesInfo getCandidateInfo(String id,String jwtToken) {
		try {
//			String jwtToken = getJwtTokenAdapter.getJwtToken(id);
			CandidatesInfo candidatesInfo = candidate_GeneralInfoAdaptor.getCandidateGeneralInfo(id, jwtToken);
			return candidatesInfo;
		} catch (Exception e) {
			throw new BgcUIException("exception occered while accessing the date" + e.getMessage());
		}
		

		
	}
	
	
	public ResponseInfo updateCandidate(CandidatesInfo candidatesInfo, String id,String jwtToken) {
		JSONObject jsonObject = null;
		ResponseInfo responseInfo = new ResponseInfo();
		try {
			
//			String jwtToken = getJwtTokenAdapter.getJwtToken(id);

			ResponseEntity<String> response = candidate_GeneralInfoAdaptor.updateCandidateInfo(jwtToken,
					candidatesInfo);
			jsonObject = new JSONObject(response.getBody());
			String responsecode = (String) jsonObject.get("responseCode");
			String responseMessage = (String) jsonObject.get("responseMessage");
			String message = (String) jsonObject.get("response");

			CandidatesInfo candidates = candidate_GeneralInfoAdaptor.getCandidateGeneralInfo(id, jwtToken);
			responseInfo.setAlertMessage(message);
			responseInfo.setMessage(responseMessage);
			responseInfo.setCandidatesInfo(candidates);
				
		}catch (Exception e) {
			throw new BgcUIException("exception occered while accessing the data"+e.getMessage());
			
		}
		return responseInfo;
		
	}
	
	

}
