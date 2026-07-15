package Arithmetic;

import java.util.ArrayList;

public interface Multipliable extends Subtractable {

    Multipliable times(Multipliable b);
    Multipliable times(ArrayList<Multipliable> l);
    
}
