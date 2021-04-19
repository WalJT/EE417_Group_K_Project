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
import java.sql.SQLException;
import java.sql.Statement;

import javax.servlet.http.Cookie;

import settings.DatabaseConfig;

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
	
	public boolean createNewUserInDatabase(String JDBCurl, String JDBCusername, String JDBCpassword) throws SQLException {
		Connection con = null;
		Statement stmt = null;
		ResultSet rs = null;
		try {
			
			// Open a connection to the database
			Class.forName("com.mysql.jdbc.Driver");
			con = DriverManager.getConnection(JDBCurl, JDBCusername, JDBCpassword);
	        
			// check if the current user already exists		
			stmt = con.createStatement();
			rs = stmt.executeQuery("select email from GroupK_Accounts");
			boolean uniqueUser = true;
			while (rs.next()) {
				if (rs.getString("email") == this.emailAddress) {
					// TODO Hnadle this error better
					uniqueUser = false;					
				}
			}
			
			if (uniqueUser) {
				// use a prepared statement to push user details into the database
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
				return true;
			}
			return false;
			
		} catch (Exception e) {
			e.printStackTrace();
			return false;
		} finally {
			if (rs != null) rs.close();
			if (stmt != null) stmt.close();
			if (con != null) con.close();
		}
	}
	
public void updateUserInDatabase(String JDBCurl, String JDBCusername, String JDBCpassword) {
		//TODO
	}

public Cookie[] createCookies() throws SQLException {
	// creates cookies to store user information and returns them in an array
	Cookie[] returnArray = new Cookie[2];
	// userEmail cookie to store the email address
	Cookie userEmailCookie = new Cookie("userEmail", this.emailAddress);
	returnArray[0] = userEmailCookie;
	
	// userIDCookie to store the id number.. this has to be extracted from the database
	Connection con = null;
	Statement stmt = null;
	ResultSet rs = null;
	try {
		con = DriverManager.getConnection(DatabaseConfig.JDBCUrl, DatabaseConfig.username, DatabaseConfig.password);
		stmt = con.createStatement();
		rs = stmt.executeQuery("SELECT id FROM GroupK_Accounts WHERE email='"+this.emailAddress+"'");
		while (rs.next()) {
			
		}
	} catch (Exception e) {
		e.printStackTrace();
	} finally {
		if (rs != null) rs.close();
		if (stmt != null) stmt.close();
		if (con != null) con.close();
	}
	
	
	return returnArray;
}

}
