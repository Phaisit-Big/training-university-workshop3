package com.tn.assignment.model;

public class Course {
	private Integer id;
	private String subjectCode;
	private String subjectName;
	private Integer credit;
	private String semester;
	

	public Integer getId() {
		return id;
	}

	public void setId(Integer id) {
		this.id = id;
	}

	public String getSubjectCode() {
		return subjectCode;
	}

	public void setSubjectCode(String subjectCode) {
		this.subjectCode = subjectCode;
	}

	public String getSubjectName() {
		return subjectName;
	}

	public void setSubjectName(String subjectName) {
		this.subjectName = subjectName;
	}

	public Integer getCredit() {
		return credit;
	}

	public void setCredit(Integer credit) {
		this.credit = credit;
	}

	public String getSemester() {
		return semester;
	}

	public void setSemester(String semester) {
		this.semester = semester;
	}

	
	@Override
	public String toString() {
		return "Course [id=" + id + ", subjectCode=" + subjectCode + ", subjectName=" + subjectName + ", credit="
				+ credit + ", semester=" + semester + "]";
	}


}
