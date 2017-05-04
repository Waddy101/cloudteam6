package com.cloudteam6.web;

import com.cloudteam6.model.Role;
import com.cloudteam6.model.User;
import com.cloudteam6.repository.RoleRepository;
import com.cloudteam6.service.SecurityService;
import com.cloudteam6.service.UserService;
import com.cloudteam6.validator.UserValidator;

import java.security.Principal;
import java.util.ArrayList;
import java.util.Set;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;

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
    
	@RequestMapping(value="/user/addrole", method = RequestMethod.GET)
	public @ResponseBody Set<Role> addRole(@RequestParam("role") String roleName, Principal principal) {
		Role role = roleRepository.findByName(roleName);
		User user = userService.findByUsername(principal.getName());
		user.addRole(role);
		userService.save(user);
		return user.getRoles();
	}
}