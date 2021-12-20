package com.bgc.backgroundVerification.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.bgc.backgroundVerification.adaptor.SsnAdaptor;
import com.bgc.backgroundVerification.model.SsnInfo;
import com.bgc.backgroundVerification.ssnModel.SSNInfo;
import com.bgc.backgroundVerification.util.BgcUIException;

@Service
public class SsnService {
	
	
	@Autowired
	SsnAdaptor ssnAdaptor;
	
	
	
	
	public SSNInfo verifySsnNumber(SsnInfo ssn,String jwtToken) {
		try {
			SSNInfo ssninfo=ssnAdaptor.verifySsnNumber(ssn, jwtToken);
			return ssninfo;
		}catch (Exception e) {
			throw new BgcUIException("exception occered while accessing the data" + e.getMessage());
		}
		
		
	}
		
	}
