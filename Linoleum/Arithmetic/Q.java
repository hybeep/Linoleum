package Arithmetic;


public class Q extends DivisionRingNumber {


    private final Long zero = 0L;
    private final Long one = 1L;


    public Q() {

        this.TYPE = "RATIONAL";
        this.A = zero;
        this.B = one;
        this.C = 1;

    }


    public Q(Long P, Long Q) {

        this.TYPE = "RATIONAL";
        this.C = 1;

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
        
        Long gcd = DivisionAlgorithm.gcd(this.A.longValue(), this.B.longValue())[2];

        this.A = this.A.longValue() / gcd;
        this.B = this.B.longValue() / gcd;
        
    }


    public Q(Q r) {

        this.TYPE = "RATIONAL";
        this.A = r.A.longValue();
        this.B = r.B.longValue();
        this.C = 1;

    }


    public Q(Z n) {

        this.TYPE = "RATIONAL";
        this.A = n.A.longValue();
        this.B = one;
        this.C = 1;

    }


    public Q(RingNumber k) {
       
        Q i = new Q(k.A.longValue(), k.B.longValue());
        
        this.TYPE = "INTEGER";
        this.A = i.A.longValue();
        this.B = i.B.longValue();
        this.C = 1;

    }


    @Override
    public Q zero() {
        
        return new Q();

    }


    @Override
    public Q identity() {
        
        return new Q(one, one);
        
    }


    @Override
    public Q plus(RingNumber r) {

        Long rA = r.A.longValue();
        Long rB = r.B.longValue();

        if (r.TYPE == TYPE)
            return new Q(A.longValue() * rB + B.longValue() * rA, B.longValue() * rB);
        else if (r.TYPE == "INTEGER")
            return new Q(A.longValue() + B.longValue() * rA, B.longValue());

        throw new IncompatibleTypesException();
    }


    @Override
    public Q negative() {

        return new Q(-A.longValue(), B.longValue());

    }


    @Override
    public Q times(RingNumber r) {

        Long rA = r.A.longValue();
        Long rB = r.B.longValue();

        if (r.TYPE == TYPE)
            return new Q(A.longValue() * rA, B.longValue() * rB);
        else if (r.TYPE == "INTEGER")
            return new Q(A.longValue() * rA, B.longValue());

        throw new IncompatibleTypesException();

    }


    @Override
    public boolean isZero() {

        return Long.compare(A.longValue(), zero) == 0;

    }


    @Override
    public Q inverse() {

        return new Q(B.longValue(), A.longValue());

    }


    @Override
    public String format() {

        return A.longValue() + "/" + B.longValue();

    }


    public Long getP() {

        return A.longValue();

    }


    public Long getQ() {

        return B.longValue();

    }

    
}