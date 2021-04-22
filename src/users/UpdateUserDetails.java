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
			address[3] = request.getParameter("zipcode");
			
			// Phone number, email, and password
			String phone = request.getParameter("mobile");
			String email = request.getParameter("email_id");
			String password = request.getParameter("password");
			
			//out.append("<p>"+address[0]+" "+address[1]+"</p>");
			//out.append("<p>"+firstname+" "+lastname+"</p>");
			
			// TODO Check if any values are null
			
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
		// TODO Auto-generated method stub
		doGet(request, response);
	}

}
