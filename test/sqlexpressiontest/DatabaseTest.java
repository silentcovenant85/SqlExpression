/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

package sqlexpressiontest;

import java.sql.SQLException;
import junit.framework.TestCase;
import org.junit.Assert;
import sqlexpression.DatabaseManager;
import sqlexpression.InsertExpression;
import sqlexpression.SqlDriver;
import sqlexpression.SqlExpressionException;

/**
 *
 * @author vladimir
 */
public class DatabaseTest extends TestCase {
    
    public DatabaseTest() {
    }
    public void testSqlConnection() {
    
        DatabaseManager.start(SqlDriver.MySql,"jdbc:mysql://localhost:3306/mysql","root","");
        DatabaseManager.start(SqlDriver.Derby,"jdbc:derby://localhost:1527/sample","app","app");
        DatabaseManager.start(SqlDriver.MySql,"localhost",3306,"mysql","root","");
        DatabaseManager.start(SqlDriver.Derby,"localhost",1527,"sample","app","app");
        
        Assert.assertNotNull(DatabaseManager.get_connection());
    }
    
    public void testInsertExpression() throws SqlExpressionException, SQLException
    {
         DatabaseManager.start(SqlDriver.Derby,"jdbc:derby://localhost:1527/sample","app","app");
         
         // create temp table
         DatabaseManager.get_connection().createStatement().execute("CREATE TABLE PRODUCT_CODE(PROD_CODE VARCHAR(2), DISCOUNT_CODE VARCHAR(1), DESCRIPTION VARCHAR(10))");
         InsertExpression exp = new InsertExpression(DatabaseManager.get_connection(),"PRODUCT_CODE");
         exp.AddInsert("PROD_CODE", "XX");//max 2
         exp.AddInsert("DISCOUNT_CODE", "X");//max 1
         exp.AddInsert("DESCRIPTION", "Sample");//max 10 
         exp.execute();
         
         // drop table after test
         DatabaseManager.get_connection().createStatement().execute("DROP TABLE PRODUCT_CODE");
    }
}
