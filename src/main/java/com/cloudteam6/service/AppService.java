package com.cloudteam6.service;

import org.springframework.stereotype.Service;

import com.cloudteam6.model.App;

@Service
public interface AppService {
	void save(App app);
	App findByName(String name);
	void toggleAppActivation(String name, boolean active);
}
