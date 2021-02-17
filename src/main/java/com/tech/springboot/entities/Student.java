/**
 * 
 */
package com.tech.springboot.entities;

import java.util.List;

import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.OneToMany;
import javax.persistence.Table;

import org.hibernate.annotations.Fetch;
import org.hibernate.annotations.FetchMode;

import com.fasterxml.jackson.annotation.JsonIgnore;

@Entity
@Table(name ="Student")
public class Student {
	
	@Id
	@GeneratedValue(strategy= GenerationType.IDENTITY)
	@Column(name="studentId")
	private Integer studentId;
	
	@Column(name="FirstName")
	private String firstName;
	
	@Column(name="LastName")
	private String lastName;
	
	@Column(name="Phone")
	private String phoneNumber;
	
	
    @OneToMany(mappedBy = "studentIds", orphanRemoval = true, cascade = CascadeType.ALL, fetch = FetchType.EAGER)
	@Fetch(value = FetchMode.SUBSELECT)
    @JsonIgnore
    private List<CourseStudent>  studentItems;

	public Student() {
		super();
		// TODO Auto-generated constructor stub
	}

	public Student(String firstName, String lastName, String phoneNumber,
			List<CourseStudent> studentItems) {
		super();
		this.firstName = firstName;
		this.lastName = lastName;
		this.phoneNumber = phoneNumber;
		this.studentItems = studentItems;
	}

	/**
	 * @return the studentId
	 */
	public Integer getStudentId() {
		return studentId;
	}

	/**
	 * @param studentId the studentId to set
	 */
	public void setStudentId(Integer studentId) {
		this.studentId = studentId;
	}

	/**
	 * @return the firstName
	 */
	public String getFirstName() {
		return firstName;
	}

	/**
	 * @param firstName the firstName to set
	 */
	public void setFirstName(String firstName) {
		this.firstName = firstName;
	}

	/**
	 * @return the lastName
	 */
	public String getLastName() {
		return lastName;
	}

	/**
	 * @param lastName the lastName to set
	 */
	public void setLastName(String lastName) {
		this.lastName = lastName;
	}

	/**
	 * @return the phoneNumber
	 */
	public String getPhoneNumber() {
		return phoneNumber;
	}

	/**
	 * @param phoneNumber the phoneNumber to set
	 */
	public void setPhoneNumber(String phoneNumber) {
		this.phoneNumber = phoneNumber;
	}

	/**
	 * @return the studentItems
	 */
	public List<CourseStudent> getStudentItems() {
		return studentItems;
	}

	/**
	 * @param studentItems the studentItems to set
	 */
	public void setStudentItems(List<CourseStudent> studentItems) {
		this.studentItems = studentItems;
	}

	/* (non-Javadoc)
	 * @see java.lang.Object#toString()
	 */
	@Override
	public String toString() {
		return "Student [studentId=" + studentId + ", firstName=" + firstName + ", lastName=" + lastName
				+ ", phoneNumber=" + phoneNumber + ", studentItems=" + studentItems + "]";
	}

    
	
}
