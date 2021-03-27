package users;

import java.io.IOException;
import java.io.PrintWriter;

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

		// Get parameter values from signup form request
		boolean userDetailsValid = true;
		try {
			// First and Last name can be read in and set
			String firstName = request.getParameter("firstName");
			String lastName = request.getParameter("lastName");
			out.append("Creating user account for "+firstName+" "+lastName+"\n");
			
			// Read in email address and compare with confirmation field
			String emailAddress = request.getParameter("EmailAddress");
			if (emailAddress.equals(request.getParameter("EmailAddress_conf"))) {
				out.append("-> Email Address: "+emailAddress+"\n");
			} else {
				out.append("** Email Addresses do not match\n");
				userDetailsValid = false;
			}
			
			// Read in password and compare with confirmation field
			String password = request.getParameter("NewPassword");
			if (password.equals(request.getParameter("NewPassword_conf"))) {
				out.append("-> Password: [Redacted, Valid]\n");
				// TODO: Salt & Hash password here?
			} else {
				out.append("** Passwords do not match\n");
				userDetailsValid = false;
			}
			
			// Phone number... store as string to allow a "+" character to be used.
			String phoneNumber = request.getParameter("phone");
			out.append("-> Phone Number: "+phoneNumber+" \n");
			
			// Store address lines in an array
			String physicalAddress[] = new String[3];
			physicalAddress[0] = request.getParameter("address");
			physicalAddress[1] = request.getParameter("city");
			physicalAddress[2] = request.getParameter("zipCode");
			
			out.append("-> Shipping Address:\n");
			out.append("\t-> "+ physicalAddress[0] +"\n");
			out.append("\t-> "+ physicalAddress[1] +"\n");
			out.append("\t-> "+ physicalAddress[2] +"\n");
			
			
		} catch (Exception e) {
			out.append(e.getMessage());
			out.close();
		}
		
		if (userDetailsValid) {
			// TODO Create User object and push info to database
			// TODO Create a cookie for signed in user
			// TODO Redirect to account / store page
		}
		
		out.close();
	}

	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// Run doGet...
		doGet(request, response);
	}

}
