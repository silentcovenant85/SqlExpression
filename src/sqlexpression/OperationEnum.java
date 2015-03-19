/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

package sqlexpression;

/**
 *
 * @author vladimir
 */
public enum OperationEnum {
    
    Equal("="),
    NotEqual("<>"),
    GreaterThan(">"),
    LesserThan("<"),
    GreaterOrEqual(">="),
    LesserThanOrEqual("<="),
    NOT_IN("NOT IN"),
    IN("IN");
    
    OperationEnum(String str)
    {
        _strOP = str;
    }
    
    private String _strOP;
    
    @Override
    public String toString()
    {
        return _strOP;
    }
}
