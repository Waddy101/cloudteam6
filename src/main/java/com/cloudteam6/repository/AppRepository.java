package com.cloudteam6.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import com.cloudteam6.model.App;

public interface AppRepository extends JpaRepository<App, Long> {
	App findByName(String name);
}
