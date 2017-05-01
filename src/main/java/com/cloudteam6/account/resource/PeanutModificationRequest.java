package com.cloudteam6.account.resource;

import org.springframework.stereotype.Component;

@Component
public class PeanutModificationRequest {
	
	private int amount;
	private String transaction;
	private String username;
	private String password;
	
	public String getUsername() {
		return username;
	}

	public void setUsername(String username) {
		this.username = username;
	}

	public int getAmount() {
		return amount;
	}
	
	public void setAmount(int amount) {
		this.amount = amount;
	}
	
	public String getTransaction() {
		return transaction;
	}
	
	public void setTransaction(String transaction) {
		this.transaction = transaction;
	}

	public String getPassword() {
		return password;
	}

	public void setPassword(String password) {
		this.password = password;
	}
}
