package com.cloudteam6.account.validator;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Component;
import org.springframework.validation.Errors;
import org.springframework.validation.ValidationUtils;
import org.springframework.validation.Validator;

import com.cloudteam6.account.model.User;
import com.cloudteam6.account.resource.PeanutModificationRequest;
import com.cloudteam6.account.service.UserService;

@Component
public class PeanutModificationRequestValidator implements Validator {
	
	static final int MAX_INT = 2147483647;
	
	@Autowired
	private UserService userService;
	
	@Autowired
    private BCryptPasswordEncoder bCryptPasswordEncoder;
	
	@Override
	/**
	 * Validator validates only the PeanutModificationRequest class
	 */
	public boolean supports(Class<?> aClass) {
		return PeanutModificationRequest.class.equals(aClass);
	}

	@Override
	public void validate(Object o, Errors e) {
		ValidationUtils.rejectIfEmpty(e, "amount", "peanut.amount.empty");
		ValidationUtils.rejectIfEmpty(e, "transaction", "peanut.transaction.empty");
		
		PeanutModificationRequest request = (PeanutModificationRequest) o;
		if ( !(request.getTransaction().equals("deposit") ||
				request.getTransaction().equals("charge")) ) {
			e.rejectValue("transaction", "peanut.unknown.transaction");
		}
		
		if (request.getAmount() <= 0) {
			e.rejectValue("amount", "peanut.negative.amount");
		}
		
		User user = userService.findByUsername(request.getUsername());
		// User authentication checks
		if (!bCryptPasswordEncoder.matches(request.getPassword(), user.getPassword())) {
			e.rejectValue("password", "peanut.bad.credential");
		}
		if (request.getTransaction().equals("deposit")) {
			// Overflow
			int depositLimit = MAX_INT - user.getPeanutBalance();
			if (request.getAmount() > depositLimit) {
				e.rejectValue("amount", "peanut.amount.overflow");
			}
		}
		else if (request.getTransaction().equals("charge")) {
			if (request.getAmount() > user.getPeanutBalance()) {
				e.rejectValue("amount", "peanut.insufficient");
			}
		}
		
	}

}
