package Arithmetic;


public class Z extends RingIdentityNumber {


    private final Long zero = 0L;
    private final Long one = 1L;
    


    public Z() {

        this.type = GroupNumber.TYPE.INTEGER;
        this.A = zero;
        this.B = 1;
        this.C = 0;

    }


    public Z(Long A) {

        this.type = GroupNumber.TYPE.INTEGER;
        this.A = A;
        this.B = 1;
        this.C = 0;

    }


    public Z(Z m) {

        this.type = GroupNumber.TYPE.INTEGER;
        this.A = m.A.longValue();
        this.B = 1;
        this.C = 0;

    }


    public Z(GroupNumber k) {

        this.type = GroupNumber.TYPE.INTEGER;
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
    public RingIdentityNumber plus(GroupNumber m) {

        switch (m.type) {

            case GroupNumber.TYPE.INTEGER:
                return new Z(A.longValue() + m.A.longValue());
            
            case GroupNumber.TYPE.RATIONAL:
            case GroupNumber.TYPE.REAL:
            case GroupNumber.TYPE.COMPLEX:
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
    public RingIdentityNumber times(RingNumber m) {

        switch (m.type) {

            case GroupNumber.TYPE.INTEGER:
                return new Z(A.longValue() * m.A.longValue());

            case GroupNumber.TYPE.RATIONAL:
            case GroupNumber.TYPE.REAL:
            case GroupNumber.TYPE.COMPLEX:
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
