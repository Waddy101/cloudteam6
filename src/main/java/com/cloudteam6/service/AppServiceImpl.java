package com.cloudteam6.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.cloudteam6.model.App;
import com.cloudteam6.repository.AppRepository;

@Service
public class AppServiceImpl implements AppService {
	
	@Autowired 
	private AppRepository appRepository;
	
	@Override
	public void save(App app) {
		appRepository.save(app);	
	}

}
