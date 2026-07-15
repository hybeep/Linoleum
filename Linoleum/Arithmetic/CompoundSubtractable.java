package Arithmetic;

public interface CompoundSubtractable<T extends Subtractable> extends CompoundZero<T> {

    CompoundSubtractable<T> negative();

}
