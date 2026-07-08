package Arithmetic;


public class C extends DivisionRingNumber {

    //C is and integer, if it is 0 the number is an ordered pair A+iB 
    // else it is in polar representation Ae^iB

    private final Double zero = 0D;
    private final Double one = 1D;


    public C() {

        this.TYPE = RingNumber.TYPE_CODE.COMPLEX;
        this.A = zero;
        this.B = zero;
        this.C = 0;

    }

    
    public C(Double A, Double B, int form) {

        this.TYPE = RingNumber.TYPE_CODE.COMPLEX;
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

        this.TYPE = RingNumber.TYPE_CODE.COMPLEX;
        this.C = z.C.intValue();
        this.A = z.A.doubleValue();
        this.B = z.B.doubleValue();

    }


    public C(C z, int form) {

        Double zA = z.A.doubleValue();
        Double zB = z.B.doubleValue();
        int zC = z.C.intValue();

        this.C = form;

        if ((form == 0 && zC == 0) || (form != 0 && zC != 0)) {

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

        this.TYPE = RingNumber.TYPE_CODE.COMPLEX;
        this.A = r.A.doubleValue();
        this.B = 0D;
        this.C = 0;

    }


    public C(Q r) {

        this.TYPE = RingNumber.TYPE_CODE.COMPLEX;
        this.A = r.A.doubleValue() / r.B.doubleValue();
        this.B = 0D;
        this.C = 0;

    }


    public C(Z n) {

        this.TYPE = RingNumber.TYPE_CODE.COMPLEX;
        this.A = n.A.doubleValue();
        this.B = 0D;
        this.C = 0;

    }


    public C(RingNumber k) {

        this.TYPE = RingNumber.TYPE_CODE.COMPLEX;
        this.A = k.A.doubleValue();
        this.B = k.B.doubleValue();
        this.C = k.C.intValue();

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
    public RingNumber plus(RingNumber w) {

        C zPair = new C(this, 0);

        C sum;
        switch (w.TYPE) {

            case RingNumber.TYPE_CODE.INTEGER:
            case RingNumber.TYPE_CODE.REAL:
                sum = new C(w.A.doubleValue() + zPair.A.doubleValue(), zPair.B.doubleValue(), 0);
                return new C(sum, C.intValue());

            case RingNumber.TYPE_CODE.RATIONAL:
                sum = new C(w.A.doubleValue() / w.B.doubleValue() + zPair.A.doubleValue(), zPair.B.doubleValue(), 0);
                return new C(sum, C.intValue());

            case RingNumber.TYPE_CODE.COMPLEX:
                C wComplex = new C(w);
                C wPair = new C(wComplex, 0);
                sum = new C(zPair.A.doubleValue() + wPair.A.doubleValue(), zPair.B.doubleValue() + wPair.B.doubleValue(), 0);
                return new C(sum, C.intValue());

            default:
                throw new IncompatibleTypesException();

        }
        
    }


    @Override
    public C negative() {

        return (C.intValue() == 0) ?
                    new C(-A.doubleValue(), -B.doubleValue(), 0)
                : 
                    new C(A.doubleValue(), B.doubleValue() + Math.PI, 1);

    }


    @Override
    public RingNumber times(RingNumber w) {

        C zPolar = new C(this, 0);

        C prod;
        switch (w.TYPE) {

            case RingNumber.TYPE_CODE.INTEGER:
            case RingNumber.TYPE_CODE.REAL:
                prod = new C(w.A.doubleValue() * zPolar.A.doubleValue(), zPolar.B.doubleValue(), 1);
                return new C(prod, C.intValue());

            case RingNumber.TYPE_CODE.RATIONAL:
                prod = new C(zPolar.A.doubleValue() * w.A.doubleValue() / w.B.doubleValue(), zPolar.B.doubleValue(), 1);
                return new C(prod, C.intValue());
                
            case RingNumber.TYPE_CODE.COMPLEX:
                C wPolar = new C(new C(w), 1);
                prod = new C(zPolar.A.doubleValue() * wPolar.A.doubleValue(), zPolar.B.doubleValue() + wPolar.B.doubleValue(), 1);
                return new C(prod, C.intValue());

            default:
                throw new IncompatibleTypesException();

        }

    }


    @Override
    public boolean isZero() {

        return Double.compare( (new C(this, 1)).A.doubleValue(), zero) == 0;

    }


    @Override
    public C inverse() {

        if (isZero())
            throw new DivideByZeroException();
        
        C zPolar = new C(this, 1);

        C invZ = new C(1 / zPolar.A.doubleValue(), -zPolar.B.doubleValue(), 1);
        
        return new C(invZ, C.intValue()); 

    }

    
    public C realPow(Double r) {

        C zPolar = new C(this, 1);

        if (isZero())
            return new C(zero, zero, C.intValue());

        C pow = new C(Math.pow(zPolar.A.longValue(), r), r * zPolar.B.longValue(), 1);

        return new C(pow, C.intValue());

    }


    public C conjugate() {

        return new C(A.doubleValue(), -B.doubleValue(), C.intValue());

    }


    public double norm() {

        Double a = A.doubleValue();
        Double b = B.doubleValue();

        return (C.intValue() == 0) ? Math.sqrt(a * a + b * b) : a;

    }


    public void reduce() {

        if (C.intValue() != 0) {

            Double _2PI = 2 * Math.PI;

            while (B.doubleValue() < zero)
                B = B.doubleValue() + _2PI;

            while (B.doubleValue() >= _2PI)
                B = B.doubleValue() - _2PI;

        }

    }


    public String format() {

        return (C.intValue() == 0) ?
                A.doubleValue() + ((B.doubleValue() >= 0D) ? "+" : "-") + "i" + Math.abs(B.doubleValue())
            :
                A.doubleValue() + "e^(i" + B.doubleValue() + ")";
    
    }


    public int getForm() {

        return C.intValue();

    }


}