package sqlexpression;

import java.sql.Connection;
import java.sql.DriverManager;

public class DatabaseManager {

	private static Connection _connection;
	public static Connection get_connection() {
		
		if(_connection == null)
		{
			initializeConnection();
		}
		
		return _connection;
	}
	private static void initializeConnection() {
		try {
			
			Class.forName("com.mysql.jdbc.Driver");
			_connection = DriverManager.getConnection("jdbc:mysql://localhost/StudenRecord", "root", "");
			
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}

	public DatabaseManager()
	{
		
	}

}
