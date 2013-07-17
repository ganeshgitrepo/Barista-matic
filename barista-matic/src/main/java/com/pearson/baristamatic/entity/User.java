package com.pearson.baristamatic.entity;

import java.io.Serializable;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.EnumType;
import javax.persistence.Enumerated;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;

@Entity
@Table(name="BARISTA_USER")
public class User implements Serializable {
	private static final long serialVersionUID = 1L;
	
	private long userId;				// Unique user ID
	private String userName;			// Username
	private String password;			// Password
	private Role role;					// Role of user (i.e. "Administrator" or "Customer")
	
	// No-argument constructor supplied for Hibernate
	protected User() { }
	
	public User(String userName, String password, Role role) {
		this.userName = userName;
		this.password = password;
		this.role = role;
	}

	@Id
	@GeneratedValue(strategy=GenerationType.AUTO)
	@Column(name="USER_ID")
	public long getUserId() {
		return userId;
	}

	public void setUserId(long userId) {
		this.userId = userId;
	}

	@Column(name="USER_NAME", nullable=false)
	public String getUserName() {
		return userName;
	}

	public void setUserName(String userName) {
		this.userName = userName;
	}

	@Column(name="PASSWORD", nullable=false)
	public String getPassword() {
		return password;
	}

	public void setPassword(String password) {
		this.password = password;
	}

	@Column(name="USER_ROLE", nullable=false)
	@Enumerated(EnumType.STRING)
	public Role getRole() {
		return role;
	}

	public void setRole(Role role) {
		this.role = role;
	}
	
	public enum Role {
		CUSTOMER, ADMINISTRATOR
	}
}