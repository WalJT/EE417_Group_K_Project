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
	protected String firstname;
	protected String surname;
	protected String passwordHash;
	protected String[] address;
	
	public User() {}
	
	public User(String emailAddress, String firstname, String surname, String password, String[] address) {
		this.userID = 0; //TODO: generate random userID
		this.emailAddress = emailAddress;
		this.firstname = firstname;
		this.surname = surname;
		this.address = address;
	}
	
	protected String genreatePasswordHash() {
		
		return null;
	}
	
}
