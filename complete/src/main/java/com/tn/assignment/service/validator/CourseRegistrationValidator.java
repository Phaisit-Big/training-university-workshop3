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
 *  - modify only this class to reduce complicated logics and enhance code readability
 *  - hint: POST /registrations  {"studentsId": 1234, "coursesId": 6}
 */
public class CourseRegistrationValidator {


    private RegistrationRepository registrationRepository;
    private Integer mxCredits;
	private Integer mxSeats;

    public CourseRegistrationValidator(RegistrationRepository registrationRepository) {
        this.registrationRepository = registrationRepository;
    }


    public void validate(StudentEntity studentEntity, CourseEntity courseEntity) {

        // check duplications of student-course registration
        if (null == registrationRepository.findByStudentsIdAndCoursesId(studentEntity.getId(), courseEntity.getId())) {

            // check credit availability of a student
            Integer userCredits = registrationRepository.countStudentCredits(studentEntity.getId());
            int availableCredits = getMaximumCreditsPerUser() - Optional.ofNullable(userCredits).orElse(0);
            if (availableCredits >= courseEntity.getCredit()) {
                
                // check seat availability of course
                Integer courseSeats = registrationRepository.countCourseSeats(courseEntity.getId());
                int availableSeats = getMaximumSeatsPerCourse() - Optional.ofNullable(courseSeats).orElse(0);
                if (availableSeats > 0) {
                    return;
                } else {
                    throw new UnavailableSeatException(courseEntity.getId());
                }     

            } else {
                throw new UnavailableCreditException(studentEntity.getId(), availableCredits, courseEntity.getCredit());
            }
            
        } else {
            throw new AlreadyRegisteredException(studentEntity.getId(), courseEntity.getId());
        }
    }


    public Integer getMaximumCreditsPerUser() {
        return mxCredits;
    }


    public void setMaximumCreditsPerUser(Integer mxCredits) {
        this.mxCredits = mxCredits;
    }


    public Integer getMaximumSeatsPerCourse() {
        return mxSeats;
    }


    public void setMaximumSeatsPerCourse(Integer mxSeats) {
        this.mxSeats = mxSeats;
    }

    
}