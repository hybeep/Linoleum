package Arithmetic;

import java.util.ArrayList;

public interface CompoundMultipliable<T extends Multipliable> extends CompoundSubtractable<T> {

    @Override
    CompoundMultipliable<T> plus(CompoundSummable<T> b);

    @Override
    CompoundMultipliable<T> plus(ArrayList<CompoundSummable<T>> l);

    @Override
    CompoundMultipliable<T> zero();

    @Override
    CompoundMultipliable<T> negative();

    @Override
    CompoundMultipliable<T> minus(CompoundSubtractable<T> b);

    @Override
    CompoundMultipliable<T> times(int n);

    CompoundMultipliable<T> times(CompoundMultipliable<T> b);
    CompoundMultipliable<T> times(ArrayList<CompoundMultipliable<T>> l);
    
}
