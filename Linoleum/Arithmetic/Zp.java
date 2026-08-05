package Arithmetic;

import java.util.ArrayList;

final public class Zp extends DivisionRingNumber {

    private Long A, B;
    private PrimeNumbers pn;

    private Long zero = 0L;
    private Long one = 1L;

    public Zp(Long Z, Long P, PrimeNumbers pn) {

        if (P < 0)
            P = -P;

        if (!pn.isPrime(P))
            throw new NotPrimeNumber();

        this.A = Z;
        this.B = P;
        reduce();

        this.pn = pn;

    }

    public Zp(Zp b) {

        this.A = b.A;
        this.B = b.B;
        this.pn = b.pn;

    }

    public Zp(Element b) {

        Long A = b.A().longValue();
        Long B = b.B().longValue();

        PrimeNumbers pn = new PrimeNumbers();
        pn.searchUntil(B);

        Zp aux = new Zp(A,B,pn);

        this.A = aux.A;
        this.B = aux.B;
        this.pn = pn;

    }

    private void reduce() {

        while(A < zero)
            A += B;

        while(B <= A)
            A -= B;

    }

    @Override
    public Zp zero() {
        
        return new Zp(zero, B, pn);

    }

    @Override
    public Zp identity() {
        
        return new Zp(one, B, pn);
        
    }

    @Override
    public Zp plus(Summable b) {
        
        if (b.type() != TYPE.MODP || B != b.B().longValue())
            throw new IncompatibleTypesException();

        return new Zp(A + b.A().longValue(), B, pn);

    }

    @Override
    public Zp plus(ArrayList<Summable> l) {

        Zp sum = this;
        for (Summable num : l)
            sum = sum.plus(num);

        return sum;

    }

    @Override
    public Zp negative() {
       
        return new Zp(-A, B, pn);

    }

    @Override
    public Zp minus(Subtractable b) {
        
        return plus(b.negative());

    }

    @Override
    public Zp times(int n) {
        
        return new Zp(n * A, B, pn);

    }

    @Override
    public Zp times(Multipliable b) {
        
        if (b.type() != TYPE.MODP || B != b.B().longValue())
            throw new IncompatibleTypesException();

        return new Zp(A * b.A().longValue(), B, pn);

    }

    @Override
    public Zp times(ArrayList<Multipliable> l) {

        Zp prod = this;
        for (Multipliable num : l)
            prod = prod.times(num);

        return prod;

    }

    @Override
    public Zp inverse() {
        
        if (A.equals(zero))
            throw new DivideByZeroException();

        Long inv = DivisionAlgorithm.gcd(A, B)[0];

        return new Zp(inv, B, pn);

    }

    @Override
    public Zp div(Invertible b) {
        
        return times(b.inverse());

    }

    @Override
    public Zp pow(int n) {

        if (isZero())
            if (n > 0)
                return zero();
            else
                throw new DivideByZeroException();

        if (n == 0 || isIdentity())
            return identity();

        Zp pow;
        
        Zp s;
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

        return Long.compare(A, zero) == 0;

    }

    @Override
    public boolean isIdentity() {

        return Long.compare(A, one) == 0;

    }

    @Override
    public String format() {

        return "[" + A+ "]_" + B;

    }

    @Override
    public TYPE type() {

        return TYPE.MODP;

    }
    
    @Override
    public Long A() {

        return A;

    }

    @Override
    public Long B() {

        return B;

    }

    @Override
    public Long C() {

        return one;

    }

    @Override
    public ArrayList<Number> extended_data() {

        return new ArrayList<Number>();

    }

}
