package sqlexpression;

import java.sql.Connection;
import java.sql.ResultSet;

public abstract class SqlExpression {

    private Connection _connection;
    private String _from;
    private String _expression;
    private ResultSet _result;
	
    public SqlExpression(Connection connection, String from) {		
            setConnection(connection);
            setFrom(from);
	}
	
    /**
     * @return the _connection
     */
    public Connection getConnection() {
        return _connection;
    }

    /**
     * @param _connection the _connection to set
     */
    public void setConnection(Connection _connection) {
        this._connection = _connection;
    }
    
    /**
     * @return the _expression
     */
    protected String getExpression() {
        return _expression;
    }

    /**
     * @param _expression the _expression to set
     */
    protected void setExpression(String _expression) {
        this._expression = _expression;
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
            
        buildExpression();
        _result = execute(getConnection(),getExpression());
        return _result;
     }
                
    protected abstract void buildExpression();
    protected abstract ResultSet execute(Connection connection, String expression) throws SqlExpressionException;

    @Override
    public String toString()
    {
        return getExpression();
    }
}
