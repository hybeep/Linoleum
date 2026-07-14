package Arithmetic;

import java.util.ArrayList;

final public class C extends DivisionRingNumber {

    //C is and integer, if it is 0 the number is an ordered pair A+iB 
    // else it is in polar representation Ae^iB

    private Double A, B;
    private int C;

    private final Double zero = 0D;
    private final Double one = 1D;

    public C() {

        this.A = zero;
        this.B = zero;
        this.C = 0;

    }

    public C(Double A, Double B, int form) {

        this.C = form;
        this.A = A;
        this.B = B;

        if (form != 0) {

            if (A < zero) {

                this.A = -A;
                this.B = B + Math.PI;

            } else if (Double.compare(A, zero) == 0) {

                this.B = zero;

            }

            this.C = 1;

        }

    }

    public C(C z) {

        this.C = (z.C == 0) ? 0 : 1;
        this.A = z.A;
        this.B = z.B;

    }

    public C(C z, int form) {

        Double zA = z.A;
        Double zB = z.B;
        int zC = z.C;

        this.C = (form == 0) ? 0 : 1;

        if (form == zC) {

            this.A = zA;
            this.B = zB;

        } else if (form == 0) { //ordered pair

            this.A = zA * Math.cos(zB);
            this.B = zA * Math.sin(zB);
            
        } else {

            this.A = Math.sqrt(zA * zA + zB * zB);
            this.B = Math.atan2(zB, zA);

        }

    }

    public C(R r) {

        this.A = r.A().doubleValue();
        this.B = zero;
        this.C = 0;

    }

    public C(Q r) {

        this.A = r.A().doubleValue() / r.B().doubleValue();
        this.B = zero;
        this.C = 0;

    }

    public C(Z n) {

        this.A = n.A().doubleValue();
        this.B = zero;
        this.C = 0;

    }

    public C(Element k) {

        this.A = k.A().doubleValue();
        this.B = k.B().doubleValue();
        this.C = (k.C().intValue() == 0) ? 0 : 1;

    }

    @Override
    public C zero() {
        
        return new C(zero, zero, 0);

    }

    @Override
    public C identity() {
        
        return new C(one,zero,0);
        
    }

    @Override
    public C plus(Summable w) {

        C zPair = new C(this, 0);

        C sum;
        switch (w.type()) {

            case TYPE.INTEGER:
            case TYPE.REAL:
                sum = new C(w.A().doubleValue() + zPair.A, zPair.B, 0);
                return new C(sum, C);

            case TYPE.RATIONAL:
                sum = new C(w.A().doubleValue() / w.B().doubleValue() + zPair.A, zPair.B, 0);
                return new C(sum, C);

            case TYPE.COMPLEX:
                C wComplex = new C(w);
                C wPair = new C(wComplex, 0);
                sum = new C(zPair.A + wPair.A, zPair.B + wPair.B, 0);
                return new C(sum, C);

            default:
                throw new IncompatibleTypesException();

        }
        
    }

    @Override
    public C plus(ArrayList<Summable> l) {

        C sum = this;

        for (Summable num : l)
            sum = sum.plus(num);

        return sum;

    }

    @Override
    public C negative() {

        return (C == 0) ?
                    new C(-A, -B, 0)
                : 
                    new C(A, B + Math.PI, 1);

    }

    @Override
    public C minus(Subtractable b) {

        return plus(b.negative());

    }

    @Override
    public C times(int n) {

        return new C(n * A, (C == 0) ? n * B : B, C);

    }

    @Override
    public C times(Multipliable w) {

        C zPolar = new C(this, 1);

        C prod;
        switch (w.type()) {

            case TYPE.INTEGER:
            case TYPE.REAL:
                prod = new C(w.A().doubleValue() * zPolar.A, zPolar.B, 1);
                return new C(prod, C);

            case TYPE.RATIONAL:
                prod = new C(zPolar.A * w.A().doubleValue() / w.B().doubleValue(), zPolar.B, 1);
                return new C(prod, C);
                
            case TYPE.COMPLEX:
                C wPolar = new C(new C(w), 1);
                prod = new C(zPolar.A * wPolar.A, zPolar.B + wPolar.B, 1);
                return new C(prod, C);

            default:
                throw new IncompatibleTypesException();

        }

    }

    @Override
    public C times(ArrayList<Multipliable> l) {

        C prod = this;

        for (Multipliable num : l)
            prod = prod.times(num);

        return prod;

    }

    @Override
    public boolean isZero() {

        return Double.compare((new C(this, 1)).A, zero) == 0;

    }

    @Override
    public C inverse() {

        if (isZero())
            throw new DivideByZeroException();
        
        C zPolar = new C(this, 1);

        C invZ = new C(1 / zPolar.A, -zPolar.B, 1);
        
        return new C(invZ, C); 

    }

    @Override
    public C div(Invertible b) {

        return times(b.inverse());

    }

    @Override
    public C pow(int n) {

        C s;

        if (n == 0) {

            s = identity();

        } else if (n <= 0) {

            s = inverse();
            n = -n;

        } else {

            s = this;

        }

        C power = s;

        int i;
        for (i = 1; i < n; i++)
            power = power.times(s);

        return power;

    }

    public C conjugate() {

        return new C(A, -B, C);

    }

    public double norm() {

        return (C == 0) ? Math.sqrt(A * A + B * B) : A;

    }

    public void reduce() {

        if (C != 0) {

            Double _2PI = 2 * Math.PI;

            while (B < zero)
                B += _2PI;

            while (B >= _2PI)
                B -= _2PI;

        }

    }

    @Override
    public String format() {

        return (C == 0) ?
                A + ((B >= zero) ? "+" : "-") + "i" + Math.abs(B)
            :
                A + "e^(i" + B + ")";
    
    }

    @Override
    public TYPE type() {

        return TYPE.COMPLEX;

    }

    @Override
    public Double A() {

        return A;

    }

    @Override
    public Double B() {

        return B;

    }

    @Override
    public Integer C() {

        return C;

    }

    @Override
    public ArrayList<Number> extended_data() {

        return new ArrayList<Number>();

    }

}