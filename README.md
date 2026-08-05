
# LINOLEUM

Linoleum is a formal maths calculator that abstracts mathematical objects. Some of these objects like numbers,
logical operators, sets or matrices are already implemented in computer science. The purpose of **Linoleum**
is to construct these concepts exactly as their formal definition. For example, the operations that can be
performed for two Long objects or for two Double objects are essentially the same, \+ and \*, but we can go further
with Double, since they represent real numbers, an inverse can be computed for non-zero elements. In this case, 1/r
will do the trick, but we can define an inverse() method for an element of a division ring.
Then it results convenient to abstract the notions of a group, a ring, a ring with identity, and a division ring. 
This way we can define polynomials and matrices over a ring (with identity), which are again rings (with identity),
and so on.

## The Arithmetic package
This package contains the abstract classes **GroupNumber**, **RingNumber**, **IdentityRingNumber**, and **DivisionRingNumber** which
are constructed from the interfaces **Element**, **Summable**, **Zero**, **Subtractable**, **Multipliable**, **Identity**, and **Invertible**.

An object of a class that implements **Element** is a 'simple' immutable number. It has a **TYPE** and is totally determined by three
Number objects A, B, and C, together with an ArrayList\<Number\>. Types like **Z** (TYPE.INTEGER) and **R** (TYPE.REAL)
need only a Long or a Double object, respectively, to be represented. The classes **Q** (TYPE.RATIONAL), **Zn** (TYPE.MODN), and **Zp** (TYPE.MODP) 
use A and B as Long objects to represent the numbers A/B and \[A\]\_B, respectively. Lastly, **C** (TYPE.COMPLEX) uses the Numbers
A, B and C as follows: if the Integer C is 0 then the Double values A and B represent a complex number of the form A+iB, otherwise
the complex number is Ae^iB. An element of the symmetric group would use the list to represent the permutation.

Objects of these classes can be constructed from the data they represent or from another object of the same type. A constructor
with an **Element** parameter is defined for each class and forces the data of the argument to represent a number of the required type.

The methods of these interfaces are:
* **Element**
    * TYPE `type()`
    * Number `A()`
    * Number `B()`
    * Number `C()`
    * ArrayList\<Number\> `extended_data()`
    * String `format()`
    * void `print()`
* **Summable** *extends* **Element**
    * Summable `plus(Summable)`
    * Summable `plus(ArrayList<Summable>)`
* **Zero** *extends* **Summable**
    * Zero `zero()`
    * boolean `isZero()`
* **Subtractable** *extends* **Zero**
    * Subtractable `negative()`
    * Subtractable `minus(Subtractable)`
    * Subtractable `times(int)`
* **Multipliable** *extends* **Subtractable**
    * Multipliable `times(Multipliable)`
    * Multipliable `times(ArrayList <Multipliable>)`
* **Identity** *extends* **Multipliable**
    * Identity `identity()`
    * boolean `isIdentity()`
* **Invertible** *extends* **Identity**
    * Invertible `inverse()`
    * Invertible `div(Invertible)`
    * Invertible `pow(int)`
    
Each child interface overrides the returning type of the parents methods to be the same child interface. 

The abstract classes **GroupNumber**, **RingNumber**, **IdentityRingNumber**, and **DivisionRingNumber** implement the interfaces **Subtractable**, **Multipliable**, **Identity**, and **Invertible**, respectively.

The final classes **Z**, **Zn**, **Zp**, **Q**, **R**, and **C**, inherit the abstract classes above.

**Notes**
* A class **Monoid** could be defined by implementing the **Zero** interface
* The chain of interfaces above is constructed without assuming commutativity since it is not necessary for the operations, but in order to construct a ring from **Subtractable**, the operation that makes the set a group is the sum, so it uses the method `plus()`.

Because the operations may not be defined for two arbitrary **Element** instances, they will only be performed when the types
are compatible, an object of **Z** can be summed to an object of **Q**, but in order to return the same type, the contrary is
not possible.

### Compound Numbers
To construct more complex numbers like direct sums, vectors, matrices or polynomials, the behavior of the interfaces and
classes above is mimed by interfaces and classes with the same names but with *Compound* as a prefix, so that **Element** becomes **CompoundElement**,
**Summable** becomes **CompoundSummable**, etcetera. 

The chain of these interfaces is defined as follows:
* **CompoundElement\<T *extends* Element\>**
    * TYPE `type()`
    * Number `A()`
    * Number `B()`
    * COMPOUND\_TYPE `compound_type()`
    * ArrayList\<T\> `entries()`
    * String `format()`
    * void `print()`
