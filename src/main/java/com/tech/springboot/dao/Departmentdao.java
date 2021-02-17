package com.tech.springboot.dao;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.tech.springboot.entities.Department;

@Repository
public interface Departmentdao extends JpaRepository<Department,String>{

}
