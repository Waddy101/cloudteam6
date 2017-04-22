package com.cloudteam6.account.repository;

import com.cloudteam6.account.model.User;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.transaction.annotation.Transactional;

public interface UserRepository extends JpaRepository<User, Long> {
    User findByUsername(String username);
    
    @Modifying(clearAutomatically = true)
    @Transactional
    @Query("UPDATE User SET peanutBalance = :newBalance WHERE username = :username")
    void updateUserPeanutBalance(@Param("username") String username,
    		@Param("newBalance") int newBalance);
}