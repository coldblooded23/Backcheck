package com.bgc.backgroundVerification.ssnModel;

import java.util.List;

public class SSNInfo {

	private MsgRsHdr msgRsHdr;
	private String areaNum;
	private String groupNum;
	private IssueDateRange issueDateRange;
	private String beginRange;
	private String stateProv;
	private CodeDigit codeDigit;
	private String sSNStatus;
	private List<Message> message;
	public MsgRsHdr getMsgRsHdr() {
		return msgRsHdr;
	}
	public void setMsgRsHdr(MsgRsHdr msgRsHdr) {
		this.msgRsHdr = msgRsHdr;
	}
	public String getAreaNum() {
		return areaNum;
	}
	public void setAreaNum(String areaNum) {
		this.areaNum = areaNum;
	}
	public String getGroupNum() {
		return groupNum;
	}
	public void setGroupNum(String groupNum) {
		this.groupNum = groupNum;
	}
	public IssueDateRange getIssueDateRange() {
		return issueDateRange;
	}
	public void setIssueDateRange(IssueDateRange issueDateRange) {
		this.issueDateRange = issueDateRange;
	}
	public String getBeginRange() {
		return beginRange;
	}
	public void setBeginRange(String beginRange) {
		this.beginRange = beginRange;
	}
	public String getStateProv() {
		return stateProv;
	}
	public void setStateProv(String stateProv) {
		this.stateProv = stateProv;
	}
	public CodeDigit getCodeDigit() {
		return codeDigit;
	}
	public void setCodeDigit(CodeDigit codeDigit) {
		this.codeDigit = codeDigit;
	}
	public String getsSNStatus() {
		return sSNStatus;
	}
	public void setsSNStatus(String sSNStatus) {
		this.sSNStatus = sSNStatus;
	}
	public List<Message> getMessage() {
		return message;
	}
	public void setMessage(List<Message> message) {
		this.message = message;
	}
	
	
	
	
	
	

}
