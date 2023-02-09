package com.tn.assignment.service;

import java.sql.Timestamp;
import java.util.ArrayList;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.tn.assignment.model.Course;
import com.tn.assignment.service.repo.CourseRepository;
import com.tn.assignment.service.repo.entity.CourseEntity;

@Service    
public class CourseService {

	@Autowired 
	private CourseRepository courseRepository;

    
    public List<Course> findAll() {   

        Iterable<CourseEntity> iter = courseRepository.findAll();
        
        List<Course> ls = new ArrayList<>();    
        iter.forEach(courseEntity -> {
            Course course = new Course();
            course.setId(courseEntity.getId());
            course.setSubjectCode(courseEntity.getSubjectCode());
            course.setSubjectName(courseEntity.getSubjectName());
            course.setCredit(courseEntity.getCredit());
            course.setSemester(courseEntity.getSemester());
            ls.add(course);
        });    
        return ls;    
    }


    public Course save(Course course) {
        CourseEntity courseEntity = new CourseEntity();
		courseEntity.setSubjectCode(course.getSubjectCode());
		courseEntity.setSubjectName(course.getSubjectName());
		courseEntity.setCredit(course.getCredit());
        courseEntity.setSemester(course.getSemester());                                         // 0: Expired, 1: Active (Default)
		courseEntity.setCreated(new Timestamp(System.currentTimeMillis()));

		CourseEntity resultCourseEntity = courseRepository.save(courseEntity);
        
        Course resultCourse = new Course();
        resultCourse.setId(resultCourseEntity.getId());
        resultCourse.setSubjectCode(resultCourseEntity.getSubjectCode());
        resultCourse.setSubjectName(resultCourseEntity.getSubjectName());
        resultCourse.setCredit(resultCourseEntity.getCredit());
        resultCourse.setSemester(resultCourseEntity.getSemester());

        return resultCourse;
    }

}
