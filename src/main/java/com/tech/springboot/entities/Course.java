package com.tech.springboot.entities;

import java.util.List;

import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.OneToMany;
import javax.persistence.Table;

import org.hibernate.annotations.Fetch;
import org.hibernate.annotations.FetchMode;

import com.fasterxml.jackson.annotation.JsonIgnore;

@Entity
@Table(name ="Course")
public class Course {

	@Id
	@GeneratedValue(strategy= GenerationType.IDENTITY)
	@Column(name="courseId")
	private Integer courseId;
	
	@ManyToOne(cascade = CascadeType.ALL)
    @JoinColumn(name="departmentName", nullable=false)
	private Department departement;
	
	@ManyToOne(cascade = CascadeType.ALL)
    @JoinColumn(name="InstructorId", nullable=false)
    private Instructor instructorIds;
	
	@Column(name="Duration")
	private Integer duration;
	
	@Column(name="Name")
	private String name;
	
	@OneToMany(mappedBy = "courseIds", orphanRemoval = true, cascade = CascadeType.ALL, fetch = FetchType.EAGER)
	@Fetch(value = FetchMode.SUBSELECT)
	@JsonIgnore
    private List<CourseStudent> courseItems;

	public Course() {
		super();
		// TODO Auto-generated constructor stub
	}

	public Course(Department departement, Instructor instructorIds, Integer duration, String name,
			List<CourseStudent> courseItems) {
		super();
		this.departement = departement;
		this.instructorIds = instructorIds;
		this.duration = duration;
		this.name = name;
		this.courseItems = courseItems;
	}

	/**
	 * @return the courseId
	 */
	public Integer getCourseId() {
		return courseId;
	}

	/**
	 * @param courseId the courseId to set
	 */
	public void setCourseId(Integer courseId) {
		this.courseId = courseId;
	}

	/**
	 * @return the departmentName
	 */
	public Department getDepartmentName() {
		return departement;
	}

	/**
	 * @param departmentName the departmentName to set
	 */
	public void setDepartmentName(Department departement) {
		this.departement = departement;
	}

	/**
	 * @return the instructorIds
	 */
	public Instructor getInstructorIds() {
		return instructorIds;
	}

	/**
	 * @param instructorIds the instructorIds to set
	 */
	public void setInstructorIds(Instructor instructorIds) {
		this.instructorIds = instructorIds;
	}

	/**
	 * @return the duration
	 */
	public Integer getDuration() {
		return duration;
	}

	/**
	 * @param duration the duration to set
	 */
	public void setDuration(Integer duration) {
		this.duration = duration;
	}

	/**
	 * @return the name
	 */
	public String getName() {
		return name;
	}

	/**
	 * @param name the name to set
	 */
	public void setName(String name) {
		this.name = name;
	}

	/**
	 * @return the courseItems
	 */
	public List<CourseStudent> getCourseItems() {
		return courseItems;
	}

	/**
	 * @param courseItems the courseItems to set
	 */
	public void setCourseItems(List<CourseStudent> courseItems) {
		this.courseItems = courseItems;
	}

	/* (non-Javadoc)
	 * @see java.lang.Object#toString()
	 */
	@Override
	public String toString() {
		return "Course [courseId=" + courseId + ", departement=" + departement + ", instructorIds="
				+ instructorIds + ", duration=" + duration + ", name=" + name + ", courseItems=" + courseItems + "]";
	}
    
    
}

