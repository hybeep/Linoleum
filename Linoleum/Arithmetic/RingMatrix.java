package Arithmetic;

import java.util.ArrayList;
import java.util.Iterator;

public class RingMatrix extends CompoundAlgebraNumber {
    
    private TYPE type;
    private Integer M, N; //M rows, N cols
    private ArrayList<Multipliable> entries;

    public RingMatrix(ArrayList<Multipliable> nums, int M, int N) {

        if (M <= 0 || N <= 0)
            throw new InvalidMatrixSizeException();

        if (nums.size() == 0)
            throw new EmptyArrayException();

        this.type = nums.get(0).type();
        this.M = M;
        this.N = N;

        Iterator<Multipliable> it = nums.iterator();

        int size = M * N;

        this.entries = new ArrayList<>();

        while (this.entries.size() < size) {

            if (it.hasNext()) {

                Multipliable next = it.next();

                if (next.type() != type)
                    throw new IncompatibleTypesException();

                this.entries.add(next);

            } else {

                this.entries.add(nums.get(0).zero());

            }

        }

    }

    public RingMatrix transpose() {

        ArrayList<Multipliable> trans = new ArrayList<>();

        int i, j;

        for (i = 0; i < N; i++) {

            for (j = 0; j < M; j++) {

                trans.add(entries.get(i + j * N));

            }

        }

        return new RingMatrix(trans, N, M);

    }

    @Override
    public RingMatrix plus(CompoundSummable<Multipliable> b) {

        if (b.compound_type() != compound_type())
            throw new IncompatibleCompoundTypesException();

        if (b.type() != type)
            throw new IncompatibleTypesException();

        if (b.A() != M || b.B() != N)
            throw new InvalidMatrixSizesException();
            
        ArrayList<Multipliable> sum = new ArrayList<>();
        ArrayList<Multipliable> bEntries = b.entries();

        int size = M * N;

        int i;
        for (i = 0; i < size; i++)
            sum.add(entries.get(i).plus(bEntries.get(i)));

        return new RingMatrix(sum, M, N);

    }

    @Override
    public RingMatrix plus(ArrayList<CompoundSummable<Multipliable>> l) {
        
        RingMatrix sum = this;

        for (CompoundSummable<Multipliable> mat : l)
            sum = sum.plus(mat);

        return sum;

    }

    @Override
    public RingMatrix zero() {

        ArrayList<Multipliable> zero = new ArrayList<>();

        for (Multipliable num : entries)
            zero.add(num.zero());

        return new RingMatrix(zero, M, N);

    }

    @Override
    public boolean isZero() {

        boolean isZero = true;

        for (Multipliable num : entries)
            if (!num.isZero()) {
                isZero = false;
                break;
            }

        return isZero;

    }

    @Override
    public RingMatrix negative() {

        ArrayList<Multipliable> neg = new ArrayList<>();

        for(Multipliable num : entries)
            neg.add(num.negative());

        return new RingMatrix(neg, M, N);

    }

    @Override
    public RingMatrix minus(CompoundSubtractable<Multipliable> b) {

        return plus(b.negative());

    }

    @Override
    public RingMatrix times(int n) {

        ArrayList<Multipliable> mult = new ArrayList<>();

        for (Multipliable num : entries)
            mult.add(num.times(n));

        return new RingMatrix(mult, M, N);

    }

    @Override
    public RingMatrix times(CompoundMultipliable<Multipliable> b) {

        if (b.compound_type() != compound_type())
            throw new IncompatibleCompoundTypesException();

        if (b.type() != type)
            throw new IncompatibleTypesException();

        if (b.A() != N)
            throw new InvalidMatrixSizesException();

        RingMatrix bMat = new RingMatrix(b.entries(), b.A().intValue(), b.B().intValue());
        RingMatrix matTrans = bMat.transpose();
        
        ArrayList<Multipliable> prod = new ArrayList<>();
        ArrayList<Multipliable> matTEntries = matTrans.entries();

        int i, j, k;

        for (i = 0; i < M; i++) {

            for (j = 0; j < matTrans.M; j++) {

                Multipliable prod_ij = entries.get(i * N).times(matTEntries.get(j * N));

                for (k = 1; k < N; k++) {

                    prod_ij = prod_ij.plus(entries.get(i * N + k).times(matTrans.entries.get(j * N + k)));

                }

                prod.add(prod_ij);

            }

        }

        return new RingMatrix(prod, M, bMat.N);

    }

    @Override
    public RingMatrix times(ArrayList<CompoundMultipliable<Multipliable>> l) {
        
        RingMatrix mult = this;

        for (CompoundMultipliable<Multipliable> mat : l)
            mult = mult.times(mat);

        return mult;

    }

    @Override
    public RingMatrix action(Multipliable b) {

        ArrayList<Multipliable> mult = new ArrayList<>();

        for (Multipliable num : entries)
            mult.add(num.times(b));

        return new RingMatrix(mult, M, N);

    }

    @Override
    public TYPE type() {

        return this.type;

    }

    @Override
    public Number A() {

        return this.M;

    }

    @Override
    public Number B() {

        return this.N;

    }

    @Override
    public COMPOUND_TYPE compound_type() {

        return COMPOUND_TYPE.MATRIX;

    }

    @Override
    public ArrayList<Multipliable> entries() {

        return this.entries;

    }

    @Override
    public String format() {

        ArrayList<String> matStr = new ArrayList<>();

        for (Multipliable num : entries)
            matStr.add(num.format());

        int maxLength = 0;
        for (String s : matStr)
            if (s.length() > maxLength)
                maxLength = s.length();

        String format = "%" + maxLength + "." + maxLength + "s";
        String matFormat = "";
        int i, j;
        for (i = 0; i < M; i++) {

            for (j = 0; j < N; j++) {

                matFormat += String.format(format, matStr.get(i * N + j));
                matFormat += " ";

            }

            matFormat += "\n";

        }

        return matFormat;

    }

}