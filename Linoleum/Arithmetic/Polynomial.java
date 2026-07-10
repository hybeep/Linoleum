package Arithmetic;


import Arithmetic.GroupNumber.TYPE;


import java.util.ArrayList;
import java.util.Collections;
import java.util.Iterator;


public class Polynomial<T extends RingNumber> {


    private ArrayList<RingNumber> coefficients;
    private int degree;


    public Polynomial(ArrayList<T> coefficients) {

        if (coefficients.size() == 0)
            throw new EmptyArrayException();


        Collections.reverse(coefficients);

        Iterator<T> it = coefficients.iterator();

        ArrayList<RingNumber> polRev = new ArrayList<>();

        boolean s = true;
        T next;

        while(it.hasNext()) {

            next = it.next();

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
        this.degree =  s ? -1 : polRev.size() - 1;

    }


    public Polynomial(Polynomial<T> polynomial) {

        this.coefficients = new ArrayList<>();
        for (RingNumber num : polynomial.coefficients)
            this.coefficients.add(num);

        this.degree = polynomial.degree;

    }


    public Polynomial<T> negative() {

        ArrayList<T> neg = new ArrayList<>();

        for (T num : coefficients)
            neg.add(num.negative());

        return new Polynomial(neg);

    }


    public Polynomial plus(Polynomial polynomial) {

        if (polynomial.TYPE != TYPE)
            throw new IncompatibleTypesException();

        int min, max;
        ArrayList<RingNumber> m, M;
        ArrayList<RingNumber> sum = new ArrayList<RingNumber>();


        if (degree == -1 && degree == polynomial.degree) {

            return new Polynomial(this.coefficients);

        } else if (degree <= polynomial.degree) {

            min = degree;
            m = coefficients;

            max = polynomial.degree;
            M = polynomial.coefficients;

        } else {

            min = polynomial.degree;
            m = polynomial.coefficients;

            max = degree;
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


    public Polynomial minus(Polynomial polynomial) {

        return plus(polynomial.negative());

    }


    public Polynomial times(Polynomial polynomial) {

        if (this.TYPE != polynomial.TYPE)
            throw new IncompatibleTypesException();


        if (degree == -1)
            return new Polynomial(coefficients);
        else if (polynomial.degree == -1)
            return new Polynomial(polynomial.coefficients);


        ArrayList<RingNumber> prod = new ArrayList<RingNumber>();

        int i, j;
        for(i = 0; i <= degree; i++) {

            for (j = 0; j <= polynomial.degree; j++) {

                if (i + j >= prod.size()) {

                    prod.add(coefficients.get(i).times(polynomial.coefficients.get(j)));

                } else {

                    prod.set(i + j, prod.get(i + j).plus(coefficients.get(i).times(polynomial.coefficients.get(j))));

                }

            }

        }

        return new Polynomial(prod);

    }


    public String format() {

        String str = "";

        int i;
        for (i = 0; i < coefficients.size(); i++) {

            str += coefficients.get(i).format();
            str += (i == 0) ? "" : "X^" + i;

        }

        return str;

    }


    public void print() {

        System.out.println(format());

    }


    public static Polynomial sum(Polynomial polynomial1, Polynomial polynomial2) {

        return polynomial1.plus(polynomial2);

    }


    public static Polynomial sum(ArrayList<Polynomial> polynomials) {

        if (polynomials.size() == 0)
            throw new EmptyArrayException();

        Iterator<Polynomial> it = polynomials.iterator();

        Polynomial sum = it.next();

        while (it.hasNext())
            sum = sum.plus(it.next());

        return sum;

    }

    public static Polynomial mult(Polynomial polynomial1, Polynomial polynomial2) {

        return polynomial1.times(polynomial2);

    }


    public static Polynomial mult(ArrayList<Polynomial> polynomials) {

        if (polynomials.size() == 0)
            throw new EmptyArrayException();

        Iterator<Polynomial> it = polynomials.iterator();

        Polynomial mult = it.next();

        while (it.hasNext())
            mult = mult.times(it.next());

        return mult;

    }

    
}
