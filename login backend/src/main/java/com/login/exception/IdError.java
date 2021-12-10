package com.login.exception;

import java.util.Date;

public class IdError {
	
//	private Date timeStamp;
	private String message;
//	private String details;
	
	public IdError(Date timeStamp, String message, String details) {
		super();

		this.message = message;

	}

	public String getMessage() {
		return message;
	}
	public void setMessage(String message) {
		this.message = message;
	}

}



