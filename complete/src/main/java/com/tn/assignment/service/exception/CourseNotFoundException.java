
package com.tn.assignment.service.exception;

import org.springframework.dao.DataIntegrityViolationException;

public class CourseNotFoundException extends DataIntegrityViolationException {

    public CourseNotFoundException(Integer coursesId) {
        this(String.valueOf(coursesId));
    }
    
    public CourseNotFoundException(String msg) {
        super(msg);
    }

}