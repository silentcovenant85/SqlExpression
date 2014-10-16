package sqlexpression;

import java.util.HashMap;
import java.sql.ResultSet;

public abstract class SqlExpression {

	private String _from;
	private HashMap<String,Object> _whereclauses;
	private ResultSet _result;
	
	public SqlExpression(String table) {
		
		this._from = table;
	}
	
        private boolean ValidateExpression()
        {
            //validation here
            return true;
        }
        
        public ResultSet Execute() throws SqlExpressionException
        {
            if(ValidateExpression())
                throw new SqlExpressionException("Something is wrong with the sql expression.");
            
            return ExecuteQuery();
        }
        
	protected abstract ResultSet ExecuteQuery();
}
