package com.tn.assignment.model;

public class Student {
	private Integer id;
	private String name;
	
	//	@JsonIgnore
	private String email;
	private Boolean isActive; 


	public Integer getId() {
		return id;
	}

	public void setId(Integer id) {
		this.id = id;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}


	public String getEmail() {
		return email;
	}

	public void setEmail(String email) {
		this.email = email;
	}


	public Boolean getIsActive() {
		return isActive;
	}

	public void setIsActive(Boolean isActive) {
		this.isActive = isActive;
	}


	@Override
	public String toString() {
		return "Student [id=" + id + ", name=" + name + ", email=" + email + ", isActive=" + isActive + "]";
	}





}
