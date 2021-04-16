/**
 * 
 */
package users;

import java.io.Serializable;
import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.Statement;

public class User implements Serializable{
	/**
	 * TODO: Documentation
	 */
	private static final long serialVersionUID = 2063583864503068064L;

	protected String emailAddress;
	protected String firstname;
	protected String surname;
	protected String phone;
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
	public User(String emailAddress, String firstname, String surname, String phone, String password, String[] address) {
		this.emailAddress = emailAddress;
		this.firstname = firstname;
		this.surname = surname;
		this.phone = phone;
		this.address = address;
		this.passwordHash = genreatePasswordHash(password);
	}
	
	protected String genreatePasswordHash(String password) {
		String hashString = null;
		//MessageDigest hashingAlgorithm = MessageDigest.getInstance("SHA-256");
		//byte[] hashBytes = hashingAlgorithm.digest(password.getBytes());
		// use hashCode method because it is easier to represent the result as a string
		
		// This is insecure, easy to crack, and should not be used in a real-life situation
		final String salt = "thisisarandomstring";
		final String hashThis = salt + password;
		final Integer hashInt = hashThis.hashCode();
		hashString = hashInt.toString();	
		
		if (hashString != null) return hashString;
		return "HASHING FAILED";
	}
	
	@Override
	public String toString() {
		String userDetails;
		userDetails = "Details of user (customer or admin) " + this.firstname + " " + this.surname + ":\n";
		userDetails += ("\t-> Email Address: " + emailAddress +"\n");
		userDetails += "\t-> Shipping Address: " + address[0] +"\n";
		userDetails += "\t-> Password Hash: " + passwordHash;
		return userDetails;
	}
	
	public void createNewUserInDatabase(String JDBCurl, String JDBCusername, String JDBCpassword) {
		try {
			
			// Open a connection to the database
			Connection con = null;
			Statement stmt = null;
			ResultSet rs = null;
			try {
				Class.forName("com.mysql.jdbc.Driver");
				con = DriverManager.getConnection(JDBCurl, JDBCusername, JDBCpassword);
			} catch (Exception e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
	        
			// check if the current user already exists
			
			
			// use a preparted statement to push user details into the database
			PreparedStatement addNewUser = con.prepareStatement(
				"INSERT INTO GroupK_Accounts (firstname,lastname,email,psd,phone,adress,city,zipcode) VALUES(?, ?, ?, ?, ?, ?, ?, ?);"
			);
			addNewUser.clearParameters();
			addNewUser.setString(1, this.firstname);
			addNewUser.setString(2, this.surname);
			addNewUser.setString(3, this.emailAddress);
			addNewUser.setString(4, this.passwordHash);
			addNewUser.setString(5, this.phone);
			addNewUser.setString(6, this.address[0]);
			addNewUser.setString(7, this.address[1]);
			addNewUser.setString(8, this.address[2]);
			addNewUser.execute();
			
		} catch (Exception e) {
			e.printStackTrace();
		} finally {
			
		}
	}
	
public void updateUserInDatabase(String JDBCurl, String JDBCusername, String JDBCpassword) {
		//TODO
	}

}
