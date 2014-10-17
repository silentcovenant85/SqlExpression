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
    
    MySql("com.mysql.jdbc.Driver"),
    PostGre("org.postgresql.Driver"),
    Derby("org.apache.derby.jdbc.ClientDriver");
            
    private SqlDriver(String driver)
    {
        this._driver = driver;
    }
    
    private String _driver;
    public String getDriver()
    {
       return _driver;
    }
}
