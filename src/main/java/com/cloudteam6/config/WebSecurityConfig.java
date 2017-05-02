package com.cloudteam6.config;

import javax.sql.DataSource;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.config.annotation.authentication.builders.AuthenticationManagerBuilder;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;

@Configuration
@EnableWebSecurity
public class WebSecurityConfig extends WebSecurityConfigurerAdapter {
	
	@Autowired 
	DataSource dataSource;

    @Autowired 
    public void configAuthentication(AuthenticationManagerBuilder auth) throws Exception { 
    	auth.jdbcAuthentication().dataSource(dataSource)
    		.usersByUsernameQuery( "select username,password, enabled from user where username=?") 
    		.authoritiesByUsernameQuery( "select username, role from user_role where username=?"); 
    	} 
    @Override protected void configure(HttpSecurity http) throws Exception { 
		http
			.authorizeRequests() 
			.antMatchers("/hello").access("hasRole('ROLE_ADMIN')")
			.anyRequest().permitAll() 
			.and() 
		.formLogin()
			.loginPage("/login") 
			.usernameParameter("username")
			.passwordParameter("password") 
			.and() 
		.logout().logoutSuccessUrl("/login?logout") 
			.and() 
		.exceptionHandling()
			.accessDeniedPage("/403") 
			.and();
	} 

    @Bean
    public PasswordEncoder passwordEncoder() {
        return new BCryptPasswordEncoder();
    }
    
} 
