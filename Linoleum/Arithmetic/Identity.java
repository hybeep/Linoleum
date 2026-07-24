package Arithmetic;

import java.util.ArrayList;

public interface Identity extends Multipliable {
    
    @Override
    Identity plus(Summable b);

    @Override
    Identity plus(ArrayList<Summable> l);

    @Override
    Identity zero();

    @Override
    Identity negative();

    @Override
    Identity minus(Subtractable b);

    @Override
    Identity times(int n);

    @Override
    Identity times(Multipliable b);

    @Override
    Identity times(ArrayList<Multipliable> l);

    Identity identity();

}
