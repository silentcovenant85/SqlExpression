/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

package sqlexpressiontest;

import junit.framework.TestCase;
import org.junit.Assert;
import sqlexpression.DatabaseManager;
import sqlexpression.SqlDriver;

/**
 *
 * @author vladimir
 */
public class DatabaseTest extends TestCase {
    
    public DatabaseTest() {
    }
    public void testSqlConnection() {
    
        DatabaseManager.start(SqlDriver.Derby,"jdbc:mysql://localhost:3306/mysql","root","");
        DatabaseManager.start(SqlDriver.Derby,"jdbc:derby://localhost:1527/sample","app","app");
        
        Assert.assertNotNull(DatabaseManager.get_connection());
    }
}
