package Arithmetic;


import java.util.ArrayList;
import java.util.Iterator;


public class RingDirectSum {

    ArrayList<RingNumber> entries;

    public RingDirectSum(ArrayList<RingNumber> entries) {

        if (entries.size() == 0)
            throw new EmptyArrayException();

        this.entries = entries;
        
    }


    public RingDirectSum(RingDirectSum dirSum) {

        this.entries = dirSum.entries;

    }


    public RingDirectSum zero() {
        
        ArrayList<RingNumber> zero = new ArrayList<>();
        Iterator<RingNumber> it = entries.iterator();

        while (it.hasNext())
            zero.add(it.next().zero());

        return new RingDirectSum(zero);

    }


    public RingDirectSum plus(RingDirectSum s) {
        
        if (s.entries.size() != entries.size())
            throw new IncompatibleTypesException();

        ArrayList<RingNumber> sum = new ArrayList<>();
        
        int n = entries.size();

        int i;
        for (i = 0; i < n; i++)
            sum.add(entries.get(i).plus(s.entries.get(i)));

        return new RingDirectSum(sum);
        
    }


    public RingDirectSum negative() {

        ArrayList<RingNumber> neg = new ArrayList<>();
        Iterator<RingNumber> it = entries.iterator();

        while(it.hasNext())
            neg.add(it.next().negative());

        return new RingDirectSum(neg);

    }


    public RingDirectSum minus(RingDirectSum dirSum) {

        return plus(dirSum.negative());

    }


    public RingDirectSum times(int n) {

        ArrayList<RingNumber> prod = new ArrayList<>();
        Iterator<RingNumber> it = entries.iterator();

        while(it.hasNext())
            prod.add(it.next().times(n));

        return new RingDirectSum(prod);

    }


    public RingDirectSum times(RingDirectSum s) {
        
        if (s.entries.size() != entries.size())
            throw new IncompatibleTypesException();

        ArrayList<RingNumber> sum = new ArrayList<>();
        
        int n = entries.size();

        int i;
        for (i = 0; i < n; i++)
            sum.add(entries.get(i).times(s.entries.get(i)));

        return new RingDirectSum(sum);
        
    }    


    public boolean isZero() {

        boolean isZero = true;
        
        for (GroupNumber num : entries)
            if (!num.isZero())
                isZero = false;

        return isZero;

    }


    public static RingDirectSum sum(RingDirectSum dirSum1, RingDirectSum dirSum2) {

        return dirSum1.plus(dirSum2);

    }


    public static RingDirectSum sum(ArrayList<RingDirectSum> dirSums) {

        if (dirSums.size() == 0)
            throw new EmptyArrayException();

        Iterator<RingDirectSum> it = dirSums.iterator();
        RingDirectSum sum = it.next();

        while(it.hasNext())
            sum = sum.plus(it.next());

        return sum;


    }


    public static RingDirectSum mult(RingDirectSum dirSum1, RingDirectSum dirSum2) {

        return dirSum1.times(dirSum2);

    }


    public static RingDirectSum mult(ArrayList<RingDirectSum> dirSums) {

        if (dirSums.size() == 0)
            throw new EmptyArrayException();

        Iterator<RingDirectSum> it = dirSums.iterator();
        RingDirectSum prod = it.next();

        while(it.hasNext())
            prod = prod.times(it.next());

        return prod;


    }


    
}
