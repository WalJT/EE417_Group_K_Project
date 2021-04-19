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
	private static final long serialVersionUID = 1L;
       
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
		// TODO Auto-generated method stub
		PrintWriter out = response.getWriter();
		response.setContentType("text/html");
		try {
			// Get username (email) and password from login form
			String userEmail = request.getParameter("username");
			String password = request.getParameter("password");
			
			User userToLogin = new User(userEmail);
			if (userToLogin.validateLogin(password)) {
				Cookie[] newCookies = userToLogin.createCookies();
				response.addCookie(newCookies[0]);
				response.addCookie(newCookies[1]);
				response.sendRedirect("home.html");
			} else {
				out.append("Wrong Password; return to <a href='login.html'>login page</a>");
			}
			
		} catch (Exception e) {
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
