package com.tn.assignment.service.validator;

import java.util.regex.Matcher;
import java.util.regex.Pattern;

import com.tn.assignment.service.repo.StudentRepository;
import com.tn.assignment.service.repo.entity.StudentEntity;

/* 
 * TODO: Code review 3.2 - Code performance
 *  - modify only this class to enhance processing performance
 *  - example: POST /students  {"name": "สมชาย แสนดี", "email": "somchai@hotmail.com"}
 */
public class StudentEmailChecker {
    
    private StudentRepository studentRepository;
    

    public StudentEmailChecker(StudentRepository studentRepository) {
        this.studentRepository = studentRepository;
    }

    public boolean isValid(String email) {

        StudentEntity studentEntity = studentRepository.findFirstByEmail(email);
        if (null != studentEntity) {
            // email is already used by other
            return false;
        }

        Pattern pattern = Pattern.compile("^[\\w-\\.]+@([\\w-]+\\.)+[\\w-]{2,4}$");
        Matcher matcher = pattern.matcher(email);  
        if (!matcher.matches()) {
            // incorrect email pattern
            return false;
        }  

        return true;
    }
}