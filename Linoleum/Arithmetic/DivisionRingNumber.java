package Arithmetic;


public abstract class DivisionRingNumber extends RingIdentityNumber {


    @Override
    public abstract DivisionRingNumber zero();


    @Override
    public abstract DivisionRingNumber plus(GroupNumber s);


    @Override
    public abstract DivisionRingNumber negative();


    @Override
    final public DivisionRingNumber minus(GroupNumber m) {
        
        return plus(m.negative());
    
    }
    

    @Override
    final public DivisionRingNumber times(Long n) {

        DivisionRingNumber bs;

        if (n == 0L) {

            bs = zero();
            return bs;

        } else if (n < 0L) {

            bs = this.negative();
            n = -n;

        } else {

            bs = this;

        }

        DivisionRingNumber prod = bs;
        int i;
        for (i = 1; i < n; i++)
            prod = prod.plus(bs);

        return prod;

    }


    @Override
    public abstract DivisionRingNumber identity();


    @Override
    public abstract DivisionRingNumber times(RingNumber m);


    abstract public DivisionRingNumber inverse();


    final public DivisionRingNumber div(DivisionRingNumber r) {

        return times(r.inverse());

    }


    final public DivisionRingNumber pow(Long n) {

        
        DivisionRingNumber bs;

        if (n == 0L) {

            bs = identity();
            return bs;

        } else if (n < 0L) {

            bs = this.inverse();
            n = -n;

        } else {

            bs = this;

        }

        DivisionRingNumber prod = bs;
        int i;
        for (i = 1; i < n; i++)
            prod = prod.times(bs);

        return prod;

    }


    public static DivisionRingNumber div(DivisionRingNumber a, DivisionRingNumber b) {

        return a.times(b.inverse());

    }

    
}
