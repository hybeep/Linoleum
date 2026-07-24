package Arithmetic;

import java.util.ArrayList;

public interface CompoundIdentity<T extends Identity> extends CompoundMultipliable<T> {

    @Override
    CompoundIdentity<T> plus(CompoundSummable<T> b);

    @Override
    CompoundIdentity<T> plus(ArrayList<CompoundSummable<T>> l);

    @Override
    CompoundIdentity<T> zero();

    @Override
    CompoundIdentity<T> negative();

    @Override
    CompoundIdentity<T> minus(CompoundSubtractable<T> b);

    @Override
    CompoundIdentity<T> times(int n);

    @Override
    CompoundIdentity<T> times(CompoundMultipliable<T> b);

    @Override
    CompoundIdentity<T> times(ArrayList<CompoundMultipliable<T>> l);

    CompoundIdentity<T> identity();
    
}
