package Arithmetic;

import java.util.ArrayList;

public interface CompoundMultipliable<T extends Multipliable> extends CompoundElement<T> {

    CompoundMultipliable<T> times(CompoundMultipliable<T> b);
    CompoundMultipliable<T> times(ArrayList<CompoundMultipliable<T>> l);
    
}
