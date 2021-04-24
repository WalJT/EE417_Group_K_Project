package users;

import java.io.IOException;
import java.io.PrintWriter;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

/**
 * Servlet implementation class RecoverPassword
 */
//@WebServlet("/RecoverPassword")
public class RecoverPassword extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
    /**
     * @see HttpServlet#HttpServlet()
     */
    public RecoverPassword() {
        super();
        // Auto-generated constructor stub
    }

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// Dummy method that imitates password reset functionality
		PrintWriter out = response.getWriter();
		response.setContentType("text/html");
		
		// TODO get email address
		String email = request.getParameter("username");
		out.append(email);
		
		// TODO demonstrate java.mail?
		
		// TODO print message about sending a password reset email
		
		out.append("<p> A password reset email has ben sent to</p>");
		
	}

	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// Auto-generated method stub
		doGet(request, response);
	}

}
