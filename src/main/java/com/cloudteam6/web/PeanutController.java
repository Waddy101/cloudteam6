package com.cloudteam6.web;

import java.util.ArrayList;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.MessageSource;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.http.converter.HttpMessageNotReadableException;
import org.springframework.web.bind.annotation.*;
import com.cloudteam6.model.User;
import com.cloudteam6.service.UserService;
import com.cloudteam6.resource.*;
import com.cloudteam6.service.PeanutService;
import com.cloudteam6.validator.PeanutModificationRequestValidator;

import org.springframework.validation.BindingResult;
import org.springframework.validation.FieldError;

@RestController
@RequestMapping("/peanutbank")
public class PeanutController {
	
	@Autowired
	private UserService userService;
	
	@Autowired
	private PeanutService peanutService;
	
	@Autowired
	private MessageSource messageSource;
	
	@Autowired
	private PeanutModificationRequestValidator peanutModificationRequestValidator;
	
	@RequestMapping(value="/deposit", method = RequestMethod.POST)
	public ResponseEntity<PeanutModificationResult> deposit(@RequestBody PeanutModificationRequest request,
								BindingResult bindingResult) {
		request.setTransaction("deposit");
		peanutModificationRequestValidator.validate(request, bindingResult);
		PeanutModificationResult response = new PeanutModificationResult(200, new ArrayList<>());
		if (bindingResult.hasErrors()) {
			addErrorsToResponse(response, bindingResult);
			return new ResponseEntity<PeanutModificationResult>(response, HttpStatus.UNPROCESSABLE_ENTITY);
		}
		
		User currentUser = userService.findByUsername(request.getUsername());
		peanutService.deposit(request.getAmount(), currentUser);
		response.addMessage("Success: " + request.getAmount() + " peanuts have been deposited.");
		return new ResponseEntity<PeanutModificationResult>(response, HttpStatus.OK);
	}
	
	@RequestMapping(value="/charge", method = RequestMethod.POST)
	public ResponseEntity<PeanutModificationResult> charge(@RequestBody PeanutModificationRequest request,
								BindingResult bindingResult) {
		request.setTransaction("charge");
		peanutModificationRequestValidator.validate(request, bindingResult);
		PeanutModificationResult response = new PeanutModificationResult(200, new ArrayList<>());
		if (bindingResult.hasErrors()) {
			addErrorsToResponse(response, bindingResult);
			return new ResponseEntity<PeanutModificationResult>(response, HttpStatus.UNPROCESSABLE_ENTITY);
		}
		
		User currentUser = userService.findByUsername(request.getUsername());
		peanutService.charge(request.getAmount(), currentUser);
		response.addMessage("Success: " + request.getAmount() + " peanuts have been charged.");
		return new ResponseEntity<PeanutModificationResult>(response, HttpStatus.OK);
	}
	
	@ExceptionHandler(HttpMessageNotReadableException.class)
	public ResponseEntity<PeanutModificationResult> handleException(Exception e) {
		PeanutModificationResult errorObj = new PeanutModificationResult(422, new ArrayList<>());
		String message = "The number of peanuts you have provided to be deposited/charged " +
						"is invalid. Please pass in an integer between -2147483648 and 2147483647";
		errorObj.addMessage(message);
		return new ResponseEntity<PeanutModificationResult>(errorObj, HttpStatus.UNPROCESSABLE_ENTITY);
	}
	
	private void addErrorsToResponse(PeanutModificationResult response, BindingResult bindingResult) {
		// dump errors in result object
		for (FieldError error : bindingResult.getFieldErrors()) {
			response.addMessage("Error: " + messageSource.getMessage(error, null));
		}
		response.setStatusCode(422);
	}
}
