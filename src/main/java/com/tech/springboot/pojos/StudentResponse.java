package com.tech.springboot.pojos;

public class StudentResponse {

	private Integer studentId;
	private Integer courseId;
	private String departmentName;
	private Integer instructorId;
	private Integer courseDuration;
	
	
	public StudentResponse() {
		super();
		// TODO Auto-generated constructor stub
	}

	public StudentResponse(Integer studentId, Integer courseId, String departmentName, Integer instructorId,
			Integer courseDuration) {
		super();
		this.studentId = studentId;
		this.courseId = courseId;
		this.departmentName = departmentName;
		this.instructorId = instructorId;
		this.courseDuration = courseDuration;
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
	public String getDepartmentName() {
		return departmentName;
	}

	/**
	 * @param departmentName the departmentName to set
	 */
	public void setDepartmentName(String departmentName) {
		this.departmentName = departmentName;
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
	 * @return the courseDuration
	 */
	public Integer getCourseDuration() {
		return courseDuration;
	}

	/**
	 * @param courseDuration the courseDuration to set
	 */
	public void setCourseDuration(Integer courseDuration) {
		this.courseDuration = courseDuration;
	}
	
	
	
	
}
