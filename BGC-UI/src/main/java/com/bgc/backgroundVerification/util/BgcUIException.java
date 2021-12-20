package com.bgc.backgroundVerification.util;

public class BgcUIException extends RuntimeException  {
	
	
	private static final long serialVersionUID = 4874944297L;

	public BgcUIException() {
		super();
	}

	public BgcUIException(String message, Throwable cause, boolean enableSuppression, boolean writableStackTrace) {
		super(message, cause, enableSuppression, writableStackTrace);
	}

	public BgcUIException(String message, Throwable cause) {
		super(message, cause);
	}

	public BgcUIException(String message) {
		super(message);
	}

	public BgcUIException(Throwable cause) {
		super(cause);
	}
	
	

}
