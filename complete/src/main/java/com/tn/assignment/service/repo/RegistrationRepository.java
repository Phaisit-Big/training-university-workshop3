package com.tn.assignment.service.repo;

import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;

import com.tn.assignment.service.repo.entity.RegistrationEntity;


public interface RegistrationRepository extends CrudRepository<RegistrationEntity, Integer> {

    @Query("SELECT r FROM REGISTRATIONS r WHERE r.student.id=?1 AND r.course.id=?2")
    public RegistrationEntity findByStudentsIdAndCoursesId(Integer usersId, Integer coursesId);


    @Query("SELECT SUM(c.credit) FROM REGISTRATIONS r LEFT JOIN COURSES c ON r.course.id=c.id WHERE r.student.id=?1")
    public Integer countStudentCredits(Integer usersId);    

    
    @Query("SELECT COUNT(r.id) FROM REGISTRATIONS r WHERE r.course.id=?1")
    public Integer countCourseSeats(Integer coursesId);    
}
