package Arithmetic;

public interface CompoundElement {
    
    TYPE type();

    Number A();
    Number B();
    Number C();

    COMPOUND_TYPE compound_type();

    public String format();
    public void print();

}
