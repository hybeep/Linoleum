package Arithmetic;

import java.util.ArrayList;

final public class R extends DivisionRingNumber {

    private Double A;

    private final Double zero = 0D;
    private final Double one = 1D;

    public R() {

        this.A = zero;

    }

    public R(Double r) {

        this.A = r;

    }

    public R(R s) {

        this.A = s.A;

    }

    public R(Q s) {

        this.A = s.A().doubleValue() / s.B().doubleValue();

    }

    public R(Z n) {

        this.A = n.A().doubleValue();

    }

    public R(Element k) {

        this.A = k.A().doubleValue();

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
    public R plus(Summable s) {

        switch (s.type()) {

            case TYPE.INTEGER:
                return new R(A + s.A().doubleValue());

            case TYPE.RATIONAL:
                return new R(A + s.A().doubleValue() / s.B().doubleValue());

            case TYPE.REAL:
                return new R(A + s.A().doubleValue());

            default:
                throw new IncompatibleTypesException();

        }

    }

    @Override
    public R plus(ArrayList<Summable> l) {

        R sum = this;
        for (Summable num : l)
            sum = sum.plus(num);

        return sum;

    }

    @Override
    public R negative() {

        return new R(-A.doubleValue());

    }

    @Override
    public R minus(Subtractable b) {

        return plus(b.negative());

    }

    @Override
    public R times(int n) {

        return new R(n * A);

    }

    @Override
    public R times(Multipliable s) {

        switch (s.type()) {

            case TYPE.INTEGER:
                return new R(A * s.A().doubleValue());

            case TYPE.RATIONAL:
                return new R(A * s.A().doubleValue() / s.B().doubleValue());

            case TYPE.REAL:
                return new R(A * s.A().doubleValue());

            default:
                throw new IncompatibleTypesException();

        }
        
    }

    @Override
    public R times(ArrayList<Multipliable> l) {

        R prod = this;
        for (Multipliable num : l)
            prod = prod.times(num);

        return prod;

    }

    @Override
    public R inverse() {

        if (isZero())
            throw new DivideByZeroException();
            
        return new R(1 / A.doubleValue()); 

    }

    @Override
    public R div(Invertible s) {

        return times(s.inverse());

    }

    @Override
    public R pow(int n) {

        if (isZero())
            if (n > 0)
                return zero();
            else
                throw new DivideByZeroException();

        if (n == 0 || isIdentity())
            return identity();

        R pow;
        
        R s;
        if (n > 0) {

            s = this;

        } else {

            s = this.inverse();
            n = -n;

        }

        pow = s;

        int i;
        for (i = 1; i < n; i++)
            pow = pow.times(s);

        return pow;

    }

    @Override
    public boolean isZero() {

        return Double.compare(A, zero) == 0;

    }

    @Override
    public boolean isIdentity() {

        return Double.compare(A, one) == 0;

    }

    @Override
    public String format() {

        return "" + A.doubleValue();

    }

    @Override
    public TYPE type() {

        return TYPE.REAL;

    }

    @Override
    public Double A() {

        return A;

    }

    @Override
    public Double B() {

        return one;

    }

    @Override
    public Double C() {

        return zero;

    }

    @Override
    public ArrayList<Number> extended_data() {

        return new ArrayList<Number>();

    }

}
