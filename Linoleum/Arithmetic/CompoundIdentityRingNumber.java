package Arithmetic;

import java.util.ArrayList;

public abstract class CompoundIdentityRingNumber<T extends IdentityRingNumber> extends CompoundRingNumber<T> implements CompoundIdentity<T> {
    
    @Override
    public abstract CompoundIdentityRingNumber<T> plus(CompoundSummable<T> b);

    @Override
    public abstract CompoundIdentityRingNumber<T> plus(ArrayList<CompoundSummable<T>> l);

    @Override
    public abstract CompoundIdentityRingNumber<T> zero();

    @Override
    public abstract CompoundIdentityRingNumber<T> negative();

    @Override
    public abstract CompoundIdentityRingNumber<T> minus(CompoundSubtractable<T> b);

    @Override
    public abstract CompoundIdentityRingNumber<T> times(int n);

    @Override
    public abstract CompoundIdentityRingNumber<T> times(CompoundMultipliable<T> b);
    
    @Override
    public abstract CompoundIdentityRingNumber<T> times(ArrayList<CompoundMultipliable<T>> l);

    @Override
    public abstract CompoundIdentityRingNumber<T> identity();

}
