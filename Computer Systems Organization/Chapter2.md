# Computer Systems Organization

Notes taken while reading _Computer Systems: A Programmer's Perspective (3rd ed)_.



## Chapter 2: Representing and Manipulating Information

* __unsigned__ encodings represent numbers `>= 0`
* __two's complement__ encodings represent __signed__ integers (numbers + or –)

### Information storage

* __bytes__ (8 bits) are the smallest addressable unit of memory
* __virtual memory__: a large array of bytes, with each given an address
  * __virtual address space__: the set of all possible addresses
* __program objects__: program data, iinstruction, & control information

#### Hexadecimal notation

* byte values range from 00000000₂ to 11111111₂, in base-10 notation, it's 0 to 255
* __hexadecimal numbers__: base-16 numbers that use digits `0-9` and characters `A-F`
  * each byte can take on values from 00₁₆ to FF₁₆
  * in C: hexadecimal numbers are denoted to machines by using `0x`, eg. `0x000000`
* hexadecimal → binary representation
  * eg. 0x163A4C becomes 00010111101001001100, because you convert each hexa-digit into its binary representation, and just append them together as if a string.

#### Data sizes

* __word size__: a computer's set size for pointer data (common word sizes are 64 bit & 32 bit)
  * the virtual address space's maximum size is `2^w - 1`, with a _w_-bit word size

| C declaration   |                  | Bytes    |          |
| --------------- | ---------------- | -------- | -------- |
| *Signed*        | *Unsigned*       | *32-bit* | *64-bit* |
| `[signed] char` | `unsigned char`  | 1        | 1        |
| `short`         | `unsigned short` | 2        | 2        |
| `int`           | `unsigned`       | 4        | 4        |
| `long`          |                  | 4        | 8        |
| `char *`        |                  | 4        | 8        |
| `float`         |                  | 4        | 4        |
| `double`        |                  | 8        | 8        |



###### Declaring pointers in C

``` c
// for data type T
T *p;
// eg.
char *p;
```



#### Addressing and byte ordering

#### Representing Strings

#### Representing code

#### Introduction to Boolean algebra

#### Bit-level operations in C

#### Logical operations in C

#### Shift operations in C



### Integer representation

### Integer arithmetic

### Floating point

