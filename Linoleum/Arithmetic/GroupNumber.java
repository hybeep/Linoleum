package Arithmetic;

import java.util.ArrayList;

public abstract class GroupNumber implements Subtractable {
   
    @Override
    public abstract GroupNumber plus(Summable b);

    @Override
    public abstract GroupNumber plus(ArrayList<Summable> b);

    @Override
    public abstract GroupNumber zero();

    @Override
    public abstract GroupNumber negative();

    @Override
    public abstract GroupNumber minus(Subtractable b);

    @Override
    public abstract GroupNumber times(int n);

    @Override
    final public void print() {

        System.out.println(format());

    }
 
}
