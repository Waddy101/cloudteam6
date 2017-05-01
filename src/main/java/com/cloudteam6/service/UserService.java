package com.cloudteam6.service;

import com.cloudteam6.model.User;

public interface UserService {
    void save(User user);

    User findByUsername(String username);
}