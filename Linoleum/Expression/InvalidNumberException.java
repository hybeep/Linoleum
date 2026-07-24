package Expression;

public class InvalidNumberException extends RuntimeException {
    
    public InvalidNumberException() {

        super("There is an invalid number in the expression.");

    }

}
