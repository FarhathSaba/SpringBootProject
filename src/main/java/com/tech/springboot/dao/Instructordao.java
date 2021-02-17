package com.tech.springboot.dao;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.tech.springboot.entities.Instructor;

@Repository
public interface Instructordao extends JpaRepository<Instructor,Integer> {

}
