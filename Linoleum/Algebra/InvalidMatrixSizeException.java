package Algebra;

public class InvalidMatrixSizeException extends RuntimeException {
    
    public InvalidMatrixSizeException() {

        super("The size of a matrix must be positive.");

    }

}
