package Arithmetic;


import java.util.ArrayList;
import java.util.Iterator;


final public class DivisionRingDirectSum extends CompoundDivisionRingNumber {

    private int A;

    private ArrayList<DivisionRingNumber> entries;

    private int zero = 0;

    public DivisionRingDirectSum(ArrayList<DivisionRingNumber> entries) {

        if (entries.size() == 0)
            throw new EmptyArrayException();

        this.A = entries.size();
        this.entries = entries;
        
    }

    public DivisionRingDirectSum(DivisionRingDirectSum dirSum) {

        this.A = dirSum.A;
        this.entries = dirSum.entries;

    }

    public DivisionRingDirectSum(CompoundElement<DivisionRingNumber> b) {

        if (b.entries().size() == 0)
            throw new EmptyArrayException();

        this.A = b.entries().size();
        this.entries = b.entries();

    }

    @Override
    public DivisionRingDirectSum zero() {
        
        ArrayList<DivisionRingNumber> zero = new ArrayList<>();

        for (DivisionRingNumber num : entries)
            zero.add(num.zero());

        return new DivisionRingDirectSum(zero);

    }

    @Override
    public DivisionRingDirectSum identity() {
        
        ArrayList<DivisionRingNumber> one = new ArrayList<>();

        for (DivisionRingNumber num : entries)
            one.add(num.identity());

        return new DivisionRingDirectSum(one);

    }

    @Override
    public DivisionRingDirectSum plus(CompoundSummable<DivisionRingNumber> s) {

        if (s.compound_type() != COMPOUND_TYPE.DIRECTSUM)
            throw new IncompatibleTypesException();

        ArrayList<DivisionRingNumber> sum = new ArrayList<>();

        Iterator<DivisionRingNumber> it = entries.iterator();
        Iterator<DivisionRingNumber> sIt = s.entries().iterator();

        while (it.hasNext() && sIt.hasNext())
            sum.add(it.next().plus(sIt.next()));
        
        while (it.hasNext())
            sum.add(it.next());

        while (sIt.hasNext())
            sum.add(sIt.next());

        return new DivisionRingDirectSum(sum);
        
    }

    @Override
    public DivisionRingDirectSum plus(ArrayList<CompoundSummable<DivisionRingNumber>> l) {

        DivisionRingDirectSum sum = this;

        for (CompoundSummable<DivisionRingNumber> num : l)
            sum = sum.plus(num);

        return sum;

    }

    @Override
    public DivisionRingDirectSum negative() {

        ArrayList<DivisionRingNumber> neg = new ArrayList<>();
        
        for (DivisionRingNumber num : entries)
            neg.add(num.negative());

        return new DivisionRingDirectSum(neg);

    }

    @Override
    public DivisionRingDirectSum minus(CompoundSubtractable<DivisionRingNumber> dirSum) {

        return plus(dirSum.negative());

    }

    @Override
    public DivisionRingDirectSum times(int n) {

        ArrayList<DivisionRingNumber> prod = new ArrayList<>();
        
        for (DivisionRingNumber num : entries)
            prod.add(num.times(n));

        return new DivisionRingDirectSum(prod);

    }

    @Override
    public DivisionRingDirectSum times(CompoundMultipliable<DivisionRingNumber> s) {
        
        if (s.compound_type() != COMPOUND_TYPE.DIRECTSUM)
            throw new IncompatibleTypesException();

        ArrayList<DivisionRingNumber> prod = new ArrayList<>();

        Iterator<DivisionRingNumber> it = entries.iterator();
        Iterator<DivisionRingNumber> sIt = s.entries().iterator();

        while (it.hasNext() && sIt.hasNext())
            prod.add(it.next().times(sIt.next()));
        
        while (it.hasNext())
            prod.add(it.next().zero());

        while (sIt.hasNext())
            prod.add(sIt.next().zero());

        return new DivisionRingDirectSum(prod);
        
    }    

    @Override
    public DivisionRingDirectSum times(ArrayList<CompoundMultipliable<DivisionRingNumber>> l) {

        DivisionRingDirectSum prod = this;

        for (CompoundMultipliable<DivisionRingNumber> num : l)
            prod = prod.times(num);

        return prod;

    }

    @Override
    public CompoundDivisionRingNumber inverse() {

        ArrayList<DivisionRingNumber> inv = new ArrayList<>();
        
        for (DivisionRingNumber num : entries)
            inv.add(num.inverse());

        return new DivisionRingDirectSum(inv);

    }

    @Override 
    public CompoundDivisionRingNumber div(CompoundInvertible<DivisionRingNumber> b) {

        return times(b.inverse());

    }

    @Override
    public DivisionRingDirectSum pow(int n) {

        ArrayList<DivisionRingNumber> power = new ArrayList<>();
        
        for (DivisionRingNumber num : entries)
            power.add(num.pow(n));

        return new DivisionRingDirectSum(power);

    }
    
    @Override
    public boolean isZero() {

        boolean isZero = true;
        
        for (DivisionRingNumber num : entries)
            if (!num.isZero()) {
                isZero = false;
                break;
            }

        return isZero;

    }

    @Override
    public String format() {

        String s = "(";

        Iterator<DivisionRingNumber> it = entries.iterator();
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
    public ArrayList<DivisionRingNumber> entries() {

        return entries;

    }
    
    
}
