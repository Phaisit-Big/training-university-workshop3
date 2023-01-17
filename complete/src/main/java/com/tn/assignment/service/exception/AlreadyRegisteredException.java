
package com.tn.assignment.service.exception;

import org.springframework.dao.DuplicateKeyException;

public class AlreadyRegisteredException extends DuplicateKeyException {

    public AlreadyRegisteredException(Integer usersId, Integer coursesId) {
        this(String.format("%s-%s", usersId, coursesId));
    }

    public AlreadyRegisteredException(String msg) {
        super(msg);
    }    
}