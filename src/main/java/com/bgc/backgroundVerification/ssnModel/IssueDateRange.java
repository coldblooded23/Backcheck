package com.bgc.backgroundVerification.ssnModel;

public class IssueDateRange {

	private String startDt;
	private String endDt;
	
	
	public IssueDateRange() {
		// TODO Auto-generated constructor stub
	}
	
	public IssueDateRange(String startDt, String endDt) {
		super();
		this.startDt = startDt;
		this.endDt = endDt;
	}
	public String getStartDt() {
		return startDt;
	}
	public void setStartDt(String startDt) {
		this.startDt = startDt;
	}
	public String getEndDt() {
		return endDt;
	}
	public void setEndDt(String endDt) {
		this.endDt = endDt;
	}
	
	
	
	
	

}
