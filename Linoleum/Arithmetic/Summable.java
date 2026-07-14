package Arithmetic;

import java.util.ArrayList;

public interface Summable extends Element {

    Summable plus(Summable b);
    Summable plus(ArrayList<Summable> l);
    
}
