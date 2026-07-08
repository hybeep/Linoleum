package Arithmetic;


public class R extends DivisionRingNumber {
    

    private final Double zero = 0D;
    private final Double one = 1D;


    public R() {

        this.TYPE = "REAL";
        this.A = zero;
        this.B = 1;
        this.C = 0;

    }


    public R(Double R) {

        this.TYPE = "REAL";
        this.A = R;
        this.B = 1;
        this.C = 0;

    }


    public R(R s) {

        this.TYPE = "REAL";
        this.A = s.A.doubleValue();
        this.B = 1;
        this.C = 0;

    }


    public R(Q s) {

        this.TYPE = "REAL";
        this.A = s.A.doubleValue() / s.B.doubleValue();
        this.B = 1;
        this.C = 0;

    }


    public R(Z n) {

        this.TYPE = "REAL";
        this.A = n.A.doubleValue();
        this.B = 1;
        this.C = 0;

    }


    public R(RingNumber k) {

        this.TYPE = "REAL";
        this.A = k.A.doubleValue();
        this.B = 1;
        this.C = 0;

    }


    @Override
    public R zero() {
        
        return new R();

    }


    @Override
    public R identity() {
        
        return new R(one);

    }


    @Override
    public R plus(RingNumber s) {

        Double sA = s.A.doubleValue();
        Double sB = s.B.doubleValue();

        if (s.TYPE == TYPE)
            return new R(A.doubleValue() + sA);
        else if (s.TYPE == "RATIONAL")
            return new R(A.doubleValue() + sA / sB);
        else if (s.TYPE == "INTEGER")
            return new R(A.doubleValue() + sA);

        throw new IncompatibleTypesException();

    }


    @Override
    public R negative() {

        return new R(-A.doubleValue());

    }


    @Override
    public R times(RingNumber s) {

        Double sA = s.A.doubleValue();
        Double sB = s.B.doubleValue(); 

        if (s.TYPE == TYPE)
            return new R(A.doubleValue() * sA);
        else if (s.TYPE == "RATIONAL")
            return new R(A.doubleValue() * sA / sB);
        else if (s.TYPE == "INTEGER")
            return new R(A.doubleValue() * sA);

        throw new IncompatibleTypesException();
        
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
