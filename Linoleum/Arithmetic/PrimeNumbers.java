package Arithmetic;

import java.io.Serializable;
import java.io.ObjectOutputStream;
import java.io.ObjectInputStream;
import java.io.FileOutputStream;
import java.io.FileInputStream;
import java.io.IOException;
import java.io.FileNotFoundException;

import java.util.Map;
import java.util.Set;
import java.util.TreeMap;

public class PrimeNumbers implements Serializable {    

    private TreeMap<Long, Long> primes;
    private Long next;
    private Long sqrt;

    public PrimeNumbers() {

        this.primes = new TreeMap<>();
        this.primes.put(2L, 4L);
        this.next = 3L;
        this.sqrt = 1L;

    }

    public PrimeNumbers(String filename) throws FileNotFoundException, IOException, ClassNotFoundException, FileNotFoundException {

        if (!filename.matches(FILENAME_PATTERN))
            throw new FileNameNotValid();

        ObjectInputStream in = new ObjectInputStream(new FileInputStream(filename));
        PrimeNumbers pm = (PrimeNumbers) in.readObject();
        in.close();

        this.primes = pm.primes;
        this.next = pm.next;
        this.sqrt = pm.sqrt;
        
    }

    private boolean testNext() {

        boolean isPrime = true;

        while (sqrt * sqrt <= next)
            sqrt += 1L;

        sqrt -= 1L;

        Set<Map.Entry<Long, Long>> entries = primes.entrySet();
        for (Map.Entry<Long, Long> entry : entries) {

            Long k = entry.getKey();
            Long val_k = entry.getValue();

            if (k > sqrt)
                break;

            while (val_k < next)
                val_k += k;

            if (next.equals(val_k)) {

                isPrime = false;
                entry.setValue(val_k + k);
                break;

            } else {

                entry.setValue(val_k);

            }

        }

        return isPrime;

    }

    public void searchUntil(Long limit) {

        while (next <= limit) {

            if (testNext())
                primes.put(next, next * next);

            next += 2L;

        }

    }

    public boolean isPrime(Long n) {

        if (n < next)
            
            return primes.containsKey(n);

        else

            throw new UnknownPrimality();

    }

    public int getSize() {

        return primes.size();

    }

    public Long maxPrime() {

        return primes.lastKey();

    }

    public Long get(int n) {

        if (n < 0 || n >= getSize())
            throw new ArrayIndexOutOfBoundsException();

        Set<Long> keys = primes.keySet();
        return keys.toArray(new Long[0])[n];

    }

    public int indexOf(Long n) {

        if (!isPrime(n))
            throw new NotPrimeNumber();

        Set<Long> keys = primes.keySet();
        
        int index = 0;
        for (Long k : keys) {

            if (n.equals(k))
                break;

            index += 1;

        }

        return index;

    }

    public int save(String filename) throws FileNotFoundException, IOException {
        
        if (!filename.matches(FILENAME_PATTERN))
            throw new FileNameNotValid();

        FileOutputStream fos = new FileOutputStream(filename);
        ObjectOutputStream out = new ObjectOutputStream(fos);

        out.writeObject(this);
        out.flush();
        out.close();

        return 1;

    }

    private final String FILENAME_PATTERN = "^[a-zA-Z0-9_]+[.]{1}lpn$";

}
