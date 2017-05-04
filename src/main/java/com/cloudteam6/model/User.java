package com.cloudteam6.model;


import javax.persistence.*;

import org.codehaus.jackson.annotate.JsonIgnore;

import java.util.Set;

@Entity
@Table(name = "user")
public class User implements java.io.Serializable {
	private static final long serialVersionUID = 1L;
	private Long id;
    private String username;
    @JsonIgnore
    private String password;
    @JsonIgnore
    private String passwordConfirm;
    private String firstname;
    private String lastname;
    @JsonIgnore
    private Set<Role> roles;
    @JsonIgnore
    private Set<App> apps;
    private int peanutbalance;

    
    /**
     * The initial number of peanuts new users get on registration
     */
    @PrePersist
    public void prePersist() {
    	peanutbalance = 5000;
    }
    
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getUsername() {
        return username;
    }

    public void setUsername(String username) {
        this.username = username;
    }
    
    @JsonIgnore
    public String getPassword() {
        return password;
    }
    
    @JsonIgnore
    public void setPassword(String password) {
        this.password = password;
    }

    @JsonIgnore
    @Transient
    public String getPasswordConfirm() {
        return passwordConfirm;
    }
    
    @JsonIgnore
    public void setPasswordConfirm(String passwordConfirm) {
        this.passwordConfirm = passwordConfirm;
    }
    
    @OneToMany(mappedBy = "user", cascade = CascadeType.ALL)
    public Set<App> getApps() {
        return apps;
    }

    public void setApps(Set<App> apps) {
        this.apps = apps;
    }
    
    @ManyToMany(fetch = FetchType.EAGER)
    @JoinTable(name = "user_role", joinColumns = @JoinColumn(name = "user_id"), inverseJoinColumns = @JoinColumn(name = "role_id"))
    @JsonIgnore
    public Set<Role> getRoles() {
        return roles;
    }

    @JsonIgnore
    public void setRoles(Set<Role> roles) {
        this.roles = roles;
    }
    
    public void addRole(Role role) {
    	this.roles.add(role);
    }
    
    public void removeRole(Role role) {
    	this.roles.remove(role);
    }
    
	public int getPeanutbalance() {
		return peanutbalance;
	}

	public void setPeanutbalance(int peanutBalance) {
		this.peanutbalance = peanutBalance;
	}
	
	public String getFirstname() {
        return firstname;
    }

    public void setFirstname(String firstname) {
        this.firstname = firstname;
    }
    
    public String getLastname() {
        return lastname;
    }

    public void setLastname(String lastname) {
        this.lastname = lastname;
    }
}
