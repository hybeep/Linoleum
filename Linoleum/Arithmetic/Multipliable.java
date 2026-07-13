package Arithmetic;

import java.util.ArrayList;

public interface Multipliable extends Element {

    public Multipliable times(Multipliable b);
    public Multipliable times(ArrayList<Multipliable> l);
    
}
