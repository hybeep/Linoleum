package Arithmetic;


public abstract class DivisionRingNumber extends RingIdentityNumber {


    abstract public RingNumber inverse();


    final public RingNumber div(DivisionRingNumber r) {

        return times(r.inverse());

    }


    public static RingNumber div(DivisionRingNumber a, DivisionRingNumber b) {

        return a.times(b.inverse());

    }

    
}
