package Arithmetic;

import java.util.ArrayList;

final public class Q extends DivisionRingNumber {

    private Long A, B;

    private final Long zero = 0L;
    private final Long one = 1L;

    public Q() {

        this.A = zero;
        this.B = one;

    }

    public Q(Long P, Long Q) {

        if (Long.compare(Q, zero) == 0)
            throw new DivideByZeroException();

        if (Long.compare(P, zero) == 0) {

            this.A = zero;
            this.B = one;

        } else if (Q < zero) {

            this.A = -P;
            this.B = -Q;

        } else {

            this.A = P;
            this.B = Q;

        }
        
        Long gcd = DivisionAlgorithm.gcd(this.A, this.B)[2];

        this.A = this.A.longValue() / gcd;
        this.B = this.B.longValue() / gcd;
        
    }

    public Q(Q r) {

        this.A = r.A.longValue();
        this.B = r.B.longValue();

    }

    public Q(Z n) {

        this.A = n.A().longValue();
        this.B = one;

    }

    public Q(Element k) {
       
        Q i = new Q(k.A().longValue(), k.B().longValue());
        
        this.A = i.A;
        this.B = i.B;

    }

    @Override
    public Q zero() {
        
        return new Q(zero, one);

    }

    @Override
    public Q identity() {
        
        return new Q(one, one);
        
    }

    @Override
    public Q plus(Summable r) {

        switch (r.type()) {

            case TYPE.INTEGER:
                return new Q(A + B * r.A().longValue(), B);

            case TYPE.RATIONAL:
                return new Q(A * r.B().longValue() + B * r.A().longValue(), B * r.B().longValue());

            default:
                throw new IncompatibleTypesException();

        }

    }

    @Override
    public Q plus(ArrayList<Summable> l) {

        Q sum = this;

        for (Summable num : l)
            sum = sum.plus(num);

        return sum;

    }

    @Override
    public Q negative() {

        return new Q(-A, B);

    }

    @Override
    public Q minus(Subtractable b) {

        return plus(b.negative());

    }

    @Override
    public Q times(int n) {

        return new Q(n * A, B);

    }

    @Override
    public Q times(Multipliable r) {

        switch (r.type()) {

            case TYPE.INTEGER:
                return new Q(A * r.A().longValue(), B);
                
            case TYPE.RATIONAL:
                return new Q(A * r.A().longValue(), B * r.B().longValue());

            default:
                throw new IncompatibleTypesException();

        }

    }

    @Override
    public Q times(ArrayList<Multipliable> l) {

        Q prod = this;

        for (Multipliable num : l)
            prod = prod.times(num);

        return prod;

    }

    @Override
    public boolean isZero() {

        return Long.compare(A, zero) == 0;

    }

    @Override
    public Q inverse() {

        return new Q(B, A);

    }

    @Override
    public Q div(Invertible b) {

        return times(b.inverse());

    }

    @Override
    public Q pow(int n) {

        Q s;

        if (n == 0) {

            s = identity();

        } else if (n <= 0) {

            s = inverse();
            n = -n;

        } else {

            s = this;

        }

        Q power = s;

        int i;
        for (i = 1; i < n; i++)
            power = power.times(s);

        return power;

    }

    @Override
    public String format() {

        return "" + A + "/" + B;

    }

    @Override
    public TYPE type() {

        return TYPE.RATIONAL;

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