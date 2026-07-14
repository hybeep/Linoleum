package Arithmetic;

public interface CompoundIdentity<T extends Identity> extends CompoundMultipliable<T> {

    CompoundIdentity<T> identity();
    
}
