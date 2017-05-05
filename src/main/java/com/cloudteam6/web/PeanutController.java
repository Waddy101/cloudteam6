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
import org.springframework.validation.ObjectError;

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
	
	@PostMapping("/bill")
	public ResponseEntity<PeanutModificationResult> bill(@RequestBody PeanutModificationRequest r, BindingResult bindingResult) {
		peanutModificationRequestValidator.validate(r, bindingResult);
		PeanutModificationResult response = new PeanutModificationResult(200, new ArrayList<>());
		if (bindingResult.hasErrors()) { // payload errors
			addErrorsToResponse(response, bindingResult);
			return new ResponseEntity<>(response, HttpStatus.BAD_REQUEST);
		}
		else {
			peanutService.bill(r.getUserId(), r.getDeveloperId(), bindingResult);
			if (bindingResult.hasErrors()) { // billing errors
				addErrorsToResponse(response, bindingResult);
				return new ResponseEntity<>(response, HttpStatus.BAD_REQUEST); 
			}
		}
		response.addMessage("Success: billing completed.");
		return new ResponseEntity<>(response, HttpStatus.OK);
	}
	
	@ExceptionHandler(HttpMessageNotReadableException.class)
	public ResponseEntity<?> handleException(Exception e) {
		PeanutModificationResult errorObj = new PeanutModificationResult(400, new ArrayList<>());
		String message = "The number of peanuts you have provided to be deposited/charged " +
						"is invalid. Please pass in an integer between -2147483648 and 2147483647";
		errorObj.addMessage(message);
		return new ResponseEntity<PeanutModificationResult>(errorObj, HttpStatus.BAD_REQUEST);
	}
	
	private void addErrorsToResponse(PeanutModificationResult response, BindingResult bindingResult) {
		// dump errors in result object
		for (ObjectError error : bindingResult.getAllErrors()) {
			response.addMessage("Error: " + messageSource.getMessage(error, null));
		}
		
		response.setStatusCode(400);
	}
}
