package com.cloudteam6.web;

import com.cloudteam6.model.Role;
import com.cloudteam6.model.User;
import com.cloudteam6.repository.AppRepository;
import com.cloudteam6.repository.RoleRepository;
import com.cloudteam6.service.SecurityService;
import com.cloudteam6.service.UserService;
import com.cloudteam6.validator.UserValidator;

import java.security.Principal;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.ResponseStatus;

@Controller
public class UserController {
    @Autowired
    private UserService userService;

    @Autowired
    private SecurityService securityService;

    @Autowired
    private UserValidator userValidator;
    
    @Autowired
    private RoleRepository roleRepository;
    
    @Autowired
    private AppRepository appRepository;

    @RequestMapping(value = "/registration", method = RequestMethod.GET)
    public String registration(Model model) {
        model.addAttribute("userForm", new User());

        return "registration";
    }

    @RequestMapping(value = "/registration", method = RequestMethod.POST)
    public String registration(@ModelAttribute("userForm") User userForm, BindingResult bindingResult, Model model) {
        userValidator.validate(userForm, bindingResult);

        if (bindingResult.hasErrors()) {
            return "registration";
        }

        userService.save(userForm);

        securityService.autologin(userForm.getUsername(), userForm.getPasswordConfirm());

        return "redirect:/welcome";
    }

    @RequestMapping(value = "/login", method = RequestMethod.GET)
    public String login(Model model, String error, String logout) {
        if (error != null)
            model.addAttribute("error", "Your username and password is invalid.");

        if (logout != null)
            model.addAttribute("message", "You have been logged out successfully.");

        return "login";
    }
    
    @RequestMapping(value = "/user/current", method = RequestMethod.GET)
    public @ResponseBody User getUser(Principal principal) {
    	return userService.findByUsername(principal.getName());
    }
    
	@RequestMapping(value="/user/addRole", method = RequestMethod.POST)
	@ResponseStatus(value = HttpStatus.OK)
	public void addRole(@RequestParam("role") String roleName, Principal principal) {
		Role role = roleRepository.findByName(roleName);
		User user = userService.findByUsername(principal.getName());
		user.addRole(role);
		userService.save(user);
	}
	
	@RequestMapping(value="/user/removeRole", method = RequestMethod.POST)
	@ResponseStatus(value = HttpStatus.OK)
	public void removeRole(@RequestParam("role") String roleName, Principal principal) {
		Role role = roleRepository.findByName(roleName);
		User user = userService.findByUsername(principal.getName());
		user.removeRole(role);
		userService.save(user);
	}
	
	@RequestMapping(value = "/users", method = RequestMethod.GET)
	public String showAllUsers(Model model, Principal principal) {	
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
		model.addAttribute("users", userService.findAll());
		return "users/list";
	}
	
}