/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

package sqlexpressiontest;

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
        
        Assert.assertNotNull(DatabaseManager.get_connection());
    }
    
    public void testInsertExpression() throws SqlExpressionException
    {
         DatabaseManager.start(SqlDriver.Derby,"jdbc:derby://localhost:1527/sample","app","app");
         InsertExpression exp = new InsertExpression(DatabaseManager.get_connection(),"PRODUCT_CODE");
         exp.AddInsert("PROD_CODE", "XX");//max 2
         exp.AddInsert("DISCOUNT_CODE", "X");//max 1
         exp.AddInsert("DESCRIPTION", "Sample");//max 10 
         exp.execute();
    }
}
