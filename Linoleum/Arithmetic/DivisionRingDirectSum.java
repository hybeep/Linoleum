package Arithmetic;


import java.util.ArrayList;
import java.util.Iterator;


final public class DivisionRingDirectSum extends CompoundDivisionRingNumber {

    private int A;

    private ArrayList<Invertible> entries;

    private int zero = 0;

    public DivisionRingDirectSum(ArrayList<Invertible> entries) {

        if (entries.size() == 0)
            throw new EmptyArrayException();

        this.A = entries.size();
        this.entries = entries;
        
    }

    public DivisionRingDirectSum(DivisionRingDirectSum dirSum) {

        this.A = dirSum.A;
        this.entries = dirSum.entries;

    }

    public DivisionRingDirectSum(CompoundElement<Invertible> b) {

        if (b.entries().size() == 0)
            throw new EmptyArrayException();

        this.A = b.entries().size();
        this.entries = b.entries();

    }

    @Override
    public DivisionRingDirectSum zero() {
        
        ArrayList<Invertible> zero = new ArrayList<>();

        for (Invertible num : entries)
            zero.add(num.zero());

        return new DivisionRingDirectSum(zero);

    }

    @Override
    public DivisionRingDirectSum identity() {
        
        ArrayList<Invertible> one = new ArrayList<>();

        for (Invertible num : entries)
            one.add(num.identity());

        return new DivisionRingDirectSum(one);

    }

    @Override
    public DivisionRingDirectSum plus(CompoundSummable<Invertible> s) {

        if (s.compound_type() != COMPOUND_TYPE.DIRECTSUM)
            throw new IncompatibleTypesException();

        ArrayList<Invertible> sum = new ArrayList<>();

        Iterator<Invertible> it = entries.iterator();
        Iterator<Invertible> sIt = s.entries().iterator();

        while (it.hasNext() && sIt.hasNext())
            sum.add(it.next().plus(sIt.next()));
        
        while (it.hasNext())
            sum.add(it.next());

        while (sIt.hasNext())
            sum.add(sIt.next());

        return new DivisionRingDirectSum(sum);
        
    }

    @Override
    public DivisionRingDirectSum plus(ArrayList<CompoundSummable<Invertible>> l) {

        DivisionRingDirectSum sum = this;

        for (CompoundSummable<Invertible> num : l)
            sum = sum.plus(num);

        return sum;

    }

    @Override
    public DivisionRingDirectSum negative() {

        ArrayList<Invertible> neg = new ArrayList<>();
        
        for (Invertible num : entries)
            neg.add(num.negative());

        return new DivisionRingDirectSum(neg);

    }

    @Override
    public DivisionRingDirectSum minus(CompoundSubtractable<Invertible> dirSum) {

        return plus(dirSum.negative());

    }

    @Override
    public DivisionRingDirectSum times(int n) {

        ArrayList<Invertible> prod = new ArrayList<>();
        
        for (Invertible num : entries)
            prod.add(num.times(n));

        return new DivisionRingDirectSum(prod);

    }

    @Override
    public DivisionRingDirectSum times(CompoundMultipliable<Invertible> s) {
        
        if (s.compound_type() != COMPOUND_TYPE.DIRECTSUM)
            throw new IncompatibleTypesException();

        ArrayList<Invertible> prod = new ArrayList<>();

        Iterator<Invertible> it = entries.iterator();
        Iterator<Invertible> sIt = s.entries().iterator();

        while (it.hasNext() && sIt.hasNext())
            prod.add(it.next().times(sIt.next()));
        
        while (it.hasNext())
            prod.add(it.next().zero());

        while (sIt.hasNext())
            prod.add(sIt.next().zero());

        return new DivisionRingDirectSum(prod);
        
    }    

    @Override
    public DivisionRingDirectSum times(ArrayList<CompoundMultipliable<Invertible>> l) {

        DivisionRingDirectSum prod = this;

        for (CompoundMultipliable<Invertible> num : l)
            prod = prod.times(num);

        return prod;

    }

    @Override
    public CompoundDivisionRingNumber inverse() {

        ArrayList<Invertible> inv = new ArrayList<>();
        
        for (Invertible num : entries)
            inv.add(num.inverse());

        return new DivisionRingDirectSum(inv);

    }

    @Override 
    public CompoundDivisionRingNumber div(CompoundInvertible<Invertible> b) {

        return times(b.inverse());

    }

    @Override
    public DivisionRingDirectSum pow(int n) {

        ArrayList<Invertible> power = new ArrayList<>();
        
        for (Invertible num : entries)
            power.add(num.pow(n));

        return new DivisionRingDirectSum(power);

    }
    
    @Override
    public boolean isZero() {

        boolean isZero = true;
        
        for (Invertible num : entries)
            if (!num.isZero()) {
                isZero = false;
                break;
            }

        return isZero;

    }

    @Override
    public boolean isIdentity() {

        boolean isOne = true;
        
        for (Invertible num : entries)
            if (!num.isIdentity()) {
                isOne = false;
                break;
            }

        return isOne;

    }

    @Override
    public String format() {

        String s = "(";

        Iterator<Invertible> it = entries.iterator();
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
    public ArrayList<Invertible> entries() {

        return entries;

    }
    
    
}
