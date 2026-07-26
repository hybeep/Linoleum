package Arithmetic;

import java.util.ArrayList;
import java.util.Iterator;

final public class RingDirectSum extends CompoundRingNumber {

    private int A;

    private ArrayList<Multipliable> entries;

    private int zero = 0;

    public RingDirectSum(ArrayList<Multipliable> entries) {

        if (entries.size() == 0)
            throw new EmptyArrayException();

        this.A = entries.size();
        this.entries = entries;
        
    }

    public RingDirectSum(RingDirectSum dirSum) {

        this.A = dirSum.A;
        this.entries = dirSum.entries;

    }

    public RingDirectSum(CompoundElement<Multipliable> b) {

        if (b.entries().size() == 0)
            throw new EmptyArrayException();

        this.A = b.entries().size();
        this.entries = b.entries();

    }

    @Override
    public RingDirectSum zero() {
        
        ArrayList<Multipliable> zero = new ArrayList<>();

        for (Multipliable num : entries)
            zero.add(num.zero());

        return new RingDirectSum(zero);

    }

    @Override
    public RingDirectSum plus(CompoundSummable<Multipliable> s) {

        if (s.compound_type() != COMPOUND_TYPE.DIRECTSUM)
            throw new IncompatibleTypesException();

        ArrayList<Multipliable> sum = new ArrayList<>();

        Iterator<Multipliable> it = entries.iterator();
        Iterator<Multipliable> sIt = s.entries().iterator();

        while (it.hasNext() && sIt.hasNext())
            sum.add(it.next().plus(sIt.next()));
        
        while (it.hasNext())
            sum.add(it.next());

        while (sIt.hasNext())
            sum.add(sIt.next());

        return new RingDirectSum(sum);
        
    }

    @Override
    public RingDirectSum plus(ArrayList<CompoundSummable<Multipliable>> l) {

        RingDirectSum sum = this;

        for (CompoundSummable<Multipliable> num : l)
            sum = sum.plus(num);

        return sum;

    }

    @Override
    public RingDirectSum negative() {

        ArrayList<Multipliable> neg = new ArrayList<>();
        
        for (Multipliable num : entries)
            neg.add(num.negative());

        return new RingDirectSum(neg);

    }

    @Override
    public RingDirectSum minus(CompoundSubtractable<Multipliable> dirSum) {

        return plus(dirSum.negative());

    }

    @Override
    public RingDirectSum times(int n) {

        ArrayList<Multipliable> prod = new ArrayList<>();
        
        for (Multipliable num : entries)
            prod.add(num.times(n));

        return new RingDirectSum(prod);

    }

    @Override
    public RingDirectSum times(CompoundMultipliable<Multipliable> s) {
        
        if (s.compound_type() != COMPOUND_TYPE.DIRECTSUM)
            throw new IncompatibleTypesException();

        ArrayList<Multipliable> prod = new ArrayList<>();

        Iterator<Multipliable> it = entries.iterator();
        Iterator<Multipliable> sIt = s.entries().iterator();

        while (it.hasNext() && sIt.hasNext())
            prod.add(it.next().times(sIt.next()));
        
        while (it.hasNext())
            prod.add(it.next().zero());

        while (sIt.hasNext())
            prod.add(sIt.next().zero());

        return new RingDirectSum(prod);
        
    }    

    @Override
    public RingDirectSum times(ArrayList<CompoundMultipliable<Multipliable>> l) {

        RingDirectSum prod = this;

        for (CompoundMultipliable<Multipliable> num : l)
            prod = prod.times(num);

        return prod;

    }

    @Override
    public boolean isZero() {

        boolean isZero = true;
        
        for (Multipliable num : entries)
            if (!num.isZero()) {
                isZero = false;
                break;
            }

        return isZero;

    }

    @Override
    public String format() {

        String s = "(";

        Iterator<Multipliable> it = entries.iterator();
        while (it.hasNext()) {

            s += it.next().format();

            if (it.hasNext())
                s += ", ";

        }

        s += ")";

        return s;

    }

    @Override
    public TYPE type() {

        return TYPE.VARIOUS;

    }

    @Override
    public Integer A() {

        return A;

    }

    @Override
    public Integer B() {

        return zero;

    }

    @Override
    public COMPOUND_TYPE compound_type() {

        return COMPOUND_TYPE.DIRECTSUM;

    }

    @Override
    public ArrayList<Multipliable> entries() {

        return entries;

    }

}
