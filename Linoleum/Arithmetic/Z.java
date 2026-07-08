package Arithmetic;


public class Z extends RingIdentityNumber {


    public Z() {

        this.TYPE = "INTEGER";
        this.A = 0L;
        this.B = 1;
        this.C = 0;

    }


    public Z(Long A) {

        this.TYPE = "INTEGER";
        this.A = A;
        this.B = 1;
        this.C = 0;

    }


    public Z(Z m) {

        this.TYPE = "INTEGER";
        this.A = m.A.longValue();
        this.B = 1;
        this.C = 0;

    }


    public Z(RingNumber k) {

        this.TYPE = "INTEGER";
        this.A = k.A.longValue();
        this.B = 1;
        this.C = 0;

    }


    @Override
    public Z zero() {
        
        return new Z();

    }

    @Override
    public Z identity() {

        return new Z(1L);

    }


    @Override
    public Z plus(RingNumber m) {

        if (m.TYPE == TYPE)
            return new Z(A.longValue() + m.A.longValue());

        throw new IncompatibleTypesException();

    }


    @Override
    public Z negative() {

        return new Z(-A.longValue());

    }


    @Override
    public Z times(RingNumber m) {

        if (m.TYPE == TYPE)
            return new Z(A.longValue() * m.A.longValue());

        throw new IncompatibleTypesException();

    }


    @Override
    public boolean isZero() {

        return Long.compare(A.longValue(), 0L) == 0;

    }


    @Override
    public String format() {

        return "" + A.longValue();

    }


    public Long getZ() {

        return A.longValue();

    }

   
}
