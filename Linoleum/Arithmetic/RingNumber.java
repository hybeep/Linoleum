package Arithmetic;


import java.util.ArrayList;
import java.util.Iterator;


public abstract class RingNumber extends GroupNumber {


    @Override
    public abstract RingNumber zero();


    @Override
    public abstract RingNumber plus(GroupNumber s);


    @Override
    public abstract RingNumber negative();


    @Override
    public RingNumber minus(GroupNumber m) {
        
        return plus(m.negative());
    
    }
    

    @Override
    public RingNumber times(int n) {

        RingNumber bs;

        if (n == 0L) {

            bs = zero();
            return bs;

        } else if (n < 0L) {

            bs = this.negative();
            n = -n;

        } else {

            bs = this;

        }

        RingNumber prod = bs;
        int i;
        for (i = 1; i < n; i++)
            prod = prod.plus(bs);

        return prod;

    }


    public abstract RingNumber times(RingNumber m);


    public static RingNumber mult(RingNumber a, RingNumber b) {

        return a.times(b);

    }


    public static RingNumber mult(ArrayList<RingNumber> nums) {

        Iterator<RingNumber> it = nums.iterator();

        RingNumber mult;

        if (it.hasNext())
            mult = it.next();
        else
            throw new EmptyArrayException();

        while (it.hasNext())
            mult = mult.times(it.next());

        return mult;

    }


}
