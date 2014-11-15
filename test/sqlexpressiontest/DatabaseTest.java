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
import sqlexpression.OperationEnum;
import sqlexpression.SelectExpression;
import sqlexpression.SqlDriver;
import sqlexpression.SqlExpressionException;

/**
 *
 * @author vladimir
 */
public class DatabaseTest extends TestCase {

    public DatabaseTest() {
    }

    protected void setUp() throws Exception {
      
       DatabaseManager.start(SqlDriver.Derby, "jdbc:derby://localhost:1527/sample", "app", "app");
        //TODO: add checking if table already exists.....
       DatabaseManager.get_connection().createStatement().execute("CREATE TABLE MYPRODUCT(PROD_CODE VARCHAR(2), DISCOUNT_CODE VARCHAR(1), DESCRIPTION VARCHAR(10))");      
    }

    /**
     * Tears down the fixture, for example, close a network connection. This
     * method is called after a test is executed.
     */
    protected void tearDown() throws Exception {
        
        DatabaseManager.get_connection().createStatement().execute("DROP TABLE MYPRODUCT");
    }

    public void testSqlConnection() {

        DatabaseManager.start(SqlDriver.MySql, "jdbc:mysql://localhost:3306/mysql", "root", "");
        DatabaseManager.start(SqlDriver.Derby, "jdbc:derby://localhost:1527/sample", "app", "app");
        DatabaseManager.start(SqlDriver.MySql, "localhost", 3306, "mysql", "root", "");
        DatabaseManager.start(SqlDriver.Derby, "localhost", 1527, "sample", "app", "app");

        Assert.assertNotNull(DatabaseManager.get_connection());
    }

    public void testInsertExpression() throws SqlExpressionException, SQLException {
        
        DatabaseManager.start(SqlDriver.Derby, "jdbc:derby://localhost:1527/sample", "app", "app");

        // create temp table
        InsertExpression exp = new InsertExpression(DatabaseManager.get_connection(), "MYPRODUCT");
        exp.AddInsert("PROD_CODE", "XX");//max 2
        exp.AddInsert("DISCOUNT_CODE", "X");//max 1
        exp.AddInsert("DESCRIPTION", "Sample");//max 10 
        exp.execute();
    }
    
    public void testSelectExpression() throws SqlExpressionException, SQLException{
  
        DatabaseManager.start(SqlDriver.Derby, "jdbc:derby://localhost:1527/sample", "app", "app");

        // create temp table
        InsertExpression insertExp = new InsertExpression(DatabaseManager.get_connection(), "MYPRODUCT");
        insertExp.AddInsert("PROD_CODE", "XX");//max 2
        insertExp.AddInsert("DISCOUNT_CODE", "X");//max 1
        insertExp.AddInsert("DESCRIPTION", "Sample");//max 10 
        insertExp.execute();
        
        SelectExpression exp = new SelectExpression(DatabaseManager.get_connection(), "MYPRODUCT");
        exp.select("PROD_CODE");
        exp.select("DISCOUNT_CODE");
        exp.select("DESCRIPTION");
        exp.where("PROD_CODE", OperationEnum.Equal, "XX");
        exp.execute();
    }
    
}
