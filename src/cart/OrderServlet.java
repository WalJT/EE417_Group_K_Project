package cart;

import group.dao.*;
import group.entity.*;
import users.*;

import java.io.IOException;
import java.io.PrintWriter;
import java.util.Vector;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import javax.servlet.http.Cookie;

/**
 * Servlet implementation class OrderServlet
 */
@WebServlet("/OrderServlet")
public class OrderServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;
	private static final String[] Cookie = null;
       
    /**
     * @see HttpServlet#HttpServlet()
     */
    public OrderServlet() {
        super();
        // TODO Auto-generated constructor stub
    }

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException 
	{
		HttpSession session = request.getSession();
		PrintWriter out = response.getWriter();
		Order newOrder=null;
		newOrder.setOrderId(OrderDao.createOrderId());
		
		Vector<OrderItem> ItemList= (Vector<OrderItem>) session.getAttribute("cart");	//get the cart of the session
		OrderItem newOrderItem=null;
		Vector<Goods> goodList=null;
		Goods newGood=null;
		String email;
		
		Cookie[] uCookie = request.getCookies();
		email=Cookie[0];
		User newuser= new User(email);
		
		
		for(int i=0;i<ItemList.size();i++)				//fill the goodlist to manage the stock
		{
			newGood.setGid(ItemList.elementAt(i).getGid());			
			goodList.add(newGood);
		}
		
		
		
		
		if(OrderItemDao.insertOrderItem(ItemList))		//create all the orderitem (stock them in the DB) 
		{
			out.print("Order complete");
		}
		else
		{
			out.print("error");
		}
		StockDao.UpdateStock(goodList);	    //Update the stock
	    
		
	    //TO DO link the user information's with the Order
	
		
		
		newOrder.setAddress(String.join(" ", newuser.getAddress()));
		newOrder.setPhone(newuser.getPhone());
		newOrder.setEmail(newuser.getEmailAddress());
		newOrder.setFirstName(newuser.getFirstname());
		
		OrderDao.insertOrder(newOrder);
	    
	  
	    
	    
	}

	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
		doGet(request, response);
	}

}
