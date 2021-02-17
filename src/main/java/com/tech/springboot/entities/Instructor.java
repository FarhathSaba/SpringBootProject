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
@Table(name ="Instructor")
public class Instructor {

	@Id
	@GeneratedValue(strategy= GenerationType.IDENTITY)
	@Column(name="InstructorId")
	private Integer instructorId;
	
	@ManyToOne(cascade = CascadeType.ALL)
    @JoinColumn(name="departmentName", nullable=false)
    private Department departement;
	
	@Column(name="headedBy")
	private String headedBy;
	
	@Column(name="FirstName")
	private String firstName;
	
	@Column(name="LastName")
	private String lastName;
	
	@Column(name="Phone")
	private String phoneNumber;
	
    @OneToMany(mappedBy = "instructorIds", orphanRemoval = true, cascade = CascadeType.ALL, fetch = FetchType.EAGER)
	@Fetch(value = FetchMode.SUBSELECT)
    @JsonIgnore
    private List<Course> instructorIds;

	public Instructor() {
		super();
		// TODO Auto-generated constructor stub
	}

	public Instructor( Department departement, String headedBy, String firstName, String lastName,
			String phoneNumber, List<Course> instructorIds) {
		super();
		this.departement = departement;
		this.headedBy = headedBy;
		this.firstName = firstName;
		this.lastName = lastName;
		this.phoneNumber = phoneNumber;
		this.instructorIds = instructorIds;
	}

	/**
	 * @return the instructorId
	 */
	public Integer getInstructorId() {
		return instructorId;
	}

	/**
	 * @param instructorId the instructorId to set
	 */
	public void setInstructorId(Integer instructorId) {
		this.instructorId = instructorId;
	}

	/**
	 * @return the departement
	 */
	public Department getDepartement() {
		return departement;
	}

	/**
	 * @param departement the departement to set
	 */
	public void setDepartement(Department departement) {
		this.departement = departement;
	}

	/**
	 * @return the headedBy
	 */
	public String getHeadedBy() {
		return headedBy;
	}

	/**
	 * @param headedBy the headedBy to set
	 */
	public void setHeadedBy(String headedBy) {
		this.headedBy = headedBy;
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
	 * @return the instructorIds
	 */
	public List<Course> getInstructorIds() {
		return instructorIds;
	}

	/**
	 * @param instructorIds the instructorIds to set
	 */
	public void setInstructorIds(List<Course> instructorIds) {
		this.instructorIds = instructorIds;
	}

	/* (non-Javadoc)
	 * @see java.lang.Object#toString()
	 */
	@Override
	public String toString() {
		return "Instructor [instructorId=" + instructorId + ", departement=" + departement + ", headedBy=" + headedBy
				+ ", firstName=" + firstName + ", lastName=" + lastName + ", phoneNumber=" + phoneNumber
				+ ", instructorIds=" + instructorIds + "]";
	}
    
    
}
