package Arithmetic;


public class R extends DivisionRingNumber {
    

    private final Double zero = 0D;
    private final Double one = 1D;


    public R() {

        this.type = GroupNumber.TYPE.REAL;
        this.A = zero;
        this.B = 1;
        this.C = 0;

    }


    public R(Double r) {

        this.type = GroupNumber.TYPE.REAL;
        this.A = r;
        this.B = 1;
        this.C = 0;

    }


    public R(R s) {

        this.type = GroupNumber.TYPE.REAL;
        this.A = s.A.doubleValue();
        this.B = 1;
        this.C = 0;

    }


    public R(Q s) {

        this.type = GroupNumber.TYPE.REAL;
        this.A = s.A.doubleValue() / s.B.doubleValue();
        this.B = 1;
        this.C = 0;

    }


    public R(Z n) {

        this.type = GroupNumber.TYPE.REAL;
        this.A = n.A.doubleValue();
        this.B = 1;
        this.C = 0;

    }


    public R(GroupNumber k) {

        this.type = GroupNumber.TYPE.REAL;
        this.A = k.A.doubleValue();
        this.B = 1;
        this.C = 0;

    }


    @Override
    public R zero() {
        
        return new R(zero);

    }


    @Override
    public R identity() {
        
        return new R(one);

    }


    @Override
    public DivisionRingNumber plus(GroupNumber s) {


        switch (s.type) {

            case GroupNumber.TYPE.INTEGER:
                return new R(A.doubleValue() + s.A.doubleValue());

            case GroupNumber.TYPE.RATIONAL:
                return new R(s.A.doubleValue() / s.B.doubleValue() + A.doubleValue());

            case GroupNumber.TYPE.REAL:
                return new R(s.A.doubleValue() + A.doubleValue());

            case GroupNumber.TYPE.COMPLEX:
                C sComplex = new C(s);
                C sPair = new C(sComplex, 0);
                C sum = new C(sPair.A.doubleValue() + A.doubleValue(), sPair.B.doubleValue(), 0);
                return new C(sum, sComplex.C.intValue());

            default:
                throw new IncompatibleTypesException();

        }

    }


    @Override
    public R negative() {

        return new R(-A.doubleValue());

    }


    @Override
    public DivisionRingNumber times(RingNumber s) {

        switch (s.type) {

            case GroupNumber.TYPE.INTEGER:
                return new R(A.doubleValue() * s.A.doubleValue());

            case GroupNumber.TYPE.RATIONAL:
                return new R(A.doubleValue() * s.A.doubleValue() / s.B.doubleValue());

            case GroupNumber.TYPE.REAL:
                return new R(A.doubleValue() * s.A.doubleValue());

            case GroupNumber.TYPE.COMPLEX:
                C sComplex = new C(s);
                C sPolar = new C(sComplex, 1);
                C prod = new C(sPolar.A.doubleValue() * A.doubleValue(), sPolar.B.doubleValue(), 1);
                return new C(prod, sComplex.C.intValue());

            default:
                throw new IncompatibleTypesException();

        }
        
    }


    @Override
    public boolean isZero() {

        return Double.compare(A.doubleValue(), zero) == 0;

    }


    @Override
    public R inverse() {

        if (isZero())
            throw new DivideByZeroException();
            
        return new R(1 / A.doubleValue()); 

    }


    @Override
    public String format() {

        return "" + A.doubleValue();

    }


    public Double getR() {

        return A.doubleValue();

    }


}
