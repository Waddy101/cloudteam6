package com.cloudteam6.account.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.cloudteam6.account.model.User;
import com.cloudteam6.account.repository.UserRepository;

@Service
public class PeanutServiceImpl implements PeanutService {
	
	@Autowired
	private UserRepository userRepository;
	
	@Override
	public boolean deposit(int amount, User user) {
		if (user.getPeanutBalance() + amount < 0) {
			return false;
		}
		else {
			user.setPeanutBalance(user.getPeanutBalance() + amount);
			userRepository.save(user);
			return true;
		}
		
	}

	@Override
	public boolean charge(int amount, User user) {
		if (amount > user.getPeanutBalance()) {
			return false;
		}
		else {
			deposit(-1 * amount, user);
			return true;
		}
	}

}
