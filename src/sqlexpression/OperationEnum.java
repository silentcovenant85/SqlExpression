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
    LesserThanOrEqual("<=");
    
    OperationEnum(String str)
    {
        _equivalent = str;
    }
    
    private String _equivalent;
    public String toString()
    {
        return _equivalent;
    }
}
