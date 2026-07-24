package Arithmetic;

import java.util.ArrayList;

public interface CompoundInvertible<T extends Invertible> extends CompoundIdentity<T> {
    
    @Override
    CompoundInvertible<T> plus(CompoundSummable<T> b);

    @Override
    CompoundInvertible<T> plus(ArrayList<CompoundSummable<T>> l);

    @Override
    CompoundInvertible<T> zero();

    @Override
    CompoundInvertible<T> negative();

    @Override
    CompoundInvertible<T> minus(CompoundSubtractable<T> b);

    @Override
    CompoundInvertible<T> times(int n);

    @Override
    CompoundInvertible<T> times(CompoundMultipliable<T> b);

    @Override
    CompoundInvertible<T> times(ArrayList<CompoundMultipliable<T>> l);

    @Override
    CompoundInvertible<T> identity();

    CompoundInvertible<T> inverse();
    CompoundInvertible<T> div(CompoundInvertible<T> b);
    CompoundInvertible<T> pow(int n);

}
