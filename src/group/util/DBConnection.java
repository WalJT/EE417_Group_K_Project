package group.util;

import java.sql.Connection;
import java.sql.DriverManager;

public class DBConnection {
	public static Connection getConnection() {
		//database connection
		String dbDriver = "com.mysql.cj.jdbc.Driver";
		String url = "jdbc:mysql://localhost:3306/groupK?useSSL=false&useUnicode=true&characterEncoding=UTF-8&serverTimezone=GMT%2B8";
		Connection conn = null;
		try {
			Class.forName(dbDriver);
			conn = DriverManager.getConnection(url, "root", "123456789");
		} catch (Exception e) {
			e.printStackTrace();
		}
		return conn;
	}

}
