package com.tn.assignment.service.validator;

import java.util.Optional;

import com.tn.assignment.service.exception.AlreadyRegisteredException;
import com.tn.assignment.service.exception.UnavailableCreditException;
import com.tn.assignment.service.exception.UnavailableSeatException;
import com.tn.assignment.service.repo.RegistrationRepository;
import com.tn.assignment.service.repo.entity.CourseEntity;
import com.tn.assignment.service.repo.entity.StudentEntity;

/* 
 * TODO: Code review 3.1 - Code complexity and code readability
 *  - use throw or return to help reduce too many nested if-else blocks 
 *    e.g. the method validate reduces logic entanglement by using throw statements instead of nested if-else of three checks
 */
public class CourseRegistrationValidator {


    private RegistrationRepository registrationRepository;
    private Integer maximumCreditsPerUser;
	private Integer maximumSeatsPerCourse;

    public CourseRegistrationValidator(RegistrationRepository registrationRepository) {
        this.registrationRepository = registrationRepository;
    }


    public void validate(StudentEntity studentEntity, CourseEntity courseEntity) {


        // check duplications of student-course registration
        if (null != registrationRepository.findByStudentsIdAndCoursesId(studentEntity.getId(), courseEntity.getId())) {
            throw new AlreadyRegisteredException(studentEntity.getId(), courseEntity.getId());
        }

        // check credit availability of a student
        Integer userCredits = registrationRepository.countStudentCredits(studentEntity.getId());
        int availableCredits = getMaximumCreditsPerUser() - Optional.ofNullable(userCredits).orElse(0);
        if (availableCredits < courseEntity.getCredit()) {
            throw new UnavailableCreditException(studentEntity.getId(), availableCredits, courseEntity.getCredit());
        }

        // check seat availability of course
        Integer courseSeats = registrationRepository.countCourseSeats(courseEntity.getId());
        int availableSeats = getMaximumSeatsPerCourse() - Optional.ofNullable(courseSeats).orElse(0);
        if (availableSeats <= 0) {
            throw new UnavailableSeatException(courseEntity.getId());
        }
    }


    public Integer getMaximumCreditsPerUser() {
        return maximumCreditsPerUser;
    }


    public void setMaximumCreditsPerUser(Integer maximumCreditsPerUser) {
        this.maximumCreditsPerUser = maximumCreditsPerUser;
    }


    public Integer getMaximumSeatsPerCourse() {
        return maximumSeatsPerCourse;
    }


    public void setMaximumSeatsPerCourse(Integer maximumSeatsPerCourse) {
        this.maximumSeatsPerCourse = maximumSeatsPerCourse;
    }

    
}