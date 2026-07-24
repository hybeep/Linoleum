package Arithmetic;

import java.util.ArrayList;

public abstract class CompoundModuleNumber implements CompoundSubtractActable<Subtractable> {
    
    @Override
    public abstract CompoundModuleNumber plus(CompoundSummable<Subtractable> b);

    @Override
    public abstract CompoundModuleNumber plus(ArrayList<CompoundSummable<Subtractable>> l);

    @Override
    public abstract CompoundModuleNumber zero();

    @Override
    public abstract CompoundModuleNumber negative();

    @Override
    public abstract CompoundModuleNumber minus(CompoundSubtractable<Subtractable> b);

    @Override
    public abstract CompoundModuleNumber times(int n);

    @Override
    public abstract CompoundModuleNumber action(Multipliable b);

    @Override
    public abstract ArrayList<Subtractable> entries();

    @Override
    final public void print() {

        System.out.println(format());

    }

}
