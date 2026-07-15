package Arithmetic;

import java.util.ArrayList;

public abstract class CompoundGroupNumber implements CompoundSubtractable<GroupNumber> {
    
    @Override
    public abstract CompoundGroupNumber plus(CompoundSummable<GroupNumber> b);

    @Override
    public abstract CompoundGroupNumber plus(ArrayList<CompoundSummable<GroupNumber>> l);

    @Override
    public abstract CompoundGroupNumber zero();

    @Override
    public abstract CompoundGroupNumber negative();

    public abstract CompoundGroupNumber minus(CompoundSubtractable<GroupNumber> b);

    public abstract CompoundGroupNumber times(int n);

    @Override
    public abstract ArrayList<GroupNumber> entries();

    @Override
    final public void print() {

        System.out.println(format());

    }

}
