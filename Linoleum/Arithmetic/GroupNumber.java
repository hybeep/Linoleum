package Arithmetic;

import java.util.ArrayList;

public abstract class GroupNumber implements Subtractable {
   
    @Override
    public abstract GroupNumber plus(Summable b);

    @Override
    public GroupNumber plus(ArrayList<Summable> l) {

        GroupNumber sum = this;
        for (Summable num : l)
            sum = sum.plus(num);

        return sum;

    }

    @Override
    public abstract GroupNumber zero();

    @Override
    public abstract GroupNumber negative();

    @Override
    public GroupNumber minus(Subtractable b) {

        return plus(b.negative());

    }

    @Override
    public GroupNumber times(int n) {

        if (n == 0 || isZero())
            return zero();

        GroupNumber prod;
        
        GroupNumber s;
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
    final public void print() {

        System.out.println(format());

    }
 
}
