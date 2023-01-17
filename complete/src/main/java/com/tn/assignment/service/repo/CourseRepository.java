package com.tn.assignment.service.repo;

import org.springframework.data.repository.CrudRepository;

import com.tn.assignment.service.repo.entity.CourseEntity;


public interface CourseRepository extends CrudRepository<CourseEntity, Integer> {

}
