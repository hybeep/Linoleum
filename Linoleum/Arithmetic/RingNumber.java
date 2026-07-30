package Arithmetic;

import java.util.ArrayList;

public abstract class RingNumber implements Multipliable {

    @Override
    public abstract RingNumber plus(Summable b);

    @Override
    public RingNumber plus(ArrayList<Summable> l) {

        RingNumber sum = this;
        for (Summable num : l)
            sum = sum.plus(num);

        return sum;

    }

    @Override
    public abstract RingNumber zero();

    @Override
    public abstract RingNumber negative();

    @Override
    public RingNumber minus(Subtractable b) {

        return plus(b.negative());

    }

    @Override
    public RingNumber times(int n) {

        if (n == 0 || isZero())
            return zero();

        RingNumber prod;
        
        RingNumber s;
        if (n > 0) {

            s = this;

        } else {

            s = this.negative();
            n = -n;

        }

        prod = s;

        int i;
        for (i = 1; i < n; i++)
            prod = prod.plus(s);

        return prod;

    }

    @Override
    public abstract RingNumber times(Multipliable b);

    @Override
    public RingNumber times(ArrayList<Multipliable> l) {

        RingNumber prod = this;
        for (Multipliable num : l)
            prod = prod.times(num);

        return prod;

    }

    @Override
    final public void print() {

        System.out.println(format());

    } 

}
