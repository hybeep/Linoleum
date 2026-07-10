package Arithmetic;


import java.util.ArrayList;
import java.util.Iterator;


public class GroupDirectSum {
    

    ArrayList<GroupNumber> entries;


    public GroupDirectSum(ArrayList<GroupNumber> entries) {

        if (entries.size() == 0)
            throw new EmptyArrayException();
        
        this.entries = entries;

    }


    public GroupDirectSum(GroupDirectSum dirSum) {

        this.entries = dirSum.entries;

    }


    public GroupDirectSum zero() {
        
        ArrayList<GroupNumber> zero = new ArrayList<>();
        Iterator<GroupNumber> it = entries.iterator();

        while (it.hasNext())
            zero.add(it.next().zero());

        return new GroupDirectSum(zero);

    }


    public GroupDirectSum plus(GroupDirectSum s) {
        
        if (s.entries.size() != entries.size())
            throw new IncompatibleTypesException();

        ArrayList<GroupNumber> sum = new ArrayList<>();
        
        int n = entries.size();

        int i;
        for (i = 0; i < n; i++)
            sum.add(entries.get(i).plus(s.entries.get(i)));

        return new GroupDirectSum(sum);
        
    }


    public GroupDirectSum negative() {

        ArrayList<GroupNumber> neg = new ArrayList<>();
        Iterator<GroupNumber> it = entries.iterator();

        while(it.hasNext())
            neg.add(it.next().negative());

        return new GroupDirectSum(neg);

    }


    public GroupDirectSum minus(GroupDirectSum dirSum) {

        return plus(dirSum.negative());

    }


    public GroupDirectSum times(int n) {

        ArrayList<GroupNumber> prod = new ArrayList<>();
        Iterator<GroupNumber> it = entries.iterator();

        while(it.hasNext())
            prod.add(it.next().times(n));

        return new GroupDirectSum(prod);

    }


    public boolean isZero() {

        boolean isZero = true;
        
        for (GroupNumber num : entries)
            if (!num.isZero())
                isZero = false;

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
