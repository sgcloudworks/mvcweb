package com.codeworks.model;

import java.io.Serializable;
import java.util.Date;


public class User implements Serializable {
	private static final long serialVersionUID = 1L;

	
	private Integer userId = -1;

	
	private Date accountExpiresOn;

	private Boolean active;

	
	private String altEmailId;

	
	private String createdBy;

	
	private Date creationDate;

	
	private String emailId;

	
	private String modifiedBy;

	
	private Date modifiedDate;

	private String password;

	
	private Date pwdExpiryDate;

	private String salt;

	
	private long studentId;
	
	private String firstName,lastName;
	
	private String adminMessage = "";
	
	
	public String getAdminMessage() {
		return adminMessage;
	}

	public void setAdminMessage(String adminMessage) {
		this.adminMessage = adminMessage;
	}

	public String getFirstName() {
		return firstName;
	}

	public void setFirstName(String firstName) {
		this.firstName = firstName;
	}

	public String getLastName() {
		return lastName;
	}

	public void setLastName(String lastName) {
		this.lastName = lastName;
	}

	public String getConfirmPassword() {
		return confirmPassword;
	}

	public void setConfirmPassword(String confirmPassword) {
		this.confirmPassword = confirmPassword;
	}

	public int getRoleId() {
		return roleId;
	}

	public void setRoleId(int roleId) {
		this.roleId = roleId;
	}

	private Date targetDate;
	
	private String confirmPassword;
	
	private int roleId;

	
	public Integer getUserId() {
		return this.userId;
	}

	public void setUserId(Integer userId) {
		this.userId = userId;
	}

	public Date getAccountExpiresOn() {
		return this.accountExpiresOn;
	}

	public void setAccountExpiresOn(Date accountExpiresOn) {
		this.accountExpiresOn = accountExpiresOn;
	}

	public Boolean getActive() {
		return this.active;
	}

	public void setActive(Boolean active) {
		this.active = active;
	}

	public String getAltEmailId() {
		return this.altEmailId;
	}

	public void setAltEmailId(String altEmailId) {
		this.altEmailId = altEmailId;
	}

	public String getCreatedBy() {
		return this.createdBy;
	}

	public void setCreatedBy(String createdBy) {
		this.createdBy = createdBy;
	}

	public Date getCreationDate() {
		return this.creationDate;
	}

	public void setCreationDate(Date creationDate) {
		this.creationDate = creationDate;
	}

	public String getEmailId() {
		return this.emailId;
	}

	public void setEmailId(String emailId) {
		this.emailId = emailId;
	}

	public String getModifiedBy() {
		return this.modifiedBy;
	}

	public void setModifiedBy(String modifiedBy) {
		this.modifiedBy = modifiedBy;
	}

	public Date getModifiedDate() {
		return this.modifiedDate;
	}

	public void setModifiedDate(Date modifiedDate) {
		this.modifiedDate = modifiedDate;
	}

	public String getPassword() {
		return this.password;
	}

	public void setPassword(String password) {
		this.password = password;
	}

	public Date getPwdExpiryDate() {
		return this.pwdExpiryDate;
	}

	public void setPwdExpiryDate(Date pwdExpiryDate) {
		this.pwdExpiryDate = pwdExpiryDate;
	}

	public String getSalt() {
		return this.salt;
	}

	public void setSalt(String salt) {
		this.salt = salt;
	}

	public long getStudentId() {
		return this.studentId;
	}

	public void setStudentId(long studentId) {
		this.studentId = studentId;
	}

	public Date getTargetDate() {
		return this.targetDate;
	}

	public void setTargetDate(Date targetDate) {
		this.targetDate = targetDate;
	}

}