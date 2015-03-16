/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

package sqlexpression;

/**
 *
 * @author vladimir
 */
public enum SqlDriver {
    
    MySql("com.mysql.jdbc.Driver","jdbc:mysql"),
    PostGre("org.postgresql.Driver","jdbc:postgre"),
    Derby("org.apache.derby.jdbc.ClientDriver","jdbc:derby");
            
    private SqlDriver(String driver,String uri)
    {
        this._driver = driver;
        this._uri = uri;
    }
    
    private String _driver;
    public String getDriver()
    {
       return _driver;
    }
    
    private String _uri;
    public String getUri()
    {
        return _uri;
    }
}
