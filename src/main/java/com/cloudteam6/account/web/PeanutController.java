package com.cloudteam6.account.web;

import java.security.Principal;
import java.util.ArrayList;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.MessageSource;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.http.converter.HttpMessageNotReadableException;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.*;

import com.cloudteam6.account.model.User;
import com.cloudteam6.account.resource.*;
import com.cloudteam6.account.service.PeanutService;
import com.cloudteam6.account.service.UserService;
import com.cloudteam6.account.validator.PeanutModificationRequestValidator;

import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.validation.FieldError;

@RestController
public class PeanutController {
	
	@Autowired
	private UserService userService;
	
	@Autowired
	private PeanutService peanutService;
	
	@Autowired
	private MessageSource messageSource;
	
	@Autowired
	private PeanutModificationRequestValidator peanutModificationRequestValidator;
	
	@PostMapping(value="/deposit")
	@PreAuthorize("hasRole('ROLE_USER')")
	public PeanutModificationResult deposit(@RequestBody PeanutModificationRequest request,
								BindingResult bindingResult,
								Principal principal) {
		peanutModificationRequestValidator.validate(request, bindingResult);
		PeanutModificationResult response = new PeanutModificationResult(request, new ArrayList<>());
		if (bindingResult.hasErrors()) {
			// dump errors in result object
			for (FieldError error : bindingResult.getFieldErrors()) {
				response.addMessage(messageSource.getMessage(error, null));
			}
			return response;
		}
		
		User currentUser = userService.findByUsername(principal.getName());
		peanutService.deposit(request.getAmount(), currentUser);
		response.addMessage("ALL OK");
		return response;
	}
	
	@PostMapping(value="/charge")
	@PreAuthorize("hasRole('ROLE_USER')")
	public PeanutModificationResult charge(@RequestBody PeanutModificationRequest request,
								BindingResult bindingResult,
								Principal principal) {
		peanutModificationRequestValidator.validate(request, bindingResult);
		PeanutModificationResult response = new PeanutModificationResult(request, new ArrayList<>());
		if (bindingResult.hasErrors()) {
			// dump errors in result object
			for (FieldError error : bindingResult.getFieldErrors()) {
				response.addMessage(messageSource.getMessage(error, null));
			}
			return response;
		}
		
		User currentUser = userService.findByUsername(principal.getName());
		peanutService.charge(request.getAmount(), currentUser);
		response.addMessage("ALL OK");
		return response;
	}
	
	@ExceptionHandler(HttpMessageNotReadableException.class)
	public ResponseEntity<?> handleException(Exception e) {
		PeanutModificationError errorObj = new PeanutModificationError();
		String message = "The number of peanuts you have provided to be deposited/charged " +
						"is invalid. Please pass in an integer between -2147483648 and 2147483647";
		errorObj.setMessage(message);
		errorObj.setStatusCode(422);
		return new ResponseEntity<PeanutModificationError>(errorObj, HttpStatus.UNPROCESSABLE_ENTITY);
	}
}
