package com.cloudteam6;

public class PeanutModificationResult {
	
	private int amount; // The number of peanuts being deposited/charged
	private String transaction;
	private String message;
	
	public PeanutModificationResult(int amount, String transaction,
									String message) {
		this.amount = amount;
		this.transaction = transaction;
		this.message = message;
	}

	public int getAmount() {
		return amount;
	}

	public String getTransaction() {
		return transaction;
	}

	public String getMessage() {
		return message;
	}
}
