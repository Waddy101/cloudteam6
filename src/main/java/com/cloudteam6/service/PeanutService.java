package com.cloudteam6.service;

import com.cloudteam6.model.User;

public interface PeanutService {
	void deposit(int amount, User user);
	
	void charge(int amount, User user);
}
