/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package sqlexpressiontest;

import java.sql.SQLException;
import java.util.ArrayList;
import junit.framework.TestCase;
import sqlexpression.DatabaseManager;
import sqlexpression.DeleteExpression;
import sqlexpression.InsertExpression;
import sqlexpression.OperationEnum;
import sqlexpression.SelectExpression;
import sqlexpression.SqlDriver;
import sqlexpression.SqlExpressionException;
import sqlexpression.UpdateExpression;

/**
 *
 * @author vladimir
 */
public class DatabaseTest extends TestCase {

    public DatabaseTest() {
    }

    protected void setUp() throws Exception {
      
        //TODO: add checking if table already exists.....
       
       DatabaseManager.start(SqlDriver.Derby, "jdbc:derby://localhost:1527/sample", "app", "app");
       DatabaseManager.getConnection().createStatement().execute("CREATE TABLE MYPRODUCT(PROD_CODE VARCHAR(2), DISCOUNT_CODE VARCHAR(1), DESCRIPTION VARCHAR(10))");      
    }

    /**
     * Tears down the fixture, for example, close a network connection. This
     * method is called after a test is executed.
     */
    protected void tearDown() throws Exception {
        
       DatabaseManager.getConnection().createStatement().execute("DROP TABLE MYPRODUCT");
    }

    public void testSqlConnectionUsingURI() throws SQLException {

        DatabaseManager.start(SqlDriver.MySql, "jdbc:mysql://localhost:3306/mysql", "root", "");
        DatabaseManager.start(SqlDriver.Derby, "jdbc:derby://localhost:1527/sample", "app", "app");
    }

    public void testSqlConnectionUsingFunction()throws SQLException{
        
        DatabaseManager.start(SqlDriver.MySql, "localhost", 3306, "mysql", "root", "");        
        DatabaseManager.start(SqlDriver.Derby, "localhost", 1527, "sample", "app", "app");  
    }
    
    public void testInsertExpression() throws SqlExpressionException{
        
        DatabaseManager.start(SqlDriver.Derby, "jdbc:derby://localhost:1527/sample", "app", "app");

        // create temp table
        InsertExpression exp = new InsertExpression(DatabaseManager.getConnection(), "MYPRODUCT");
        exp.insert("PROD_CODE", "XX");//max 2
        exp.insert("DISCOUNT_CODE : 'X'; DESCRIPTION : 'Sample'");//max 1
        exp.execute();
    }
    
    public void testSelectExpression() throws SqlExpressionException{
  
        testInsertExpression();
    
        SelectExpression exp = new SelectExpression(DatabaseManager.getConnection(), "MYPRODUCT");
        exp.select("PROD_CODE");
        exp.select("DISCOUNT_CODE,DESCRIPTION");
        exp.where("PROD_CODE", OperationEnum.Equal, "XX");
        exp.execute();
    }
    
    public void testINexpression() throws SqlExpressionException{
  
        testInsertExpression();
    
        SelectExpression exp = new SelectExpression(DatabaseManager.getConnection(), "MYPRODUCT");
        exp.select("PROD_CODE");
        exp.select("DISCOUNT_CODE,DESCRIPTION");
        
        ArrayList<String> list = new ArrayList<String>();
        list.add("XX");
        exp.where("PROD_CODE", OperationEnum.NOT_IN, list);
        exp.execute();
    }
    
    public void testDeleteExpression() throws SqlExpressionException
    {
        testInsertExpression();
        
        DeleteExpression deleteExp = new DeleteExpression(DatabaseManager.getConnection(),"MYPRODUCT");
        deleteExp.execute();
    }    
    
    public void testUpdateExpresson() throws SqlExpressionException
    {
        testInsertExpression();
        
        UpdateExpression updateExp = new UpdateExpression(DatabaseManager.getConnection(),"MYPRODUCT");
        updateExp.update("PROD_CODE:ZZ");
        updateExp.execute();
    }
}
