package Arithmetic;

import java.util.ArrayList;
import java.util.Iterator;

final public class GroupDirectSum extends CompoundGroupNumber {

    TYPE type;
    Number A, B, C;
    COMPOUND_TYPE compound_type;
    ArrayList<GroupNumber> entries;

    public GroupDirectSum(ArrayList<GroupNumber> entries) {

        if (entries.size() == 0)
            throw new EmptyArrayException();
        
        this.type = entries.get(0).type();
        this.A = 0;
        this.B = 0;
        this.C = 0;

        this.compound_type = COMPOUND_TYPE.DIRECTSUM;
        this.entries = entries;

    }

    public GroupDirectSum(GroupDirectSum dirSum) {

        this.type = entries.get(0).type;
        this.A = 0;
        this.B = 0;
        this.C = 0;

        this.compound_type = COMPOUND_TYPE.DIRECTSUM;
        this.entries = dirSum.entries;

    }

    @Override
    public GroupDirectSum times(Multipliable s) {
        
        if (s.entries.size() != entries.size())
            throw new IncompatibleTypesException();

        ArrayList<GroupNumber> sum = new ArrayList<>();
        
        int n = entries.size();

        int i;
        for (i = 0; i < n; i++)
            sum.add(entries.get(i).plus(s.entries.get(i)));

        return new GroupDirectSum(sum);
        
    }

    @Override
    public GroupDirectSum identity() {
        
        ArrayList<GroupNumber> one = new ArrayList<>();

        for (GroupNumber num : entries)
            one.add(num.identity());

        return new GroupDirectSum(one);

    }


    public GroupDirectSum negative() {

        ArrayList<GroupNumber> neg = new ArrayList<>();

        for (GroupNumber num : entries)
            neg.add(num.negative());

        return new GroupDirectSum(neg);

    }


    public GroupDirectSum minus(GroupDirectSum dirSum) {

        return plus(dirSum.negative());

    }


    public GroupDirectSum times(int n) {

        ArrayList<GroupNumber> prod = new ArrayList<>();

        for (GroupNumber num : entries)
            prod.add(num.times(n));

        return new GroupDirectSum(prod);

    }


    public boolean isZero() {

        boolean isZero = true;
        
        for (GroupNumber num : entries)
            if (!num.isZero()) {
                isZero = false;
                break;
            }

        return isZero;

    }


    public static GroupDirectSum sum(GroupDirectSum dirSum1, GroupDirectSum dirSum2) {

        return dirSum1.plus(dirSum2);

    }


    public static GroupDirectSum sum(ArrayList<GroupDirectSum> dirSums) {

        if (dirSums.size() == 0)
            throw new EmptyArrayException();

        Iterator<GroupDirectSum> it = dirSums.iterator();
        GroupDirectSum sum = it.next();

        while(it.hasNext())
            sum = sum.plus(it.next());

        return sum;

    }


}
