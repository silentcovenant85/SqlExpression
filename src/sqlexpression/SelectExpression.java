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
    
    public SelectExpression(Connection connection, String table) {
	super(connection,table);
        
        _selects = new ArrayList<String>();
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
    protected ResultSet execute(Connection connection, String expression) throws SqlExpressionException {
        
        try 
        {
            return connection.createStatement().executeQuery(expression);
            
        } catch (SQLException ex) {
            
            throw new SqlExpressionException("Error occured during selection.\nQuery:\n"+ expression, ex);
        }
    }

    @Override
    protected void buildExpression() {
        
        StringBuilder expression = new StringBuilder(QueryType.SELECT.toString());
        
        if(_selects.isEmpty())
            expression.append(" * ");
        else    
        {
            for(String item : _selects)
            {
                expression.append(" " + item + ",");
            }
            
            expression = expression.deleteCharAt(expression.lastIndexOf(","));
        }
        
        expression.append(" FROM " + this.getFrom() + " ");
        this.setExpression(expression.toString());
        
        super.buildExpression();
    }
  
}
