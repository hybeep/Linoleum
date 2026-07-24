package Expression;

public class TooManyOperandsException extends RuntimeException {
    
    public TooManyOperandsException() {

        super("There are unbalanced operations in the expression.");

    }

}
