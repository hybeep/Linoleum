package Arithmetic;

import java.util.ArrayList;

public interface CompoundZero<T extends Zero> extends CompoundSummable<T> {
    
    @Override
    CompoundZero<T> plus(CompoundSummable<T> b);

    @Override
    CompoundZero<T> plus(ArrayList<CompoundSummable<T>> l);

    CompoundZero<T> zero();
    boolean isZero();

}
