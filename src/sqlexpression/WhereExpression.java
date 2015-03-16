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
public class WhereExpression extends OperationExpression<String,Object>{

    public WhereExpression(String operand1, Object operand2, OperationEnum op) {
        super(operand1, operand2, op);
    }  
    
    @Override
    public String toString()
    {
        StringBuilder retval = new StringBuilder();
        retval.append("(" + this.getOperand1());
        retval.append(this.getOperation().toString());
        
        String operand2 = "";
        if(this.getOperand2() instanceof String)
            operand2 = "'" + this.getOperand2().toString() + "'";
        else
            operand2 = this.getOperand2().toString();
              
        retval.append(operand2 + ")");
        
        return retval.toString();
    }
}
