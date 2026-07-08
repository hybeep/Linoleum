package Arithmetic;


public class Q extends DivisionRingNumber {


    private final Long zero = 0L;
    private final Long one = 1L;


    public Q() {

        this.TYPE = RingNumber.TYPE_CODE.RATIONAL;
        this.A = zero;
        this.B = one;
        this.C = 1;

    }


    public Q(Long P, Long Q) {

        this.TYPE = RingNumber.TYPE_CODE.RATIONAL;
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

        this.TYPE = RingNumber.TYPE_CODE.RATIONAL;
        this.A = r.A.longValue();
        this.B = r.B.longValue();
        this.C = 1;

    }


    public Q(Z n) {

        this.TYPE = RingNumber.TYPE_CODE.RATIONAL;
        this.A = n.A.longValue();
        this.B = one;
        this.C = 1;

    }


    public Q(RingNumber k) {
       
        Q i = new Q(k.A.longValue(), k.B.longValue());
        
        this.TYPE = RingNumber.TYPE_CODE.RATIONAL;
        this.A = i.A.longValue();
        this.B = i.B.longValue();
        this.C = 1;

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
    public RingNumber plus(RingNumber r) {
        
        Long rA = r.A.longValue();
        Long rB = r.B.longValue();

        Long _A = A.longValue();
        Long _B = B.longValue();

        switch (r.TYPE) {

            case RingNumber.TYPE_CODE.INTEGER:
                return new Q(_A + _B * rA, _B);

            case RingNumber.TYPE_CODE.RATIONAL:
                return new Q(_A * rB + _B * rA, _B * rB);

            case RingNumber.TYPE_CODE.REAL:
            case RingNumber.TYPE_CODE.COMPLEX:
                return r.plus(this);

            default:
                throw new IncompatibleTypesException();

        }

    }


    @Override
    public Q negative() {

        return new Q(-A.longValue(), B.longValue());

    }


    @Override
    public RingNumber times(RingNumber r) {

        switch (r.TYPE) {

            case RingNumber.TYPE_CODE.INTEGER:
                return new Q(A.longValue() * r.A.longValue(), B.longValue());
                
            case RingNumber.TYPE_CODE.RATIONAL:
                return new Q(A.longValue() * r.A.longValue(), B.longValue() * r.B.longValue());

            case RingNumber.TYPE_CODE.REAL:
            case RingNumber.TYPE_CODE.COMPLEX:
                return r.times(this);

            default:
                throw new IncompatibleTypesException();

        }

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