/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

package sqlexpression;

import java.sql.Connection;
import java.sql.ResultSet;
import java.util.ArrayList;
import java.util.List;


public abstract class ConditionalSqlExpression extends SqlExpression{

    private List<WhereExpression> _whereclauses;
    
    public ConditionalSqlExpression(Connection connection) {
        super(connection);
        _whereclauses = new ArrayList<WhereExpression>();
    }
        
    public void where(String memberName,OperationEnum op, Object value)
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
    
    public StringBuilder buildCondition(StringBuilder str)
    {
        boolean first = true;
        for(WhereExpression exp : getWhereStatements())
        {
            if(first)
            {
                str.append(" WHERE " + exp.toString() + " AND ");
                first = false;
                continue;
            }
            
            str.append(exp.toString() + "AND");
        }
        
         if(str.lastIndexOf("AND")!=-1)
            str.delete(str.lastIndexOf("A"),str.lastIndexOf("A") + 3);
        
         return str;
    }
            
    public List<WhereExpression> getWhereStatements()
    {
        return _whereclauses;
    }
    
}
