package com.tech.springboot.dao;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.tech.springboot.entities.CourseStudent;

@Repository
public interface CourseStudentdao extends JpaRepository<CourseStudent,Integer>{

}
