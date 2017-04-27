package com.cloudteam6.account.resource;

import java.util.List;

public class PeanutModificationResult {
	
	private PeanutModificationRequest modificationRequest;
	private List<String> messages;
	
	public PeanutModificationResult(PeanutModificationRequest modificationRequest, List<String> messages) {
		this.modificationRequest = modificationRequest;
		this.messages = messages;
	}
	
	public List<String> getMessages() {
		return messages;
	}

	public void setMessages(List<String> messages) {
		this.messages = messages;
	}

	public PeanutModificationRequest getModificationRequest() {
		return modificationRequest;
	}
	
	public void addMessage(String message) {
		messages.add(message);
	}
}
