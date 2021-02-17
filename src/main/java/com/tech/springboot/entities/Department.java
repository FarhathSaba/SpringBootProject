package com.tech.springboot.entities;

import java.util.List;

import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.Id;
import javax.persistence.OneToMany;
import javax.persistence.Table;

import org.hibernate.annotations.Fetch;
import org.hibernate.annotations.FetchMode;

import com.fasterxml.jackson.annotation.JsonIgnore;

@Entity
@Table(name ="Department")
public class Department {

	@Id
	@Column(name="departmentName")
	private String Name;
	
	@Column(name="Location")
	private String location;
	
    @OneToMany(mappedBy = "departement", orphanRemoval = true, cascade = CascadeType.ALL, fetch = FetchType.EAGER)
	@Fetch(value = FetchMode.SUBSELECT)
    @JsonIgnore
    private List<Instructor> instructors;
	
    @OneToMany(mappedBy = "departement", orphanRemoval = true, cascade = CascadeType.ALL, fetch = FetchType.EAGER)
	@Fetch(value = FetchMode.SUBSELECT)
    @JsonIgnore
    private List<Course> courses;

	public Department() {
		super();
		// TODO Auto-generated constructor stub
	}

	public Department(String name, String location, List<Instructor> instructors, List<Course> courses) {
		super();
		Name = name;
		this.location = location;
		this.instructors = instructors;
		this.courses = courses;
	}

	/**
	 * @return the name
	 */
	public String getName() {
		return Name;
	}

	/**
	 * @param name the name to set
	 */
	public void setName(String name) {
		Name = name;
	}

	/**
	 * @return the location
	 */
	public String getLocation() {
		return location;
	}

	/**
	 * @param location the location to set
	 */
	public void setLocation(String location) {
		this.location = location;
	}

	/**
	 * @return the instructors
	 */
	public List<Instructor> getInstructors() {
		return instructors;
	}

	/**
	 * @param instructors the instructors to set
	 */
	public void setInstructors(List<Instructor> instructors) {
		this.instructors = instructors;
	}

	/**
	 * @return the courses
	 */
	public List<Course> getCourses() {
		return courses;
	}

	/**
	 * @param courses the courses to set
	 */
	public void setCourses(List<Course> courses) {
		this.courses = courses;
	}

	/* (non-Javadoc)
	 * @see java.lang.Object#toString()
	 */
	@Override
	public String toString() {
		return "Department [Name=" + Name + ", location=" + location + ", instructors=" + instructors + ", courses="
				+ courses + "]";
	}
    
    
}
