package com.tn.assignment.service.repo.entity;

import java.sql.Timestamp;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;

@Entity(name="COURSES") 
public class CourseEntity {
	@Id
	@GeneratedValue(strategy=GenerationType.AUTO)
    @Column(name="ID", updatable=false)	
	private Integer id;

	@Column(name="SUBJECTCODE")
	private String subjectCode;

	@Column(name="SUBJECTNAME")
	private String subjectName;

	@Column(name="CREDIT")
	private Integer credit;

	@Column(name="SEMESTER")
	private String semester;

	@Column(name="CREATED")
	private Timestamp created;

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

	public Timestamp getCreated() {
		return created;
	}

	public void setCreated(Timestamp created) {
		this.created = created;
	}



	
}
