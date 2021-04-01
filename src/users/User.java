/**
 * 
 */
package users;

import java.io.Serializable;

public class User implements Serializable{
	/**
	 * TODO: Documentation
	 */
	private static final long serialVersionUID = 2063583864503068064L;
	
	protected int userID; // static NextID?
	protected String emailAddress;
	protected String firstName;
	protected String surName;
	protected String passwordHash;
	protected String[] address;
	
	public User() {}
	
	public User(String emailAddress, String firstName, String surName, String password, String[] address) {
		this.userID = 0; //TODO: generate random userID
		this.emailAddress = emailAddress;
		this.firstName = firstName;
		this.surName = surName;
		this.address = address;
	}
	
	protected String genreatePasswordHash() {
		
		return null;
	}
	
}
