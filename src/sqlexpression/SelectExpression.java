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

/**
 *
 * @author vladimir
 */
public class SelectExpression extends ConditionalSqlExpression {

    private ArrayList<String> _selects;
    
    public SelectExpression(Connection connection) {
        super(connection);
        _selects = new ArrayList<String>();
    }
    public SelectExpression(Connection _connection, String table) {
	this(_connection);
        super.setFrom(table);
    }
    
    public void select(ArrayList<String> selections)
    {
        _selects = selections;
    }
    
    public void select(String selection)
    {
        if(_selects.contains(selection))
            return;
        
        _selects.add(selection);
    }
    
    @Override
    protected ResultSet execute(Connection _connection) throws SqlExpressionException {
        
        StringBuilder str = new StringBuilder("SELECT ");
        
        if(_selects.isEmpty())
            str.append("*");
        else    
        {
            for(String item : _selects)
            {
                str.append(item + ",");
            }
            str.deleteCharAt(str.lastIndexOf(","));
        }
        
        str.append(" FROM " + this.getFrom());
        
        boolean first = true;
        for(WhereExpression exp : this.getWhereStatements())
        {
            if(first)
            {
                str.append(" WHERE " + exp.toString() + " AND ");
                first = false;
                continue;
            }
            
            str.append(exp.toString() + "AND");
        }
         str.delete(str.lastIndexOf("A"),str.lastIndexOf("A") + 3);
        
         try {
            return _connection.createStatement().executeQuery(str.toString());
        } catch (SQLException ex) {
            
            throw new SqlExpressionException("Error occured during selection.\nQuery:\n"+str.toString());
        }
    }
  
}
