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

import Error.FileNameNotValid;


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


    public PrimeNumbers(String filename) throws FileNotFoundException, IOException, ClassNotFoundException, FileNotFoundException{

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

        while (this.sqrt * this.sqrt <= this.next)
            this.sqrt += 1;

        this.sqrt -= 1;

        Set<Map.Entry<Long, Long>> entries = this.primes.entrySet();
        for (Map.Entry<Long, Long> entry : entries) {

            Long i = entry.getKey();

            if (i > this.sqrt)
                break;

            Long val_i = entry.getValue();

            while (val_i < this.next)
                val_i += i;

            if (this.next.equals(val_i)) {

                isPrime = false;
                primes.put(i, val_i + i);

            } else {

                primes.put(i, val_i);

            }

        }

        return isPrime;

    }


    public void searchUntil(Long limit) {

        while (this.next <= limit) {

            if (testNext())
                primes.put(this.next, 2 * this.next);

            this.next += 2L;

        }

    }


    public Set<Long> get() {

        return this.primes.keySet();

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
