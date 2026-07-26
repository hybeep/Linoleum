package Arithmetic;

import java.util.ArrayList;

public abstract class CompoundRingNumber implements CompoundMultipliable<Multipliable> {
    
    @Override
    public abstract CompoundRingNumber plus(CompoundSummable<Multipliable> b);

    @Override
    public abstract CompoundRingNumber plus(ArrayList<CompoundSummable<Multipliable>> l);

    @Override
    public abstract CompoundRingNumber zero();

    @Override
    public abstract CompoundRingNumber negative();

    public abstract CompoundRingNumber minus(CompoundSubtractable<Multipliable> b);

    public abstract CompoundRingNumber times(int n);

    @Override
    public abstract CompoundRingNumber times(CompoundMultipliable<Multipliable> b);

    @Override
    public abstract CompoundRingNumber times(ArrayList<CompoundMultipliable<Multipliable>> l);

    @Override
    public abstract ArrayList<Multipliable> entries();

    @Override
    final public void print() {

        System.out.println(format());

    }
    
}
