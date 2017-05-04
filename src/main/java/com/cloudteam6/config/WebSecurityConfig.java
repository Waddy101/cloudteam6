package com.cloudteam6.config;

import javax.sql.DataSource;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.config.annotation.authentication.builders.AuthenticationManagerBuilder;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;

@Configuration
@EnableWebSecurity
public class WebSecurityConfig extends WebSecurityConfigurerAdapter {
	
	 @Autowired 
	 DataSource dataSource;

	 @Autowired
	 private UserDetailsService userDetailsService;
	  
	 @Autowired
	 public void configAuthentication(AuthenticationManagerBuilder auth) throws Exception {    
		 auth.userDetailsService(userDetailsService).passwordEncoder(passwordEncoder());
	 } 
    
    @Override protected void configure(HttpSecurity http) throws Exception { 
    	http.csrf().disable();
    	
    	http
			.authorizeRequests() 
			.antMatchers("/login", "/registration", "/resources/**").permitAll()
			.anyRequest().authenticated()
			.and() 
		.formLogin()
			.loginPage("/login") 
			.usernameParameter("username")
			.passwordParameter("password") 
			.and() 
		.logout().logoutSuccessUrl("/login?logout") 
			.and() 
		.exceptionHandling()
			.accessDeniedPage("/403");
	} 

    @Bean
    public PasswordEncoder passwordEncoder() {
        return new BCryptPasswordEncoder();
    }
    
} 
