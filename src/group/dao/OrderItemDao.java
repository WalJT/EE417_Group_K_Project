package group.dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.util.Vector;

import group.entity.OrderItem;
import group.util.DBConnection;

public class OrderItemDao {
	//used to detect if the database is updated after a new order item is inserted.
//	public static void main(String[] args) {
//		Vector<OrderItem> itemList = new Vector<OrderItem>();
//		OrderItem oi = new OrderItem();
//		oi.setId(0);
//		oi.setOrderId("555");
//		oi.setGid(353245);
//		oi.setGname("c");
//		oi.setPrice(340.00);
//		oi.setAmount(5);
//		itemList.add(oi);
//		new OrderItemDao().insertOrderItem(itemList);
//	}
	
	public boolean insertOrderItem(Vector<OrderItem> itemList) {
		String sql = "insert into order_item values(?,?,?,?,?,?)";
		Connection conn = null;
		try {
			conn = DBConnection.getConnection();
			PreparedStatement pStmt = conn.prepareStatement(sql);
			for(int i = 0; i < itemList.size(); i++) {
				OrderItem item = itemList.get(i);
				pStmt.setInt(1, item.getId());//sequence number of table
				pStmt.setString(2, item.getOrderId());
				pStmt.setInt(3, item.getGid());//goods id
				pStmt.setString(4, item.getGname());//goods name
				pStmt.setDouble(5, item.getPrice());
				pStmt.setInt(6, item.getAmount());
				pStmt.addBatch();
			}
			pStmt.executeBatch();
			pStmt.close();
			conn.close();
			return true;
		} catch (Exception e) {
			e.printStackTrace();
			return false;
		}
	}
}
