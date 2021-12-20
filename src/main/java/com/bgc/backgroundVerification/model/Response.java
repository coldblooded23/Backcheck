package com.bgc.backgroundVerification.model;

public class Response<T> {

	private static final String R_MSG_EMPTY = "";
	  private static final String R_CODE_OK = "OK";

	  private final String responseCode;
	  private final String responseMessage;

	  private Object response;

	  /**
	   * A Creates a new instance of Response
	   *
	   * @param code
	   * @param responseMessage
	   * @param execDt
	   */
	public Response(final String code, final String responseMessage, Object response) {

	    this.responseMessage = responseMessage == null ? Response.R_MSG_EMPTY : responseMessage;
	    this.responseCode = code == null ? Response.R_CODE_OK : code;
	    this.response = response;
	  }
	
	

	  /**
	   * @return the responseMessage
	   */
	  public String getResponseMessage() {

	    return this.responseMessage;
	  }

	  /**
	   * @return the response
	   */
	  public Object getResponse() {

	    return this.response;
	  }

	  /**
	   * @return the responseCode
	   */
	  public String getResponseCode() {

	    return this.responseCode;
	  }

	  /**
	   * sets the response object
	   *
	   * @param obj
	   * @return
	   */
	  public Response<T> setResponse(final T obj) {

	    this.response = obj;
	    return this;
	  }
}
