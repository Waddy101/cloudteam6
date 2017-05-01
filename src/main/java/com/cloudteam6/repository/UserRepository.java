package com.cloudteam6.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import com.cloudteam6.model.User;

public interface UserRepository extends JpaRepository<User, Long> {
    User findByUsername(String username);
}