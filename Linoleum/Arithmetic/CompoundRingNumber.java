package Arithmetic;

import java.util.ArrayList;

public abstract class CompoundRingNumber<T extends RingNumber> extends CompoundGroupNumber<T> implements CompoundMultipliable<T> {
    
    @Override
    public abstract CompoundRingNumber<T> plus(CompoundSummable<T> b);

    @Override
    public abstract CompoundRingNumber<T> plus(ArrayList<CompoundSummable<T>> l);

    @Override
    public abstract CompoundRingNumber<T> zero();

    @Override
    public abstract CompoundRingNumber<T> negative();

    @Override
    public abstract CompoundRingNumber<T> minus(CompoundSubtractable<T> b);

    @Override
    public abstract CompoundRingNumber<T> times(int n);

    @Override
    public abstract CompoundRingNumber<T> times(CompoundMultipliable<T> b);

    @Override
    public abstract CompoundRingNumber<T> times(ArrayList<CompoundMultipliable<T>> l);

}
