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
        String[] tokens = selection.split(",");
        for (String token : tokens) {
              if(_selects.contains(token))
                continue;
              
            _selects.add(token);
        }
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
        str = super.buildCondition(str);
        this.setExpression(str.toString());
        
        try {
            return _connection.createStatement().executeQuery(this.getExpression());
        } catch (SQLException ex) {
            
            throw new SqlExpressionException("Error occured during selection.\nQuery:\n"+str.toString());
        }
    }
  
}
