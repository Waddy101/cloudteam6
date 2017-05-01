package com.cloudteam6.model;

import javax.persistence.*;

@Entity
@Table(name = "app")
public class App {
	private Long id;	
	private String name;	
	private String URL;	
	private String applicationImageURL;

	@Id
	@GeneratedValue(strategy = GenerationType.AUTO)
	public Long getId() {
		return id;
	}

	public void setId(Long id) {
		this.id = id;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public String getURL() {
		return URL;
	}

	public void setURL(String uRL) {
		URL = uRL;
	}

	public String getApplicationImageURL() {
		return applicationImageURL;
	}

	public void setApplicationImageURL(String applicationImageURL) {
		this.applicationImageURL = applicationImageURL;
	}
		
}
