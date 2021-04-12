package settings;

/**
 * @author waljt
 * Stores database configuration in a central location,
 * so that it only needs to be changed once
 */
public class DatabaseConfig {
	public static final String url = "jdbc:mysql://localhost";
	public static final int port = 3306;
	public static final String databaseName = "groupK";
	public static final String username = "groupk";
	public static final String password = "groupk";
}
