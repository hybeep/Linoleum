package Arithmetic;

import java.util.ArrayList;

public abstract class CompoundGroupNumber implements CompoundSubtractable<Subtractable> {
    
    @Override
    public abstract CompoundGroupNumber plus(CompoundSummable<Subtractable> b);

    @Override
    public abstract CompoundGroupNumber plus(ArrayList<CompoundSummable<Subtractable>> l);

    @Override
    public abstract CompoundGroupNumber zero();

    @Override
    public abstract CompoundGroupNumber negative();

    @Override
    public abstract CompoundGroupNumber minus(CompoundSubtractable<Subtractable> b);

    @Override
    public abstract CompoundGroupNumber times(int n);

    @Override
    public abstract ArrayList<Subtractable> entries();

    @Override
    final public void print() {

        System.out.println(format());

    }

}
