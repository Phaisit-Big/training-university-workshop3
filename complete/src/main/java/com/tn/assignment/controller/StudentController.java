package com.tn.assignment.controller;

import java.util.Locale;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.context.MessageSource;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestHeader;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;

import com.tn.assignment.controller.handler.AddStudentHandler;
import com.tn.assignment.model.Student;
import com.tn.assignment.service.StudentService;

@Controller
public class StudentController {


	@Autowired    
	private StudentService studentService;

	@Autowired
	@Qualifier(value="messageSource1")   
	private MessageSource messageSource;


	/*
	 * @return the number of students
	 */
	@RequestMapping(path="/students/count", method=RequestMethod.GET) 
	public @ResponseBody ResponseEntity<Object> countStudents(@RequestParam(name="isActive", defaultValue="true") boolean isActive) {

		int count = studentService.countStudents(isActive);

		return ResponseEntity.status(HttpStatus.OK).body(count);
	}

	/*
	 * @return list all students in a JSONArray with JSONObjects transfermed from Student models
	 */
	@RequestMapping(path="/students", method=RequestMethod.GET) 
	public @ResponseBody ResponseEntity<Object> getAllStudents() {

		return ResponseEntity.status(HttpStatus.OK).body(studentService.findAll());
	}

	/*
	 * @param student - the Student model with name and optional email  
	 * @return a JSONObject with description "SAVED ID: <student id>!" and the saved Student model.
	 *         The Student model contains four fields as follows:
	 * <ul>
	 *   <li>id - student id</li>
	 *   <li>isActive - true as a default value for new student</li>
	 *   <li>name - parametered name</li>
	 *   <li>email - parametered email</li>
	 * </ul>
	 */
	@RequestMapping(path="/students", method=RequestMethod.POST) 
	public @ResponseBody ResponseEntity<Object> addStudent(@RequestHeader(name="Accept-Language", required=false) Locale locale,
																@RequestBody Student student) {


		return new AddStudentHandler(studentService, messageSource).addStudent(locale, student);
				
	}

}
