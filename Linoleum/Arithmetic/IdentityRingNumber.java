package Arithmetic;

import java.util.ArrayList;

public abstract class IdentityRingNumber extends RingNumber implements Identity {

    @Override
    public abstract IdentityRingNumber plus(Summable b);

    @Override
    public abstract IdentityRingNumber plus(ArrayList<Summable> l);

    @Override
    public abstract IdentityRingNumber zero();

    @Override
    public abstract IdentityRingNumber negative();

    @Override
    public abstract IdentityRingNumber minus(Subtractable b);

    @Override
    public abstract IdentityRingNumber times(int n);

    @Override
    public abstract IdentityRingNumber times(Multipliable b);

    @Override
    public abstract IdentityRingNumber times(ArrayList<Multipliable> l);

    @Override
    public abstract IdentityRingNumber identity();

}
