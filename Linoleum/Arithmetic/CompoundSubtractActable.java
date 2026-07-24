package Arithmetic;

import java.util.ArrayList;

public interface CompoundSubtractActable<T extends Subtractable> extends CompoundSubtractable<T> {
    
    @Override
    CompoundSubtractActable<T> plus(CompoundSummable<T> b);

    @Override
    CompoundSubtractActable<T> plus(ArrayList<CompoundSummable<T>> l);

    @Override
    CompoundSubtractActable<T> zero();

    @Override
    CompoundSubtractActable<T> negative();

    @Override
    CompoundSubtractActable<T> minus(CompoundSubtractable<T> b);

    @Override
    CompoundSubtractActable<T> times(int n);

    CompoundSubtractActable<T> action(Multipliable b);

}
