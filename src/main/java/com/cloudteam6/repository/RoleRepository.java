package com.cloudteam6.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import com.cloudteam6.model.Role;

public interface RoleRepository extends JpaRepository<Role, Long>{
	
}