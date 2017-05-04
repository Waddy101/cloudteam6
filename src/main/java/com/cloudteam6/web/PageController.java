package com.cloudteam6.web;

import java.io.IOException;
import java.security.Principal;

import javax.servlet.http.HttpServletResponse;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;

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
	public String getApplication(@RequestParam("appName") String appname, Model model, Principal principal) {		
		App app = appRepository.findByName(appname);
		String appURL = app.getURL();
		User currentUser = userService.findByUsername(principal.getName());
		if (currentUser != null) {
        	int peanutBalance = currentUser.getPeanutbalance();
        	model.addAttribute("peanutBalance", "Balance: " + peanutBalance +
        				((peanutBalance != 1)? " peanuts ": " peanut"));
        	for(Role role: currentUser.getRoles()) {
		        if (role.getName().equals("ROLE_ADMIN")) {
		            model.addAttribute("admin", true);
		            model.addAttribute("canupload", true);
		            break;
		        } else if (role.getName().equals("ROLE_DEV")) {
		        	model.addAttribute("canupload", true);
		        	model.addAttribute("dev", true);
		        	break;
		        } else {
		        	model.addAttribute("admin", false);
		        	model.addAttribute("dev", false);
		        	model.addAttribute("canupload", false);
;		        }
        	}
		}	
		model.addAttribute("appURL", appURL);
		model.addAttribute("appList", appRepository.findAll());
		if (app.isActive()) {
			model.addAttribute("active", "checked");
			System.out.println("application is active");
		} else {
			model.addAttribute("active", "");
			System.out.println("Application isnt active");
		}
		return "app";
	}
	

    @RequestMapping(value = {"/", "/welcome"}, method = RequestMethod.GET)
    public String welcome(Model model, Principal principal) {
		User currentUser = userService.findByUsername(principal.getName());
		if (currentUser != null) {
        	int peanutBalance = currentUser.getPeanutbalance();
        	model.addAttribute("peanutBalance", "Balance: " + peanutBalance +
        				((peanutBalance != 1)? " peanuts ": " peanut"));
        	for(Role role: currentUser.getRoles()) {
		        if (role.getName().equals("ROLE_ADMIN")) {
		            model.addAttribute("admin", true);
		            model.addAttribute("canupload", true);
		            System.out.println("admin");
		            break;
		        } else if (role.getName().equals("ROLE_DEV")) {
		        	model.addAttribute("canupload", true);
		        } else {
		        	model.addAttribute("admin", false);
		        }
        	}
		}
    	model.addAttribute("appList", appRepository.findAll());
    	return "welcome";
    }
	
	@RequestMapping(value = "/deleteApp", method = RequestMethod.POST, params = {"id"})
    public @ResponseBody void delete(HttpServletResponse response, Model model, Principal principal,@RequestParam("id") long id) throws IOException{
    	User currentUser = userService.findByUsername(principal.getName());
		if (currentUser != null) {
        	for(Role role: currentUser.getRoles()) {
		        if (role.getName().equals("ROLE_ADMIN")) {
		            appRepository.delete(id);
		            break;
		        } 
        	}
		}
		response.sendRedirect("/");
    }
}
