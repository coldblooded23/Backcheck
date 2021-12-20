package com.bgc.backgroundVerification.ssnModel;

public class Status {
	
	
	private String statusCode;
    private String severity;
    private String statusDesc;
    
    public Status() {
		// TODO Auto-generated constructor stub
	}
    
    
    
	public Status(String statusCode, String severity, String statusDesc) {
		super();
		this.statusCode = statusCode;
		this.severity = severity;
		this.statusDesc = statusDesc;
	}



	public String getStatusCode() {
		return statusCode;
	}
	public void setStatusCode(String statusCode) {
		this.statusCode = statusCode;
	}
	public String getSeverity() {
		return severity;
	}
	public void setSeverity(String severity) {
		this.severity = severity;
	}
	public String getStatusDesc() {
		return statusDesc;
	}
	public void setStatusDesc(String statusDesc) {
		this.statusDesc = statusDesc;
	}

   
    
    
    
    

}
