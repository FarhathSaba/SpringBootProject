package com.tech.springboot.pojos;

import java.util.List;

import com.tech.springboot.entities.Course;
import com.tech.springboot.entities.Department;
import com.tech.springboot.entities.Instructor;
import com.tech.springboot.entities.Student;

public class StudentEnrollmentPojo {

	private Student studentdetails;
	private List<Course> coursesInterestedin;
	private Department enrolldepartment;
	private Instructor enrollMentor;
	
	public StudentEnrollmentPojo() {
		super();
		// TODO Auto-generated constructor stub
	}



	public StudentEnrollmentPojo(Student studentdetails, List<Course> coursesInterestedin, Department enrolldepartment,
			Instructor enrollMentor) {
		super();
		this.studentdetails = studentdetails;
		this.coursesInterestedin = coursesInterestedin;
		this.enrolldepartment = enrolldepartment;
		this.enrollMentor = enrollMentor;
	}


	/**
	 * @return the studentdetails
	 */
	public Student getStudentdetails() {
		return studentdetails;
	}

	/**
	 * @param studentdetails the studentdetails to set
	 */
	public void setStudentdetails(Student studentdetails) {
		this.studentdetails = studentdetails;
	}

	/**
	 * @return the coursesInterestedin
	 */
	public List<Course> getCoursesInterestedin() {
		return coursesInterestedin;
	}

	/**
	 * @param coursesInterestedin the coursesInterestedin to set
	 */
	public void setCoursesInterestedin(List<Course> coursesInterestedin) {
		this.coursesInterestedin = coursesInterestedin;
	}
	
	

	/**
	 * @return the enrolldepartment
	 */
	public Department getEnrolldepartment() {
		return enrolldepartment;
	}



	/**
	 * @param enrolldepartment the enrolldepartment to set
	 */
	public void setEnrolldepartment(Department enrolldepartment) {
		this.enrolldepartment = enrolldepartment;
	}



	/**
	 * @return the enrollMentor
	 */
	public Instructor getEnrollMentor() {
		return enrollMentor;
	}



	/**
	 * @param enrollMentor the enrollMentor to set
	 */
	public void setEnrollMentor(Instructor enrollMentor) {
		this.enrollMentor = enrollMentor;
	}



	/* (non-Javadoc)
	 * @see java.lang.Object#toString()
	 */
	@Override
	public String toString() {
		return "StudentEnrollmentPojo [studentdetails=" + studentdetails + ", coursesInterestedin="
				+ coursesInterestedin + ", enrolldepartment=" + enrolldepartment + ", enrollMentor=" + enrollMentor
				+ "]";
	}

	
}
