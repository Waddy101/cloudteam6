package com.cloudteam6.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.validation.BindingResult;
import org.springframework.validation.ObjectError;

import com.cloudteam6.model.User;
import com.cloudteam6.repository.UserRepository;

@Service
public class PeanutServiceImpl implements PeanutService {
	
	@Autowired
	private UserRepository userRepository;
	
	static final int SINGLE_REQUEST_COST = 2;
	static final int MAX_PEANUTS = 2147483647;
	
	// the user ID of the user modelling the platform
	static final long PLATFORM_ID = 1;
	
	@Override
	public void bill(long userId, long devId, BindingResult e) {
		User consumer = userRepository.findById(userId);
		User developer = userRepository.findById(devId);
		// TODO: get platform user
		int devDepositLimit = getDepositLimit(developer.getPeanutbalance());
		// TODO: platformLimit
		
		if (consumer.getPeanutbalance() < SINGLE_REQUEST_COST) {
			e.addError(new ObjectError("billingError", "User do not have enough peanuts to be charged by this transaction."));
		}
		else if (devDepositLimit < 1) {
			e.addError(new ObjectError("billingError", "Credit denied - developer already have the maximum number of peanuts allowed."));
		}
		else {
			charge(2, consumer);
			deposit(1, developer);
			//TODO: credit platform
		}
		
	}
	
	private void deposit(int amount, User user) {
		user.setPeanutbalance(user.getPeanutbalance() + amount);
		userRepository.save(user);
		
	}

	private void charge(int amount, User user) {
		deposit(-1 * amount, user);
	}
	
	private int getDepositLimit(int balance) {
		return MAX_PEANUTS - balance;
	}
}
