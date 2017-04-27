package com.cloudteam6.account.service;

import com.cloudteam6.account.model.User;

public interface PeanutService {
	void deposit(int amount, User user);
	
	void charge(int amount, User user);
}
