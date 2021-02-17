package com.tech.springboot.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.tech.springboot.pojos.StudentEnrollmentPojo;
import com.tech.springboot.services.StudentServiceImpl;

@RestController
@CrossOrigin
@RequestMapping({ "/private" })
public class StudentController {

	@Autowired
	private StudentServiceImpl service;
	
	@GetMapping("/students/{studentId}/courses")
	public List<String> retrieveCoursesForStudent(@PathVariable String studentId) {
		
		return service.retrieveCoursesforStudent(Integer.parseInt(studentId));
	}
	
	@GetMapping("/instructors/{instructorId}/students")
	public List<String> retrieveStudentsforInstructor(@PathVariable String instructorId) {
		
		return service.retrieveStudentsforInstructor(Integer.parseInt(instructorId));
	}
	
	@GetMapping("/courses/{studentId}/courseDuration")
	public Integer retrievecourseDurationforStudent(@PathVariable String studentId) {
		return service.retrievecourseDurationforStudent(Integer.parseInt(studentId));
	}
	
	// sent mapping list courseStudent with existing courseId to enroll student to a course
	@PostMapping("/addNewStudent")
	public Integer addNewStudentforaCourse(@RequestBody StudentEnrollmentPojo enrollForm) {
		service.addStudent(enrollForm);
		return 1;
	}
}
