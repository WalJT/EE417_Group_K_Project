package users;

import java.io.Serializable;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;

import javax.servlet.http.Cookie;

import settings.DatabaseConfig;
/**
 * The User class is designed to handle processing of user/customer data
 * A `User` object can be created by passing all the relevant parameters as Strings
 * with the exception of the address, which is an array of strings.
 * An overloaded constructor is implemented, such that if only the email address
 * is passed, the rest of the information is sourced from the database.
 * 
 * generatePasswordHash() takes a password as input, and returns the hash as a string,
 * using a simple algorithm built into the String class... for demonstration purposes
 * only: THIS IS NOT A SECURE HASH FUNCTION.
 * 
 * The validateLogin method takes a password as input, implements the generatePasswordHash method,
 * And compares the result to the passwordHash state of the current instance. If they
 * match, it returns true, otherwise false.
 * 
 * the createNewUserInDatabase() method first checks to see if a user with the email address
 * of the current instance already exists, in which case it returns false. If not, prepared
 * statement (java.sql.PreparedStatemnt) is used to add new user details to the DB,
 * returning true unless an exception is thrown.
 * 
 * TODO updateUserInDatabase
 */
public class User implements Serializable{

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
	 * Constructor using all parameters intend for new user signup
	 */
	public User(String emailAddress, String firstname, String surname, String phone, String password, String[] address) {
		this.emailAddress = emailAddress;
		this.firstname = firstname;
		this.surname = surname;
		this.phone = phone;
		this.address = address;
		this.passwordHash = genreatePasswordHash(password);
	}
	/**
	 * Constructor using only email address.
	 * Other paramters are read from database
	 * this is for login of existing users
	 * @param emailAddress
	 */
	public User(String emailAddress) {
		Connection con = null;
		Statement stmt = null;
		ResultSet rs = null;
		try {
			this.emailAddress = emailAddress;
			Class.forName("com.mysql.jdbc.Driver");
			con = DriverManager.getConnection(DatabaseConfig.JDBCUrl, DatabaseConfig.username, DatabaseConfig.password);
			stmt = con.createStatement();
			this.address = new String[3];
			
			rs = stmt.executeQuery("SELECT * FROM GroupK_Accounts WHERE email='"+emailAddress+"'");
			while (rs.next()) {
				this.firstname = rs.getString("firstname");
				this.surname = rs.getString("lastname");
				this.passwordHash = rs.getString("psd");
				this.phone = rs.getString("phone");
				this.address[0] = rs.getString("adress");
				this.address[1] = rs.getString("city");
				this.address[2] = Integer.toString(rs.getInt("zipcode"));
			}
		} catch (Exception e) {
			e.printStackTrace();
		} finally {
			try {
				if (rs != null) rs.close();
				if (stmt != null) stmt.close();
				if (con != null) con.close();
			} catch (SQLException e) {
				e.printStackTrace();
			}
		}
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
	
	public boolean validateLogin(String password) {
		// Check hash of a given password to that of a user object
		String hashToTest = genreatePasswordHash(password);
		if (hashToTest.equals(this.passwordHash)) return true;
		return false;
	}
	
	@Override
	public String toString() {
		// Print user details... used for testing constructors
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
			while (rs.next()) {
				if (rs.getString("email").equals(this.emailAddress)) {
					// Return false if user already exists
					return false;				
				}
			}
			
			// If we reach this point, the user has a unique email address, and can be created
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
			
		} catch (Exception e) {
			// Return false if something goes wrong
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
	Cookie userIDCookie = null;
	Connection con = null;
	Statement stmt = null;
	ResultSet rs = null;
	try {
		con = DriverManager.getConnection(DatabaseConfig.JDBCUrl, DatabaseConfig.username, DatabaseConfig.password);
		stmt = con.createStatement();
		rs = stmt.executeQuery("SELECT id FROM GroupK_Accounts WHERE email='"+this.emailAddress+"'");
		while (rs.next()) {
			int theID = rs.getInt("id");
			userIDCookie = new Cookie("userID", Integer.toString(theID));
		}
	} catch (Exception e) {
		e.printStackTrace();
	} finally {
		if (rs != null) rs.close();
		if (stmt != null) stmt.close();
		if (con != null) con.close();
	}
	
	returnArray[1] = userIDCookie;
	return returnArray;
}

}
