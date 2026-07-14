package Arithmetic;

import java.util.ArrayList;

public interface Multipliable extends Element {

    Multipliable times(Multipliable b);
    Multipliable times(ArrayList<Multipliable> l);
    
}
