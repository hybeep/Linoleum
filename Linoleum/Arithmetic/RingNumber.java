package Arithmetic;

import java.util.ArrayList;

public abstract class RingNumber implements Multipliable {

    @Override
    public abstract RingNumber plus(Summable b);

    @Override
    public abstract RingNumber plus(ArrayList<Summable> l);

    @Override
    public abstract RingNumber zero();

    @Override
    public abstract RingNumber negative();

    @Override
    public abstract RingNumber minus(Subtractable b);

    @Override
    public abstract RingNumber times(int n);

    @Override
    public abstract RingNumber times(Multipliable b);

    @Override
    public abstract RingNumber times(ArrayList<Multipliable> l);

    @Override
    final public void print() {

        System.out.println(format());

    } 

}
