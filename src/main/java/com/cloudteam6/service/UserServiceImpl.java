package com.cloudteam6.service;

import com.cloudteam6.model.Role;
import com.cloudteam6.model.User;
import com.cloudteam6.repository.RoleRepository;
import com.cloudteam6.repository.UserRepository;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;

@Service
public class UserServiceImpl implements UserService {
    @Autowired
    private UserRepository userRepository;
    @Autowired
    private RoleRepository roleRepository;
    @Autowired
    private PasswordEncoder passwordEncoder;

    @Override
    public void save(User user) {
        user.setPassword(passwordEncoder.encode(user.getPassword()));
        if(user.getRoles() == null) {
	        Role userRole = roleRepository.findOne((long) 1);
	        ArrayList<Role> userRoles = new ArrayList<Role>();
	        userRoles.add(userRole);
	        user.setRoles(new HashSet<>(userRoles));
        }
        userRepository.save(user);
    }

    @Override
    public User findByUsername(String username) {
        return userRepository.findByUsername(username);
    }

	@Override
	public List<User> findAll() {
		return userRepository.findAll();
	}
	
	@Override
	public User findCurrent() {
		return userRepository.findCurrent();
	}

	@Override
	public User findById(long Id) {
		return userRepository.findById(Id);
	}
}