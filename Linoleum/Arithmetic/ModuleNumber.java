package Arithmetic;


import java.util.ArrayList;


import Arithmetic.GroupNumber.TYPE;


public abstract class ModuleNumber {

    TYPE type;
    MOD_TYPE MOD_TYPE;

    Number A, B, C;
    ArrayList<RingNumber> entries;

    public static enum MOD_TYPE {

        POLYNOMIAL,
        MATRIX

    }


    abstract public ModuleNumber zero();


    abstract public ModuleNumber plus(ModuleNumber s);


    abstract public ModuleNumber negative();


    final public ModuleNumber minus(ModuleNumber m) {
        
        return plus(m.negative());
    
    }
    

    abstract public ModuleNumber times(RingNumber m);


    public ModuleNumber times(Long n) {

        int i;

        ModuleNumber prod = this;

        for (i = 1; i < n; i++)
            prod = prod.plus(this);

        return prod;

    }


    public RingNumber pow(Long n) {

        int i;

        RingNumber pow = this;

        for (i = 1; i < n; i++)
            pow = pow.times(this);

        return pow;

    }


    abstract public boolean isZero();


    public static RingNumber sum(RingNumber a, RingNumber b) {

        return a.plus(b);

    }


    public static RingNumber sum(ArrayList<RingNumber> nums) {

        Iterator<RingNumber> it = nums.iterator();

        RingNumber sum;

        if (it.hasNext())
            sum = it.next();
        else
            throw new Error();


        while (it.hasNext())
            sum = sum.plus(it.next());

        return sum;

    }


    public static RingNumber mult(RingNumber a, RingNumber b) {

        return a.times(b);

    }


    public static RingNumber mult(ArrayList<RingNumber> nums) {

        Iterator<RingNumber> it = nums.iterator();

        RingNumber mult;

        if (it.hasNext())
            mult = it.next();
        else
            throw new Error();

        while (it.hasNext())
            mult = mult.times(it.next());

        return mult;

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


    final public TYPE_CODE getType() {

        return TYPE;

    }


    
}
