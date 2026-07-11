package Arithmetic;

import java.util.ArrayList;
import java.util.Iterator;

public class DivisionRingDirectSum {

    ArrayList<DivisionRingNumber> entries;


    public DivisionRingDirectSum(ArrayList<DivisionRingNumber> entries) {

        if (entries.size() == 0)
            throw new EmptyArrayException();

        this.entries = entries;
        
    }


    public DivisionRingDirectSum(DivisionRingDirectSum dirSum) {

        this.entries = dirSum.entries;

    }


    public DivisionRingDirectSum zero() {
        
        ArrayList<DivisionRingNumber> zero = new ArrayList<>();
        Iterator<DivisionRingNumber> it = entries.iterator();

        while (it.hasNext())
            zero.add(it.next().zero());

        return new DivisionRingDirectSum(zero);

    }


    public DivisionRingDirectSum one() {
        
        ArrayList<DivisionRingNumber> one = new ArrayList<>();
        Iterator<DivisionRingNumber> it = entries.iterator();

        while (it.hasNext())
            one.add(it.next().identity());

        return new DivisionRingDirectSum(one);

    }


    public DivisionRingDirectSum plus(DivisionRingDirectSum s) {
        
        if (s.entries.size() != entries.size())
            throw new IncompatibleTypesException();

        ArrayList<DivisionRingNumber> sum = new ArrayList<>();
        
        int n = entries.size();

        int i;
        for (i = 0; i < n; i++)
            sum.add(entries.get(i).plus(s.entries.get(i)));

        return new DivisionRingDirectSum(sum);
        
    }


    public DivisionRingDirectSum negative() {

        ArrayList<DivisionRingNumber> neg = new ArrayList<>();
        Iterator<DivisionRingNumber> it = entries.iterator();

        while(it.hasNext())
            neg.add(it.next().negative());

        return new DivisionRingDirectSum(neg);

    }


    public DivisionRingDirectSum minus(DivisionRingDirectSum dirSum) {

        return plus(dirSum.negative());

    }


    public DivisionRingDirectSum times(int n) {

        ArrayList<DivisionRingNumber> prod = new ArrayList<>();
        Iterator<DivisionRingNumber> it = entries.iterator();

        while(it.hasNext())
            prod.add(it.next().times(n));

        return new DivisionRingDirectSum(prod);

    }


    public DivisionRingDirectSum times(DivisionRingDirectSum s) {
        
        if (s.entries.size() != entries.size())
            throw new IncompatibleTypesException();

        ArrayList<DivisionRingNumber> sum = new ArrayList<>();
        
        int n = entries.size();

        int i;
        for (i = 0; i < n; i++)
            sum.add(entries.get(i).times(s.entries.get(i)));

        return new DivisionRingDirectSum(sum);
        
    }    


    public DivisionRingDirectSum inverse() {

        ArrayList<DivisionRingNumber> inv = new ArrayList<>();
        Iterator<DivisionRingNumber> it = entries.iterator();

        while(it.hasNext())
            inv.add(it.next().inverse());

        return new DivisionRingDirectSum(inv);

    }


    public DivisionRingDirectSum div(DivisionRingDirectSum s) {

        return times(s.inverse());

    }


    public boolean isZero() {

        boolean isZero = true;
        
        for (GroupNumber num : entries)
            if (!num.isZero())
                isZero = false;

        return isZero;

    }


    public static DivisionRingDirectSum sum(DivisionRingDirectSum dirSum1, DivisionRingDirectSum dirSum2) {

        return dirSum1.plus(dirSum2);

    }


    public static DivisionRingDirectSum sum(ArrayList<DivisionRingDirectSum> dirSums) {

        if (dirSums.size() == 0)
            throw new EmptyArrayException();

        Iterator<DivisionRingDirectSum> it = dirSums.iterator();
        DivisionRingDirectSum sum = it.next();

        while(it.hasNext())
            sum = sum.plus(it.next());

        return sum;

    }


    public static DivisionRingDirectSum mult(DivisionRingDirectSum dirSum1, DivisionRingDirectSum dirSum2) {

        return dirSum1.times(dirSum2);

    }


    public static DivisionRingDirectSum mult(ArrayList<DivisionRingDirectSum> dirSums) {

        if (dirSums.size() == 0)
            throw new EmptyArrayException();

        Iterator<DivisionRingDirectSum> it = dirSums.iterator();
        DivisionRingDirectSum prod = it.next();

        while(it.hasNext())
            prod = prod.times(it.next());

        return prod;

    }
    
}
