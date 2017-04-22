package com.cloudteam6.account.web;

import java.security.Principal;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.*;

import com.cloudteam6.PeanutModificationResult;
import com.cloudteam6.account.model.User;
import com.cloudteam6.account.service.PeanutService;
import com.cloudteam6.account.service.UserService;

import org.springframework.ui.Model;

@RestController
public class PeanutController {
	
	@Autowired
	private UserService userService;
	
	@Autowired
	private PeanutService peanutService;
	
	@GetMapping(value="/deposit")
	@PreAuthorize("hasRole('ROLE_USER')")
	public PeanutModificationResult deposit(@RequestParam(value="amount", defaultValue="0") int amount, Model model, Principal principal) {
		User currentUser = userService.findByUsername(principal.getName());
		
		if (peanutService.deposit(amount, currentUser)) {
			return new PeanutModificationResult(amount, "deposit", "Success!");
		}
		else {
			return new PeanutModificationResult(-1, "deposit", "Error: The number of peanuts you are trying to deposit exceeds the upper limit in which our system can hold!");
		}
		
	}
	
	@GetMapping(value="/charge")
	@PreAuthorize("hasRole('ROLE_USER')")
	public PeanutModificationResult charge(@RequestParam(value="amount", defaultValue="0") int amount, Model model, Principal principal) {
		User currentUser = userService.findByUsername(principal.getName());
		
		if (peanutService.charge(amount, currentUser)) {
			return new PeanutModificationResult(amount, "charge", "Success!");
		}
		else {
			return new PeanutModificationResult(-1, "charge", "Error: You do not have enough peanuts to be charged by this transaction.");
		}
		
	}
}
