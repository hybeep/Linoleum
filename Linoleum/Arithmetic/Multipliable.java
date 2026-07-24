package Arithmetic;

import java.util.ArrayList;

public interface Multipliable extends Subtractable {

    @Override
    Multipliable plus(Summable b);

    @Override
    Multipliable plus(ArrayList<Summable> l);

    @Override
    Multipliable zero();

    @Override
    Multipliable negative();

    @Override
    Multipliable minus(Subtractable b);

    @Override
    Multipliable times(int n);

    Multipliable times(Multipliable b);
    Multipliable times(ArrayList<Multipliable> l);
    
}
