package com.cloudteam6.model;

import javax.persistence.*;

@Entity
@Table(name = "app")
public class App {
	private Long id;	
	private String name;	
	private String URL;	
	private String applicationimageurl;
	private boolean active;
	
	protected App() {}
	
	public App(String name, String URL, String applicationImageURL, boolean active) {
		this.name = name;
		this.URL = URL;
		this.applicationimageurl = applicationImageURL;
		this.active = active;
	}

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

	public String getApplicationimageurl() {
		return applicationimageurl;
	}

	public void setApplicationimageurl(String applicationImageURL) {
		this.applicationimageurl = applicationImageURL;
	}

	public boolean isActive() {
		return active;
	}

	public void setActive(boolean active) {
		this.active = active;
	}
		
}
