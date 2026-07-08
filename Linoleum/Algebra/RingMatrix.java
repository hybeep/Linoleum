package Algebra;

import Arithmetic.IncompatibleTypesException;
import Arithmetic.RingNumber;


import java.util.ArrayList;
import java.util.Iterator;

public class RingMatrix {
    

    private String TYPE;
    private int M, N; //M rows, N cols
    private ArrayList<RingNumber> entries;

    public RingMatrix(ArrayList<RingNumber> nums, int M, int N) {

        if (M <= 0 || N <= 0)
            throw new InvalidMatrixSizeException();

        if (nums.size() == 0)
            throw new EmptyArrayException();

        this.TYPE = nums.get(0).getType();

        Iterator<RingNumber> it = nums.iterator();

        int size = M * N;

        this.entries = new ArrayList<>();

        while (this.entries.size() < size) {

            if (it.hasNext()) {

                RingNumber next = it.next();

                if (next.getType() != TYPE)
                    throw new IncompatibleTypesException();

                this.entries.add(it.next());

            } else {

                this.entries.add(nums.get(0).zero());

            }

        }

    }


    public RingMatrix negative() {

        ArrayList<RingNumber> neg = new ArrayList<>();

        for(RingNumber num : entries)
            neg.add(num.negative());

        return new RingMatrix(neg, M, N);

    }


    public RingMatrix transpose() {

        ArrayList<RingNumber> trans = new ArrayList<>();

        int i, j;

        for (i = 0; i < N; i++) {

            for (j = 0; j < M; j++) {

                trans.add(entries.get(i + j * N));

            }

        }

        return new RingMatrix(trans, N, M);

    }


    public RingMatrix plus(RingMatrix mat) {

        if (mat.TYPE != TYPE)
            throw new IncompatibleTypesException();

        if (mat.N != N || mat.M != M)
            throw new InvalidMatrixSizeException();
            
        ArrayList<RingNumber> sum = new ArrayList<>();

        int size = M * N;

        int i;
        for (i = 0; i < size; i++)
            sum.add(entries.get(i).plus(mat.entries.get(i)));

        return new RingMatrix(sum, M, N);

    }


    public RingMatrix minus(RingMatrix mat) {

        return plus(mat.negative());

    }


    public RingMatrix times(RingNumber r) {

        if (r.getType() != TYPE)
            throw new IncompatibleTypesException();

        ArrayList<RingNumber> prod = new ArrayList<>();

        for (RingNumber num : entries)
            prod.add(r.times(num));

        return new RingMatrix(prod, M, N);

    }


    public RingMatrix times(RingMatrix mat) {

        if (mat.TYPE != TYPE)
            throw new IncompatibleTypesException();

        if (mat.M != N)
            throw new InvalidMatrixSizeException();

        ArrayList<RingNumber> prod = new ArrayList<>();

        RingMatrix matTrans = mat.transpose();

        int i, j, k;

        for (i = 0; i < M; i++) {

            for (j = 0; j < matTrans.M; j++) {

                RingNumber prod_ij = entries.get(i * N).times(matTrans.entries.get(j * N));

                for (k = 1; k < N; k++) {

                    prod_ij = prod_ij.plus(entries.get(i * N + k).times(matTrans.entries.get(j * N + k)));

                }

                prod.add(prod_ij);

            }

        }

        return new RingMatrix(prod, M, mat.N);

    }


    public static RingMatrix sum(RingMatrix mat1, RingMatrix mat2) {

        return mat1.plus(mat2);

    }


    public static RingMatrix sum(ArrayList<RingMatrix> matrices) {

        if (matrices.size() == 0)
            throw new EmptyArrayException();

        
        Iterator<RingMatrix> it = matrices.iterator();

        RingMatrix sum = it.next();

        while(it.hasNext())
            sum = sum.plus(it.next());

        return sum;

    }


    public static RingMatrix mult(RingMatrix mat1, RingMatrix mat2) {

        return mat1.times(mat2);

    }


    public static RingMatrix mult(ArrayList<RingMatrix> matrices) {

        if (matrices.size() == 0)
            throw new EmptyArrayException();

        
        Iterator<RingMatrix> it = matrices.iterator();

        RingMatrix mult = it.next();

        while(it.hasNext())
            mult = mult.times(it.next());

        return mult;

    }


}
