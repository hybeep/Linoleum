package Arithmetic;

import java.util.ArrayList;
import java.util.Iterator;

final public class IdentityRingDirectSum extends CompoundIdentityRingNumber {

    private int A;

    private ArrayList<IdentityRingNumber> entries;

    private int zero = 0;

    public IdentityRingDirectSum(ArrayList<IdentityRingNumber> entries) {

        if (entries.size() == 0)
            throw new EmptyArrayException();

        this.A = entries.size();
        this.entries = entries;
        
    }

    public IdentityRingDirectSum(IdentityRingDirectSum dirSum) {

        this.A = dirSum.A;
        this.entries = dirSum.entries;

    }

    public IdentityRingDirectSum(CompoundElement<IdentityRingNumber> b) {

        if (b.entries().size() == 0)
            throw new EmptyArrayException();

        this.A = b.entries().size();
        this.entries = b.entries();

    }

    @Override
    public IdentityRingDirectSum zero() {
        
        ArrayList<IdentityRingNumber> zero = new ArrayList<>();

        for (IdentityRingNumber num : entries)
            zero.add(num.zero());

        return new IdentityRingDirectSum(zero);

    }

    @Override
    public IdentityRingDirectSum identity() {
        
        ArrayList<IdentityRingNumber> one = new ArrayList<>();

        for (IdentityRingNumber num : entries)
            one.add(num.identity());

        return new IdentityRingDirectSum(one);

    }

    @Override
    public IdentityRingDirectSum plus(CompoundSummable<IdentityRingNumber> s) {

        if (s.compound_type() != COMPOUND_TYPE.DIRECTSUM)
            throw new IncompatibleTypesException();

        ArrayList<IdentityRingNumber> sum = new ArrayList<>();

        Iterator<IdentityRingNumber> it = entries.iterator();
        Iterator<IdentityRingNumber> sIt = s.entries().iterator();

        while (it.hasNext() && sIt.hasNext())
            sum.add(it.next().plus(sIt.next()));
        
        while (it.hasNext())
            sum.add(it.next());

        while (sIt.hasNext())
            sum.add(sIt.next());

        return new IdentityRingDirectSum(sum);
        
    }

    @Override
    public IdentityRingDirectSum plus(ArrayList<CompoundSummable<IdentityRingNumber>> l) {

        IdentityRingDirectSum sum = this;

        for (CompoundSummable<IdentityRingNumber> num : l)
            sum = sum.plus(num);

        return sum;

    }

    @Override
    public IdentityRingDirectSum negative() {

        ArrayList<IdentityRingNumber> neg = new ArrayList<>();
        
        for (IdentityRingNumber num : entries)
            neg.add(num.negative());

        return new IdentityRingDirectSum(neg);

    }

    @Override
    public IdentityRingDirectSum minus(CompoundSubtractable<IdentityRingNumber> dirSum) {

        return plus(dirSum.negative());

    }

    @Override
    public IdentityRingDirectSum times(int n) {

        ArrayList<IdentityRingNumber> prod = new ArrayList<>();
        
        for (IdentityRingNumber num : entries)
            prod.add(num.times(n));

        return new IdentityRingDirectSum(prod);

    }

    @Override
    public IdentityRingDirectSum times(CompoundMultipliable<IdentityRingNumber> s) {
        
        if (s.compound_type() != COMPOUND_TYPE.DIRECTSUM)
            throw new IncompatibleTypesException();

        ArrayList<IdentityRingNumber> prod = new ArrayList<>();

        Iterator<IdentityRingNumber> it = entries.iterator();
        Iterator<IdentityRingNumber> sIt = s.entries().iterator();

        while (it.hasNext() && sIt.hasNext())
            prod.add(it.next().times(sIt.next()));
        
        while (it.hasNext())
            prod.add(it.next().zero());

        while (sIt.hasNext())
            prod.add(sIt.next().zero());

        return new IdentityRingDirectSum(prod);
        
    }    

    @Override
    public IdentityRingDirectSum times(ArrayList<CompoundMultipliable<IdentityRingNumber>> l) {

        IdentityRingDirectSum prod = this;

        for (CompoundMultipliable<IdentityRingNumber> num : l)
            prod = prod.times(num);

        return prod;

    }

    @Override
    public boolean isZero() {

        boolean isZero = true;
        
        for (IdentityRingNumber num : entries)
            if (!num.isZero()) {
                isZero = false;
                break;
            }

        return isZero;

    }

    @Override
    public String format() {

        String s = "(";

        Iterator<IdentityRingNumber> it = entries.iterator();
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
    public ArrayList<IdentityRingNumber> entries() {

        return entries;

    }

}
