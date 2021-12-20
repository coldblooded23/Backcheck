package com.bgc.backgroundVerification.ssnModel;

public class Message {

	private String msgClass;
	private String text;
	
	
	public Message() {
		// TODO Auto-generated constructor stub
	}
	
	public Message(String msgClass, String text) {
		super();
		this.msgClass = msgClass;
		this.text = text;
	}
	public String getMsgClass() {
		return msgClass;
	}
	public void setMsgClass(String msgClass) {
		this.msgClass = msgClass;
	}
	public String getText() {
		return text;
	}
	public void setText(String text) {
		this.text = text;
	}
	
	
	
	
	
	

}
