package com.tn.assignment.service.repo;

import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;

import com.tn.assignment.service.repo.entity.StudentEntity;

public interface StudentRepository extends CrudRepository<StudentEntity, Integer> {

    @Query("SELECT COUNT(u.id) FROM STUDENTS u WHERE u.state=?1")
    public Integer countStudents(Integer state);    

    
}
