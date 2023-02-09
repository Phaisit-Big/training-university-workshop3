package com.tn.assignment.controller.handler;

import java.util.ArrayList;
import java.util.List;
import java.util.Locale;

import org.springframework.context.MessageSource;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;

import com.tn.assignment.model.CourseRegistration;
import com.tn.assignment.model.CourseRegistrationResult;
import com.tn.assignment.service.CourseRegistrationService;
import com.tn.assignment.service.exception.AlreadyRegisteredException;
import com.tn.assignment.service.exception.CourseNotFoundException;
import com.tn.assignment.service.exception.StudentNotFoundException;
import com.tn.assignment.service.exception.UnavailableCreditException;
import com.tn.assignment.service.exception.UnavailableSeatException;


public class RegisterCourseHandler {

	
	private CourseRegistrationService courseRegistationService;

	private MessageSource messageSource;


	public RegisterCourseHandler(CourseRegistrationService courseRegistationService,
								 MessageSource messageSource) {
		this.courseRegistationService = courseRegistationService;
		this.messageSource = messageSource;
	}
	
	protected CourseRegistrationResult process(Locale locale, CourseRegistration courseReg) {
		try {

			CourseRegistration resultCourseReg = courseRegistationService.register(courseReg.getStudentsId(), courseReg.getCoursesId());

			String resultMessage = messageSource.getMessage("regs.add.rs", new Object[] {resultCourseReg.getId()}, locale);
			return new CourseRegistrationResult(resultMessage, resultCourseReg);

		} catch (StudentNotFoundException unfex) {
			String errorMessage = messageSource.getMessage("regs.add.rs.err.usernotfound", new Object[] {unfex.getMessage()}, locale);
			return new CourseRegistrationResult(errorMessage, null);

		} catch (CourseNotFoundException cnfex) {
			String errorMessage = messageSource.getMessage("regs.add.rs.err.coursenotfound", new Object[] {cnfex.getMessage()}, locale);
			return new CourseRegistrationResult(errorMessage, null);

		} catch (AlreadyRegisteredException arex) {
			String errorMessage = messageSource.getMessage("regs.add.rs.err.alreadyregistered", new Object[] {arex.getMessage()}, locale);						
			return new CourseRegistrationResult(errorMessage, null);

		} catch (UnavailableCreditException ucex) {
			String errorMessage = messageSource.getMessage("regs.add.rs.err.unavailablecredit", new Object[] {ucex.getStudentsId(), ucex.getMessage()}, locale);						
			return new CourseRegistrationResult(errorMessage, null);	

		} catch (UnavailableSeatException ucex) {
			String errorMessage = messageSource.getMessage("regs.add.rs.err.unavailableseat", new Object[] {ucex.getMessage()}, locale);						
			return new CourseRegistrationResult(errorMessage, null);

		}		
	}



	public ResponseEntity<Object> register(Locale locale, CourseRegistration courseReg) {

		CourseRegistrationResult result = process(locale, courseReg);
		return ResponseEntity.status((result.getRegistration() != null)? HttpStatus.CREATED: HttpStatus.BAD_REQUEST).body(result);

	}


	public ResponseEntity<Object> registerBatch(Locale locale, List<CourseRegistration> courseRegList) {
		
		List<CourseRegistrationResult> resultList = new ArrayList<CourseRegistrationResult>();

		for (CourseRegistration courseReg: courseRegList) {
			CourseRegistrationResult result = process(locale, courseReg);
			resultList.add(result);
		}
		
		return ResponseEntity.status(HttpStatus.OK).body(resultList);
	}
}
