package com.bgc.backgroundVerification.adaptor;

import java.util.Arrays;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpEntity;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpMethod;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Component;
import org.springframework.web.client.RestTemplate;

import com.bgc.backgroundVerification.model.SsnInfo;
import com.bgc.backgroundVerification.ssnModel.SSNInfo;
import com.bgc.backgroundVerification.util.AllUrls;
import com.bgc.backgroundVerification.util.BgcUIException;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.databind.ObjectWriter;

@Component
public class SsnAdaptor {

	@Autowired
	RestTemplate restTemplate;

	public SSNInfo verifySsnNumber(SsnInfo ssn, String jwtToken) {
		String url = AllUrls.url+"/ssn/verifySsnNumber";
		SSNInfo info = new SSNInfo();
		try {
			HttpHeaders headers = new HttpHeaders();
			headers.setAccept(Arrays.asList(MediaType.APPLICATION_JSON));
			headers.set("Accept", "application/json");
			headers.set("Content-Type", "application/json");
			headers.set("Authorization", "Bearer " + jwtToken);
			ObjectWriter ow = new ObjectMapper().writer().withDefaultPrettyPrinter();
			String json = ow.writeValueAsString(ssn);
			HttpEntity<String> entity = new HttpEntity<String>(json, headers);
			ResponseEntity<SSNInfo> response = restTemplate.exchange(url, HttpMethod.POST, entity, SSNInfo.class);
			info.setMsgRsHdr(response.getBody().getMsgRsHdr());
			info.setAreaNum(response.getBody().getAreaNum());
			info.setGroupNum(response.getBody().getGroupNum());
			info.setIssueDateRange(response.getBody().getIssueDateRange());
			info.setBeginRange(response.getBody().getBeginRange());
			info.setStateProv(response.getBody().getStateProv());
			info.setCodeDigit(response.getBody().getCodeDigit());
			info.setsSNStatus(response.getBody().getsSNStatus());
			info.setMessage(response.getBody().getMessage());

		} catch (Exception e) {
			throw new BgcUIException("Exception occered while accessing the data" + e.getMessage());
		}

		return info;

	}

}