* **CompoundSummable\<T *extends* Summable\>** *extends* **CompoundElement\<T\>**
    * CompoundSummable\<T\> `plus(CompoundSummable<T>)`
    * CompoundSummable\<T\> `plus(ArrayList<CompoundSummable<T>>)`
* **CompoundZero\<T *extends* Zero\>** *extends* **CompoundSummable\<T\>**
    * CompoundZero\<T\> `zero()`
    * boolean `isZero()`
* **CompoundSubtractable\<T *extends* Subtractable\>** *extends* **CompoundZero\<T\>**
    * CompoundSubtractable\<T\> `negative()`
    * CompoundSubtractable\<T\> `minus(CompoundSubtractable<T>)`
    * CompoundSubtractable\<T\> `times(int)`
* **CompoundMultipliable\<T *extends* Multipliable\>** *extends* **CompoundSubtractable\<T\>**
    * CompoundMultipliable\<T\> `times(CompoundMultipliable<T>)`
    * CompoundMultipliable\<T\> `times(ArrayList<CompoundMultipliable<T>>)`
* **CompoundIdentity\<T *extends* Identity\>** *extends* **CompoundMultipliable\<T\>**
    * CompoundIdentity\<T\> `identity()`
    * boolean `isIdentity()`
* **CompoundInvertible\<T *extends* Invertible\>** *extends* **CompoundIdentity\<T\>**
    * CompoundInvertible\<T\> `inverse()`
    * CompoundInvertible\<T\> `div(CompoundInvertible<T>)`
    * CompoundInvertible\<T\> `pow(int)`

In order to construct modules and algebras, the following interfaces are defined:
* **CompoundSubtractActable\<T *extends* Subtractable\>** *extends* **CompoundSubtractable\<T\>**
    * CompoundSubtractActable\<T\> `action(Multipliable)`
* **CompoundMultiplyActable\<T *extends* Multipliable\>** *extends* **CompoundMultipliable\<T\>**
    * CompoundMultiplyActable\<T\> `action(Multipliable)`


The abstract classes of these types are constructed as follows:
* **CompoundGroupNumber** *implements* **CompoundSubtractable\<Subtractable\>**
* **CompoundRingNumber** *implements* **CompoundMultipliable\<Multipliable\>**
* **CompoundIdentityRingNumber** *implements* **CompoundIdentity\<Identity\>**
* **CompoundDivisionRingNumber** *implements* **CompoundInvertible\<Invertible\>**
* **CompoundModuleNumber** *implements* **CompoundSubtractActable\<Subtractable\>**
* **CompoundAlgebraNumber** *implements* **CompoundMultiplyActable\<Multipliable\>**

The final classes are defined as follows:
*  **GroupDirectSum** *implements* **CompoundGroupNumber**
* **RingDirectSum** *implements* **CompoundRingNumber**
* **IdentityRingDirectSum** *implements* **CompoundIdentityRingNumber**
* **DivisionRingDirectSum** *implements* **CompoundDivisionRingNumber**
* **RingMatrix** *extends* **CompoundAlgebraNumber**
* **Polynomial** *extends* **CompoundAlgebraNumber**

### Division Algorithm
Given two integer numbers **A** and **B**, the class constructors `DivisionAlgorithm(Long, Long)` creates an object whose fields are the integers needed to complete the equation **AC=BQ+R**, where initially **C=1**.

The private method `Long[] GCD()` returns `[C,Q,R]`, which satisfy the equation **CA+QB=R**, where **R** is the greatest common divisor of **A** and **B**.

To compute **(A,B)** use the static method `Long[] gcd(Long, Long)`.


### Prime Numbers
The class **PrimeNumbers** computes the integer prime numbers in a range.

* **Constructors**
    * `PrimeNumbers()`
    * `PrimeNumbers(String)`: the parameter is the name of a **.lpn** file.
* **Methods**
    * void `searchUntil(Long)`
    * boolean `isPrime(Long)`
    * int `getSize()`
    * Long `maxPrime()`
    * Long `get(int)`
    * int `indexOf(Long)`: returns the index of the parameter if it is a prime number, else throws **NotPrimeNumber** or **UnknownPrimality**.
    * int `save(String)`: writes the object to a **.lpn** file.

An object of this class is used to check if a number is prime in order to create an instance of **Zp**.

## To-Do
* Finish Expression and Derivative


