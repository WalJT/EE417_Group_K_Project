package users;

import java.io.IOException;
import java.io.PrintWriter;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.Cookie;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

/**
 * Servlet implementation class LoginHandler
 */
//@WebServlet("/LoginHandler")
public class LoginHandler extends HttpServlet {
	private static final long serialVersionUID = 175785765546L;
       
    /**
     * @see HttpServlet#HttpServlet()
     */
    public LoginHandler() {
        super();
        // TODO Auto-generated constructor stub
    }

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		PrintWriter out = response.getWriter();
		response.setContentType("text/html");
		
		try {
			// Get username (email) and password from login form
			String userEmail = request.getParameter("username");
			String password = request.getParameter("password");
			
			// Create a new user using the email address,
			// this constructor pulls the rest of the information from the database
			User userToLogin = new User(userEmail);
			
			// Check if a state (any state) if the user is null.. this happens if the email is incorrect
			if (userToLogin.getFirstname() == null) {	
				out.append("<p>Invalid username (Email address); return to <a href='login.html'>login page</a></p>");
			} else if (userToLogin.validateLogin(password)) {
				// Check to see if the password was valid,
				// if it is, set cookies and redirect
				Cookie[] newCookies = userToLogin.createCookies();
				response.addCookie(newCookies[0]);
				response.addCookie(newCookies[1]);
				response.sendRedirect("home.html");
			} else {
				// Notify user if password was wrong
				out.append("<p>Wrong Password; return to <a href='login.html'>login page</a></p>");
			}
			
		} catch (Exception e) {
			// Error message when we need to check server logs
			out.append("Internal Error; return to <a href=\"login.html\">login page</a>");
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
