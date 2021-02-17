package com.tech.springboot.entities;

import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.Table;

@Entity
@Table(name ="Course_Student")
public class CourseStudent {

	@Id
	@GeneratedValue(strategy= GenerationType.IDENTITY)
	@Column(name="Id")
	private Integer id;
	
	@ManyToOne(cascade = CascadeType.ALL)
    @JoinColumn(name="studentId", nullable=false)
    private Student studentIds;
    
	@ManyToOne(cascade = CascadeType.ALL)
    @JoinColumn(name="courseId", nullable=false)
    private Course courseIds;

	public CourseStudent() {
		super();
		// TODO Auto-generated constructor stub
	}

	public CourseStudent(Student studentIds, Course courseIds) {
		super();
		this.studentIds = studentIds;
		this.courseIds = courseIds;
	}

	/**
	 * @return the studentIds
	 */
	public Student getStudentIds() {
		return studentIds;
	}

	/**
	 * @param studentIds the studentIds to set
	 */
	public void setStudentIds(Student studentIds) {
		this.studentIds = studentIds;
	}

	/**
	 * @return the courseIds
	 */
	public Course getCourseIds() {
		return courseIds;
	}

	/**
	 * @param courseIds the courseIds to set
	 */
	public void setCourseIds(Course courseIds) {
		this.courseIds = courseIds;
	}

	/* (non-Javadoc)
	 * @see java.lang.Object#toString()
	 */
	@Override
	public String toString() {
		return "CourseStudent [studentIds=" + studentIds + ", courseIds=" + courseIds + "]";
	}
    
    
}
