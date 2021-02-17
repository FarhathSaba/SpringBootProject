package com.tech.springboot.services;

import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.tech.springboot.dao.Coursedao;
import com.tech.springboot.dao.Departmentdao;
import com.tech.springboot.dao.Instructordao;
import com.tech.springboot.dao.Studentdao;
import com.tech.springboot.entities.Course;
import com.tech.springboot.entities.CourseStudent;
import com.tech.springboot.entities.Instructor;
import com.tech.springboot.entities.Student;
import com.tech.springboot.pojos.StudentEnrollmentPojo;

@Service
public class StudentServiceImpl implements StudentService{

	private static final Logger LOGGER = LoggerFactory.getLogger(StudentServiceImpl.class);

	@Autowired
	private Studentdao studentRepo;

	@Autowired
	private Instructordao instructorRepo;
	
	@Autowired
	private Coursedao courseRepo;
//	
//	@Autowired 
//	private CourseStudentdao course_studentRepo;
	
	@Autowired
	private Departmentdao departmentRepo;


	@Override
	public List<String> retrieveCoursesforStudent(Integer studentId) {
		// TODO Auto-generated method stub
		List<String> courses= new ArrayList<String>();
		try {
			Student student = studentRepo.findById(studentId).get();
			List<CourseStudent> list= student.getStudentItems();
			List<Course> cids= list.stream().map(x-> x.getCourseIds()).collect(Collectors.toList());

			//If interested in only course Names
			courses= cids.stream().map(x-> x.getName()).collect(Collectors.toList());
		}
		catch(Exception ex) {
			LOGGER.error("Error while retrieveStudentsforInstructor : {} , {}", ex.getMessage(), ex);
			courses.add("StudentId requested not available");
		}
		finally {
			LOGGER.info("exiting from retrieveStudentsforInstructor");
		}
		return courses;
	}

	@Override
	public List<String> retrieveStudentsforInstructor(Integer instructorId) {
		// TODO Auto-generated method stub
		List<String> studentsforInstructor = new ArrayList<String>();
		try {
			Instructor instruct = instructorRepo.findById(instructorId).get();
			List<Course> courses= instruct.getInstructorIds();
			for(Course c:courses) {
				List<CourseStudent> studentInfo= c.getCourseItems();
				for(CourseStudent student: studentInfo) {
					studentsforInstructor.add(student.getStudentIds().getFirstName());
				}
			}
		}
		catch(Exception ex) {
			LOGGER.error("Error while retrieveStudentsforInstructor : {} , {}", ex.getMessage(), ex);
			studentsforInstructor.add("Instructor Id requested is not available");
		}
		finally {
			LOGGER.info("exiting from retrieveStudentsforInstructor");
		}
		return studentsforInstructor;
	}

	@Override
	public Integer retrievecourseDurationforStudent(Integer studentId) {
		// TODO Auto-generated method stub
		Integer duration= null;
		try {
			Student student = studentRepo.findById(studentId).get();
			List<CourseStudent> list= student.getStudentItems();
			List<Course> cids= list.stream().map(x-> x.getCourseIds()).collect(Collectors.toList());
			duration = (int)cids.stream().map(x->x.getDuration()).collect(Collectors.summarizingInt(Integer::intValue)).getSum();
		}
		catch(Exception ex) {
			LOGGER.error("Error while retrievecourseDurationforStudent : {} , {}", ex.getMessage(), ex);
			duration=-1;
		}
		finally {
			LOGGER.info("exiting from retrievecourseDurationforStudent");
		}
		return duration;
	}

	@Override
	public Integer addStudent(StudentEnrollmentPojo enroll) {
		// TODO Auto-generated method stub
		//studentRepo.save(s);
		Student newStudent= enroll.getStudentdetails();
		List<Course> coursesEnrolled = enroll.getCoursesInterestedin();
		enroll.getEnrollMentor().setDepartement(departmentRepo.findById(enroll.getEnrolldepartment().getName()).get());
		
		List<CourseStudent> mapping = new ArrayList<CourseStudent>();
		for(Course c: coursesEnrolled) {
			Course actualEntity= courseRepo.findById(c.getCourseId()).get();
			actualEntity.setDepartmentName(departmentRepo.findById(enroll.getEnrolldepartment().getName()).get());
			actualEntity.setInstructorIds(instructorRepo.findById(enroll.getEnrollMentor().getInstructorId()).get());
			mapping.add(new CourseStudent(newStudent,actualEntity));
		}
		
		newStudent.setStudentItems(mapping);
		studentRepo.save(newStudent);
		return null;
	}

}
