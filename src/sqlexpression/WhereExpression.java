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

    public WhereExpression(String operand1, Object operand2, OperationEnum op) throws SqlExpressionException {
        super(operand1, operand2, op);
    }  
    
    @Override
    public String toString()
    {
        StringBuilder expression = new StringBuilder();
        expression.append("(").append(this.getOperand1()).append(" ");
        expression.append(this.getOperation().toString()).append(" ");
        
        String operand2 = "";
        if(this.getOperand2() instanceof String)
            operand2 = "'" + this.getOperand2().toString() + "'";
        
        if(this.getOperand2() instanceof Iterable)
        {
            operand2+="(";
            for (Object item : (Iterable)this.getOperand2()) {
                if(item instanceof String)
                    operand2 += "'" + this.getOperand2().toString() + "'";
                else
                    operand2 += this.getOperand2().toString();
                
                operand2+=",";
            }
           
            operand2 = operand2.substring(0,operand2.lastIndexOf(",")); 
            operand2+=")";
        }
        else
            operand2 = this.getOperand2().toString();
        
        expression.append(operand2).append(")");
        
        return expression.toString();
    }
}
