package users;

import java.io.IOException;
import java.io.PrintWriter;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
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
        // TODO Auto-generated constructor stub
    }

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
				
		PrintWriter out = response.getWriter();
		response.setContentType("text/html");
		
		// Pull information from form at user.html
		// First and Last name
		String firstname = request.getParameter("fname");
		String middlename = request.getParameter("mname");
		if (middlename != null) firstname = firstname + " " + middlename;
		String lastname = request.getParameter("lname");
		
		// Address
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
		
		// Create new user object with this information
		User updatedUser = new User(email, firstname, lastname, phone, password, address);
		out.append(updatedUser.toString());
		
		// TODO update user in database with instance method
	}

	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
		doGet(request, response);
	}

}
