package Arithmetic;

import java.util.ArrayList;
import java.util.Iterator;

final public class GroupDirectSum<T extends GroupNumber> extends CompoundGroupNumber<T> {

    private int A;

    private ArrayList<T> entries;

    private int zero = 0;

    public GroupDirectSum(ArrayList<T> entries) {

        if (entries.size() == 0)
            throw new EmptyArrayException();
        
        this.A = entries.size();
        this.entries = entries;

    }

    public GroupDirectSum(GroupDirectSum<T> dirSum) {

        this.A = dirSum.A;
        this.entries = dirSum.entries;

    }

    public GroupDirectSum(CompoundElement<T> b) {

        if (b.entries().size() == 0)
            throw new EmptyArrayException();

        this.A = b.entries().size();
        this.entries = b.entries();

    }

    @Override
    public GroupDirectSum<T> zero() {

        ArrayList<T> zero = new ArrayList<T>();

        for (T num : entries)
            zero.add(num.zero());

        return new GroupDirectSum<T>(zero);

    }

    @Override
    public GroupDirectSum<T> plus(CompoundSummable<T> s) {

        if (s.compound_type() != COMPOUND_TYPE.DIRECTSUM)
            throw new IncompatibleTypesException();

        ArrayList<T> sum = new ArrayList<>();

        Iterator<T> it = entries.iterator();
        Iterator<T> sIt = s.entries().iterator();

        while (it.hasNext() && sIt.hasNext())
            sum.add(it.next().plus(sIt.next()));
        
        while (it.hasNext())
            sum.add(it.next());

        while (sIt.hasNext())
            sum.add(it.next());

        return new GroupDirectSum<T>(sum);
        
    }

    @Override
    public GroupDirectSum<T> plus(ArrayList<CompoundSummable<T>> l) {

        GroupDirectSum<T> sum = this;

        for (CompoundSummable<T> num : l)
            sum = sum.plus(num);

        return sum;

    }

    @Override
    public GroupDirectSum<T> negative() {

        ArrayList<T> neg = new ArrayList<>();

        for (T num : entries)
            neg.add(num.negative());

        return new GroupDirectSum<T>(neg);

    }

    @Override
    public GroupDirectSum<T> minus(CompoundSubtractable<T> dirSum) {

        return plus(dirSum.negative());

    }

    @Override
    public GroupDirectSum<T> times(int n) {

        ArrayList<T> prod = new ArrayList<>();

        for (T num : entries)
            prod.add(num.times(n));

        return new GroupDirectSum<T>(prod);

    }

    @Override
    public boolean isZero() {

        boolean isZero = true;
        
        for (GroupNumber num : entries)
            if (!num.isZero()) {
                isZero = false;
                break;
            }

        return isZero;

    }

    @Override
    public String format() {

        String s = "(";

        Iterator<T> it = entries.iterator();
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
    public ArrayList<T> entries() {

        return entries;

    }

}
