package com.tn.assignment.controller.handler;

import java.util.HashMap;
import java.util.Locale;
import java.util.Map;

import org.springframework.context.MessageSource;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;

import com.tn.assignment.model.CourseRegistration;
import com.tn.assignment.service.CourseRegistrationService;
import com.tn.assignment.service.exception.AlreadyRegisteredException;
import com.tn.assignment.service.exception.CourseNotFoundException;
import com.tn.assignment.service.exception.UnavailableCreditException;
import com.tn.assignment.service.exception.UnavailableSeatException;
import com.tn.assignment.service.exception.StudentNotFoundException;


public class RegisterCourseHandler {

	
	private CourseRegistrationService courseRegistationService;

	private MessageSource messageSource;


	public RegisterCourseHandler(CourseRegistrationService courseRegistationService,
								 MessageSource messageSource) {
		this.courseRegistationService = courseRegistationService;
		this.messageSource = messageSource;
	}

	
	public ResponseEntity<Object> process(Locale locale, CourseRegistration courseReg) {
		try {

			CourseRegistration resultCourseReg = courseRegistationService.register(courseReg.getStudentsId(), courseReg.getCoursesId());

			String resultMessage = messageSource.getMessage("regs.add.rs", new Object[] {resultCourseReg.getId()}, locale);
			Map<String, Object> resultMap = formatResult(resultMessage, resultCourseReg);
			return ResponseEntity.status(HttpStatus.CREATED).body(resultMap);

		} catch (StudentNotFoundException unfex) {
			String errorMessage = messageSource.getMessage("regs.add.rs.err.usernotfound", new Object[] {unfex.getMessage()}, locale);
			Map<String, Object> resultMap = formatResult(errorMessage, null);
			return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(resultMap);

		} catch (CourseNotFoundException cnfex) {
			String errorMessage = messageSource.getMessage("regs.add.rs.err.coursenotfound", new Object[] {cnfex.getMessage()}, locale);
			Map<String, Object> resultMap = formatResult(errorMessage, null);
			return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(resultMap);	

		} catch (AlreadyRegisteredException arex) {
			String errorMessage = messageSource.getMessage("regs.add.rs.err.alreadyregistered", new Object[] {arex.getMessage()}, locale);						
			Map<String, Object> resultMap = formatResult(errorMessage, null);
			return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(resultMap);		

		} catch (UnavailableCreditException ucex) {
			String errorMessage = messageSource.getMessage("regs.add.rs.err.unavailablecredit", new Object[] {ucex.getStudentsId(), ucex.getMessage()}, locale);						
			Map<String, Object> resultMap = formatResult(errorMessage, null);
			return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(resultMap);			

		} catch (UnavailableSeatException ucex) {
			String errorMessage = messageSource.getMessage("regs.add.rs.err.unavailableseat", new Object[] {ucex.getMessage()}, locale);						
			Map<String, Object> resultMap = formatResult(errorMessage, null);
			return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(resultMap);		

		}		
	}

	public Map<String, Object> formatResult(String description, Object resultCourseReg) {

			Map<String, Object> resultMap = new HashMap<String, Object>();
			resultMap.put("description", description);
			resultMap.put("registration", resultCourseReg);

			return resultMap;
	}

}
