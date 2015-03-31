package sqlexpression;

import java.sql.Connection;
import java.sql.DriverManager;
import java.util.HashMap;


public class DatabaseManager {

        private static HashMap<String,Connection> _connections = new HashMap<String,Connection>();
                
        private static SqlDriver _driver;
        private static String _username;
        private static String _password;
        private static String _uri;
        private static int _port;
        
	public static Connection getConnection() {

               Connection _connection = null;
		if(_connections.containsKey(_uri) == false)
		{
                    _connection = initializeConnection();
		}
                else    
                {
                    _connection = _connections.get(_uri);
                }
                
		return _connection;
	} 
	private static Connection initializeConnection() {
            
            	Connection _connection = null;
		try {
		
			Class.forName(_driver.getDriver());
			_connection = DriverManager.getConnection(_uri,_username ,_password);
                        
                        if(_connections.containsKey(_uri) == false)
                        {
                            _connections.put(_uri, _connection);
                        }
			
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
                
               return _connection;
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
        
        public static void start(SqlDriver driver, String server, int port, String dbName, String username, String password)
	{
            _driver = driver;
            _uri = _driver.getUri() + "://" + server + ":" + port + "/" + dbName;
            _username = username;
            _password = password;
	}
}
