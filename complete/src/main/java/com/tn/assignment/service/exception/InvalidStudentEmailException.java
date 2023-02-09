
package com.tn.assignment.service.exception;

import org.springframework.dao.DataIntegrityViolationException;

public class InvalidStudentEmailException extends DataIntegrityViolationException {

    public InvalidStudentEmailException(String email) {
        super(String.format("%s", email));
    }
 
}