package sqlexpression;

import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.LinkedHashMap;
import java.util.Map;
import java.util.Map.Entry;

public class InsertExpression extends SqlExpression {

    private Map<String,String> _inserts;
	
    public InsertExpression(Connection _connection) {
	super(_connection);
        _inserts = new LinkedHashMap<String,String>();
    }
    public InsertExpression(Connection _connection, String table) {
		this(_connection);
                super.setFrom(table);
		// TODO Auto-generated constructor stub
	}
    public InsertExpression(Connection _connection,String table, LinkedHashMap<String,String> values)
	{
		this(_connection);
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

    protected boolean validateExpression() throws SqlExpressionException 
    {
        boolean retval = super.validateExpression();
         
        if(getInserts().isEmpty())
            retval = false;
        
        return retval;
    }
            
    @Override
    protected ResultSet execute(Connection _connection) throws SqlExpressionException {
        StringBuilder builder = new StringBuilder("INSERT INTO");
        builder.append(" " + this.getFrom() + " (");

        for(Entry<String, String> item : _inserts.entrySet())
        {
            builder.append(item.getKey()+",");
        }
        builder.deleteCharAt(builder.lastIndexOf(","));
        builder.append(")");
        builder.append(" VALUES(");
        for(Entry<String, String> item : _inserts.entrySet())
        {
            builder.append(item.getValue()+",");
        }
        builder.deleteCharAt(builder.lastIndexOf(","));
        builder.append(")");
            
        try {
            setExpression(builder.toString());
            _connection.createStatement().execute(this.getExpression());
        } catch (SQLException ex) {
            
            throw new SqlExpressionException("Error occured during insert.\nQuery:\n"+builder.toString());
        }
          
        return null;
    }
}
