package com.tn.assignment.service;

import java.sql.Timestamp;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.tn.assignment.model.CourseRegistration;
import com.tn.assignment.service.exception.AlreadyRegisteredException;
import com.tn.assignment.service.exception.CourseNotFoundException;
import com.tn.assignment.service.exception.UnavailableCreditException;
import com.tn.assignment.service.exception.UnavailableSeatException;
import com.tn.assignment.service.exception.StudentNotFoundException;
import com.tn.assignment.service.repo.CourseRepository;
import com.tn.assignment.service.repo.RegistrationRepository;
import com.tn.assignment.service.repo.StudentRepository;
import com.tn.assignment.service.repo.entity.CourseEntity;
import com.tn.assignment.service.repo.entity.RegistrationEntity;
import com.tn.assignment.service.repo.entity.StudentEntity;

@Service    
public class CourseRegistrationService {


    @Value("#{new Integer(${users.credits.max})}")
	private Integer maximumCreditsPerUser;
	// public static int maximumCreditsPerUser = 12;

    @Value("#{new Integer(${courses.seats.max})}")
	private Integer maximumSeatsPerCourse;
	// public static int maximumSeatsPerCourse = 5;


	@Autowired 
	private StudentRepository studentRepository;

	@Autowired 
	private CourseRepository courseRepository;
    
	@Autowired 
	private RegistrationRepository registrationRepository;


    public List<CourseRegistration> findAll() {   
        
        Iterable<RegistrationEntity> iter = registrationRepository.findAll();
        
        List<CourseRegistration> ls = new ArrayList<>();    
        iter.forEach(regEntity -> {
            CourseRegistration courseReg = new CourseRegistration();
            courseReg.setId(regEntity.getId());
            courseReg.setStudentsId(regEntity.getStudent().getId());
            courseReg.setCoursesId(regEntity.getCourse().getId());   
            courseReg.setStudentName(regEntity.getStudent().getName());                       
            courseReg.setSubjectCode(regEntity.getCourse().getSubjectCode());
            courseReg.setSubjectName(regEntity.getCourse().getSubjectName());
            ls.add(courseReg);
        });    
        return ls;    
    }

    @Transactional
    public CourseRegistration register(Integer studentsId, Integer coursesId) {
        Optional<StudentEntity> studentOpt = studentRepository.findById(studentsId);
        if (studentOpt.isEmpty()) {
            throw new StudentNotFoundException(studentsId);
        }

        Optional<CourseEntity> courseOpt = courseRepository.findById(coursesId);
        if (courseOpt.isEmpty()) {
            throw new CourseNotFoundException(coursesId);
        }


        // check duplications of student-course registration
        if (null != registrationRepository.findByStudentsIdAndCoursesId(studentsId, coursesId)) {
            throw new AlreadyRegisteredException(studentsId, coursesId);
        }

        // check credit availability of a student
        Integer userCredits = registrationRepository.countStudentCredits(studentsId);
        int availableCredits = maximumCreditsPerUser - Optional.ofNullable(userCredits).orElse(0);
        if (availableCredits < courseOpt.get().getCredit()) {
            throw new UnavailableCreditException(studentsId, availableCredits, courseOpt.get().getCredit());
        }

        // check seat availability of course
        Integer courseSeats = registrationRepository.countCourseSeats(coursesId);
        int availableSeats = maximumSeatsPerCourse - Optional.ofNullable(courseSeats).orElse(0);
        if (availableSeats <= 0) {
            throw new UnavailableSeatException(coursesId);
        }			


        RegistrationEntity regEntity = new RegistrationEntity();
        regEntity.setStudent(studentOpt.get());
        regEntity.setCourse(courseOpt.get());
        regEntity.setCreated(new Timestamp(System.currentTimeMillis()));

        RegistrationEntity resultEntity = registrationRepository.save(regEntity);
        
        CourseRegistration result = new CourseRegistration();
        result.setId(resultEntity.getId());
        result.setStudentsId(resultEntity.getStudent().getId());
        result.setCoursesId(resultEntity.getCourse().getId());
        result.setStudentName(regEntity.getStudent().getName());  
        result.setSubjectCode(resultEntity.getCourse().getSubjectCode());
        result.setSubjectName(resultEntity.getCourse().getSubjectName());
        
        return result;

    }

}
