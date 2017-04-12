package com.cloudteam6.account.repository;

import com.cloudteam6.account.model.Role;
import org.springframework.data.jpa.repository.JpaRepository;

public interface RoleRepository extends JpaRepository<Role, Long>{
	
}