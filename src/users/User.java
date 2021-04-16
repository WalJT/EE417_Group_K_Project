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
		String hashString = null;
		try {
			MessageDigest sha256 = MessageDigest.getInstance("SHA-256");
			byte[] hashBytes = sha256.digest(password.getBytes());
			
			// The MessageDigest instance returns an arry of bytes, which me must convert to a string
			hashString = new String(hashBytes);	
		} catch (NoSuchAlgorithmException e) {
			System.err.println("The hash algorithm being used is not implemented");
			e.printStackTrace();
		} catch (Exception e) {
			e.printStackTrace();
		} finally {
			if (hashString != null) return hashString;
		}
	}
	
	@Override
	public String toString() {
		String userDetails;
		userDetails = "Details of user (customer or admin) " + this.firstname + " " + this.surname + ":\n";
		userDetails += ("\t-> Email Address: " + emailAddress +"\n");
		userDetails += "\t-> Shipping Address: " + address[0];
		return userDetails;
	}
	
	public void createNewUserInDatabase(String JDBCurl, String JDBCusername, String JDBCpassword) {
		try {
			
			// Open a connection to the database
			Connection con = null;
			Statement stmt = null;
			ResultSet rs = null;
			try {
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
			addNewUser.setString(1, this.firstname);
			addNewUser.setString(2, this.surname);
			addNewUser.setString(3, this.emailAddress);
			
		} catch (Exception e) {
			e.printStackTrace();
		} finally {
			
		}
	}
	
public void updateUserInDatabase(String JDBCurl, String JDBCusername, String JDBCpassword) {
		//TODO
	}

}
