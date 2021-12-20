package com.bgc.backgroundVerification.model;

import java.sql.Timestamp;

public class Candidate_DocumentInfo {
	
	
	private Long documentId;
	
	private Long candidateId;
	
	private byte[] document;
	
	private String documenttype;
	
	private String docName;
	
	private String typeofDoc;
	
	private Timestamp createdOn;
	
	private Timestamp updatedOn;
	

	private CandidatesInfo candidatesInfo;


	public Long getDocumentId() {
		return documentId;
	}


	public void setDocumentId(Long documentId) {
		this.documentId = documentId;
	}


	public Long getCandidateId() {
		return candidateId;
	}


	public void setCandidateId(Long candidateId) {
		this.candidateId = candidateId;
	}


	public byte[] getDocument() {
		return document;
	}


	public void setDocument(byte[] document) {
		this.document = document;
	}


	public String getDocumenttype() {
		return documenttype;
	}


	public void setDocumenttype(String documenttype) {
		this.documenttype = documenttype;
	}


	public String getDocName() {
		return docName;
	}


	public void setDocName(String docName) {
		this.docName = docName;
	}


	public String getTypeofDoc() {
		return typeofDoc;
	}


	public void setTypeofDoc(String typeofDoc) {
		this.typeofDoc = typeofDoc;
	}


	public Timestamp getCreatedOn() {
		return createdOn;
	}


	public void setCreatedOn(Timestamp createdOn) {
		this.createdOn = createdOn;
	}


	public Timestamp getUpdatedOn() {
		return updatedOn;
	}


	public void setUpdatedOn(Timestamp updatedOn) {
		this.updatedOn = updatedOn;
	}


	public CandidatesInfo getCandidatesInfo() {
		return candidatesInfo;
	}


	public void setCandidatesInfo(CandidatesInfo candidatesInfo) {
		this.candidatesInfo = candidatesInfo;
	}
	
	
	
	
	

}
