package Arithmetic;

import java.util.ArrayList;

public interface CompoundMultiplyActable<T extends Multipliable> extends CompoundMultipliable<T> {
    
    @Override
    CompoundMultiplyActable<T> plus(CompoundSummable<T> b);

    @Override
    CompoundMultiplyActable<T> plus(ArrayList<CompoundSummable<T>> l);

    @Override
    CompoundMultiplyActable<T> zero();

    @Override
    CompoundMultiplyActable<T> negative();

    @Override
    CompoundMultiplyActable<T> minus(CompoundSubtractable<T> b);

    @Override
    CompoundMultiplyActable<T> times(int n);

    @Override
    CompoundMultiplyActable<T> times(CompoundMultipliable<T> b);

    @Override
    CompoundMultiplyActable<T> times(ArrayList<CompoundMultipliable<T>> l);

    CompoundMultiplyActable<T> action(Multipliable b);

}
