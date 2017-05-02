package com.cloudteam6.service;

import org.springframework.stereotype.Service;

import com.cloudteam6.model.User;

@Service
public interface PeanutService {
	void deposit(int amount, User user);
	
	void charge(int amount, User user);
}
