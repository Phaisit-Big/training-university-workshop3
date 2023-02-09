package com.tn.assignment.service.repo;

import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;

import com.tn.assignment.service.repo.entity.StudentEntity;

public interface StudentRepository extends CrudRepository<StudentEntity, Integer> {

    @Query("SELECT COUNT(u.id) FROM STUDENTS u WHERE u.state=?1")
    public Integer countStudents(Integer state);    

    @Query("SELECT u FROM STUDENTS u WHERE u.email=?1 ORDER BY u.id LIMIT 1")
    public StudentEntity findFirstByEmail(String email);
}
