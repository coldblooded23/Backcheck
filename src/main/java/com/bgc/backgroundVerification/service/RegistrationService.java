package com.bgc.backgroundVerification.service;

import org.json.JSONObject;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

import com.bgc.backgroundVerification.adaptor.RegistrationAdaptor;
import com.bgc.backgroundVerification.model.CustomerInfo;
import com.bgc.backgroundVerification.util.BgcUIException;

@Service
public class RegistrationService {

	@Autowired
	RegistrationAdaptor adaptor;

	public String saveAUser(CustomerInfo customerInfo) {
		JSONObject jsonObject = null;
		try {

			ResponseEntity<String> response = adaptor.addAUser(customerInfo);
			jsonObject = new JSONObject(response.getBody());
			System.out.println(response.getBody().toString());

			String responsecode = (String) jsonObject.get("responseCode");
			String responseMessage = (String) jsonObject.get("responseMessage");
			String message = (String) jsonObject.get("response");
			System.out.println(responsecode + " df" + responseMessage + "dfdff" + message);
			if (responsecode.equals(Constants.SuccessResponseCode)) {

				if (responseMessage.equals(Constants.Success)) {
					return responseMessage;
				} else {
					return message;
				}

			} else {
				return Constants.AuthenticationFailed;
			}

		} catch (Exception e) {
			throw new BgcUIException("exception occerred while accessing the data" + e.getMessage());
		}

	}

	public String verifyAccount(String id) {

		JSONObject jsonObject = null;
		try {
			ResponseEntity<String> response = adaptor.verifyAccount(id);
			jsonObject = new JSONObject(response.getBody());
			System.out.println(response.getBody().toString());
			String responsecode = (String) jsonObject.get("responseCode");
			String responseMessage = (String) jsonObject.get("responseMessage");
			String message = (String) jsonObject.get("response");
			System.out.println(responsecode + " df" + responseMessage + "dfdff" + message);
			if (responsecode.equals(Constants.SuccessResponseCode)) {

				if (responseMessage.equals(Constants.Success)) {
					return responseMessage;
				} else {
					return message;
				}

			} else {
				return Constants.AuthenticationFailed;
			}

		} catch (Exception e) {

			throw new BgcUIException("exception occerred while accessing the data" + e.getMessage());
			// TODO: handle exception
		}

	}

}
