package Arithmetic;

public interface CompoundZero<T extends Zero> extends CompoundSummable<T> {
    
    CompoundZero<T> zero();
    boolean isZero();


}
