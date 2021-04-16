package users;

import java.io.IOException;
import java.io.PrintWriter;
import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.Statement;

import settings.DatabaseConfig;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

/**
 * Servlet implementation class SignupHandler
 * dGet method is used to take information from the signup form
 * and create a User object TODO: Create User class
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
		String[] physicalAddress = new String[3]; // TODO: Discuss address input and possibly adjust implementation

		// Get parameter values from signup form request
		boolean userDetailsValid = true;
		try {
			// First and Last name can be read in and set
			firstname = request.getParameter("firstName");
			lastname = request.getParameter("lastName");
			out.append("Creating user account for "+firstname+" "+lastname+"\n");
			
			// Read in email address and compare with confirmation field
			emailAddress = request.getParameter("EmailAddress");
			if (emailAddress.equals(request.getParameter("EmailAddress_conf"))) {
				out.append("-> Email Address: "+emailAddress+"\n");
			} else {
				out.append("** Email Addresses do not match\n");
				userDetailsValid = false;
			}
			
			// Read in password and compare with confirmation field
			password = request.getParameter("NewPassword");
			if (password.equals(request.getParameter("NewPassword_conf"))) {
				out.append("-> Password: [Redacted, Valid]\n");
			} else {
				out.append("** Passwords do not match\n");
				userDetailsValid = false;
			}
			
			// Phone number... store as string to allow a "+" character to be used.
			phoneNumber = request.getParameter("phone");
			out.append("-> Phone Number: "+phoneNumber+" \n");
			
			// Store address lines in an array
			physicalAddress[0] = request.getParameter("address");
			physicalAddress[1] = request.getParameter("city");
			physicalAddress[2] = request.getParameter("zipCode");
			
			out.append("-> Shipping Address:\n");
			out.append("\t-> "+ physicalAddress[0] +"\n");
			out.append("\t-> "+ physicalAddress[1] +"\n");
			out.append("\t-> "+ physicalAddress[2] +"\n");
			
			
		} catch (Exception e) {
			out.append(e.getMessage());
		} finally {
			// TODO: Check if any values are null or not defined
			if (userDetailsValid) {
				User newUser = new User(emailAddress, firstname, lastname, phoneNumber, password, physicalAddress);
				out.append(newUser.toString());
				
				newUser.createNewUserInDatabase(DatabaseConfig.JDBCUrl, DatabaseConfig.username, DatabaseConfig.password);
				
				// TODO Create a cookie for signed in user
				// TODO Redirect to account / store page
			}
			
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
