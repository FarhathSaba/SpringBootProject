package com.tech.springboot.dao;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.tech.springboot.entities.Student;

@Repository
public interface Studentdao extends JpaRepository<Student,Integer>{

}
