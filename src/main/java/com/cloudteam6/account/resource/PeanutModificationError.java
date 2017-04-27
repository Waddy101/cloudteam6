package com.cloudteam6.account.resource;

import org.springframework.stereotype.Component;

@Component
public class PeanutModificationError {
	
	private int statusCode;
	private String message;
	
	public int getStatusCode() {
		return statusCode;
	}
	public void setStatusCode(int statusCode) {
		this.statusCode = statusCode;
	}
	public String getMessage() {
		return message;
	}
	public void setMessage(String message) {
		this.message = message;
	}
}