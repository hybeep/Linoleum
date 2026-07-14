package Arithmetic;

import java.util.ArrayList;

public interface Element {

    TYPE type();
    Number A();
    Number B();
    Number C();

    ArrayList<Number> extended_data();

    String format();
    void print();
    
}
