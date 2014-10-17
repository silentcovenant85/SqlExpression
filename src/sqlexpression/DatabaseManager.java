package sqlexpression;

import java.sql.Connection;
import java.sql.DriverManager;

public class DatabaseManager {

        private static SQLDriver _driver;
        public SQLDriver getDriver()
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
	public DatabaseManager(SQLDriver driver, String uri, String username, String password)
	{
            this._driver = driver;
            this._uri = uri;
            this._username = username;
            this._password = password;
	}
}
