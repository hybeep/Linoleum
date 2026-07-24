package Arithmetic;

import java.util.ArrayList;

public interface Zero extends Summable {

    @Override
    Zero plus(Summable b);

    @Override
    Zero plus(ArrayList<Summable> l);

    Zero zero();
    boolean isZero();
    
}
