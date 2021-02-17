package com.tech.springboot.dao;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.tech.springboot.entities.Course;

@Repository
public interface Coursedao extends JpaRepository<Course,Integer> {

}
