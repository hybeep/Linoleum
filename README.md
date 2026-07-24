
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
This package contains the abstract classes GroupNumber, RingNumber, IdentityRingNumber, and DivisionRingNumber which
are constructed from the interfaces Element, Summable, Zero, Subtractable, Multipliable, Identity, and Invertible.

An object of a class that implements Element is a 'simple' immutable number. It has a TYPE and is totally determined by three
Number objects A, B, and C, together with an ArrayList\< Number \>. Types like *Z* (IdentityRingNumber) and *R* (DivisionRingNumber)
need only a Long or a Double object, respectively, to be represented. The classes *Q* (DivisionRingNumber) and *Zn* (IdentityRingNumber)
use A and B as Long objects to represent the numbers A/B and \[ A \] \_ B, respectively. Lastly, *C* (DivisionRingNumber) uses the Numbers
A, B and C as follows: if the Integer C is 0 then the Double values A and B represent a complex number of the form A + iB, otherwise
the complex number is Ae^iB. An element of the symmetric group would use the list to represent the permutation.

Objects of these classes can be constructed from the data they represent and from another object of the same type. The last
constructor forces the data of the argument to represent a number of the required type.

The methods of these interfaces are:
* Element
    * TYPE *type()*
    * Number *A()*
    * Number *B()*
    * Number *C()*
    * ArrayList\< Number \> *extended_data()*
    * String *format()*
    * void *print()*
* Summable extends Element
    * Summable *plus(Summable)*
    * Summable *plus(ArrayList\< Summable \>)*
* Zero extends Summable
    * Zero *zero()*
    * boolean *isZero()*
* Subtractable extends Zero
    * Subtractable *negative()*
* Multipliable extends Subtractable
    * Multipliable *times(Multipliable)*
    * Multipliable *times(ArrayList \< Multipliable \> )*
* Identity extends Multipliable
    * Identity *identity()*
* Invertible extends Identity
    * Invertible *inverse()*
    
The abstract classes override the returning type of the interfaces methods to be the same class. Additionally, these have the following methods:
* GroupNumber implements Subtractable
    * GroupNumber *minus(Subtractable)*
    * GroupNumber *times(int)*
* RingNumber implements Multipliable
    * The same as GroupNumber
* IdentityRingNumber implements Identity
    * The same as GroupNumber
* DivisionRingNumber implements Invertible
    * The same as GroupNumber
    * DivisionRingNumber *div(Invertible)*
    * DivisionRingNumber *pow(int)*

**Notes**
* A class Monoid could be defined by implementing the Zero interface
* The chain of interfaces above is constructed without assuming commutativity since it is not necessary for the operations, but in order to construct a Ring from Subtractable, the operation that makes the set a group is the sum, so it uses the method *plus()*.

Because the operations may not be defined for two arbitrary Element instances, they will only be performed when the types
are compatible, an object of *Z* can be summed to an object of *Q*, but in order to return the same type, the contrary is
not possible.

### Compound Numbers
To construct more complex numbers like direct sums, vectors, matrices or polynomials, the behavior of the interfaces and
classes above is mimed by interfaces and classes with the same names with Compound as prefix, so that Element becomes CompoundElement,
Summable becomes CompoundSummable, etcetera. 

The interface CompoundElement \< T extends Element \> has the methods:
* TYPE *type()*
* Number *A()*
* Number *B()*
* COMPOUND\_TYPE *compound_type()*
* ArrayList\< T \> *entries()*
* String *format()*
* void *print()*

The rest of the classes have the same methods as the 'simple' ones except for the returning type. The classes of these types are *GroupDirectSum* (CompoundGroupNumber), *RingDirectSum* (CompoundRingNumber), *IdentityRingDirectSum* (CompoundIdentityRingNumber), and *DivisionRingDirectSum* (CompoundDivisionRingNumber).


## To-Do
* The division ring *Zp* (Z modulo a prime number).
* Modules over a ring and Algebras: define Action of a ring on a group and on another ring.
* Matrices and polynomials (algebras) over an indentity ring. 
* Transform *Compound* into 'simple', so that polynomials of matrices can be created, for example.
* Finish Expression and Derivative


