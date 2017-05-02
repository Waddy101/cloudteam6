package com.cloudteam6.web;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;

import com.cloudteam6.model.User;
import com.cloudteam6.model.App;
import com.cloudteam6.model.Role;
import com.cloudteam6.repository.AppRepository;
import com.cloudteam6.service.UserService;

@Controller
public class PageController {
	
	@Autowired
	private AppRepository appRepository;
	
	@Autowired
	private UserService userService;
	
	@RequestMapping(value = "/loadApp", method = RequestMethod.GET)
	public String getApplication(@RequestParam("appName") String appname, Model model) {		
		App app = appRepository.findByName(appname);
		String appURL = app.getURL();
		System.out.println("appURL");
		org.springframework.security.core.userdetails.User secUser = (org.springframework.security.core.userdetails.User) SecurityContextHolder.getContext().getAuthentication().getPrincipal();
		User user = userService.findByUsername(secUser.getUsername());
		boolean admin = false;
		boolean dev = false;
		for(Role role : user.getRoles()) {
			if (role.getName().equals("ROLE_ADMIN")) {
				admin = true;
			} else if (role.getName().equals("ROLE_DEV")) {
				dev = true;
			}
		}
		model.addAttribute("admin", admin);
		model.addAttribute("dev", dev);
		model.addAttribute("appURL", appURL);
		if (app.isActive()) {
			model.addAttribute("active", "checked");
			System.out.println("application is active");
		} else {
			model.addAttribute("active", "");
			System.out.println("Application isnt active");
		}
		return "app";
	}
	
}
