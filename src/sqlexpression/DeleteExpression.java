/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package sqlexpression;

import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;

/**
 *
 * @author vladimir
 */
public class DeleteExpression extends ConditionalSqlExpression {

    public DeleteExpression(Connection connection, String table) {
	super(connection,table);
    }

    @Override
    protected ResultSet execute(Connection connection, String expression) throws SqlExpressionException {
         
        try 
        {    
            connection.createStatement().execute(this.getExpression());
            
        } catch (SQLException ex) {
            
            throw new SqlExpressionException("Error occured during deletion.\nQuery:\n"+ expression.toString());
        }
         
         return null;
    }
    
    @Override
    protected void buildExpression()
    {
        StringBuilder expression = new StringBuilder(QueryType.DELETE.toString());
        expression.append(" FROM ").append(this.getFrom());
        setExpression(expression.toString());
        super.buildExpression();
    }
}
