package group.dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.util.Vector;

import group.entity.Goods;
import group.util.*;


public class StockDao {
//	public static void main(String[] args) {//used to detect if the stock is reduced
//		Vector<Goods> goodList = new Vector<Goods>();
//		Goods g = new Goods();
//		g.setGid(2);
//		g.setGname("b");
//		g.setPrice(100.00);
//		g.setAmount(4);
//		goodList.add(g);
//		new StockDao().UpdateStock(goodList);
//	}
	//reduce stocks when customer buy goods
	public static boolean UpdateStock (Vector<Goods> goodList){
		String sql = "UPDATE Goods SET amount = amount-? WHERE gname = ?";
		Connection conn = null;
		try {
			conn = DBConnection.getConnection();
			PreparedStatement pStmt = conn.prepareStatement(sql);
			for(int i = 0; i < goodList.size(); i++) {
				Goods good = goodList.get(i);
				int num = good.getAmount();
				pStmt.setInt(1, num);
				pStmt.setString(2, good.getGname());
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

