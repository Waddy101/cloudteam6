package com.cloudteam6.service;

import org.springframework.stereotype.Service;

import com.cloudteam6.model.User;

@Service
public interface UserService {
    void save(User user);

    User findByUsername(String username);
}