/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

package sqlexpression;

import java.sql.Connection;
import java.util.ArrayList;
import java.util.List;


public abstract class ConditionalSqlExpression extends SqlExpression{

    private List<WhereExpression> _whereclauses;
    
    public ConditionalSqlExpression(Connection connection, String table) {
        super(connection, table);
        
        _whereclauses = new ArrayList<WhereExpression>();
    }
        
    public void where(String memberName,OperationEnum op, Object value) throws SqlExpressionException
    {
        WhereExpression exp = new WhereExpression(memberName, value, op);
        where(exp);
    }
        
    public void where(WhereExpression exp)
    {
        if(_whereclauses.contains(exp))
            return;
        
        _whereclauses.add(exp);
    }
    
    public List<WhereExpression> getWhereStatements()
    {
        return _whereclauses;
    }
    
    @Override
    protected void buildExpression() {
    
        StringBuilder expression = new StringBuilder(getExpression());
        
        boolean first = true;
        for(WhereExpression exp : getWhereStatements())
        {
            if(first)
            {
                expression.append(" WHERE " + exp.toString() + " AND ");
                first = false;
                continue;
            }
            
            expression.append(exp.toString() + "AND");
        }
        
         if(expression.lastIndexOf("AND")!=-1)
            expression.delete(expression.lastIndexOf("A"),expression.lastIndexOf("A") + 3);
        
         setExpression(expression.toString());
    } 
    
}
