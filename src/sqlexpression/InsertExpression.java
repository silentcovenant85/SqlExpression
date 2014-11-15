package sqlexpression;

import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.LinkedHashMap;
import java.util.Map;
import java.util.Map.Entry;

public class InsertExpression extends SqlExpression {

    private Map<String,Object> _inserts;
	
    public InsertExpression(Connection _connection) {
	super(_connection);
        _inserts = new LinkedHashMap<String,Object>();
    }
    public InsertExpression(Connection _connection, String table) {
		this(_connection);
                super.setFrom(table);
		// TODO Auto-generated constructor stub
	}
    public InsertExpression(Connection _connection,String table, LinkedHashMap<String,Object> values)
	{
		this(_connection);
		this._inserts = values;
	}

    /**
     * @return the _inserts
     */
    public Map<String,Object> getInserts() {
        return _inserts;
    }
    /**
     * @param _inserts the _inserts to set
     */
    public void setInserts(Map<String,Object> _inserts) {
        this._inserts = _inserts;
    }

    public void AddInsert(String key, Object value)
    {
        if(this._inserts.containsKey(key))
            return;
        
        this._inserts.put(key, value);
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

        for(Entry<String, Object> item : _inserts.entrySet())
        {
            builder.append(item.getKey()+",");
        }
        builder.deleteCharAt(builder.lastIndexOf(","));
        builder.append(")");
        builder.append(" VALUES(");
        for(Entry<String, Object> item : _inserts.entrySet())
        {
            Object obj = item.getValue();
            String objStr = "";
            if(obj.getClass().isInstance(String.class) == false)//non-numeric
            {
                objStr = "'"+obj+"'";
            }

            builder.append(objStr+",");
        }
        builder.deleteCharAt(builder.lastIndexOf(","));
        builder.append(")");
            
        try {
            _connection.createStatement().execute(builder.toString());
        } catch (SQLException ex) {
            
            throw new SqlExpressionException("Error occured during insert.\nQuery:\n"+builder.toString());
        }
          
           return null;
    }


}
