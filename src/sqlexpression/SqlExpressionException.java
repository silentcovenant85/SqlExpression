/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

package sqlexpression;

import java.sql.SQLException;

/**
 *
 * @author vladimir
 */
public class SqlExpressionException extends SQLException{
    
     public SqlExpressionException(String msg)
    {
        super(msg);
    }
     
    public SqlExpressionException(String msg, SQLException exception)
    {
        super(msg,exception);
    }
}
