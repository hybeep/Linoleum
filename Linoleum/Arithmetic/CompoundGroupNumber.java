package Arithmetic;

import java.util.ArrayList;

public abstract class CompoundGroupNumber<T extends GroupNumber> implements CompoundSubtractable<T> {
    
    @Override
    public abstract CompoundGroupNumber<T> plus(CompoundSummable<T> b);

    @Override
    public abstract CompoundGroupNumber<T> plus(ArrayList<CompoundSummable<T>> l);

    @Override
    public abstract CompoundGroupNumber<T> zero();

    @Override
    public abstract CompoundGroupNumber<T> negative();

    public abstract CompoundGroupNumber<T> minus(CompoundSubtractable<T> b);

    public abstract CompoundGroupNumber<T> times(int n);

    @Override
    final public void print() {

        System.out.println(format());

    }

}
