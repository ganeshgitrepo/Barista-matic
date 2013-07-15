package com.pearson.baristamatic.entity;

import java.io.Serializable;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;

@Entity
@Table(name="USER_ROLE")
public class Role implements Serializable {
	private static final long serialVersionUID = 1L;

	private long roleId;				// Unique role ID
	private String roleName;			// Name of role (i.e. Customer or Administrator)
	
	// No-argument constructor supplied for Hibernate
	protected Role() { }
	
	public Role(long roleId, String roleName) {
		this.roleId = roleId;
		this.roleName = roleName;
	}

	@Id
	@GeneratedValue(strategy=GenerationType.AUTO)
	@Column(name="ROLE_ID")
	public long getRoleId() {
		return roleId;
	}
	
	public void setRoleId(long roleId) {
		this.roleId = roleId;
	}
	
	@Column(name="ROLE_NAME")
	public String getRoleName() {
		return roleName;
	}
	
	public void setRoleName(String roleName) {
		this.roleName = roleName;
	}
}
