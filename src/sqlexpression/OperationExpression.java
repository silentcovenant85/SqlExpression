/*
 * OperationExpression covers both logical, mathematical, and sql expression
 */
package sqlexpression;

/**
 *
 * @author vladimir
 */
public class OperationExpression<T,V> {

    private OperationEnum _operation;
    private T _operand1;
    private V _operand2;

    public OperationExpression(T operand1, V operand2, OperationEnum op) {
        this._operand1 = operand1;
        this._operand2 = operand2;
        this._operation = op;
    }

    /**
     * @return the _operation
     */
    public OperationEnum getOperation() {
        return _operation;
    }

    /**
     * @param _operation the _operation to set
     */
    public void setOperation(OperationEnum _operation) {
        this._operation = _operation;
    }

    /**
     * @return the _operand1
     */
    public Object getOperand1() {
        return _operand1;
    }

    /**
     * @param _operand1 the _operand1 to set
     */
    public void setOperand1(T _operand1) {
        this._operand1 = _operand1;
    }

    /**
     * @return the _operand2
     */
    public Object getOperand2() {
        return _operand2;
    }

    /**
     * @param _operand2 the _operand2 to set
     */
    public void setOperand2(V _operand2) {
        this._operand2 = _operand2;
    }
}
