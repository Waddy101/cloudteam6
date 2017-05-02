package com.cloudteam6.web;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;

import com.cloudteam6.repository.AppRepository;

@Controller
public class PageController {
	
	@Autowired
	private AppRepository appRepository;
	
	@RequestMapping(value = "/loadApp", method = RequestMethod.GET)
	public String getApplication(@RequestParam("appName") String appname, Model model) {
		String appURL = appRepository.findByName(appname).getURL();
		System.out.println("appURL");
		model.addAttribute("appURL", appURL);
		return "app";
	}
}
