package com.bgc.backgroundVerification.adaptor;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.core.ParameterizedTypeReference;
import org.springframework.http.HttpEntity;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpMethod;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Component;
import org.springframework.web.client.RestTemplate;

import com.bgc.backgroundVerification.model.CustomerInfo;
import com.bgc.backgroundVerification.util.AllUrls;
import com.bgc.backgroundVerification.util.BgcUIException;

@Component
public class CustomerAdaptor {

	@Autowired
	RestTemplate restTemplate;

	public List<CustomerInfo> getAllCustomer() {
		ResponseEntity<List<CustomerInfo>> response = null;
		List<CustomerInfo> customerInfo = new ArrayList<CustomerInfo>();
		String url = AllUrls.GetAllCustomer;
		try {
			HttpHeaders headers = new HttpHeaders();
			headers.setAccept(Arrays.asList(MediaType.APPLICATION_JSON));
			headers.set("Accept", "application/json");
			headers.set("Content-Type", "application/json");
			HttpEntity<String> entity = new HttpEntity<String>(headers);
			response = restTemplate.exchange(url, HttpMethod.GET, entity, new ParameterizedTypeReference<List<CustomerInfo>>() {});
			if (response != null) {
				List<CustomerInfo> customerInfos = new ArrayList<CustomerInfo>();
				Iterable<CustomerInfo> customer = response.getBody();
				for (CustomerInfo info : customer) {
					CustomerInfo customerInfo2 = new CustomerInfo();
					customerInfo2.setCustomerId(info.getCustomerId());
					customerInfo2.setCompanyName(info.getCompanyName());
					customerInfos.add(customerInfo2);
				}
				customerInfo.addAll(customerInfos);
			}
		} catch (Exception e) {
			throw new BgcUIException("exception occered while accessing the data" + e.getMessage());
		}
		return customerInfo;
	}
}
