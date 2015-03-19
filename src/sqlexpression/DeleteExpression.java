/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package sqlexpression;

import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.LinkedHashMap;

/**
 *
 * @author vladimir
 */
public class DeleteExpression extends ConditionalSqlExpression {
    
    public DeleteExpression(Connection _connection) {
	super(_connection);
    }
    public DeleteExpression(Connection _connection, String table) {
		this(_connection);
                super.setFrom(table);
		// TODO Auto-generated constructor stub
	}

    @Override
    protected ResultSet execute(Connection _connection) throws SqlExpressionException {
        
        StringBuilder str = new StringBuilder("DELETE FROM ");
        str.append(this.getFrom());
        str = super.buildCondition(str);
        this.setExpression(str.toString());
        
         try {    
            _connection.createStatement().execute(this.getExpression());
        } catch (SQLException ex) {
            
            throw new SqlExpressionException("Error occured during deletion.\nQuery:\n"+str.toString());
        }
         
         return null;
    }
}
