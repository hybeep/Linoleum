package Arithmetic;

import java.util.ArrayList;
import java.util.Iterator;

public abstract class ModuleNumber implements CompoundSubtractActable<GroupNumber> {


    TYPE type;
    MOD_TYPE mod_type;


    Number A, B, C;
    ArrayList<RingNumber> entries;

    
    public static enum MOD_TYPE {

        POLYNOMIAL,
        VECTOR,
        MATRIX

    }


    abstract public ModuleNumber zero();


    abstract public ModuleNumber plus(ModuleNumber s);


    abstract public ModuleNumber negative();


    public ModuleNumber minus(ModuleNumber m) {
        
        return plus(m.negative());
    
    }


    public ModuleNumber times(Long n) {

        int i;

        ModuleNumber prod = this;

        for (i = 1; i < n; i++)
            prod = prod.plus(this);

        return prod;

    }
    

    abstract public ModuleNumber times(RingNumber m);


    abstract public boolean isZero();


    public static ModuleNumber sum(ModuleNumber a, ModuleNumber b) {

        return a.plus(b);

    }


    public static ModuleNumber sum(ArrayList<ModuleNumber> nums) {

        Iterator<ModuleNumber> it = nums.iterator();

        ModuleNumber sum;

        if (it.hasNext())
            sum = it.next();
        else
            throw new Error();


        while (it.hasNext())
            sum = sum.plus(it.next());

        return sum;

    }


    public static ModuleNumber mult(ModuleNumber a, RingNumber b) {

        return a.times(b);

    }


    abstract public String format();


    final public void print() {

        System.out.println(format());

    }


    final public Number getA() {

        return A;

    }


    final public Number getB() {

        return B;

    }


    final public Number getC() {

        return C;

    }


    final public TYPE getType() {

        return type;

    }


    final public MOD_TYPE getModType() {

        return mod_type;

    }
    
    
}
