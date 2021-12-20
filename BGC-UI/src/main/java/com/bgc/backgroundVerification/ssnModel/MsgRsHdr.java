package com.bgc.backgroundVerification.ssnModel;

public class MsgRsHdr {

	private String rqUID;

	private Status status;
	
	
	public MsgRsHdr() {
		// TODO Auto-generated constructor stub
	}

	public MsgRsHdr(String rqUID, Status status) {
		super();
		this.rqUID = rqUID;
		this.status = status;
	}

	public String getRqUID() {
		return rqUID;
	}

	public void setRqUID(String rqUID) {
		this.rqUID = rqUID;
	}

	public Status getStatus() {
		return status;
	}

	public void setStatus(Status status) {
		this.status = status;
	}
	
	

}
