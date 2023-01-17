
package com.tn.assignment.service.exception;

import org.springframework.dao.DataIntegrityViolationException;

public class UnavailableCreditException extends DataIntegrityViolationException {

    private Integer studentsId;

    public UnavailableCreditException(Integer studentsId, int availableCredit, int courseCredit) {
        this(studentsId, String.format("%d<%d", availableCredit, courseCredit));
    }

    public UnavailableCreditException(Integer studentsId, String msg) {
        super(msg);
        this.studentsId = studentsId;
    }

    public Integer getStudentsId() {
        return studentsId;
    }

    
}