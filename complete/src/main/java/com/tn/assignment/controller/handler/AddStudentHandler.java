package com.tn.assignment.controller.handler;

import java.util.ArrayList;
import java.util.List;
import java.util.Locale;

import org.springframework.context.MessageSource;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;

import com.tn.assignment.model.CourseRegistration;
import com.tn.assignment.model.CourseRegistrationResult;
import com.tn.assignment.model.Student;
import com.tn.assignment.model.StudentResult;
import com.tn.assignment.service.CourseRegistrationService;
import com.tn.assignment.service.StudentService;
import com.tn.assignment.service.exception.AlreadyRegisteredException;
import com.tn.assignment.service.exception.CourseNotFoundException;
import com.tn.assignment.service.exception.InvalidStudentEmailException;
import com.tn.assignment.service.exception.StudentNotFoundException;
import com.tn.assignment.service.exception.UnavailableCreditException;
import com.tn.assignment.service.exception.UnavailableSeatException;


public class AddStudentHandler {

	
	private StudentService studentService;

	private MessageSource messageSource;


	public AddStudentHandler(StudentService studentService,
								 MessageSource messageSource) {
		this.studentService = studentService;
		this.messageSource = messageSource;
	}
	
	protected StudentResult process(Locale locale, Student student) {
		try {

			Student resultStudent = studentService.save(student);
				
			//locale = new Locale("th"); 
			//locale = LocaleContextHolder.getLocale(); 
			String resultMessage = messageSource.getMessage("users.add.rs", new Object[] {resultStudent.getId()}, locale);
			
			return new StudentResult(resultMessage, resultStudent);
			
		} catch (InvalidStudentEmailException iseex) {
			String errorMessage = messageSource.getMessage("users.add.rs.err.invalidemail", new Object[] {iseex.getMessage()}, locale);
			return new StudentResult(errorMessage, null);

		}	
	}



	public ResponseEntity<Object> addStudent(Locale locale, Student student) {

		StudentResult result = process(locale, student);
		return ResponseEntity.status((result.getStudent() != null)? HttpStatus.CREATED: HttpStatus.BAD_REQUEST).body(result);

	}

}
