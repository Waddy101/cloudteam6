package com.cloudteam6.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.cloudteam6.model.App;
import com.cloudteam6.repository.AppRepository;

@Service
public class AppServiceImpl implements AppService{

	@Autowired
	private AppRepository appRepository;
	
	@Override
	public void save(App app) {
		appRepository.save(app);
	}
	
	@Override
	public App findByName(String name) {
		return appRepository.findByName(name);
	}

	@Override
	public void toggleAppActivation(String name, boolean active) {
		App app = appRepository.findByName(name);
		app.setActive(active);
		appRepository.save(app);
	}
}
