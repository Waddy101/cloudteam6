package com.cloudteam6.validator;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Component;
import org.springframework.validation.Errors;
import org.springframework.validation.Validator;

import com.cloudteam6.model.User;
import com.cloudteam6.service.UserService;
import com.cloudteam6.resource.PeanutModificationRequest;

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
		PeanutModificationRequest request = (PeanutModificationRequest) o;
		if (request.getUserId() <= 0) {
			e.rejectValue("userId", "invalid.user.id");
		}
		if (request.getDeveloperId() <= 0) {
			e.rejectValue("developerId", "invalid.developer.id");
		}
		
	}
}
