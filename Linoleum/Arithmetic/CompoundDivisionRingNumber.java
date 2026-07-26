package Arithmetic;

import java.util.ArrayList;

public abstract class CompoundDivisionRingNumber implements CompoundInvertible<Invertible> {

    @Override
    public abstract CompoundDivisionRingNumber plus(CompoundSummable<Invertible> b);

    @Override
    public abstract CompoundDivisionRingNumber plus(ArrayList<CompoundSummable<Invertible>> l);

    @Override
    public abstract CompoundDivisionRingNumber zero();

    @Override
    public abstract CompoundDivisionRingNumber negative();

    public abstract CompoundDivisionRingNumber minus(CompoundSubtractable<Invertible> b);

    public abstract CompoundDivisionRingNumber times(int n);

    @Override
    public abstract CompoundDivisionRingNumber times(CompoundMultipliable<Invertible> b);
    
    @Override
    public abstract CompoundDivisionRingNumber times(ArrayList<CompoundMultipliable<Invertible>> l);

    @Override
    public abstract CompoundDivisionRingNumber identity();

    @Override
    public abstract CompoundDivisionRingNumber inverse();

    public abstract CompoundDivisionRingNumber div(CompoundInvertible<Invertible> b);

    public abstract CompoundDivisionRingNumber pow(int n);

    @Override
    public abstract ArrayList<Invertible> entries();

    @Override
    final public void print() {

        System.out.println(format());

    }
    
}
