package com.cloudteam6.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.cloudteam6.model.User;
import com.cloudteam6.repository.UserRepository;

@Service
public class PeanutServiceImpl implements PeanutService {
	
	@Autowired
	private UserRepository userRepository;
	
	@Override
	public void deposit(int amount, User user) {
		user.setPeanutbalance(user.getPeanutbalance() + amount);
		userRepository.save(user);
		
	}

	@Override
	public void charge(int amount, User user) {
		deposit(-1 * amount, user);
	}

}
