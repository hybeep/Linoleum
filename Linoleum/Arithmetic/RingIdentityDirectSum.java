package Arithmetic;


import java.util.ArrayList;
import java.util.Iterator;


final public class RingIdentityDirectSum {


    ArrayList<IdentityRingNumber> entries;


    public RingIdentityDirectSum(ArrayList<IdentityRingNumber> entries) {

        if (entries.size() == 0)
            throw new EmptyArrayException();

        this.entries = entries;
        
    }


    public RingIdentityDirectSum(RingIdentityDirectSum dirSum) {

        this.entries = dirSum.entries;

    }


    public RingIdentityDirectSum zero() {
        
        ArrayList<IdentityRingNumber> zero = new ArrayList<>();
        
        for (IdentityRingNumber num : entries)
            zero.add(num.zero());

        return new RingIdentityDirectSum(zero);

    }


    public RingIdentityDirectSum identity() {
        
        ArrayList<IdentityRingNumber> one = new ArrayList<>();
        
        for (IdentityRingNumber num : entries)
            one.add(num.identity());

        return new RingIdentityDirectSum(one);

    }


    public RingIdentityDirectSum plus(RingIdentityDirectSum s) {
        
        if (s.entries.size() != entries.size())
            throw new IncompatibleTypesException();

        ArrayList<IdentityRingNumber> sum = new ArrayList<>();
        
        int n = entries.size();

        int i;
        for (i = 0; i < n; i++)
            sum.add(entries.get(i).plus(s.entries.get(i)));

        return new RingIdentityDirectSum(sum);
        
    }


    public RingIdentityDirectSum negative() {

        ArrayList<IdentityRingNumber> neg = new ArrayList<>();
        
        for (IdentityRingNumber num : entries)
            neg.add(num.negative());

        return new RingIdentityDirectSum(neg);

    }


    public RingIdentityDirectSum minus(RingIdentityDirectSum dirSum) {

        return plus(dirSum.negative());

    }


    public RingIdentityDirectSum times(int n) {

        ArrayList<IdentityRingNumber> prod = new ArrayList<>();
        
        for (IdentityRingNumber num : entries)
            prod.add(num.times(n));

        return new RingIdentityDirectSum(prod);

    }


    public RingIdentityDirectSum times(RingIdentityDirectSum s) {
        
        if (s.entries.size() != entries.size())
            throw new IncompatibleTypesException();

        ArrayList<IdentityRingNumber> sum = new ArrayList<>();
        
        int n = entries.size();

        int i;
        for (i = 0; i < n; i++)
            sum.add(entries.get(i).times(s.entries.get(i)));

        return new RingIdentityDirectSum(sum);
        
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


    public static RingIdentityDirectSum sum(RingIdentityDirectSum dirSum1, RingIdentityDirectSum dirSum2) {

        return dirSum1.plus(dirSum2);

    }


    public static RingIdentityDirectSum sum(ArrayList<RingIdentityDirectSum> dirSums) {

        if (dirSums.size() == 0)
            throw new EmptyArrayException();

        Iterator<RingIdentityDirectSum> it = dirSums.iterator();
        RingIdentityDirectSum sum = it.next();

        while(it.hasNext())
            sum = sum.plus(it.next());

        return sum;

    }


    public static RingIdentityDirectSum mult(RingIdentityDirectSum dirSum1, RingIdentityDirectSum dirSum2) {

        return dirSum1.times(dirSum2);

    }


    public static RingIdentityDirectSum mult(ArrayList<RingIdentityDirectSum> dirSums) {

        if (dirSums.size() == 0)
            throw new EmptyArrayException();

        Iterator<RingIdentityDirectSum> it = dirSums.iterator();
        RingIdentityDirectSum prod = it.next();

        while(it.hasNext())
            prod = prod.times(it.next());

        return prod;

    }

    
}
