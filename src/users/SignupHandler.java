package users;

import java.io.IOException;
import java.io.PrintWriter;
import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;

import settings.DatabaseConfig;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.Cookie;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

/**
 * @author Julian Tarquin Walsh
 * Servlet implementation class SignupHandler
 * dGet method is used to take information from the signup form
 * and create a User object
 */

public class SignupHandler extends HttpServlet {
	private static final long serialVersionUID = 4968548569L;
       
    /**
     * @see HttpServlet#HttpServlet()
     */
    public SignupHandler() {
        super();
        // TODO Auto-generated constructor stub
    }

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
		PrintWriter out = response.getWriter();
		response.setContentType("text/html");

		// Define variables for the parameters we need to collect
		String firstname = null, lastname = null;
		String emailAddress = null;
		String password = null;
		String phoneNumber = null;
		String[] physicalAddress = new String[4];

		// Get parameter values from signup form request
		try {
			// First and Last name can be read in and set
			firstname = request.getParameter("firstName");
			lastname = request.getParameter("lastName");
			out.append("Creating user account for "+firstname+" "+lastname+"\n");
			
			// Read in email address and compare with confirmation field
			emailAddress = request.getParameter("EmailAddress");
			if (emailAddress.equals(request.getParameter("EmailAddress_conf"))) {
				//out.append("-> Email Address: "+emailAddress+"\n");
			} else {
				out.append("** Email Addresses do not match\n");
				emailAddress = null;
			}
			
			// Read in password and compare with confirmation field
			password = request.getParameter("NewPassword");
			if (password.equals(request.getParameter("NewPassword_conf"))) {
				//out.append("-> Password: [Redacted, Valid]\n");
			} else {
				out.append("** Passwords do not match\n");
				password = null;
			}
			
			// Phone number... store as string to allow a "+" character to be used.
			phoneNumber = request.getParameter("phone");
			//out.append("-> Phone Number: "+phoneNumber+" \n");
			
			// Store address lines in an array of Strings
			physicalAddress[0] = request.getParameter("address");
			physicalAddress[1] = request.getParameter("city");
			physicalAddress[2] = request.getParameter("city");
			physicalAddress[3] = request.getParameter("zipCode");
			
			
			
			//out.append("-> Shipping Address:\n");
			//out.append("\t-> "+ physicalAddress[0] +"\n");
			//out.append("\t-> "+ physicalAddress[1] +"\n");
			//out.append("\t-> "+ physicalAddress[2] +"\n");
			
			boolean userDetailsValid = true;
			// Make sure the address has the right number of lines
			if (physicalAddress.length < 3) {
				userDetailsValid = false;
			} else {
				// Check if any values are null, and set userDetailsValid accordingly
				Object[] arrayOfParams = {firstname, lastname, emailAddress, password, physicalAddress};
				for (Object param: arrayOfParams) {
					if (param == null) {
						userDetailsValid = false;
					}
				}
			}
			
			
			if (userDetailsValid) {
				User newUser = new User(emailAddress, firstname, lastname, phoneNumber, password, physicalAddress);
				out.append(newUser.toString());
				if (newUser.createNewUserInDatabase(DatabaseConfig.JDBCUrl, DatabaseConfig.username, DatabaseConfig.password)) {
					// create this user's cookies, if the sign up has been successful
					Cookie[] userCookies = newUser.createCookies();
					
					//out.append(userCookies[0].getValue());
					//out.append(userCookies[1].getValue());
					response.addCookie(userCookies[0]);
					response.addCookie(userCookies[1]);
					
					// redirect new user to storefront
					response.sendRedirect("home.html");
				} else {
					// print a message if 
					String signupErrorMsg = "<p>Signup has not been successful. Reasons may include:";
					signupErrorMsg += "<ul><li>The email address has already been used by somebody else</li>";
					signupErrorMsg += "<li>An internal server error</li></ul>";
					signupErrorMsg += "Please return to the <a href='signup.html'>signup page</a> and contact an administrator if you continue to experience this issue.</p>";
					
					out.append(signupErrorMsg);
				}
				
			}
			
		}catch (SQLException e) {
			System.err.println("Database connection error, return to <a href='signup.html'>the signup page</a>");
			e.printStackTrace();
		}catch (Exception e) {
			System.err.println("Internal error, return to <a href='signup.html'>the signup page</a>");
			e.printStackTrace();
		} finally {
			
			out.close();
		}
		

		
	}

	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// Run doGet...
		doGet(request, response);
	}

}
