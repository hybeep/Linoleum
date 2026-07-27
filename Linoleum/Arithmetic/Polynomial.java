package Arithmetic;

import java.util.ArrayList;
import java.util.Collections;
import java.util.Iterator;

public class Polynomial extends CompoundAlgebraNumber {

    private TYPE type;
    private ArrayList<Multipliable> coefficients;
    private int dgr;

    public Polynomial(ArrayList<Multipliable> coefficients) {

        if (coefficients.size() == 0)
            throw new EmptyArrayException();

        Collections.reverse(coefficients);
        this.type = coefficients.get(0).type();

        Iterator<Multipliable> it = coefficients.iterator();
        ArrayList<Multipliable> polRev = new ArrayList<>();

        boolean s = true;
        Multipliable next;

        while(it.hasNext()) {

            next = it.next();

            if (next.type() != type())
                throw new IncompatibleTypesException();

            if (s) {

                if (!next.isZero()) {

                    polRev.add(next);
                    s = false;
            
                } else if(!it.hasNext()) {

                    polRev.add(next);

                }

            } else {

                polRev.add(next);

            }

        }

        Collections.reverse(polRev);
        
        this.coefficients = polRev;
        this.dgr =  s ? -1 : polRev.size() - 1;

    }

    public Polynomial(Polynomial b) {

        this.type = b.type;
        this.coefficients = b.coefficients;
        this.dgr = b.dgr;

    }

    @Override
    public Polynomial plus(CompoundSummable<Multipliable> b) {

        if (b.compound_type() != compound_type())
            throw new IncompatibleCompoundTypesException();

        if (b.type() != type)
            throw new IncompatibleTypesException();

        Polynomial bPol = new Polynomial(b.entries());

        int min, max;
        ArrayList<Multipliable> m, M;
        ArrayList<Multipliable> sum = new ArrayList<>();

        if (dgr == -1 && dgr == bPol.dgr) {

            return new Polynomial(this.coefficients);

        } else if (dgr <= bPol.dgr) {

            min = dgr;
            m = coefficients;

            max = bPol.dgr;
            M = bPol.coefficients;

        } else {

            min = bPol.dgr;
            m = bPol.coefficients;

            max = dgr;
            M = coefficients;

        }

        int i;

        for (i = 0; i <= min; i++) {

            sum.add(M.get(i).plus(m.get(i)));

        }

        for (i = min + 1; i <= max; i++) {

            sum.add(M.get(i));

        }

        return new Polynomial(sum);

    }

    @Override
    public Polynomial plus(ArrayList<CompoundSummable<Multipliable>> l) {

        Polynomial sum = this;

        for (CompoundSummable<Multipliable> b : l)
            sum = sum.plus(b);

        return sum;

    }

    @Override
    public Polynomial zero() {

        ArrayList<Multipliable> zero = new ArrayList<>();
        zero.add(coefficients.get(0).zero());
        return new Polynomial(zero);

    }

    @Override
    public boolean isZero() {

        boolean isZero = true;

        for (Multipliable num : coefficients)
            if (!num.isZero()) {
                isZero = false;
                break;
            }

        return isZero;

    }

    @Override
    public Polynomial negative() {

        ArrayList<Multipliable> neg = new ArrayList<>();

        for (Multipliable num : coefficients)
            neg.add(num.negative());

        return new Polynomial(neg);

    }

    @Override
    public Polynomial minus(CompoundSubtractable<Multipliable> b) {

        return plus(b.negative());

    }

    @Override
    public Polynomial times(int n) {

        ArrayList<Multipliable> prod = new ArrayList<>();

        for (Multipliable num : coefficients)
            prod.add(num.times(n));

        return new Polynomial(prod);

    }

    @Override
    public Polynomial times(CompoundMultipliable<Multipliable> b) {

        if (b.compound_type() != compound_type())
            throw new IncompatibleCompoundTypesException();

        if (b.type() != type)
            throw new IncompatibleTypesException();

        Polynomial bPol = new Polynomial(b.entries());

        if (dgr == -1) {

            return new Polynomial(coefficients);

        } else if (bPol.dgr == -1) {

            return new Polynomial(bPol.coefficients);
        
        }

        ArrayList<Multipliable> prod = new ArrayList<>();

        int i, j;
        for(i = 0; i <= dgr; i++) {

            for (j = 0; j <= bPol.dgr; j++) {

                if (i + j >= prod.size()) {

                    prod.add(coefficients.get(i).times(bPol.coefficients.get(j)));

                } else {

                    prod.set(i + j, prod.get(i + j).plus(coefficients.get(i).times(bPol.coefficients.get(j))));

                }

            }

        }

        return new Polynomial(prod);

    }

    @Override
    public Polynomial times(ArrayList<CompoundMultipliable<Multipliable>> l) {

        Polynomial prod = this;

        for (CompoundMultipliable<Multipliable> b : l)
            prod = prod.times(b);

        return prod;

    }

    @Override
    public Polynomial action(Multipliable b) {

        ArrayList<Multipliable> prod = new ArrayList<>();

        for (Multipliable num : coefficients)
            prod.add(num.times(b));

        return new Polynomial(prod);

    }

    @Override
    public TYPE type() {

        return type;

    }

    @Override
    public Integer A() {

        return dgr;

    }

    @Override
    public Integer B() {

        return 0;

    }

    @Override
    public COMPOUND_TYPE compound_type() {

        return COMPOUND_TYPE.POLYNOMIAL;

    }

    @Override
    public ArrayList<Multipliable> entries() {

        return coefficients;

    }

    @Override
    public String format() {

        String str = "";

        int i;
        for (i = 0; i <= dgr; i++) {

            str += coefficients.get(i).format();
            str += (i == 0) ? "" : "X^" + i;

        }

        return str;

    }
    
}
