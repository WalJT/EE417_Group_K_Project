package group.util;

import java.sql.Connection;
import java.sql.DriverManager;

public class DBConnection {
	public static Connection getConnection() {
		//database connection
		String dbDriver = "com.mysql.cj.jdbc.Driver";
		String url = "jdbc:mysql://groupk.ccuoxucn9lr2.us-east-2.rds.amazonaws.com:3306/GroupKDB";
		Connection conn = null;
		try {
			Class.forName(dbDriver);
			conn = DriverManager.getConnection(url, "groupkdb", "groupk123");
		} catch (Exception e) {
			e.printStackTrace();
		}
		return conn;
	}

}
