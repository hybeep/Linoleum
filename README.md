
# LINOLEUM

Linoleum is a formal maths calculator that abstracts mathematical objects. Some of these objects like numbers,
logical operators, sets or matrices are already implemented in computer science. The purpose of **Linoleum**
is to construct these concepts exactly as their formal definition. For example, the operations that can be
performed for two Long objects or for two Double objects are essentially the same, + and *, but we can go further
with Double, since they represent real numbers, an inverse can be computed for non-zero elements. In this case, 1/r
will do the trick, but we can define an inverse() method for an element of a division ring.
Then it results convenient to abstract the notions of a group, a ring, a ring with identity, and a division ring. 
This way we can define polynomials and matrices over ring (with identity), which are again rings (with identity),
and so on.

The Arithmetic package has the abstract classes GroupNumber, RingNumber, RingIdentityNumber, and DivisionRingNumber.
An object of a class that extends RingNumber is immutable, must have a TYPE and is totally represented by three
Number objects A, B, and C. Types like *Z* and *R* need only a Long or a Double object, respectively, to be represented.
The classes *Q* and *Zn* use A and B as Long objects to represent the numbers A/B and \[A\]\_B, respectively.
Lastly, *C* uses the Numbers A, B and C as follows: if the Integer C is 0 then the Double values A and B represent
a complex number of the form A + iB, otherwise the complex number is Ae^iB.

Objects of these classes can be constructed from the data they represent, from another object of the same type and from
any other RingNumber. The last constructor forces the data of the object so that it represents a number of the required
type. Also, these objects have the following methods: *zero()*, *plus(RingNumber)*, *negative()*, *minus(RingNumber)*,
*times(Long)*, *times(RingNumber)*, *pow(Long)*, *isZero()*, *format()*, *print()*, and the getters for TYPE, A, B, and C.

For RingIdentityNumber and DivisionRingNumber objects, one can compute *identity()*, *inverse()*, and *div(RingNumber)*.

Because the operations may not be defined for two arbitrary RingNumber objects, they will only be performed when the types
are compatible, for example elements from *Z*, *Q*, *R*, and *C* can be summed or multiplied, returning the result as an
object of the 'biggest' class, so these classes are compatible. When trying to perform an operation between two objects of
incompatible classes, an IncompatibleTypesException will be thrown.

Algebra package contains Polynomial and RingMatrix classes. 

An object of Polynomial is immutable, must have a TYPE, and is represented by an ArrayList\<RingNumber\> of length degree + 1.
An object of RingMatrix is also immutable, also have a TYPE, and is represented by an ArrayList\<RingNumber\> of length M * N,
where M is the number of rows and N is the number of columns.

When a Polynomial or a RingMatrix object is constructed, the TYPE is defined to be that of the first element of the ArrayList
argument, an empty list will cause an EmptyArrayException since the TYPE cannot be determined. A list with only zero entries
will produce the Polynomial of length 1, whose unique entry is zero and its degree is defined to be -1.

Once the TYPE has been determined, all of the elements added to the objects list must be of this same type, other types will
be treated as incompatible, even when the operations can be performed.

Two Polynomial objects with the same TYPE can be summed and multiplied with the methods *plus(Polynomial)* and *times(Polynomial)*.
They also have methods *negative()*, *minus(Polynomial)*, *format()*, and *print()*.

Methods with the same name and behavior are defined for RingMatrix objects. These objects can also be transposed with *transpose()*.  
