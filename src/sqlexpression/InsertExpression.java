package sqlexpression;

import java.util.HashMap;
import java.sql.ResultSet;

public class InsertExpression extends SqlExpression {

    private HashMap<String,Object> _inserts;
	
    public InsertExpression(String table) {
		super(table);
		// TODO Auto-generated constructor stub
	}
    public InsertExpression(String table, HashMap<String,Object> values)
	{
		this(table);
		this._inserts = values;
	}

    /**
     * @return the _inserts
     */
    public HashMap<String,Object> getInserts() {
        return _inserts;
    }
    /**
     * @param _inserts the _inserts to set
     */
    public void setInserts(HashMap<String,Object> _inserts) {
        this._inserts = _inserts;
    }
    
    public void AddInsert(String key, Object value)
    {
        if(this._inserts.containsKey(key))
            return;
        
        this._inserts.put(key, value);
    }
    
    @Override
    public ResultSet ExecuteQuery() {
		// TODO Auto-generated method stub
		//
		return null;
	}
}
