# Computer Systems Organization

Notes taken while reading _Computer Systems: A Programmer's Perspective (3rd ed)_.



## Chapter 2: Representing & Manipulating Information

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
| `long`          | `unsigned long`  | 4        | 8        |
| `char *`        |                  | 4        | 8        |
| `float`         |                  | 4        | 4        |
| `double`        |                  | 8        | 8        |

The compiler won't always treat the data type as `signed`, if you want it to, then you must declare `signed [data type]`

Can declare in multiple ways (all of the following are identical)

``` c
unsigned long
unsigned long int
long unsigned
long unsigned int
```

###### Declaring pointers in C

``` c
// for data type T
T *p;
// eg.
char *p;
```

#### Addressing and byte ordering

* multi-byte objects are stored as a contiguous sequence of bytes in memory
* _little endian_: storing bytes from least to most significant
  * _big endian_: storing bytes from most to least significant
  * why does this even matter?: if you're sending data over from a machine w/ the reverse ordering
    * thus, in networking, the data is usually converted  to match the network standard
  * why does this even matter?: when reading machine-level programs

###### "Aliases" (`typedef`) in C

``` c
typedef int *int_pointer
// allows you to declare a pointer to an int like this, which is semantically better
int_pointer ip;
```

* also you can cast pointers in C… or maybe it's only `void *` pointers… you'll probably figure it out

#### Representing Strings

* __string__: array of characters terminated by `null` (ideally, if the memory is really full or something, you can't count on there being a null at the end, it could be another in-use memory chunk)

#### Bit-level operations in C

* _masking operations_ w/ bit-level operations: mask selects a part of the bits in the word using a bit pattern (everything but the pattern becomes 0s)

#### Shift operations in C

* consider an operand _x_ that has a bit representation of x, sub …, w-1, w, w-2,.. and so on, doing `x << k` will do w-k-1, w-k, w-k-2,… and so on. `x >> k` will do w+k-1, w+k, w+k-2, and so on.
  * `x << 4` will add 4 0's to the end of the value, shifting the rest 4 to the left
    * 01100011 becomes 0011_0000_
  * `x >> 4` would add 4 0's to the beginning of the value, shifting the rest 4 to the right

### Integer representations

#### Integral data types

Typical ranges for C integral data types for 64-bit programs

| C data type      | Minimum          | Maximum          |
| ---------------- | ---------------- | ---------------- |
| `[signed] char`  | -128             | 127              |
| `unsigned char`  | 0                | 255              |
| `short`          | -32,768          | 32,767           |
| `unsigned short` | 0                | 65,535           |
| `int`            | ≈-2.15 million   | ≈2.15 million    |
| `unsigned`       | 0                | ≈4.30 million    |
| `long`           | ≈-9,223 trillion | ≈9.223 trillion  |
| `unsigned long`  | 0                | ≈18.446 trillion |

### Integer arithmetic

* Encodings
* Two's complement encodings

#### Unsigned addition

* overflows occur when the sum goes above the max of the data type
  * it's not a C error when overflows occur, but you probably want to check for them
  * there can be positive & negative overflow (p. 90)

##### Two's-complement multiplication

| Mode             |    x     |    y     |    x • y    | Truncated x • y |
| ---------------- | :------: | :------: | :---------: | :-------------: |
| Unsigned         | 5 [101]  | 3 [011]  | 15 [001111] |     7 [111]     |
| Two's complement | -3 [101] | 3 [011]  |     -9      |    -1 [111]     |
| Unsigned         | 4 [100]  | 7 [111]  | 28 [011100] |     4 [100]     |
| Two's complement | -4 [100] | -1 [111] | 4 [000100]  |    -4 [100]     |
| Unsigned         | 3 [001]  | 3 [011]  | 9 [001001]  |     1 [001]     |
| Two's complement | 3 [011]  | 3 [011]  | 9 [001001]  |     1 [001]     |

* Multiplying by constants is just shifting the bits over to the left (p. 101)

* Dividing by constants is just shifting bits over to the right (p. 104)

  * It's x/2^k rounded down in bit form

  * C expression that compules value x/2^k is

    ```c
     (x<0 ? x+(1<<k)-1 : x) >>k
    ```



### Floating point

* ​