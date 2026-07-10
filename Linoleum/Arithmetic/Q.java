package Arithmetic;


public class Q extends DivisionRingNumber {


    private final Long zero = 0L;
    private final Long one = 1L;


    public Q() {

        this.type = GroupNumber.TYPE.RATIONAL;
        this.A = zero;
        this.B = one;
        this.C = 1;

    }


    public Q(Long P, Long Q) {

        this.type = GroupNumber.TYPE.RATIONAL;
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

        this.type = GroupNumber.TYPE.RATIONAL;
        this.A = r.A.longValue();
        this.B = r.B.longValue();
        this.C = 1;

    }


    public Q(Z n) {

        this.type = GroupNumber.TYPE.RATIONAL;
        this.A = n.A.longValue();
        this.B = one;
        this.C = 1;

    }


    public Q(GroupNumber k) {
       
        Q i = new Q(k.A.longValue(), k.B.longValue());
        
        this.type = GroupNumber.TYPE.RATIONAL;
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
    public DivisionRingNumber plus(GroupNumber r) {

        switch (r.type) {

            case GroupNumber.TYPE.INTEGER:
                return new Q(A.longValue() + B.longValue() * r.A.longValue(), B.longValue());

            case GroupNumber.TYPE.RATIONAL:
                return new Q(A.longValue() * r.B.longValue() + B.longValue() * r.A.longValue(), B.longValue() * r.B.longValue());

            case GroupNumber.TYPE.REAL:
                return new R(r.A.doubleValue() + A.doubleValue() / B.doubleValue());

            case GroupNumber.TYPE.COMPLEX:
                C rComplex = new C(r);
                C rPair = new C(rComplex, 0);
                C sum = new C(rPair.A.doubleValue() + A.doubleValue() / B.doubleValue(), rPair.B.doubleValue(), 0);
                return new C(sum, rComplex.C.intValue());

            default:
                throw new IncompatibleTypesException();

        }

    }


    @Override
    public Q negative() {

        return new Q(-A.longValue(), B.longValue());

    }


    @Override
    public DivisionRingNumber times(RingNumber r) {

        switch (r.type) {

            case GroupNumber.TYPE.INTEGER:
                return new Q(A.longValue() * r.A.longValue(), B.longValue());
                
            case GroupNumber.TYPE.RATIONAL:
                return new Q(A.longValue() * r.A.longValue(), B.longValue() * r.B.longValue());

            case GroupNumber.TYPE.REAL:
                return new R(r.A.doubleValue() * A.doubleValue() / B.doubleValue());

            case GroupNumber.TYPE.COMPLEX:
                C rComplex = new C(r);
                C rPolar = new C(rComplex, 1);
                C prod = new C(rPolar.A.doubleValue() * A.doubleValue() / B.doubleValue(), rPolar.B.doubleValue(), 1);
                return new C(prod, rComplex.C.intValue());

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