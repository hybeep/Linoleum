package Arithmetic;

import java.util.ArrayList;

public abstract class CompoundIdentityRingNumber implements CompoundIdentity<IdentityRingNumber> {
    
    @Override
    public abstract CompoundIdentityRingNumber plus(CompoundSummable<IdentityRingNumber> b);

    @Override
    public abstract CompoundIdentityRingNumber plus(ArrayList<CompoundSummable<IdentityRingNumber>> l);

    @Override
    public abstract CompoundIdentityRingNumber zero();

    @Override
    public abstract CompoundIdentityRingNumber negative();

    public abstract CompoundIdentityRingNumber minus(CompoundSubtractable<IdentityRingNumber> b);

    public abstract CompoundIdentityRingNumber times(int n);

    @Override
    public abstract CompoundIdentityRingNumber times(CompoundMultipliable<IdentityRingNumber> b);
    
    @Override
    public abstract CompoundIdentityRingNumber times(ArrayList<CompoundMultipliable<IdentityRingNumber>> l);

    @Override
    public abstract CompoundIdentityRingNumber identity();

    @Override
    public abstract ArrayList<IdentityRingNumber> entries();

    @Override
    final public void print() {

        System.out.println(format());

    }

}
