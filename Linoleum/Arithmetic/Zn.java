package Arithmetic;

import java.util.ArrayList;

final public class Zn extends IdentityRingNumber {

    private Long A, B;

    private Long zero = 0L;
    private Long one = 1L;

    public Zn(Long Z, Long N) {

        this.A = Z;
        this.B = N > 0L ? N : -N;
        reduce();

    }

    public Zn(Zn c) {

        this.A = c.A;
        this.B = c.B;

    }

    public Zn(Element a) {

        Zn aZn = new Zn(a.A().longValue(), a.B().longValue());
        
        this.A = aZn.A.longValue();
        this.B = aZn.B.longValue();

    }

    private void reduce() {

        while(A < zero)
            A += B;

        while(B <= A)
            A -=  B;

    }

    @Override
    public Zn zero() {

        return new Zn(zero, B);

    }

    @Override
    public Zn identity() {

        return new Zn(one, B);

    }

    @Override
    public Zn plus(Summable c) {

        if (c.type() != TYPE.MODN || Long.compare(c.B().longValue(), B) != 0)
            throw new IncompatibleTypesException();
        
        return new Zn(A + c.A().longValue(), B);

    }

    @Override
    public Zn plus(ArrayList<Summable> l) {

        Zn sum = this;

        for (Summable num : l)
            sum = sum.plus(num);

        return sum;

    }

    @Override
    public Zn negative() {

        return new Zn(-A.longValue(), B.longValue());

    }

    @Override
    public Zn minus(Subtractable b) {

        return plus(b.negative());

    }

    @Override
    public Zn times(int n) {

        return new Zn(n * A, B);

    }

    @Override
    public Zn times(Multipliable c) {

        if (c.type() != TYPE.MODN || Long.compare(c.B().longValue(), B) != 0)
            throw new IncompatibleTypesException();
        
        return new Zn(A * c.A().longValue(), B);
        
    }

    @Override
    public Zn times(ArrayList<Multipliable> l) {

        Zn prod = this;

        for (Multipliable num : l)
            prod = prod.times(num);

        return prod;

    }

    @Override
    public boolean isZero() {

        return Long.compare(A, zero) == 0;

    }

    @Override
    public String format() {

        return "[" + A+ "]_" + B;

    }

    @Override
    public TYPE type() {

        return TYPE.MODN;

    }
    
    @Override
    public Long A() {

        return A;

    }

    @Override
    public Long B() {

        return B;

    }

    @Override
    public Long C() {

        return one;

    }

    @Override
    public ArrayList<Number> extended_data() {

        return new ArrayList<Number>();

    }
    
    
}
