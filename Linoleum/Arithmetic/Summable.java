package Arithmetic;

import java.util.ArrayList;

public interface Summable extends Element {

    public Summable plus(Summable b);
    public Summable plus(ArrayList<Summable> l);
    
}
