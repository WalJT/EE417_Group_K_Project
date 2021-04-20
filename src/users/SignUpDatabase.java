package users;

import java.io.IOException;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.SQLException;
import java.sql.Statement;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

/**
 * Servlet implementation class SignUpDatabase
 */
@WebServlet("/SignUpDatabase")
public class SignUpDatabase extends HttpServlet 
{
	private static final long serialVersionUID = 1L;
       
    /**
     * @see HttpServlet#HttpServlet()
     */
    public SignUpDatabase() {
        super();
        // TODO Auto-generated constructor stub
    }

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
		response.getWriter().append("Served at: ").append(request.getContextPath());
	}

	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
		doGet(request, response);

		//Get all the data of the form 
		String firstname = request.getParameter("firstName");
		String lastname = request.getParameter("lastName");
		String email = request.getParameter("EmailAddress");
		String password = request.getParameter("NewPassword");
		String phone = request.getParameter("phone");
		String adress = request.getParameter("address");
		String city = request.getParameter("city");
		int zipcode = Integer.parseInt(request.getParameter("zipCode"));
				
		//DATABASE CONNECTION
		Connection conn = null;
		Statement stat = null;
		int rs;
		//database url, username and password
		String JDBUrl = "jdbc:mysql://ee417.crxkzf89o3fh.eu-west-1.rds.amazonaws.com:3306/testdb";
		String username = "ee417";
		String pswd = "ee417";
				
		try
		{
			Class.forName("com.mysql.jdbc.Driver");
			//make the connection with the database
			conn = DriverManager.getConnection(JDBUrl, username, pswd);
			//sql request
			String myquery = "insert into GroupK_Accounts (firstname,lastname,email,psd,phone,adress,city,zipcode) value(?,?,?,?,?,?,?,?)";
			//prepared statement
			PreparedStatement mystatement = conn.prepareStatement(myquery);
			mystatement.setString(1, firstname);
			mystatement.setString(2, lastname);
			mystatement.setString(3, email);
			mystatement.setString(4, password);
			mystatement.setString(5, phone);
			mystatement.setString(6, adress);
			mystatement.setString(7, city);
			mystatement.setInt(8, zipcode);
					
			//execute the query 
			rs = mystatement.executeUpdate();
			
			response.sendRedirect("home.html");
					
					
		}
		catch (SQLException e) 
		{
			// TODO Auto-generated catch block
			e.printStackTrace();
		} 
		catch (ClassNotFoundException e) 
		{
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}

}
