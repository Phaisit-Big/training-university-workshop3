package com.tn.assignment.controller;

import java.util.Locale;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.context.MessageSource;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestHeader;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;

import com.tn.assignment.controller.handler.RegisterCourseHandler;
import com.tn.assignment.model.CourseRegistration;
import com.tn.assignment.service.CourseRegistrationService;

@Controller
public class CourseRegistrationController {


	@Autowired    
	private CourseRegistrationService courseRegistationService;

	@Autowired    
	@Qualifier(value="messageSource1")   
	private MessageSource messageSource;



	/*
	 * @return list all registrations in a JSONArray with JSONObjects transfermed from CourseRegistration models
	 */
	@RequestMapping(path="/registrations", method=RequestMethod.GET) 
	public @ResponseBody Iterable<CourseRegistration> getAllRegistrations() {
		return courseRegistationService.findAll();
	}



	/*
	 * @param courseReg - the CourseRegistration representing student id and course id 
	 * @return a JSONObject with description "SAVED ID: <registration id>!" and the saved CourseRegistration model.
	 *         The CourseRegistration model contains three fields as follows:
	 * <ul>
	 *   <li>id - registration id</li>
	 *   <li>studentsid -  student id</li>
	 *   <li>coursesid - course id</li>
	 * </ul>
	 */
	@RequestMapping(path="/registrations", method=RequestMethod.POST) 
	public @ResponseBody ResponseEntity<Object> register(@RequestHeader(name="Accept-Language", required=false) Locale locale,
														 @RequestBody CourseRegistration courseReg) {
				
		return new RegisterCourseHandler(courseRegistationService, messageSource).process(locale, courseReg);

	}

}
