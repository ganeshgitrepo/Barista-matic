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
@Table(name="BARISTA_USER_ROLE")
public class Role implements Serializable {
	private static final long serialVersionUID = 1L;

	private long roleId;				// Unique role ID
	private RoleType roleType;				// Type of role (i.e. Customer or Administrator)
	
	// No-argument constructor supplied for Hibernate
	protected Role() { }
	
	public Role(long roleId, RoleType roleType) {
		this.roleId = roleId;
		this.roleType = roleType;
	}

	@Id
	@GeneratedValue(strategy=GenerationType.AUTO)
	@Column(name="ROLE_ID", nullable=false)
	public long getRoleId() {
		return roleId;
	}
	
	public void setRoleId(long roleId) {
		this.roleId = roleId;
	}
	
	@Enumerated(EnumType.STRING)
	@Column(name="ROLE_NAME")
	public RoleType getRoleType() {
		return this.roleType;
	}
	
	public void setRoleType(RoleType roleType) {
		this.roleType = roleType;
	}
	
	public enum RoleType {
		Customer, Administrator
	}
}
