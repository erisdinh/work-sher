package util;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;

public class DBUtil {
	private static Connection conn = null;
	private static String dbURL = "jdbc:mysql://localhost:3306/worksher_db";
	private static String username = "root";
	private static String password = "1234";

	// Method to get the database connection
	public static Connection getConnection() throws SQLException {
		try {
			Class.forName("com.mysql.jdbc.Driver");
			conn = DriverManager.getConnection(dbURL, username, password);
		} catch (Exception e) {
			e.printStackTrace();
		}
		
		return conn;
	}
	
	// Method to close the database connection
	public static void closeConnection(Connection conn) {
		try {
			conn.close();
		} catch (Exception e) {
			e.getMessage();
		}
	}
}
