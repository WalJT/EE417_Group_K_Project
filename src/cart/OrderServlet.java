package cart;

import group.dao.*;
import group.entity.*;
import users.*;

import java.io.IOException;
import java.io.PrintWriter;
import java.util.*;

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
		
		String order[]= request.getParameterValues("order");
		String quantity[]= request.getParameterValues("quantity");
		
		
		PrintWriter out = response.getWriter();
		Order newOrder= new Order();
		newOrder.setOrderId(OrderDao.createOrderId());
		
		
		Vector<OrderItem> ItemList= new Vector<OrderItem>();
		OrderItem newOrderItem= new OrderItem();
		
		Vector<Goods> goodList= new Vector<Goods>();
		Goods newGood= new Goods();
		
		
		Cookie[] uCookie = request.getCookies();
		User newuser= new User(uCookie[0].getValue());
		
		for(int i=0;i<order.length;i++)
		{	
			newGood.setGname(order[i]);
			newOrderItem.setGname(order[i]);
			
			newGood.setAmount(Integer.valueOf(quantity[i]));
			newOrderItem.setAmount(Integer.valueOf(quantity[i]));
			
			newOrderItem.setOrderId(newOrder.getOrderId());
			newOrderItem.setId(Integer.parseInt(OrderDao.createOrderId()));
			
			goodList.add(newGood);
			ItemList.add(newOrderItem);
		}
		
		
		
		
		if(OrderItemDao.insertOrderItem(ItemList))		//create all the order (stock them in the DB) 
		{
			out.print("Order complete");
		}
		else
		{
			out.print("error");
		}
		StockDao.UpdateStock(goodList);	    //Update the stock	
		
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
