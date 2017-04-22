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
		
		peanutService.deposit(amount, currentUser);
		
		return new PeanutModificationResult(amount, "deposit");
	}
	
	@GetMapping(value="/charge")
	@PreAuthorize("hasRole('ROLE_USER')")
	public PeanutModificationResult charge(@RequestParam(value="amount", defaultValue="0") int amount, Model model, Principal principal) {
		User currentUser = userService.findByUsername(principal.getName());
		
		if (amount > currentUser.getPeanutBalance()) {
			return new PeanutModificationResult(-1, "failed");
		}
		else {
			peanutService.charge(amount, currentUser);
			return new PeanutModificationResult(amount, "charge");
		}
		
	}
}
