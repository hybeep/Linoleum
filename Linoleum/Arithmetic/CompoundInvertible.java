package Arithmetic;

public interface CompoundInvertible<T extends Invertible> extends CompoundIdentity<T> {
    
    CompoundInvertible<T> inverse();

}
