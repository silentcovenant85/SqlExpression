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
public class WhereExpression extends OperationExpression<Object,Object>{

    public WhereExpression(String operand1, OperationEnum op, Object operand2) throws SqlExpressionException {
        super(operand1, operand2, op);
    }  
    
    public WhereExpression(WhereExpression operand1, OperationEnum op, WhereExpression operand2) throws SqlExpressionException {
        super(operand1, operand2, op);
    }  
    
    @Override
    public String toString()
    {
        StringBuilder expression = new StringBuilder();   
        if(this.getOperand1() instanceof WhereExpression)
        {
            expression.append(this.getOperand1().toString());
        }
        else    
        {
            expression.append("(");
            expression.append(this.getOperand1());
        }
        
        expression.append(" ").append(this.getOperation().toString()).append(" ");
        
        String operand2 = "";
        if(this.getOperand2() instanceof String)
            operand2 = "'" + this.getOperand2().toString() + "'" + ")";
        else if(this.getOperand2() instanceof Iterable)
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
        else if(this.getOperand2() instanceof WhereExpression)
        {
            operand2 = this.getOperand2().toString();
        }
        else
        {
           operand2 = this.getOperand2().toString() + ")";
        }

        expression.append(operand2);
        
        return expression.toString();
    }
}
