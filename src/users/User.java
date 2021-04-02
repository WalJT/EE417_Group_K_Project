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

	protected String emailAddress;
	protected String firstname;
	protected String surname;
	protected String passwordHash;
	protected String[] address;
	
	public User() {}
	
	/**
	 * @param emailAddress
	 * @param firstname
	 * @param surname
	 * @param password
	 * @param address
	 */
	public User(String emailAddress, String firstname, String surname, String password, String[] address) {
		this.emailAddress = emailAddress;
		this.firstname = firstname;
		this.surname = surname;
		this.address = address;
		this.passwordHash = genreatePasswordHash(password);
	}
	
	protected String genreatePasswordHash(String password) {
		
		return null;
	}
	
	@Override
	public String toString() {
		String userDetails;
		userDetails = "Details of user (customer or admin) " + this.firstname + " " + this.surname + "\n";
		userDetails += "	-> Email Address: " + emailAddress;
		return userDetails;
	}


}
