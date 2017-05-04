package com.cloudteam6.web;

import java.security.Principal;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.ResponseStatus;

import com.cloudteam6.model.App;
import com.cloudteam6.model.User;
import com.cloudteam6.service.AppService;
import com.cloudteam6.service.UserService;

@Controller
public class AppController {
	@Autowired 
	private AppService appService;
	
	@Autowired
	private UserService userService;

	@RequestMapping(value="/add", method = RequestMethod.GET)
	public @ResponseBody String addNewApp (@RequestParam String name
			, @RequestParam String URL, @RequestParam String applicationImageURL, Principal principal) {
		User user = userService.findByUsername(principal.getName());
		App a = new App(name, URL, applicationImageURL, false, user);
		appService.save(a);
		return a.getURL();
	}
	
	@RequestMapping(value = "/toggleApp", method = RequestMethod.POST)
	@ResponseStatus(value = HttpStatus.OK)
	public void toggleAppActivation(@RequestParam("active") boolean active, @RequestParam("appName") String appName) {
		appService.toggleAppActivation(appName, active);	
	}
}
