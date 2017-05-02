package com.cloudteam6.config;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.EnableAutoConfiguration;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.autoconfigure.domain.EntityScan;
import org.springframework.boot.builder.SpringApplicationBuilder;
import org.springframework.boot.web.support.SpringBootServletInitializer;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.Configuration;
import org.springframework.data.jpa.repository.config.EnableJpaRepositories;

@Configuration
@ComponentScan(basePackages = {"com.cloudteam6"})
@EnableAutoConfiguration
@SpringBootApplication
@EntityScan(basePackages = {"com.cloudteam6.model"})
@EnableJpaRepositories(basePackages = {"com.cloudteam6.repository"})
public class Application extends SpringBootServletInitializer {

	@Override
    protected SpringApplicationBuilder configure(SpringApplicationBuilder application) {
        return application.sources(appClass);
    }

    public static void main(String[] args) throws Exception {
       SpringApplication.run(Application.class, args);
    }
    
    private static Class<Application> appClass = Application.class;

}