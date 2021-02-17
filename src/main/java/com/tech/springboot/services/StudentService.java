package com.tech.springboot.services;

import java.util.List;

import org.springframework.stereotype.Service;

import com.tech.springboot.pojos.StudentEnrollmentPojo;

@Service
public interface StudentService {

	List<String> retrieveCoursesforStudent(Integer studentId);
	List<String> retrieveStudentsforInstructor(Integer instructorId);
	Integer retrievecourseDurationforStudent(Integer studentId);
	Integer addStudent(StudentEnrollmentPojo enroll);
}
