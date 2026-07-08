package Arithmetic;


import java.util.ArrayList;
import java.util.Iterator;


public abstract class RingNumber {


    TYPE_CODE TYPE;

    Number A, B, C;


    public static enum TYPE_CODE {

        INTEGER,
        MODN,
        RATIONAL,
        REAL,
        COMPLEX

    }


    abstract public RingNumber zero();


    abstract public RingNumber plus(RingNumber s);


    abstract public RingNumber negative();


    final public RingNumber minus(RingNumber m) {
        
        return plus(m.negative());
    
    }
    

    abstract public RingNumber times(RingNumber m);


    public RingNumber times(Long n) {

        int i;

        RingNumber prod = this;

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
