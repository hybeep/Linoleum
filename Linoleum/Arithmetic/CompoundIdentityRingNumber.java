package Arithmetic;

import java.util.ArrayList;

public abstract class CompoundIdentityRingNumber implements CompoundIdentity<Identity> {
    
    @Override
    public abstract CompoundIdentityRingNumber plus(CompoundSummable<Identity> b);

    @Override
    public abstract CompoundIdentityRingNumber plus(ArrayList<CompoundSummable<Identity>> l);

    @Override
    public abstract CompoundIdentityRingNumber zero();

    @Override
    public abstract CompoundIdentityRingNumber negative();

    public abstract CompoundIdentityRingNumber minus(CompoundSubtractable<Identity> b);

    public abstract CompoundIdentityRingNumber times(int n);

    @Override
    public abstract CompoundIdentityRingNumber times(CompoundMultipliable<Identity> b);
    
    @Override
    public abstract CompoundIdentityRingNumber times(ArrayList<CompoundMultipliable<Identity>> l);

    @Override
    public abstract CompoundIdentityRingNumber identity();

    @Override
    public abstract ArrayList<Identity> entries();

    @Override
    final public void print() {

        System.out.println(format());

    }

}
