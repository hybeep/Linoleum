package Arithmetic;

public class Zn extends RingIdentityNumber {

    // [A]_B

    private Long zero = 0L;
    private Long one = 1L;


    public Zn(Long Z, Long N) {

        this.type = GroupNumber.TYPE.MODN;
        this.A = Z;
        this.B = N >= 0L ? N : -N;
        this.C = 0;
        reduce();

    }


    public Zn(Zn c) {

        this.type = GroupNumber.TYPE.MODN;
        this.A = c.A.longValue();
        this.B = c.B.longValue();
        this.C = 0;

    }


    public Zn(GroupNumber a) {

        this.type = GroupNumber.TYPE.MODN;
        
        Zn aZn = new Zn(a.A.longValue(), a.B.longValue());

        this.A = aZn.A.longValue();
        this.B = aZn.B.longValue();
        this.C = 0;


    }


    private void reduce() {

        while(A.longValue() < zero)
            A = A.longValue() + B.longValue();

        while(B.longValue() <= A.longValue())
            A = A.longValue() - B.longValue();

    }


    @Override
    public Zn zero() {

        return new Zn(zero, B.longValue());

    }


    @Override
    public Zn identity() {

        return new Zn(one, B.longValue());

    }


    @Override
    public Zn plus(GroupNumber c) {

        if (c.type != type || Long.compare(c.B.longValue(), B.longValue()) != 0)
            throw new IncompatibleTypesException();
        
        return new Zn(A.longValue() + c.A.longValue(), B.longValue());

    }


    @Override
    public Zn negative() {

        return new Zn(-A.longValue(), B.longValue());

    }


    @Override
    public Zn times(RingNumber c) {

        if (c.type != type || Long.compare(c.B.longValue(), B.longValue()) != 0)
            throw new IncompatibleTypesException();
        
        return new Zn(A.longValue() * c.A.longValue(), B.longValue());
        
    }


    @Override
    public boolean isZero() {

        return Long.compare(A.longValue(), zero) == 0;

    }


    @Override
    public String format() {

        return "[" + A.longValue()+ "]_" + B.longValue();

    }

    
    public Long getZ() {

        return A.longValue();

    }


    public Long getN() {

        return B.longValue();

    }
    
    
}
