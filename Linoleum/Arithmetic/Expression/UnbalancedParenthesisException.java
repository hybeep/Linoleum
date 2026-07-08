package Arithmetic.Expression;

public class UnbalancedParenthesisException extends RuntimeException {
    
    public UnbalancedParenthesisException() {

        super("The parenthesis are not correctly balanced in the expression.");

    }

}
