package Arithmetic;


public class Z extends RingIdentityNumber {


    private final Long zero = 0L;
    private final Long one = 1L;
    


    public Z() {

        this.TYPE = RingNumber.TYPE_CODE.INTEGER;
        this.A = zero;
        this.B = 1;
        this.C = 0;

    }


    public Z(Long A) {

        this.TYPE = RingNumber.TYPE_CODE.INTEGER;
        this.A = A;
        this.B = 1;
        this.C = 0;

    }


    public Z(Z m) {

        this.TYPE = RingNumber.TYPE_CODE.INTEGER;
        this.A = m.A.longValue();
        this.B = 1;
        this.C = 0;

    }


    public Z(RingNumber k) {

        this.TYPE = RingNumber.TYPE_CODE.INTEGER;
        this.A = k.A.longValue();
        this.B = 1;
        this.C = 0;

    }


    @Override
    public Z zero() {
        
        return new Z(zero);

    }

    @Override
    public Z identity() {

        return new Z(one);

    }


    @Override
    public RingNumber plus(RingNumber m) {

        switch (m.TYPE) {

            case RingNumber.TYPE_CODE.INTEGER:
                return new Z(A.longValue() + m.A.longValue());
            
            case RingNumber.TYPE_CODE.RATIONAL:
            case RingNumber.TYPE_CODE.REAL:
            case RingNumber.TYPE_CODE.COMPLEX:
                return m.plus(this);

            default:
                throw new IncompatibleTypesException();

        }


    }


    @Override
    public Z negative() {

        return new Z(-A.longValue());

    }


    @Override
    public RingNumber times(RingNumber m) {

        switch (m.TYPE) {

            case RingNumber.TYPE_CODE.INTEGER:
                return new Z(A.longValue() * m.A.longValue());

            case RingNumber.TYPE_CODE.RATIONAL:
            case RingNumber.TYPE_CODE.REAL:
            case RingNumber.TYPE_CODE.COMPLEX:
                return m.times(this);

            default:
                throw new IncompatibleTypesException();

        }

    }


    @Override
    public boolean isZero() {

        return Long.compare(A.longValue(), zero) == 0;

    }


    @Override
    public String format() {

        return "" + A.longValue();

    }


    public Long getZ() {

        return A.longValue();

    }

   
}
