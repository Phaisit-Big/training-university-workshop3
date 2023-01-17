package com.tn.assignment.model;

public class CourseRegistration {

	private Integer id;
	private Integer studentsId;
	private Integer coursesId;
	private String studentName;			// students.name
	private String subjectCode;		// courses.subjectCode
	private String subjectName;		// courses.subjectName

	
	public Integer getId() {
		return id;
	}
	public void setId(Integer id) {
		this.id = id;
	}

	public Integer getStudentsId() {
		return studentsId;
	}
	public void setStudentsId(Integer usersId) {
		this.studentsId = usersId;
	}

	public Integer getCoursesId() {
		return coursesId;
	}
	public void setCoursesId(Integer coursesId) {
		this.coursesId = coursesId;
	}


	public String getStudentName() {
		return studentName;
	}
	public void setStudentName(String name) {
		this.studentName = name;
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

	
	@Override
	public String toString() {
		return "CourseRegistration [id=" + id + ", usersId=" + studentsId + ", coursesId=" + coursesId + ", name=" + studentName
				+ ", subjectCode=" + subjectCode + ", subjectName=" + subjectName + "]";
	}

}
