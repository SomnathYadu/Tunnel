package com.tunnel.exceptions;

import java.util.Date;

public class ExceptionResponse {

	private Date timestamp;
	private String errorCode;
	private String message;
	private String details;

	public ExceptionResponse(Date timestamp, String errorCode, String message, 
			String details) {
		super();
		this.errorCode = errorCode;
		this.message = message;
		this.details = details;
		this.timestamp = timestamp;
	}

	public String getErrorCode() {
		return errorCode;
	}

	public String getMessage() {
		return message;
	}

	public Date getTimestamp() {
		return timestamp;
	}

	public String getDetails() {
		return details;
	}

}
