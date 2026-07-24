package Arithmetic;

import java.util.ArrayList;

public interface Subtractable extends Zero {

    @Override
    Subtractable plus(Summable b);

    @Override
    Subtractable plus(ArrayList<Summable> l);

    @Override
    Subtractable zero();

    Subtractable negative();

    Subtractable minus(Subtractable b);

    Subtractable times(int n);

    
    
}
