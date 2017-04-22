package com.cloudteam6;

public class PeanutModificationResult {
	
	private int amount; // The number of peanuts being deposited/charged
	private String transaction;
	
	public PeanutModificationResult(int amount, String transaction) {
		this.amount = amount;
		this.transaction = transaction;
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
	
}
