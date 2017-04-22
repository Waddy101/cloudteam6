package com.cloudteam6.account.service;

import com.cloudteam6.account.model.User;

public interface PeanutService {
	boolean deposit(int amount, User user);
	
	boolean charge(int amount, User user);
}
