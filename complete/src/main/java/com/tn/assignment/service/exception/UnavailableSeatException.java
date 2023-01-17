
package com.tn.assignment.service.exception;

import org.springframework.dao.DataIntegrityViolationException;

public class UnavailableSeatException extends DataIntegrityViolationException {

    public UnavailableSeatException(Integer coursesId) {
        super(String.valueOf(coursesId));
    }

    public UnavailableSeatException(String msg) {
        super(msg);
    }
}