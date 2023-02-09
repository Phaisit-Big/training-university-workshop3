package com.tn.assignment.model;

public class CourseRegistrationResult {


	private String description;
	private CourseRegistration registration;

	

	
	public CourseRegistrationResult(String description, CourseRegistration registration) {
		this.description = description;
		this.registration = registration;
	}

	
	public String getDescription() {
		return description;
	}
	public void setDescription(String description) {
		this.description = description;
	}

	
	public CourseRegistration getRegistration() {
		return registration;
	}
	public void setRegistration(CourseRegistration registration) {
		this.registration = registration;
	}

	
	@Override
	public String toString() {
		return "CourseRegistrationResult [description=" + description + ", registration=" + registration + "]";
	}




	

}
