package sqlexpression;

import java.sql.Connection;
import java.sql.DriverManager;

public class DatabaseManager {

        private static SqlDriver _driver;
        public SqlDriver getDriver()
        {
            return _driver;
        }
          
        private static String _username;
        private static String _password;
        private static String _uri;
    
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
			
			Class.forName(_driver.getDriver());
			_connection = DriverManager.getConnection(_uri,_username ,_password);
			
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}
                
        /**
        * @param driver driver (SqlDriver)
        * @param uri example: jdbc:derby://localhost:1527
        */
	public static void start(SqlDriver driver, String uri, String username, String password)
	{
            _driver = driver;
            _uri = uri;
            _username = username;
            _password = password;
	}
}
