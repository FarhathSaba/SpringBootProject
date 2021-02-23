package com.tech.springboot.Assignment;


import static org.assertj.core.api.Assertions.assertThat;

import java.net.URI;
import java.net.URISyntaxException;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.List;

import org.json.JSONObject;
import org.junit.Assert;
import org.junit.jupiter.api.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.context.SpringBootTest.WebEnvironment;
import org.springframework.boot.test.web.client.TestRestTemplate;
import org.springframework.boot.web.server.LocalServerPort;
import org.springframework.http.HttpEntity;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpMethod;
import org.springframework.http.ResponseEntity;
import org.springframework.test.context.junit4.SpringRunner;

import com.tech.springboot.pojos.JwtRequest;
import com.tech.springboot.pojos.StudentResponse;


/*
 1. Aim of this JUNIT test is to check whether the inserted data matches the result
  of our REST API's
 2.Test case is for one inserted entry mapping. To specify multiple entries
	we can rather go with select query with the data given and retrieve information 
 3. Here we do check data with Actual Database query 
 4.Before running test please ensure to change the department name as Its unique for each
  entry or map the data with known department */

@RunWith(SpringRunner.class)
@SpringBootTest(webEnvironment=WebEnvironment.RANDOM_PORT)
class AssignmentApplicationTests {

	@Autowired
	private TestRestTemplate restTemplate;

	@LocalServerPort
	int randomServerPort=8080;

	static StudentResponse respone=  null;

	// JDBC driver name and database URL
	static final String JDBC_DRIVER = "org.h2.Driver";  
	static final String DB_URL = "jdbc:h2:tcp://localhost/~/test;DB_CLOSE_ON_EXIT=FALSE;AUTO_RECONNECT=TRUE;AUTO_SERVER=TRUE";  

	// Database credentials 
	static final String USER = "sa"; 
	static final String PASS = "password"; 
	static String token="";
	
	static {
		AssignmentApplicationTests appTest = new AssignmentApplicationTests();
		respone= appTest.dummyDataEntryFortest();
	}

	public int insertNewStudent() {
		ResultSet rs = null;
		int studentId = 0;

		String sql = "INSERT INTO Student(firstName,lastName,phone) "
				+ "VALUES(?,?,?)";


		try (Connection conn = AssignmentApplicationTests.getConnection();
				PreparedStatement pstmt = conn.prepareStatement(sql,Statement.RETURN_GENERATED_KEYS);) {

			// set parameters for statement
			pstmt.setString(1, "Saba");
			pstmt.setString(2, "Syed");
			pstmt.setString(3, "234567890");

			int rowAffected = pstmt.executeUpdate();
			if(rowAffected == 1)
			{
				rs = pstmt.getGeneratedKeys();
				if(rs.next())
					studentId = rs.getInt(1);
			}
		} catch (SQLException ex) {
			System.out.println(ex.getMessage());
		} finally {
			try {
				if(rs != null)  rs.close();
			} catch (SQLException e) {
				System.out.println(e.getMessage());
			}
		}
		return studentId;
	}

	public String insertNewDepartment() {
		ResultSet rs = null;
		String departmentName = "";

		String sql = "INSERT INTO Department(DepartmentName,Location) "
				+ "VALUES(?,?)";


		try (Connection conn = AssignmentApplicationTests.getConnection();
				PreparedStatement pstmt = conn.prepareStatement(sql);) {

			// set parameters for statement
			pstmt.setString(1, "Sample Department 0");
			pstmt.setString(2, "Hyderabad");

			int rowAffected = pstmt.executeUpdate();
			if(rowAffected == 1)
			{
				departmentName="Sample Department 0";
			}
		} catch (SQLException ex) {
			System.out.println(ex.getMessage());
		} finally {
			try {
				if(rs != null)  rs.close();
			} catch (SQLException e) {
				System.out.println(e.getMessage());
			}
		}
		return departmentName;
	}

