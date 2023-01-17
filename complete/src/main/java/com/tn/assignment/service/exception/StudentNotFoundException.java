
package com.tn.assignment.service.exception;

import org.springframework.dao.DataIntegrityViolationException;

public class StudentNotFoundException extends DataIntegrityViolationException {

    public StudentNotFoundException(Integer usersId) {
        this(String.valueOf(usersId));
    }
    
    public StudentNotFoundException(String msg) {
        super(msg);
    }

}