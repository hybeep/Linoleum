package Arithmetic;

import java.util.ArrayList;

public abstract class CompoundAlgebraNumber implements CompoundMultiplyActable<Multipliable> {
    
    @Override
    public abstract CompoundAlgebraNumber plus(CompoundSummable<Multipliable> b);

    @Override
    public abstract CompoundAlgebraNumber plus(ArrayList<CompoundSummable<Multipliable>> l);

    @Override
    public abstract CompoundAlgebraNumber zero();

    @Override
    public abstract CompoundAlgebraNumber negative();

    @Override
    public abstract CompoundAlgebraNumber minus(CompoundSubtractable<Multipliable> b);

    @Override
    public abstract CompoundAlgebraNumber times(int n);

    @Override
    public abstract CompoundAlgebraNumber times(CompoundMultipliable<Multipliable> b);

    @Override
    public abstract CompoundAlgebraNumber times(ArrayList<CompoundMultipliable<Multipliable>> l);

    @Override
    public abstract CompoundAlgebraNumber action(Multipliable b);

    @Override
    public abstract ArrayList<Multipliable> entries();

    @Override
    final public void print() {

        System.out.println(format());

    }

}
