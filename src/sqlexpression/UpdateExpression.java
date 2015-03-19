package sqlexpression;


import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.LinkedHashMap;
import java.util.Map;


/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

/**
 *
 * @author vladimir
 */
public class UpdateExpression extends ConditionalSqlExpression{
    
    private Map<String,Object> _updates;
    
    public UpdateExpression(Connection _connection) {
	super(_connection);
        _updates = new LinkedHashMap<String,Object>();
    }
    public UpdateExpression(Connection _connection, String table){
		this(_connection);
                super.setFrom(table);
		// TODO Auto-generated constructor stub
    }
    public UpdateExpression(Connection _connection,String table, LinkedHashMap<String,Object> values){
		this(_connection);
		this._updates = values;
    }

        /**
     * @return the _inserts
     */
    public Map<String,Object> getUpdates() {
        return _updates;
    }
    /**
     * @param _inserts the _inserts to set
     */
    public void setUpdates(Map<String,Object> _updates) {
        this._updates = _updates;
    }

    public void update(String key, Object value)
    {
       if(this._updates.containsKey(key))
            return;
        
        String strValue = "";
        if(value.getClass().equals(String.class) && (!value.toString().startsWith("'")&&!value.toString().endsWith("'")))
        {
            strValue = "'" + value + "'";
        }
        else 
            strValue= value.toString();
        
        this._updates.put(key, strValue);
    }
    
    /*
    *  Usage: Update("key:value;key:value")
    *   
    */
    public void update(String expression)
    {
        String[] tokens = expression.split(";");
        for (String token : tokens) {
            String[] subTokens = token.split(":");
            String key = subTokens[0];
            String value = subTokens[1];
            
            update(key.trim(),value.trim());
        }
    }
    
    @Override
    protected ResultSet execute(Connection _connection) throws SqlExpressionException {
       
        StringBuilder builder = new StringBuilder("UPDATE");
        builder.append(" " + this.getFrom() + " SET ");

        for(Map.Entry<String, Object> item : _updates.entrySet())
        {
            Object obj = item.getValue();
            String objStr = obj.toString();    
            if(obj.getClass().isInstance(String.class) == true)//non-numeric
            {
                objStr = "'"+obj+"'";
            }

            builder.append(item.getKey()+ "=" + objStr + ",");
        }
        
        builder.deleteCharAt(builder.lastIndexOf(","));
        builder = super.buildCondition(builder);
        this.setExpression(builder.toString());
                    
        try {
            _connection.createStatement().execute(this.getExpression());
        } catch (SQLException ex) {
            
            throw new SqlExpressionException("Error occured during update.\nQuery:\n"+builder.toString());
        }
          
           return null;
    }

}
