package Arithmetic;


import java.util.ArrayList;
import java.util.Iterator;


public class RingDirectSum extends RingNumber {


    ArrayList<RingNumber> entries;


    public RingDirectSum(ArrayList<RingNumber> entries) {

        if (entries.size() == 0)
            throw new EmptyArrayException();

        this.TYPE = entries.get(0).TYPE;
        this.A = entries.size();
        this.B = 0;
        this.C = 0;

        this.entries = entries;
        
    }


    public RingDirectSum(RingDirectSum dirSum) {

        this.TYPE = dirSum.TYPE;
        this.A = dirSum.A;
        this.B = 0;
        this.C = 0;

        this.entries = dirSum.entries;

    }


    @Override
    public RingDirectSum zero() {

        ArrayList<RingNumber> zero = new ArrayList<>();

        for (RingNumber num : entries)
            zero.add(num.zero());

        return new RingDirectSum(zero);

    }


    @Override
    public RingDirectSum plus(RingNumber s) {

        if (s.TYPE != RingNumber.TYPE_CODE.DIRECTSUM)
            throw new IncompatibleTypesException();

        ArrayList<RingNumber> sum = new ArrayList<>();

        Iterator<RingNumber> it = entries.iterator();
        Iterator<RingNumber> itS = s..iterator();

        while (it.hasNext()) {

            if
            sum.add();


        }



    }


    
}
