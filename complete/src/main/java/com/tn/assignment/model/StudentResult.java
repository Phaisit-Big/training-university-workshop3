package com.tn.assignment.model;

public class StudentResult {


	private String description;
	private Student student;

	

	
	public StudentResult(String description, Student student) {
		this.description = description;
		this.student = student;
	}

	
	public String getDescription() {
		return description;
	}
	public void setDescription(String description) {
		this.description = description;
	}


	public Student getStudent() {
		return student;
	}


	public void setStudent(Student student) {
		this.student = student;
	}


	@Override
	public String toString() {
		return "StudentResult [description=" + description + ", student=" + student + "]";
	}




	

}
