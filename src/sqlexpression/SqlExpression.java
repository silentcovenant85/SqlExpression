package sqlexpression;

import java.sql.Connection;
import java.sql.ResultSet;
import java.util.HashMap;

public abstract class SqlExpression {

    private Connection _connection;
    private String _from;
    private HashMap<String,Object> _whereclauses;
    private ResultSet _result;
	
    public SqlExpression(Connection connection) {		
            this._connection = connection;
	}
	
    /**
     * @return the _from
     */
    public String getFrom() {
        return _from;
    }

    /**
     * @param _from the _from to set
     */
    public void setFrom(String _from) {
        this._from = _from;
    }
    
    protected boolean validateExpression() throws SqlExpressionException
    {
        if(_from == null)
        {
            throw new SqlExpressionException("Missing table");
        }

        return true;
    }
        
    public ResultSet execute() throws SqlExpressionException
    {
        if(validateExpression() == false)
           throw new SqlExpressionException("Something is wrong with the sql expression.");
            
        _result = execute(_connection);
        return _result;
     }
        
    protected abstract ResultSet execute(Connection _connection) throws SqlExpressionException;
    protected abstract boolean hasResultSet();
}
