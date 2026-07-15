package Arithmetic;

import java.util.ArrayList;

public abstract class CompoundDivisionRingNumber implements CompoundInvertible<DivisionRingNumber> {

    @Override
    public abstract CompoundDivisionRingNumber plus(CompoundSummable<DivisionRingNumber> b);

    @Override
    public abstract CompoundDivisionRingNumber plus(ArrayList<CompoundSummable<DivisionRingNumber>> l);

    @Override
    public abstract CompoundDivisionRingNumber zero();

    @Override
    public abstract CompoundDivisionRingNumber negative();

    public abstract CompoundDivisionRingNumber minus(CompoundSubtractable<DivisionRingNumber> b);

    public abstract CompoundDivisionRingNumber times(int n);

    @Override
    public abstract CompoundDivisionRingNumber times(CompoundMultipliable<DivisionRingNumber> b);
    
    @Override
    public abstract CompoundDivisionRingNumber times(ArrayList<CompoundMultipliable<DivisionRingNumber>> l);

    @Override
    public abstract CompoundDivisionRingNumber identity();

    @Override
    public abstract CompoundDivisionRingNumber inverse();

    public abstract CompoundDivisionRingNumber div(CompoundInvertible<DivisionRingNumber> b);

    public abstract CompoundDivisionRingNumber pow(int n);

    @Override
    public abstract ArrayList<DivisionRingNumber> entries();

    @Override
    final public void print() {

        System.out.println(format());

    }
    
}
