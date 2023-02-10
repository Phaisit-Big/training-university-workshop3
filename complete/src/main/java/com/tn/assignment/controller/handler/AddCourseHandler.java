package com.tn.assignment.controller.handler;

import java.util.HashMap;
import java.util.Locale;
import java.util.Map;

import org.springframework.context.MessageSource;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;

import com.tn.assignment.model.Course;
import com.tn.assignment.service.CourseService;


public class AddCourseHandler {

	
	private CourseService courseService;

	private MessageSource messageSource;


	public AddCourseHandler(CourseService courseService,
								 MessageSource messageSource) {
		this.courseService = courseService;
		this.messageSource = messageSource;
	}
	
	public ResponseEntity<Object> addCourse(Locale locale, Course course) {
		try {
			Course resultCourse = courseService.save(course);
				
			//locale = new Locale("th"); 
			//locale = LocaleContextHolder.getLocale(); 
			String resultMessage = messageSource.getMessage("courses.add.rs", new Object[] {resultCourse.getId()}, locale);
			
			Map<String, Object> resultMap = new HashMap<String, Object>();
			resultMap.put("description", resultMessage);
			resultMap.put("course", resultCourse);
			
			return ResponseEntity.status(HttpStatus.CREATED).body(resultMap);
		} catch (Exception ex) {
			return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body(ex.getMessage());
		}	
	}


}
