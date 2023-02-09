package com.tn.assignment.service;

import java.sql.Timestamp;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.tn.assignment.model.Student;
import com.tn.assignment.service.repo.StudentRepository;
import com.tn.assignment.service.repo.entity.StudentEntity;

@Service    
public class StudentService {

	@Autowired 
	private StudentRepository studentRepository;

    
    public List<Student> findAll() {  

        Iterable<StudentEntity> iter = studentRepository.findAll();
        
        List<Student> ls = new ArrayList<>();    
        iter.forEach(studentEntity -> {
            Student student = new Student();
            student.setId(studentEntity.getId());
            student.setName(studentEntity.getName());
            student.setEmail(studentEntity.getEmail());
            student.setIsActive(1 == studentEntity.getState());
            ls.add(student);
        });    
        return ls;    
    }


    public Student save(Student student) {
        StudentEntity studentEntity = new StudentEntity();
		studentEntity.setName(student.getName());
		studentEntity.setEmail(student.getEmail());
		studentEntity.setState(1);                                         // 0: Expired, 1: Active (Default)
		studentEntity.setCreated(new Timestamp(System.currentTimeMillis()));

		StudentEntity resultStudentEntity = studentRepository.save(studentEntity);
        
        Student resultStudent = new Student();
        resultStudent.setId(resultStudentEntity.getId());
        resultStudent.setName(resultStudentEntity.getName());
        resultStudent.setEmail(resultStudentEntity.getEmail());
        resultStudent.setIsActive(1 == resultStudentEntity.getState());

        return resultStudent;
    }

    
    public int countStudents(boolean isActive) {   
        Integer count = studentRepository.countStudents(isActive? 1: 0);
        return Optional.ofNullable(count).orElse(0);    
    }

}
