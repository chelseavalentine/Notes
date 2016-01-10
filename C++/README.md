# Learning C++

Notes taken while reading __Jumping into C++__ by Alex Allain.



## Pointers

+ __pointer__: variable holding an address to a memory store (not necessarily of a diff. variable)
  + knowing this address allows you to hold onto memory from the OS
  + can refer to the memory address or the variable that stores the memory address

### Memory's structure

* __stack__: variables declared in functions that're currently being used
* __heap__ (aka. __free store__): requestable unallocated memory
* __owner__: the code responsible for a memory chunk
  * best practice: document whether function takes ownership of the memory @ a pointer's address
    * esp. important b/c memory can be returned to OS if you don't keep track of whether it's being used by another part of the program
* __memory leak__: result of not freeing memory when out of use
* __invalid memory__: memory that hasn't been allocated, and thus cannot be accessed (causes crashes)
  * can occur when you don't init pointers (—> data corruption or crashes)

### Pointer syntax

Pointer declaration:

``` c++
<type> *<pointer_name>
// or
<type>* <pointer_name>

/**
* However, if you're declaring multiple pointers, indicate that
* each is a pointer.
*/
int *pointer1, *pointer2, *pointer3;
// not
int* pointer1, nonpointer2, nonpointer3;
```

__Variable addresses__

* __address-of__ operator: `&`: returns a variable's memory address

``` c++
int x;
int *p_x = & x; // assign the vaddress of x to p_x
*p_x = 2; // initialize value of x to 2
```

How do we find out the memory address? Can we print the value that a pointer points to?

* __`*`__: appended to a pointer variable, this gives us the value of the variable pointed to. Follows the pointer to get the value stored in the memory
  * called __dereferencing the pointer__ because you're using the reference to the memory address & following it
  * you can also use this to change the value at a memory address (`*p_x = 7`)

``` c++
int x = 5;
int *p_x = & x;
// memory address of x
cout << p_x;
// value stored in x
cout << *p_x;
// change value stored at memory address
*p_x = 7;
cout << endl << "now it is " << *p_x << endl;
```

Note:

_You can set the address stored in a pointer, but you cannot set the address of a variable._

__You should initialize pointers before you use them__. Or mark it as uninitialized:

``` c++
int *p_x = NULL;
// then we can do something like
if (p_x != NULL) {
  // then...
  *p_x = 1;
  // or something
}
```

#### Pointers and functions

If we want to pass values into a function and have the actual values passed in change, then we can use pointers. Here, we are writing to the memory.

``` c++
void swap(int *p_x1, int *p_x2) {
  int temp = *p_x1;
  *p_x1 = *p_x2;
  *p_x2 = temp;
}
```

The following function would not do anything to the variables that we pass in, it would only utilize their values:

``` c++
void swap(int x1, int x2) {
  int temp = x1;
  x1 = x2;
  x2 = temp;
}
```

#### References

* __reference__: a variable referring to another variable, and shares the same backing memory
  * used like regular variables. It has a subset of the power of a pointer
    * the reference gives you the value of the referenced memory, not the memory address
  * has to refer to valid memory & must always be valid (not `NULL`)
  * behind the scenes, the compiler is using pointers to reference and dereference memory for you
  * ___after initializing a reference, you cannot change the memory it refers to!!___
  * declared with ampersand, like:

``` c++
int x = 5;
int &ref = x;
```

So we can pass a reference to a structure into a function, rather than the whole structure!:

``` c++
struct structure {
  int x[1000];
}
void wow(structure& struc1) {
  struc.x[1] = 4;
}
```

Also, references can make our earlier function look a lot cleaner!

``` c++
void swap(int& x1, int& x2) {
  int temp = x1;
  x1 = x2;
  x2 = temp;
}
```

It looks virtually the same as the second function, which didn't actually swap the value, but it actually works because of the parameter types are suffixed with `&`.

