package Arithmetic;

import java.util.ArrayList;

public interface Invertible extends Identity {
    
    @Override
    Invertible plus(Summable b);

    @Override
    Invertible plus(ArrayList<Summable> l);

    @Override
    Invertible zero();

    @Override
    Invertible negative();

    @Override
    Invertible minus(Subtractable b);

    @Override
    Invertible times(int n);

    @Override
    Invertible times(Multipliable b);

    @Override
    Invertible times(ArrayList<Multipliable> l);

    @Override
    Invertible identity();

    Invertible inverse();
    Invertible div(Invertible b);
    Invertible pow(int n);

}
