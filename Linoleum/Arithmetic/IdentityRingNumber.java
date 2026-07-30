package Arithmetic;

import java.util.ArrayList;

public abstract class IdentityRingNumber implements Identity {

    @Override
    public abstract IdentityRingNumber plus(Summable b);

    @Override
    public IdentityRingNumber plus(ArrayList<Summable> l) {

        IdentityRingNumber sum = this;
        for (Summable num : l)
            sum = sum.plus(num);

        return sum;

    }

    @Override
    public abstract IdentityRingNumber zero();

    @Override
    public abstract IdentityRingNumber negative();

    @Override
    public IdentityRingNumber minus(Subtractable b) {

        return plus(b.negative());

    }

    @Override
    public IdentityRingNumber times(int n) {

        if (n == 0 || isZero())
            return zero();

        IdentityRingNumber prod;
        
        IdentityRingNumber s;
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
    public abstract IdentityRingNumber times(Multipliable b);

    @Override
    public IdentityRingNumber times(ArrayList<Multipliable> l) {

        IdentityRingNumber prod = this;
        for (Multipliable num : l)
            prod = prod.times(num);

        return prod;

    }

    @Override
    public abstract IdentityRingNumber identity();

    @Override
    final public void print() {

        System.out.println(format());

    }

}
