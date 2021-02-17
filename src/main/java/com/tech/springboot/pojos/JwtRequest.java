package com.tech.springboot.pojos;

import java.io.Serializable;

public class JwtRequest implements Serializable {

	private static final long serialVersionUID = 5926468583005150707L;
	
	private String username;
	private String passWord;
	
	//need default constructor for JSON Parsing
	public JwtRequest()
	{
		
	}

	public JwtRequest(String username, String passWord) {
		super();
		this.username = username;
		this.passWord = passWord;
	}

	/**
	 * @return the username
	 */
	public String getUsername() {
		return username;
	}

	/**
	 * @param username the username to set
	 */
	public void setUsername(String username) {
		this.username = username;
	}

	/**
	 * @return the passWord
	 */
	public String getPassWord() {
		return passWord;
	}

	/**
	 * @param passWord the passWord to set
	 */
	public void setPassWord(String passWord) {
		this.passWord = passWord;
	}

	/* (non-Javadoc)
	 * @see java.lang.Object#toString()
	 */
	@Override
	public String toString() {
		return "JwtRequest [username=" + username + ", passWord=" + passWord + "]";
	}

	
	
}