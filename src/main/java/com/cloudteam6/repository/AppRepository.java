package com.cloudteam6.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import com.cloudteam6.model.App;

@Repository
public interface AppRepository extends JpaRepository<App, Long> {
	App findByName(String name);
	
	@Query("select a from App a where URL = ?1")
	App findByURL(String URL);
}
