package com.cloudteam6.web;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

@Controller
public class PageController {
	
	@RequestMapping(value = "/loadApp", method = RequestMethod.GET)
	public String getApplication(Model model) {
		return "app";
	}
}
