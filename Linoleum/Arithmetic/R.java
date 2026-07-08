package Arithmetic;


public class R extends DivisionRingNumber {
    

    private final Double zero = 0D;
    private final Double one = 1D;


    public R() {

        this.TYPE = RingNumber.TYPE_CODE.REAL;
        this.A = zero;
        this.B = 1;
        this.C = 0;

    }


    public R(Double r) {

        this.TYPE = RingNumber.TYPE_CODE.REAL;
        this.A = r;
        this.B = 1;
        this.C = 0;

    }


    public R(R s) {

        this.TYPE = RingNumber.TYPE_CODE.REAL;
        this.A = s.A.doubleValue();
        this.B = 1;
        this.C = 0;

    }


    public R(Q s) {

        this.TYPE = RingNumber.TYPE_CODE.REAL;
        this.A = s.A.doubleValue() / s.B.doubleValue();
        this.B = 1;
        this.C = 0;

    }


    public R(Z n) {

        this.TYPE = RingNumber.TYPE_CODE.REAL;
        this.A = n.A.doubleValue();
        this.B = 1;
        this.C = 0;

    }


    public R(RingNumber k) {

        this.TYPE = RingNumber.TYPE_CODE.REAL;
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
    public RingNumber plus(RingNumber s) {

        Double sA = s.A.doubleValue();
        Double sB = s.B.doubleValue();

        Double _A = A.doubleValue();

        switch (s.TYPE) {

            case RingNumber.TYPE_CODE.INTEGER:
                return new R(_A + sA);

            case RingNumber.TYPE_CODE.RATIONAL:
                return new R(sA / sB + _A);

            case RingNumber.TYPE_CODE.REAL:
                return new R(sA + _A);

            case RingNumber.TYPE_CODE.COMPLEX:
                return s.plus(this);

            default:
                throw new IncompatibleTypesException();

        }

    }


    @Override
    public R negative() {

        return new R(-A.doubleValue());

    }


    @Override
    public RingNumber times(RingNumber s) {

        Double sA = s.A.doubleValue();
        Double sB = s.B.doubleValue();

        Double _A = A.doubleValue();

        switch (s.TYPE) {

            case RingNumber.TYPE_CODE.INTEGER:
                return new R(_A * sA);

            case RingNumber.TYPE_CODE.RATIONAL:
                return new R(_A * sA / sB);

            case RingNumber.TYPE_CODE.REAL:
                return new R(_A * sA);

            case RingNumber.TYPE_CODE.COMPLEX:
                return s.times(this);

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
