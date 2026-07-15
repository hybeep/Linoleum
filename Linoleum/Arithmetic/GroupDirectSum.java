package Arithmetic;

import java.util.ArrayList;
import java.util.Iterator;

final public class GroupDirectSum extends CompoundGroupNumber {

    private int A;

    private ArrayList<GroupNumber> entries;

    private int zero = 0;

    public GroupDirectSum(ArrayList<GroupNumber> entries) {

        if (entries.size() == 0)
            throw new EmptyArrayException();
        
        this.A = entries.size();
        this.entries = entries;

    }

    public GroupDirectSum(GroupDirectSum dirSum) {

        this.A = dirSum.A;
        this.entries = dirSum.entries;

    }

    public GroupDirectSum(CompoundElement<GroupNumber> b) {

        if (b.entries().size() == 0)
            throw new EmptyArrayException();

        this.A = b.entries().size();
        this.entries = b.entries();

    }

    @Override
    public GroupDirectSum zero() {

        ArrayList<GroupNumber> zero = new ArrayList<>();

        for (GroupNumber num : entries)
            zero.add(num.zero());

        return new GroupDirectSum(zero);

    }

    @Override
    public GroupDirectSum plus(CompoundSummable<GroupNumber> s) {

        if (s.compound_type() != COMPOUND_TYPE.DIRECTSUM)
            throw new IncompatibleTypesException();

        ArrayList<GroupNumber> sum = new ArrayList<>();

        Iterator<GroupNumber> it = entries.iterator();
        Iterator<GroupNumber> sIt = s.entries().iterator();

        while (it.hasNext() && sIt.hasNext())
            sum.add(it.next().plus(sIt.next()));
        
        while (it.hasNext())
            sum.add(it.next());

        while (sIt.hasNext())
            sum.add(sIt.next());

        return new GroupDirectSum(sum);
        
    }

    @Override
    public GroupDirectSum plus(ArrayList<CompoundSummable<GroupNumber>> l) {

        GroupDirectSum sum = this;

        for (CompoundSummable<GroupNumber> num : l)
            sum = sum.plus(num);

        return sum;

    }

    @Override
    public GroupDirectSum negative() {

        ArrayList<GroupNumber> neg = new ArrayList<>();

        for (GroupNumber num : entries)
            neg.add(num.negative());

        return new GroupDirectSum(neg);

    }

    @Override
    public GroupDirectSum minus(CompoundSubtractable<GroupNumber> dirSum) {

        return plus(dirSum.negative());

    }

    @Override
    public GroupDirectSum times(int n) {

        ArrayList<GroupNumber> prod = new ArrayList<>();

        for (GroupNumber num : entries)
            prod.add(num.times(n));

        return new GroupDirectSum(prod);

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

        Iterator<GroupNumber> it = entries.iterator();
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
    public ArrayList<GroupNumber> entries() {

        return entries;

    }

}
