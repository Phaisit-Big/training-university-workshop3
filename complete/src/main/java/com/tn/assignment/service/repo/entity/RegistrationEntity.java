package com.tn.assignment.service.repo.entity;

import java.sql.Timestamp;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.ManyToOne;

@Entity(name="REGISTRATIONS") 
public class RegistrationEntity {
	@Id
	@GeneratedValue(strategy=GenerationType.AUTO)
    @Column(name="ID", updatable=false)	
	private Integer id;

	@ManyToOne
    @JoinColumn(name="STUDENTSID")
	private StudentEntity student;

    @ManyToOne
    @JoinColumn(name="COURSESID")
	private CourseEntity course;


	@Column(name="CREATED")
	private Timestamp created;


	public Integer getId() {
		return id;
	}


	public void setId(Integer id) {
		this.id = id;
	}


	public StudentEntity getStudent() {
		return student;
	}


	public void setStudent(StudentEntity user) {
		this.student = user;
	}


	public CourseEntity getCourse() {
		return course;
	}


	public void setCourse(CourseEntity course) {
		this.course = course;
	}


	public Timestamp getCreated() {
		return created;
	}


	public void setCreated(Timestamp created) {
		this.created = created;
	}


	
}
