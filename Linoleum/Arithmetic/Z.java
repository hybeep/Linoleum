package Arithmetic;

import java.util.ArrayList;

final public class Z extends IdentityRingNumber {

    private Long A;

    private final Long zero = 0L;
    private final Long one = 1L;

    public Z() {

        this.A = zero;

    }

    public Z(Long A) {

        this.A = A;

    }

    public Z(Z m) {

        this.A = m.A();

    }

    public Z(Element k) {

        this.A = k.A().longValue();

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
    public Z plus(Summable m) {

        switch (m.type()) {

            case TYPE.INTEGER:
                return new Z(A + m.A().longValue());

            default:
                throw new IncompatibleTypesException();

        }

    }

    @Override
    public Z plus(ArrayList<Summable> l) {

        Z sum = this;

        for (Summable num : l)
            sum = sum.plus(num);

        return sum;

    }

    @Override
    public Z negative() {

        return new Z(-A);

    }

    @Override
    public Z minus(Subtractable b) {

        return plus(b.negative());

    }

    @Override
    public Z times(int n) {

        return new Z(n * A);

    }

    @Override
    public Z times(Multipliable m) {

        switch (m.type()) {

            case TYPE.INTEGER:
                return new Z(A * m.A().longValue());

            default:
                throw new IncompatibleTypesException();

        }

    }

    @Override
    public Z times(ArrayList<Multipliable> l) {

        Z prod = this;

        for (Multipliable num : l)
            prod = prod.times(num);

        return prod;

    }

    @Override
    public boolean isZero() {

        return Long.compare(A, zero) == 0;

    }

    @Override
    public String format() {

        return "" + A;

    }

    @Override
    public TYPE type() {

        return TYPE.INTEGER;

    }

    @Override
    public Long A() {

        return A;

    }

    @Override
    public Long B() {

        return one;

    }

    @Override
    public Long C() {

        return zero;

    }

    @Override
    public ArrayList<Number> extended_data() {

        return new ArrayList<Number>();

    }

}
