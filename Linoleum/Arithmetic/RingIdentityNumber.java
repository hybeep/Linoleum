package Arithmetic;


public abstract class RingIdentityNumber extends RingNumber {


    @Override
    public abstract RingIdentityNumber zero();


    @Override
    public abstract RingIdentityNumber plus(GroupNumber s);


    @Override
    public abstract RingIdentityNumber negative();


    @Override
    public RingIdentityNumber minus(GroupNumber m) {
        
        return plus(m.negative());
    
    }
    

    @Override
    public RingIdentityNumber times(int n) {

        RingIdentityNumber bs;

        if (n == 0L) {

            bs = zero();
            return bs;

        } else if (n < 0L) {

            bs = this.negative();
            n = -n;

        } else {

            bs = this;

        }

        RingIdentityNumber prod = bs;
        int i;
        for (i = 1; i < n; i++)
            prod = prod.plus(bs);

        return prod;

    }


    public abstract RingIdentityNumber identity();


    @Override
    public abstract RingIdentityNumber times(RingNumber m);


}
