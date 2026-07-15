package Arithmetic;

import java.util.ArrayList;

public abstract class CompoundRingNumber implements CompoundMultipliable<RingNumber> {
    
    @Override
    public abstract CompoundRingNumber plus(CompoundSummable<RingNumber> b);

    @Override
    public abstract CompoundRingNumber plus(ArrayList<CompoundSummable<RingNumber>> l);

    @Override
    public abstract CompoundRingNumber zero();

    @Override
    public abstract CompoundRingNumber negative();

    public abstract CompoundRingNumber minus(CompoundSubtractable<RingNumber> b);

    public abstract CompoundRingNumber times(int n);

    @Override
    public abstract CompoundRingNumber times(CompoundMultipliable<RingNumber> b);

    @Override
    public abstract CompoundRingNumber times(ArrayList<CompoundMultipliable<RingNumber>> l);

    @Override
    public abstract ArrayList<RingNumber> entries();

    @Override
    final public void print() {

        System.out.println(format());

    }
    
}
