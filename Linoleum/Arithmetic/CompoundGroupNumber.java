package Arithmetic;

import java.util.ArrayList;

public abstract class CompoundGroupNumber implements CompoundGroupElement, Subtractable {
    
    @Override
    public abstract CompoundGroupNumber plus(Summable b);

    @Override
    public abstract CompoundGroupNumber plus(ArrayList<Summable> l);

    @Override
    public abstract CompoundGroupNumber zero();

    @Override
    public abstract CompoundGroupNumber negative();

    public abstract CompoundGroupNumber minus(CompoundGroupNumber b);

    public abstract CompoundGroupNumber times(int n);

    @Override
    final public void print() {

        System.out.println(format());

    }

}
