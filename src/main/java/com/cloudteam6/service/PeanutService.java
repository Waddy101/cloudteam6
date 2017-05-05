package com.cloudteam6.service;

import org.springframework.stereotype.Service;
import org.springframework.validation.BindingResult;

@Service
public interface PeanutService {
	/**
	 * Charges the user and credits the developer of the app.
	 * @param userId - the user to be charged
	 * @param devId - the developer to be credited
	 */
	void bill(long userId, long devId, BindingResult e);
}