	public int insertNewInstructor(String departmentName) {
		ResultSet rs = null;
		int instructorId = 0;

		String sql = "INSERT INTO Instructor(FIRSTNAME, HEADEDBY, LASTNAME, PHONE, DEPARTMENTNAME) "
				+ "VALUES(?,?,?,?,?)";


		try (Connection conn = AssignmentApplicationTests.getConnection();
				PreparedStatement pstmt = conn.prepareStatement(sql,Statement.RETURN_GENERATED_KEYS);) {

			// set parameters for statement
			pstmt.setString(1, "Dummy Instructor");
			pstmt.setString(2, "Dummy Head");
			pstmt.setString(3, "lastName");
			pstmt.setString(4, "678901245");
			pstmt.setString(5, departmentName);

			int rowAffected = pstmt.executeUpdate();
			if(rowAffected == 1)
			{
				rs = pstmt.getGeneratedKeys();
				if(rs.next())
					instructorId = rs.getInt(1);
			}
		} catch (SQLException ex) {
			System.out.println(ex.getMessage());
		} finally {
			try {
				if(rs != null)  rs.close();
			} catch (SQLException e) {
				System.out.println(e.getMessage());
			}
		}
		return instructorId;
	}

	public int[] insertNewCourse(String departmentName, int instructorId) {
		ResultSet rs = null;
		int[] array= new int[2];

		String sql = "INSERT INTO Course(DURATION, NAME, DEPARTMENTNAME, INSTRUCTORID ) "
				+ "VALUES(?,?,?,?)";


		try (Connection conn = AssignmentApplicationTests.getConnection();
				PreparedStatement pstmt = conn.prepareStatement(sql,Statement.RETURN_GENERATED_KEYS);) {

			// set parameters for statement
			pstmt.setInt(1, 100);
			pstmt.setString(2, "Dummy Course");
			pstmt.setString(3, departmentName);
			pstmt.setInt(4, instructorId);

			int rowAffected = pstmt.executeUpdate();
			if(rowAffected == 1)
			{
				rs = pstmt.getGeneratedKeys();
				if(rs.next())
					array[0] =  rs.getInt(1);
				array[1] = 100;
			}
		} catch (SQLException ex) {
			System.out.println(ex.getMessage());
		} finally {
			try {
				if(rs != null)  rs.close();
			} catch (SQLException e) {
				System.out.println(e.getMessage());
			}
		}
		return array;
	}

	public int insertNewCourseStudentMapping(int studentId, int courseId) {
		ResultSet rs = null;
		int coursestuId = 0;

		String sql = "INSERT INTO Course_Student(COURSEID, STUDENTID) "
				+ "VALUES(?,?)";


		try (Connection conn = AssignmentApplicationTests.getConnection();
				PreparedStatement pstmt = conn.prepareStatement(sql,Statement.RETURN_GENERATED_KEYS);) {

			// set parameters for statement
			pstmt.setInt(1, courseId);
			pstmt.setInt(2, studentId);

			int rowAffected = pstmt.executeUpdate();
			if(rowAffected == 1)
			{
				rs = pstmt.getGeneratedKeys();
				if(rs.next())
					coursestuId = rs.getInt(1);
			}
		} catch (SQLException ex) {
			System.out.println(ex.getMessage());
		} finally {
			try {
				if(rs != null)  rs.close();
			} catch (SQLException e) {
				System.out.println(e.getMessage());
			}
		}
		return coursestuId;
	}

	public static Connection getConnection() throws SQLException {
		Connection conn = null;



		try {

			// STEP 1: Register JDBC driver 
			Class.forName(JDBC_DRIVER); 

			// create a connection to the database
			conn = DriverManager.getConnection(DB_URL, USER, PASS);
		}catch(SQLException se) { 
			//Handle errors for JDBC 
			se.printStackTrace(); 
		} catch(Exception e) { 
			//Handle errors for Class.forName 
			e.printStackTrace(); 
		}
		return conn;
	}


