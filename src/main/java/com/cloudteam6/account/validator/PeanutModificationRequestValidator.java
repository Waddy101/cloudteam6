package com.cloudteam6.account.validator;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import org.springframework.validation.Errors;
import org.springframework.validation.ValidationUtils;
import org.springframework.validation.Validator;

import com.cloudteam6.account.model.User;
import com.cloudteam6.account.resource.PeanutModificationRequest;
import com.cloudteam6.account.service.UserService;

@Component
public class PeanutModificationRequestValidator implements Validator {
	
	@Autowired
	private UserService userService;
	
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
		if (request.getTransaction().equals("deposit")) {
			// Overflow
			if (request.getAmount() + user.getPeanutBalance() < 0) {
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
