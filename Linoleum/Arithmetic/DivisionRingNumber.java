package Arithmetic;

import java.util.ArrayList;

public abstract class DivisionRingNumber extends IdentityRingNumber implements Invertible {

    @Override
    public abstract DivisionRingNumber plus(Summable b);

    @Override
    public abstract DivisionRingNumber plus(ArrayList<Summable> l);

    @Override
    public abstract DivisionRingNumber zero();

    @Override
    public abstract DivisionRingNumber negative();

    @Override
    public abstract DivisionRingNumber minus(Subtractable b);

    @Override
    public abstract DivisionRingNumber times(int n);

    @Override
    public abstract DivisionRingNumber times(Multipliable b);

    @Override
    public abstract DivisionRingNumber times(ArrayList<Multipliable> l);

    @Override
    public abstract DivisionRingNumber identity();

    @Override
    public abstract DivisionRingNumber inverse();

    public abstract DivisionRingNumber div(Invertible b);

    public abstract DivisionRingNumber pow(int n);
    
}
