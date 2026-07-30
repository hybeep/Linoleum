package Arithmetic;

import java.util.ArrayList;

public abstract class DivisionRingNumber implements Invertible {

    @Override
    public abstract DivisionRingNumber plus(Summable b);

    @Override
    public DivisionRingNumber plus(ArrayList<Summable> l) {

        DivisionRingNumber sum = this;
        for (Summable num : l)
            sum = sum.plus(num);

        return sum;

    }

    @Override
    public abstract DivisionRingNumber zero();

    @Override
    public abstract DivisionRingNumber negative();

    @Override
    public DivisionRingNumber minus(Subtractable b) {

        return plus(b.negative());

    }

    @Override
    public DivisionRingNumber times(int n) {

        if (n == 0 || isZero())
            return zero();

        DivisionRingNumber prod;
        
        DivisionRingNumber s;
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
    public abstract DivisionRingNumber times(Multipliable b);

    @Override
    public DivisionRingNumber times(ArrayList<Multipliable> l) {

        DivisionRingNumber prod = this;
        for (Multipliable num : l)
            prod = prod.times(num);

        return prod;

    }

    @Override
    public abstract DivisionRingNumber identity();

    @Override
    public abstract DivisionRingNumber inverse();

    @Override
    public DivisionRingNumber div(Invertible b) {

        return times(b.inverse());

    }

    @Override
    public DivisionRingNumber pow(int n) {

        if (isZero())
            if (n > 0)
                return zero();
            else
                throw new DivideByZeroException();

        if (n == 0 || isIdentity())
            return identity();

        DivisionRingNumber pow;
        
        DivisionRingNumber s;
        if (n > 0) {

            s = this;

        } else {

            s = this.inverse();
            n = -n;

        }

        pow = s;

        int i;
        for (i = 1; i < n; i++)
            pow = pow.times(s);

        return pow;

    }

    @Override
    final public void print() {

        System.out.println(format());

    } 
    
}
