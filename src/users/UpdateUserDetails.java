package users;

import java.io.IOException;
import java.io.PrintWriter;
import java.sql.SQLException;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.Cookie;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

/**
 * Servlet implementation class UpdateUserDetails
 */
//@WebServlet("/UpdateUserDetails")
public class UpdateUserDetails extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
    /**
     * @see HttpServlet#HttpServlet()
     */
    public UpdateUserDetails() {
        super();
        // Auto-generated constructor stub
    }

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
				
		PrintWriter out = response.getWriter();
		response.setContentType("text/html");
		
		try {
			// Pull information from form at user.html
			// First and Last name
			String firstname = request.getParameter("fname");
			String middlename = request.getParameter("mname");
			if (middlename != null) firstname = firstname + " " + middlename;
			String lastname = request.getParameter("lname");
			
			// Address elements
			String[] address = new String[4];
			address[0] = request.getParameter("address");
			address[1] = request.getParameter("city");
			address[2] = request.getParameter("country");
			address[3] = request.getParameter("Zip/Postal Code");
			
			// Phone number, email, and password
			String phone = request.getParameter("mobile");
			String email = request.getParameter("email_id");
			String password = request.getParameter("password");
			
			//out.append("<p>"+address[0]+" "+address[1]+"</p>");
			//out.append("<p>"+firstname+" "+lastname+"</p>");
			
			// Check if any values are null
			// first, create an array of all the parameters
			String[] arrayOfParameters = {firstname,
					lastname,
					address[0],
					address[1],
					address[2],
					address[3],
					phone,
					email,
					password};
			
			boolean parametersValid = true;
			// now loop through the array and set the set parametersValid to false if any are null
			for (String parameter: arrayOfParameters) {
				
				if (parameter == null || parameter.equals("")) {
					
					parametersValid = false;
				} else {
					//out.append(parameter);
				}
			}
			
			if (parametersValid) {
				// Create new user object with this information
				User updatedUser = new User(email, firstname, lastname, phone, password, address);
				//out.append(updatedUser.toString());
				
				// Get ID if currently signed in user from cookie
				Integer userID = null;
				Cookie[] cookies = request.getCookies();
				if (cookies != null) {
					for (Cookie cookie: cookies) {
						if (cookie.getName().equals("userID")) {
							System.out.println(cookie.getValue());
							userID = Integer.parseInt(cookie.getValue());
						}
					}
					
				}

				if (userID == null) {
					// Print an error and prompt for a redirect
					out.append("<p>No ID; This means no user is logged in"
							+ "<br /><a href='login.html'>Go to the Login Page</a></p>");
				} else {
					if (updatedUser.updateUserInDatabase(userID)) {
						response.sendRedirect("home.html");
					} else {
						// Print a different error if updating the database fails
						out.append("<p>Something went wrong when trying to update the database<br />"
								+ "Check the server logs for more details</p>");
					}
				}
			} else {
				
				// Grab current user's email address from the cookie
				String currentEmail = null;
				Integer userID = null;
				Cookie[] cookies = request.getCookies();
				if (cookies != null) {
					for (Cookie cookie: cookies) {
						if (cookie.getName().equals("userEmail")) {
							System.out.println(cookie.getValue());
							currentEmail = cookie.getValue();
						} else if (cookie.getName().equals("userID")) {
							System.out.println(cookie.getValue());
							userID = Integer.parseInt(cookie.getValue());
						}
					}
					
				}
				
				if (currentEmail == null) {
					out.append("<p>No Email address found; This means no user is logged in"
							+ "<br /><a href='login.html'>Go to the Login Page</a></p>");
				} else {
					User userToUpdate = new User(currentEmail);
					
					// Update the parameters that are valid using setters
					if (firstname != null && firstname != "") {
						userToUpdate.setFirstname(firstname);
					}
					if (lastname != null && lastname != "") {
						userToUpdate.setSurname(lastname);
					}
					
					// Update all or none of the address fields to avoid issues
					if (address[0] != "" && address[1] != "" && address[2] != "" && address[3] != "") {
						userToUpdate.setAddress(address);
						userToUpdate.setAddressString(userToUpdate.stringifyAddress(address));
					}
					
					if (phone != null && phone != "") {
						userToUpdate.setPhone(phone);
					}
					if (email != null && email != "") {
						userToUpdate.setEmailAddress(email);
					}
					if (password != null && password != "") {
						userToUpdate.updatePasswordHash(password);
					}
					
					// call update in database function and redirect home
					userToUpdate.updateUserInDatabase(userID);
					response.sendRedirect("home.html");
				}
				
			}
			
			
			
		} catch (SQLException e) {
			// Message for SQL Exception
			out.append("<p>An SQL Exception was caught... check the server logs</p>");
			e.printStackTrace();
		} catch (Exception e) {
			// Error message for generic failure
			out.append("<p>An Exception was caught... check the server logs</p>");
			e.printStackTrace();
		} finally {
			out.close();
		}
		
		
		
		
	}

	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// Auto-generated method stub
		doGet(request, response);
	}

}
