package group.dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.text.SimpleDateFormat;

import group.entity.Order;
import group.util.DBConnection;

public class OrderDao {//create new order,put information of customer in DB
	//used to test if the database is updated after a new order is inserted.
	public static void main(String[] args) {
		OrderDao order = new OrderDao();
		Order o = new Order("12353",4,"MMM","skfha","ee@qq.com","e","123","sdfa","da",7);
		boolean b = order.insertOrder(o);
		System.out.print(b);
	}
	
	public boolean insertOrder(Order order) {
		String sql = "insert into `order` values(?,?,?,?,?,?,?,?,?,?)";
		Connection conn = null;
		try {
			conn = DBConnection.getConnection();
			PreparedStatement pStmt = conn.prepareStatement(sql);
			pStmt.setString(1, order.getOrderId());
			pStmt.setInt(2, order.getId());
			pStmt.setString(3, order.getFirstName());
			pStmt.setString(4, order.getLastName());
			pStmt.setString(5, order.getEmail());
			pStmt.setString(6, order.getPsd());
			pStmt.setString(7, order.getPhone());
			pStmt.setString(8, order.getAddress());
			pStmt.setString(9, order.getCity());
			pStmt.setInt(10, order.getPostalCode());
			pStmt.executeUpdate();
			pStmt.close();
			conn.close();
			return true;
		} catch (Exception e) {
			e.printStackTrace();
			return false;
		}
	}
	
	// generate orderId:year,month,day,hour,minutes,second,random number
		public static String createOrderId() {
			StringBuffer sb = new StringBuffer();
			SimpleDateFormat s = new SimpleDateFormat("yyyyMMddHHmmssSSS");
			String dateStr = s.format(new java.util.Date());
			sb.append(dateStr);
			for (int i = 0; i < 3; i++) {
				sb.append((int) (Math.random() * 10));
			}
			return sb.toString();
		}
}
