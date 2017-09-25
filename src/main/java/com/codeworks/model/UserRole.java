package com.codeworks.model;

import java.io.Serializable;




public class UserRole implements Serializable {
	private static final long serialVersionUID = 1L;

	
	private Integer roleId;

	private Boolean active;

	private String roleName;

	public UserRole() {
	}

	public Integer getRoleId() {
		return this.roleId;
	}

	public void setRoleId(Integer roleId) {
		this.roleId = roleId;
	}

	public Boolean getActive() {
		return this.active;
	}

	public void setActive(Boolean active) {
		this.active = active;
	}

	public String getRoleName() {
		return this.roleName;
	}

	public void setRoleName(String roleName) {
		this.roleName = roleName;
	}

}