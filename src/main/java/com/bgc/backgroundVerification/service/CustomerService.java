package com.bgc.backgroundVerification.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.bgc.backgroundVerification.adaptor.CustomerAdaptor;
import com.bgc.backgroundVerification.model.CustomerInfo;
import com.bgc.backgroundVerification.util.BgcUIException;

@Service
public class CustomerService {
	
	@Autowired
	CustomerAdaptor customerAdaptor;

	public List<CustomerInfo> getAllCustomer(){
		try {
		List<CustomerInfo> customerInfos= customerAdaptor.getAllCustomer();
		return customerInfos;
		} catch (Exception e) {
			throw new BgcUIException("Exeception occered while accessing the data" + e.getMessage());
		}
	}

}
