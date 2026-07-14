package Arithmetic;

import java.util.ArrayList;

public interface CompoundElement<T extends Element> {

    TYPE type();
    Number A();
    Number B();

    COMPOUND_TYPE compound_type();
    ArrayList<T> entries();

    String format();
    void print();
    
}
