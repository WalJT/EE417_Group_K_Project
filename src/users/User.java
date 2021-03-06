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
 * @author Julian Tarquin Walsh
 * @author Antoine Laveran (getters and setters)
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
 *  updateUserInDatabase() takes a userID as a parameter. This id is used to update user details
 *  in the database, based on the states of the User object it is called from
 */
public class User implements Serializable{

	private static final long serialVersionUID = 2063583864503068064L;

	private String emailAddress;
	private String firstname;
	protected String surname;
	private String phone;
	protected String passwordHash;
	private String[] address;
	protected String addressString;
	
	public User() {}
	
	
	/**
	 * @param emailAddress
	 * @param firstname
	 * @param surname
	 * @param password
	 * @param address
	 * Constructor using all parameters intend for new user signup and updating details
	 */
	public User(String emailAddress, String firstname, String surname, String phone, String password, String[] address) {
		this.setEmailAddress(emailAddress);
		this.setFirstname(firstname);
		this.surname = surname;
		this.setPhone(phone);
		this.setAddress(address);
		this.passwordHash = genreatePasswordHash(password);
		this.addressString = stringifyAddress(address);
	}
	
	
	/**
	 * Constructor using only email address.
	 * Other paramters are read from database
	 * this is for login of existing users
	 * @param emailAddress
	 */
	public User(String emailAddress) {
		
		// Set up database connection
		Connection con = null;
		Statement stmt = null;
		ResultSet rs = null;
		try {
			// Set the email address from parameter
			this.setEmailAddress(emailAddress);
			Class.forName("com.mysql.cj.jdbc.Driver");
			con = DriverManager.getConnection(DatabaseConfig.JDBCUrl, DatabaseConfig.username, DatabaseConfig.password);
			stmt = con.createStatement();
			
			// Set up address array
			this.setAddress(new String[4]);
			
			// Pull user information from database into the relevant state variables
			rs = stmt.executeQuery("SELECT * FROM GroupK_Accounts WHERE email='"+emailAddress+"'");
			while (rs.next()) {
				this.setFirstname(rs.getString("firstname"));
				this.surname = rs.getString("lastname");
				this.passwordHash = rs.getString("psd");
				this.setPhone(rs.getString("phone"));
				this.getAddress()[0] = rs.getString("adress");
				this.getAddress()[1] = rs.getString("city");
				this.getAddress()[2] = rs.getString("country");
				this.getAddress()[3] = Integer.toString(rs.getInt("zipcode"));
				this.addressString = rs.getString("fullAddress");
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
	
	
	/**
	 * concatenates the address array into a single string
	 * @param address
	 * @return
	 */
	public String stringifyAddress(String[] address) {
		String addressString = "";
		
		// Set each line of the address, separated by a comma
		for (String line: address) {
			addressString += (line + ",");
		}
		
		return addressString;
	}
	
	
	/**
	 * Hash the password using a simple algorithm built
	 * in to the Java String class.
	 * @param password
	 * @return passwordHash or error message
	 */
	protected String genreatePasswordHash(String password) {
		String hashString = null;
		//MessageDigest hashingAlgorithm = MessageDigest.getInstance("SHA-256");
		//byte[] hashBytes = hashingAlgorithm.digest(password.getBytes());
		// use hashCode method because it is easier to represent the result as a string
		
		// Use the String built-in, as it can easily be converted to a string, which the database expects
		// This is insecure, easy to crack, and should not be used in a real-life situation
		final String salt = "thisisarandomstring";
		final String hashThis = salt + password;
		final Integer hashInt = hashThis.hashCode();
		hashString = hashInt.toString();	
		
		if (hashString != null) return hashString;
		return "HASHING FAILED"; // I don't think this should ever happen...
	}
	
	
	/**
	 * hashes the input string, returns true if it
	 * matches the passwordHash of the current instance
	 * @param password
	 * @return
	 */
	public boolean validateLogin(String password) {
		// Check hash of a given password to that of a user object
		String hashToTest = genreatePasswordHash(password);
		if (hashToTest.equals(this.passwordHash)) return true;
		return false;
	}
	
	public void updatePasswordHash(String password) {
		this.passwordHash = genreatePasswordHash(password);
	}
	
	/**
	 * Used for testing
	 */
	@Override
	public String toString() {
		// Print user details... used for testing constructors
		String userDetails;
		userDetails = "Details of user (customer or admin) " + this.getFirstname() + " " + this.surname + ":\n";
		userDetails += ("\t-> Email Address: " + getEmailAddress() +"\n");
		userDetails += "\t-> Shipping Address: " + addressString +"\n";
		userDetails += "\t-> Password Hash: " + passwordHash;
		return userDetails;
	}
	
	
	public String getSurname() {
		return surname;
	}


	public void setSurname(String surname) {
		this.surname = surname;
	}


	public String getAddressString() {
		return addressString;
	}


	public void setAddressString(String addressString) {
		this.addressString = addressString;
	}


	/**
	 * Creates a new row in the "GroupK_Accounts" database table, using the information
	 * from the current User object
	 * @param JDBCurl
	 * @param JDBCusername
	 * @param JDBCpassword
	 * @return
	 * @throws SQLException
	 */
	public boolean createNewUserInDatabase(String JDBCurl, String JDBCusername, String JDBCpassword) throws SQLException {
		Connection con = null;
		Statement stmt = null;
		ResultSet rs = null;
		try {
			
			// Open a connection to the database
			Class.forName("com.mysql.cj.jdbc.Driver");
			con = DriverManager.getConnection(JDBCurl, JDBCusername, JDBCpassword);
	        
			// check if the current user already exists		
			stmt = con.createStatement();
			rs = stmt.executeQuery("select email from GroupK_Accounts");
			while (rs.next()) {
				if (rs.getString("email").equals(this.getEmailAddress())) {
					// Return false if user already exists
					return false;				
				}
			}
			
			// If we reach this point, the user has a unique email address, and can be created
			// use a prepared statement to push user details into the database
			PreparedStatement addNewUser = con.prepareStatement(
				"INSERT INTO GroupK_Accounts (firstname,lastname,email,psd,phone,adress,city,country,zipcode,fullAddress) VALUES(?, ?, ?, ?, ?, ?, ?, ?, ?, ?);"
			);
			addNewUser.clearParameters();
			addNewUser.setString(1, this.getFirstname());
			addNewUser.setString(2, this.surname);
			addNewUser.setString(3, this.getEmailAddress());
			addNewUser.setString(4, this.passwordHash);
			addNewUser.setString(5, this.getPhone());
			addNewUser.setString(6, this.getAddress()[0]);
			addNewUser.setString(7, this.getAddress()[1]);
			addNewUser.setString(8, this.getAddress()[2]);
			addNewUser.setString(9, this.getAddress()[3]);
			addNewUser.setString(10, this.addressString);
			addNewUser.execute();
			
			// If there was no exception, return true
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


/**
 * Updates the row corresponding to an existing user in the table.
 * The userID parameter can be read either from the database or a cookie
 * before passing to this function
 * @param userID
 * @return
 * @throws SQLException
 */
public boolean updateUserInDatabase(int userID) throws SQLException {
	
	Connection con = null;
	Statement stmt = null;
	ResultSet rs = null;
	try {
		Class.forName("com.mysql.cj.jdbc.Driver");
		con = DriverManager.getConnection(DatabaseConfig.JDBCUrl, DatabaseConfig.username, DatabaseConfig.password);
		stmt = con.createStatement();
		
		// Find existing user details based on the userID parameter.
		//rs = stmt.executeQuery("SELECT * FROM GroupK_Accounts WHERE id='"+userID+"'");
		//while (rs.next()) {
		//	System.out.println(rs.getString("email"));
		//}
		
		// Update table based on ID with current user instance details
		stmt.executeUpdate("UPDATE GroupK_Accounts SET"
				+ " email='"+this.getEmailAddress()+"',"
				+ " firstname='"+this.getFirstname()+"',"
				+ " lastname='"+this.surname+"',"
				+ " psd='"+this.passwordHash+"',"
				+ " phone='"+this.getPhone()+"',"
				+ " adress='"+this.getAddress()[0]+"',"
				+ " city='"+this.getAddress()[1]+"',"
				+ " country='"+this.getAddress()[2]+"',"
				+ " zipcode="+this.getAddress()[3]+","
				+ " fullAddress='"+this.addressString+"'"
				+ " WHERE id="+userID+";");
		
		// return true if there was no exception
		return true;
	} catch (Exception e) {
		e.printStackTrace();
		return false;
	} finally {
		if (rs != null) rs.close();
		if (stmt != null) stmt.close();
		if (con != null) con.close();
	}
}


/**
 * Creates a cookie containing the user ID, and another
 * that contains the email address
 * @return
 * @throws SQLException
 */
public Cookie[] createCookies() throws SQLException {
	// creates cookies to store user information and returns them in an array
	Cookie[] returnArray = new Cookie[2];
	// userEmail cookie to store the email address
	Cookie userEmailCookie = new Cookie("userEmail", this.getEmailAddress());
	returnArray[0] = userEmailCookie;
	
	// userIDCookie to store the id number.. this has to be extracted from the database
	Cookie userIDCookie = null;
	Connection con = null;
	Statement stmt = null;
	ResultSet rs = null;
	try {
		Class.forName("com.mysql.cj.jdbc.Driver");
		con = DriverManager.getConnection(DatabaseConfig.JDBCUrl, DatabaseConfig.username, DatabaseConfig.password);
		stmt = con.createStatement();
		rs = stmt.executeQuery("SELECT id FROM GroupK_Accounts WHERE email='"+this.getEmailAddress()+"'");
		while (rs.next()) {
			int theID = rs.getInt("id");
			userIDCookie = new Cookie("userID", Integer.toString(theID));
			System.out.println(userIDCookie.getValue());
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


public String[] getAddress() {
	return address;
}


public void setAddress(String[] address) {
	this.address = address;
}


public String getPhone() {
	return phone;
}


public void setPhone(String phone) {
	this.phone = phone;
}


public String getEmailAddress() {
	return emailAddress;
}


public void setEmailAddress(String emailAddress) {
	this.emailAddress = emailAddress;
}


public String getFirstname() {
	return firstname;
}


public void setFirstname(String firstname) {
	this.firstname = firstname;
}

}
