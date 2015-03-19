package sqlexpression;

import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.LinkedHashMap;
import java.util.Map;
import java.util.Map.Entry;

public class InsertExpression extends SqlExpression {

    private Map<String,String> _inserts;
	
    public InsertExpression(Connection connection, String table) {
		super(connection, table);
                _inserts = new LinkedHashMap<String,String> ();
	}
    public InsertExpression(Connection connection,String table, LinkedHashMap<String,String> values)
	{
		this(connection, table);
		this._inserts = values;
	}

    /**
     * @return the _inserts
     */
    public Map<String,String> getInserts() {
        return _inserts;
    }
    /**
     * @param _inserts the _inserts to set
     */
    public void setInserts(Map<String,String> _inserts) {
        this._inserts = _inserts;
    }

    public void insert(String key, Object value)
    {
        if(this._inserts.containsKey(key))
            return;
        
        String strValue = "";
        if(value.getClass().equals(String.class) && (!value.toString().startsWith("'")&&!value.toString().endsWith("'")))
        {
            strValue = "'" + value + "'";
        }
        else 
            strValue= value.toString();
        
        this._inserts.put(key, strValue);
    }
    
    /*
    *  Usage: Insert("key":value)
    *   
    */
    public void insert(String expression)
    {
        String[] tokens = expression.split(";");
        for (String token : tokens) {
            String[] subTokens = token.split(":");
            String key = subTokens[0];
            String value = subTokens[1];
            
            insert(key.trim(),value.trim());
        }
    }

    @Override
    protected boolean validateExpression() throws SqlExpressionException 
    {
        boolean retval = super.validateExpression();
         
        if(getInserts().isEmpty())
            retval = false;
        
        return retval;
    }
            
     @Override
    protected void buildExpression()
    {
        StringBuilder expression = new StringBuilder(QueryType.INSERT.toString());
        expression.append(" INTO ");
        expression.append(this.getFrom()).append(" (");
        
        for(Entry<String, String> item : _inserts.entrySet())
        {
            expression.append(item.getKey()+",");
        }
        
        expression = trimExpression(expression);
        expression.append(" VALUES(");
        for(Entry<String, String> item : _inserts.entrySet())
        {
            expression.append(item.getValue()+",");
        }
        expression = trimExpression(expression);
  
        setExpression(expression.toString());
    }
            
    private StringBuilder trimExpression(StringBuilder expression)
    {
        expression.deleteCharAt(expression.lastIndexOf(","));
        expression.append(")");
        
        return expression;
    }
        
    @Override
    protected ResultSet execute(Connection connection, String expression) throws SqlExpressionException {       
        try 
        {
            connection.createStatement().executeUpdate(this.getExpression());
            
        } catch (SQLException ex) {
            
            throw new SqlExpressionException("Error occured during insert.\nQuery:\n"+expression.toString(), ex);
        }
          
        return null;
    }
}
