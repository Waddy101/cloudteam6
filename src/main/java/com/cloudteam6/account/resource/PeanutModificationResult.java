package com.cloudteam6.account.resource;

import java.util.List;

public class PeanutModificationResult {
	
	private int statusCode;
	private List<String> messages;
	
	public PeanutModificationResult(int statusCode, List<String> messages) {
		this.statusCode = statusCode;
		this.messages = messages;
	}
	
	public List<String> getMessages() {
		return messages;
	}

	public void setMessages(List<String> messages) {
		this.messages = messages;
	}
	
	public int getStatusCode() {
		return statusCode;
	}

	public void setStatusCode(int statusCode) {
		this.statusCode = statusCode;
	}

	public void addMessage(String message) {
		messages.add(message);
	}
}
