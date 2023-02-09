package com.tn.assignment.service.validator;

import java.util.regex.Matcher;
import java.util.regex.Pattern;

import com.tn.assignment.service.repo.StudentRepository;
import com.tn.assignment.service.repo.entity.StudentEntity;

/* 
 * TODO: Code review 3.2 - Logic performance
 *  - internal memory logic should be checked before querying databases or calling external parties
 *    e.g. the method isValid check email patterns before querying the existing of it in the database
 *  - a reuseable object could be static to help reduce unnecessary object instantiation
 *    e.g. EMAIL_PATTERN is declared once to reduce repetition of regex pattern parsing
 */
public class StudentEmailChecker {
    
    private static final Pattern EMAIL_PATTERN = Pattern.compile("^[\\w-\\.]+@([\\w-]+\\.)+[\\w-]{2,4}$");

    private StudentRepository studentRepository;
    

    public StudentEmailChecker(StudentRepository studentRepository) {
        this.studentRepository = studentRepository;
    }

    public boolean isValid(String email) {

        Matcher matcher = EMAIL_PATTERN.matcher(email);  
        if (!matcher.matches()) {
            // incorrect email pattern
            return false;
        }  

        StudentEntity studentEntity = studentRepository.findFirstByEmail(email);
        if (null != studentEntity) {
            // email is already used by other
            return false;
        }

        return true;
    }
}