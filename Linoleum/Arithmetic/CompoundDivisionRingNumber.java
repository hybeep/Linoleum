package Arithmetic;

import java.util.ArrayList;

public abstract class CompoundDivisionRingNumber<T extends DivisionRingNumber> extends CompoundIdentityRingNumber<T> implements CompoundInvertible<T> {

    @Override
    public abstract CompoundDivisionRingNumber<T> plus(CompoundSummable<T> b);

    @Override
    public abstract CompoundDivisionRingNumber<T> plus(ArrayList<CompoundSummable<T>> l);

    @Override
    public abstract CompoundDivisionRingNumber<T> zero();

    @Override
    public abstract CompoundDivisionRingNumber<T> negative();

    @Override
    public abstract CompoundDivisionRingNumber<T> minus(CompoundSubtractable<T> b);

    @Override
    public abstract CompoundDivisionRingNumber<T> times(int n);

    @Override
    public abstract CompoundDivisionRingNumber<T> times(CompoundMultipliable<T> b);
    
    @Override
    public abstract CompoundDivisionRingNumber<T> times(ArrayList<CompoundMultipliable<T>> l);

    @Override
    public abstract CompoundDivisionRingNumber<T> identity();

    @Override
    public abstract CompoundDivisionRingNumber<T> inverse();

    public abstract CompoundDivisionRingNumber<T> div(CompoundInvertible<T> b);

    public abstract CompoundDivisionRingNumber<T> pow(int n);
    
}
