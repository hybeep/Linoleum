package Arithmetic.Expression;

public class TooFewOperandsException extends RuntimeException {


    public TooFewOperandsException () {

        super("There are unbalanced operations in the expression.");

    }

    
}
