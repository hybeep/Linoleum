package Arithmetic;

import java.util.ArrayList;

public interface CompoundSummable<T extends Summable> extends CompoundElement<T> {

    CompoundSummable<T> plus(CompoundSummable<T> b);
    CompoundSummable<T> plus(ArrayList<CompoundSummable<T>> l);
    
}
