package com.cloudteam6.account.service;

import com.cloudteam6.account.model.User;

public interface UserService {
    void save(User user);

    User findByUsername(String username);
}