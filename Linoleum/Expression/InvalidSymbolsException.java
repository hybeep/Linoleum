package Expression;

public class InvalidSymbolsException extends RuntimeException {
    
    public InvalidSymbolsException() {

        super("There are invalid symbols in the expression.");

    }

}
