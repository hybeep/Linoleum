package Arithmetic;

import java.util.ArrayList;

public interface CompoundSubtractable<T extends Subtractable> extends CompoundZero<T> {

    @Override
    CompoundSubtractable<T> plus(CompoundSummable<T> b);

    @Override
    CompoundSubtractable<T> plus(ArrayList<CompoundSummable<T>> l);

    @Override
    CompoundSubtractable<T> zero();

    CompoundSubtractable<T> negative();
    CompoundSubtractable<T> minus(CompoundSubtractable<T> b);
    CompoundSubtractable<T> times(int n);

}