	public StudentResponse dummyDataEntryFortest() 
	{
		StudentResponse response = null;
		try { 
			int studentId = insertNewStudent();
			String departmentName = insertNewDepartment();
			int instructorId= insertNewInstructor(departmentName);
			int coursedata[] = insertNewCourse(departmentName, instructorId);
			insertNewCourseStudentMapping(studentId, coursedata[0]);
			response = new StudentResponse(studentId, coursedata[0], departmentName, instructorId, coursedata[1]);

			//STEP 3: Execute a query 
			//			insertNewStudent(conn);
			System.out.println("Created table in given database..."); 

		} catch(Exception e) { 
			//Handle errors for Class.forName 
			e.printStackTrace(); 
		} 

		return response;

	}
	
	public String getTokenforAuthentication() throws URISyntaxException {
		
		final String baseUrl = "http://localhost:8080/api/getAccessToken";
		URI uri = new URI(baseUrl);
		
		HttpHeaders headers = new HttpHeaders();
		headers.set("Content-Type", "application/json");
		
		JwtRequest jwtReq= new JwtRequest();
		jwtReq.setUsername("Dummy");
		jwtReq.setPassWord("fwzv2OqS3JqaVs8sXpm1/Pt1FezoECY/b6/8tA4l7YyynQI0Tf1DGPT+SPre4xiViNM/SDHjJ/uk5m2fVd/h/XwOXOTchQNe3HqKQkhw+bm9heVOnjPQEIBQcvjhj3v4PtR+52NwHoacLtbzVc3nk4/Y26yIQtCi0rLGOiqyzk8=");

		HttpEntity<JwtRequest> request = new HttpEntity<JwtRequest>(jwtReq, headers);
		ResponseEntity<String> result = null;
		String value= "";
		try {
		 result = this.restTemplate.postForEntity(uri, request, String.class);
		 JSONObject jsonObject = new JSONObject(result.getBody());
		 value= (String) jsonObject.get("token");
		}
		catch(Exception e) {
			e.printStackTrace();
		}
		
		return value;

	}

	
	@Test
	public void testRetrievecourseDurationforStudent() throws URISyntaxException 
	{

		token = getTokenforAuthentication();
		final String baseUrl = "http://localhost:8080/private/courses/"+respone.getStudentId()+"/courseDuration";
		URI uri = new URI(baseUrl);

		HttpHeaders headers = new HttpHeaders();
		headers.set("Authorization", "Bearer "+token);      

		HttpEntity<String> request = new HttpEntity<>(String.valueOf(respone.getStudentId()), headers);

		ResponseEntity<String> result = this.restTemplate.exchange(uri, HttpMethod.GET, request, String.class);

		
		assertThat(result.getBody().equals(String.valueOf(respone.getCourseDuration())));	 
	}

	@Test
	public void testRetrieveCoursesForStudent() throws URISyntaxException 
	{

		token = getTokenforAuthentication();
		final String baseUrl = "http://localhost:8080/private/students/"+ respone.getStudentId()+"/courses";
		URI uri = new URI(baseUrl);

		HttpHeaders headers = new HttpHeaders();
		headers.set("Authorization", "Bearer "+token);      

		HttpEntity<String> request = new HttpEntity<>(String.valueOf(respone.getStudentId()), headers);

		ResponseEntity<List> result = this.restTemplate.exchange(uri, HttpMethod.GET, request, List.class);

		//Verify request succeed
		result.getBody().size();


		if(result.getBody().get(0).equals("StudentId requested not available"))
			result.getBody().remove(0);

		// then
		assertThat(result.getBody().size()).isEqualTo(1);	     
	}

	@Test
	public void testRetrieveStudentsforInstructor() throws URISyntaxException 
	{

		token = getTokenforAuthentication();
		final String baseUrl = "http://localhost:8080/private/instructors/"+respone.getInstructorId()+"/students";
		URI uri = new URI(baseUrl);

		HttpHeaders headers = new HttpHeaders();
		headers.set("Authorization", "Bearer "+token);      

		HttpEntity<String> request = new HttpEntity<>(String.valueOf(respone.getInstructorId()), headers);

		ResponseEntity<List> result = this.restTemplate.exchange(uri, HttpMethod.GET, request, List.class);
     
		if(result.getBody().get(0).equals("Instructor Id requested is not available"))
				result.getBody().remove(0);

		// then
		assertThat(result.getBody().size()).isEqualTo(1);	
	}

}
