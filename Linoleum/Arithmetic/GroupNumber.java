package Arithmetic;


import java.util.ArrayList;
import java.util.Iterator;


public abstract class GroupNumber {


    TYPE type;
    Number A, B, C;


    public static enum TYPE {

        INTEGER,
        MODN,
        RATIONAL,
        REAL,
        COMPLEX

    }


    public abstract GroupNumber zero();


    public abstract GroupNumber plus(GroupNumber s);


    public abstract GroupNumber negative();


    public GroupNumber minus(GroupNumber m) {
        
        return plus(m.negative());
    
    }


    public GroupNumber times(int n) {

        GroupNumber bs;

        if (n == 0L) {

            bs = zero();
            return bs;

        } else if (n < 0L) {

            bs = this.negative();
            n = -n;

        } else {

            bs = this;

        }

        GroupNumber prod = bs;
        int i;
        for (i = 1; i < n; i++)
            prod = prod.plus(bs);

        return prod;

    }


    public abstract boolean isZero();


    public static GroupNumber sum(GroupNumber a, GroupNumber b) {

        return a.plus(b);

    }


    public static GroupNumber sum(ArrayList<GroupNumber> nums) {

        Iterator<GroupNumber> it = nums.iterator();

        GroupNumber sum;

        if (it.hasNext())
            sum = it.next();
        else
            throw new EmptyArrayException();


        while (it.hasNext())
            sum = sum.plus(it.next());

        return sum;

    }


    abstract public String format();


    final public void print() {

        System.out.println(format());

    }


    final public Z asZ() {

        return new Z(this);

    }


    final public Q asQ() {

        return new Q(this);

    }


    final public R asR() {

        return new R(this);

    }


    final public C asC() {

        return new C(this);

    }


    final public Zn asZn() {

        return new Zn(this);

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
    

}
