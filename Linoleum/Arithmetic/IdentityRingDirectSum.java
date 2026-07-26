package Arithmetic;

import java.util.ArrayList;
import java.util.Iterator;

final public class IdentityRingDirectSum extends CompoundIdentityRingNumber {

    private int A;

    private ArrayList<Identity> entries;

    private int zero = 0;

    public IdentityRingDirectSum(ArrayList<Identity> entries) {

        if (entries.size() == 0)
            throw new EmptyArrayException();

        this.A = entries.size();
        this.entries = entries;
        
    }

    public IdentityRingDirectSum(IdentityRingDirectSum dirSum) {

        this.A = dirSum.A;
        this.entries = dirSum.entries;

    }

    public IdentityRingDirectSum(CompoundElement<Identity> b) {

        if (b.entries().size() == 0)
            throw new EmptyArrayException();

        this.A = b.entries().size();
        this.entries = b.entries();

    }

    @Override
    public IdentityRingDirectSum zero() {
        
        ArrayList<Identity> zero = new ArrayList<>();

        for (Identity num : entries)
            zero.add(num.zero());

        return new IdentityRingDirectSum(zero);

    }

    @Override
    public IdentityRingDirectSum identity() {
        
        ArrayList<Identity> one = new ArrayList<>();

        for (Identity num : entries)
            one.add(num.identity());

        return new IdentityRingDirectSum(one);

    }

    @Override
    public IdentityRingDirectSum plus(CompoundSummable<Identity> s) {

        if (s.compound_type() != COMPOUND_TYPE.DIRECTSUM)
            throw new IncompatibleTypesException();

        ArrayList<Identity> sum = new ArrayList<>();

        Iterator<Identity> it = entries.iterator();
        Iterator<Identity> sIt = s.entries().iterator();

        while (it.hasNext() && sIt.hasNext())
            sum.add(it.next().plus(sIt.next()));
        
        while (it.hasNext())
            sum.add(it.next());

        while (sIt.hasNext())
            sum.add(sIt.next());

        return new IdentityRingDirectSum(sum);
        
    }

    @Override
    public IdentityRingDirectSum plus(ArrayList<CompoundSummable<Identity>> l) {

        IdentityRingDirectSum sum = this;

        for (CompoundSummable<Identity> num : l)
            sum = sum.plus(num);

        return sum;

    }

    @Override
    public IdentityRingDirectSum negative() {

        ArrayList<Identity> neg = new ArrayList<>();
        
        for (Identity num : entries)
            neg.add(num.negative());

        return new IdentityRingDirectSum(neg);

    }

    @Override
    public IdentityRingDirectSum minus(CompoundSubtractable<Identity> dirSum) {

        return plus(dirSum.negative());

    }

    @Override
    public IdentityRingDirectSum times(int n) {

        ArrayList<Identity> prod = new ArrayList<>();
        
        for (Identity num : entries)
            prod.add(num.times(n));

        return new IdentityRingDirectSum(prod);

    }

    @Override
    public IdentityRingDirectSum times(CompoundMultipliable<Identity> s) {
        
        if (s.compound_type() != COMPOUND_TYPE.DIRECTSUM)
            throw new IncompatibleTypesException();

        ArrayList<Identity> prod = new ArrayList<>();

        Iterator<Identity> it = entries.iterator();
        Iterator<Identity> sIt = s.entries().iterator();

        while (it.hasNext() && sIt.hasNext())
            prod.add(it.next().times(sIt.next()));
        
        while (it.hasNext())
            prod.add(it.next().zero());

        while (sIt.hasNext())
            prod.add(sIt.next().zero());

        return new IdentityRingDirectSum(prod);
        
    }    

    @Override
    public IdentityRingDirectSum times(ArrayList<CompoundMultipliable<Identity>> l) {

        IdentityRingDirectSum prod = this;

        for (CompoundMultipliable<Identity> num : l)
            prod = prod.times(num);

        return prod;

    }

    @Override
    public boolean isZero() {

        boolean isZero = true;
        
        for (Identity num : entries)
            if (!num.isZero()) {
                isZero = false;
                break;
            }

        return isZero;

    }

    @Override
    public String format() {

        String s = "(";

        Iterator<Identity> it = entries.iterator();
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
    public ArrayList<Identity> entries() {

        return entries;

    }

}
