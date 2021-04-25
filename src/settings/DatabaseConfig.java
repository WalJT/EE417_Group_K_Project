package settings;

/**
 * @author Julian Tarquin Walsh
 * Stores database configuration in a central location,
 * so that it only needs to be changed once
 */
public class DatabaseConfig {
	public static final String url = "jdbc:mysql://groupk.ccuoxucn9lr2.us-east-2.rds.amazonaws.com";
	public static final int port = 3306;
	public static final String databaseName = "GroupKDB";
	public static final String username = "removed";
	public static final String password = "removed";
	
	// This is the complete url that should be referenced
	public static final String JDBCUrl = url+":"+port+"/"+databaseName;
}
