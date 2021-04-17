package cart;


import java.io.IOException;
import java.io.PrintWriter;
import java.util.Vector;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.Cookie;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import group.dao.OrderDao;
import group.entity.OrderItem;

/**
 * Servlet implementation class AddtoCart
 */
@WebServlet("/AddtoCart")
public class AddtoCart extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
    /**
     * @see HttpServlet#HttpServlet()
     */
    public AddtoCart() {
        super();
        // TODO Auto-generated constructor stub
    }

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {

		HttpSession session=request.getSession();
		PrintWriter out = response.getWriter();
		String r = request.getParameter("itemID");
		String a = request.getParameter("amount");
		
		OrderItem newOrderItem=null;					
		
		
		newOrderItem.setGid(Integer.parseInt(r));
		newOrderItem.setAmount(Integer.parseInt(a));
		
		
		newOrderItem.setId(Integer.parseInt(OrderDao.createOrderId()));	//create the order_item id
		
		if(session.getAttribute("cart")==null)		//if the cart is empty create a new one
		{
			Vector<OrderItem> cart=null;
			
			cart.add(newOrderItem);
			session.setAttribute("cart", cart);
		}
		else
		{
			Vector<OrderItem> cart= (Vector<OrderItem>) session.getAttribute("cart");	//If the cart is not empty add the new product

			cart.add(newOrderItem);
		}
		
			out.append("Added to Cart");
		
		
	}

	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
		doGet(request, response);
	}

}
