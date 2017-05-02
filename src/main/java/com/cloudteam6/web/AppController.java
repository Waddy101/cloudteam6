package com.cloudteam6.web;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;

import com.cloudteam6.model.App;
import com.cloudteam6.repository.AppRepository;

@Controller
@RequestMapping(path="/app")
public class AppController {
	@Autowired 
	private AppRepository appRepository;

	@GetMapping(path="/add")
	public @ResponseBody String addNewApp (@RequestParam String name
			, @RequestParam String URL, @RequestParam String applicationImageURL) {
		App a = new App(name, URL, applicationImageURL);
		appRepository.save(a);
		return a.getURL();
	}

	@GetMapping(path="/all")
	public @ResponseBody Iterable<App> getAllApps() {
		return appRepository.findAll();
	}
}
