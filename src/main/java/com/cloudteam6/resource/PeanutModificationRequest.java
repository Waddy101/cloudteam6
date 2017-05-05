package com.cloudteam6.resource;

import org.springframework.stereotype.Component;

@Component
public class PeanutModificationRequest {
	
	private long userId = -1;
	private long developerId = -1;
	
	public long getUserId() {
		return userId;
	}
	
	public void setUserId(long userId) {
		this.userId = userId;
	}
	
	public long getDeveloperId() {
		return developerId;
	}
	
	public void setDeveloperId(long developerId) {
		this.developerId = developerId;
	}
	
	
}
