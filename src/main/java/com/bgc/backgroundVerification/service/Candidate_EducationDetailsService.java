package com.bgc.backgroundVerification.service;

import org.json.JSONObject;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

import com.bgc.backgroundVerification.adaptor.Candidate_EducationDetailsAdaptor;
import com.bgc.backgroundVerification.adaptor.GetJwtTokenAdapter;
import com.bgc.backgroundVerification.model.Candidate_EducationDetailsInfo;
import com.bgc.backgroundVerification.model.CandidatesInfo;
import com.bgc.backgroundVerification.model.CustomerInfo;
import com.bgc.backgroundVerification.model.ResponseInfo;
import com.bgc.backgroundVerification.util.BgcUIException;

@Service
public class Candidate_EducationDetailsService {

	@Autowired
	GetJwtTokenAdapter getJwtTokenAdapter;

	@Autowired
	Candidate_EducationDetailsAdaptor candidate_EducationDetailsAdaptor;

	public CandidatesInfo getEducationDetails(String username, String jwtToken) {
		;
		try {
//			String jwtToken = getJwtTokenAdapter.getJwtToken(username);

			CandidatesInfo candidatesInfo = candidate_EducationDetailsAdaptor.getEducationDetails(username, jwtToken);
			return candidatesInfo;

		} catch (Exception e) {
			throw new BgcUIException("exception occered while accessing the data" + e.getMessage());
		}

	}

	public ResponseInfo addEducationDetailsInfo(Candidate_EducationDetailsInfo candidate_EducationDetailsInfo,
			String id,String jwtToken) {
		JSONObject jsonObject = null;
		ResponseInfo responseInfo = new ResponseInfo();
		try {
//			String jwtToken = getJwtTokenAdapter.getJwtToken(id);

			ResponseEntity<String> response = candidate_EducationDetailsAdaptor
					.addEducationInfo(candidate_EducationDetailsInfo, jwtToken);

			jsonObject = new JSONObject(response.getBody());
			String responsecode = (String) jsonObject.get("responseCode");
			String responseMessage = (String) jsonObject.get("responseMessage");
			String message = (String) jsonObject.get("response");

			CandidatesInfo candidates = candidate_EducationDetailsAdaptor.getEducationDetails(id, jwtToken);
			responseInfo.setAlertMessage(message);
			responseInfo.setMessage(responseMessage);
			responseInfo.setCandidatesInfo(candidates);
		} catch (Exception e) {
			throw new BgcUIException("exception occered while accessing the data" + e.getMessage());

		}
		return responseInfo;

	}

	public ResponseInfo UpdateEducationDetailsInfo(Candidate_EducationDetailsInfo candidate_EducationDetailsInfo,
			String id,String jwtToken) {
		JSONObject jsonObject = null;
		ResponseInfo responseInfo = new ResponseInfo();
		try {
//			String jwtToken = getJwtTokenAdapter.getJwtToken(id);

			ResponseEntity<String> response = candidate_EducationDetailsAdaptor
					.updateEducationInfo(candidate_EducationDetailsInfo, jwtToken);

			jsonObject = new JSONObject(response.getBody());
			String responsecode = (String) jsonObject.get("responseCode");
			String responseMessage = (String) jsonObject.get("responseMessage");
			String message = (String) jsonObject.get("response");
			responseInfo.setAlertMessage(message);
			responseInfo.setMessage(responseMessage);
		} catch (Exception e) {
			throw new BgcUIException("exception occered while accessing the data" + e.getMessage());

		}
		return responseInfo;

	}

	public ResponseInfo deleteEducationInfo(String id,String jwtToken) {
//		String jwtToken = getJwtTokenAdapter.getJwtToken(id);
		ResponseInfo responseInfo = new ResponseInfo();
		JSONObject jsonObject = null;
		try {

			ResponseEntity<String> response = candidate_EducationDetailsAdaptor.deleteEducationInfo(id, jwtToken);
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

	public Candidate_EducationDetailsInfo getEducationInfoById(Long id,String jwtToken) {

		try {
//			String jwtToken = getJwtTokenAdapter.getJwtToken("");

			Candidate_EducationDetailsInfo candidatesInfo = candidate_EducationDetailsAdaptor.getEducationInfoById(id,
					jwtToken);
			return candidatesInfo;

		} catch (Exception e) {
			throw new BgcUIException("exception occered while accessing the data" + e.getMessage());
		}

	}



}
