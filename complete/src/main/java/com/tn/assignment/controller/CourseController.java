package com.tn.assignment.controller;

import java.util.HashMap;
import java.util.Locale;
import java.util.Map;

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
import org.springframework.web.bind.annotation.ResponseBody;

import com.tn.assignment.controller.handler.AddCourseHandler;
import com.tn.assignment.model.Course;
import com.tn.assignment.service.CourseService;

/*
 * TODO: Code review 3.4 - Cohesion and decoupling
 * - Move non-routing codes in a controller to another specific-purpose handler class
 *   e.g. separate codes that handle response formattting to AddCourseHandler
 */
@Controller
public class CourseController {


	@Autowired    
	private CourseService courseService;

	@Autowired
	@Qualifier(value="messageSource1")   
	private MessageSource messageSource;


	/*
	 * @return list all courses in a JSONArray with JSONObjects transfermed from Course models
	 */
	@RequestMapping(path="/courses", method=RequestMethod.GET) 
	public @ResponseBody Iterable<Course> getAllCourses() {

		return courseService.findAll();
	}

	/*
	 * @param course - the Course model with name and optional email  
	 * @return a JSONObject with description "SAVED ID: <course id>!" and the saved Course model.
	 *         The Course model contains four fields as follows:
	 * <ul>
	 *   <li>id - course id</li>
	 *   <li>subjectCode - subject code, might be the same for many semesters</li>
	 *   <li>subjectName - subject name</li>
	 *   <li>credit - course credit</li>
	 *   <li>semester - course semester e.g. 2022/1</li>
	 * </ul>
	 */
	@RequestMapping(path="/courses", method=RequestMethod.POST) 
	public @ResponseBody ResponseEntity<Object> addCourse(@RequestHeader(name="Accept-Language", required=false) Locale locale,
																@RequestBody Course course) {

		return new AddCourseHandler(courseService, messageSource).addCourse(locale, course);
	}

}
