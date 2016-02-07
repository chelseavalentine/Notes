# Chapter 2: The basics of data structures

## Elementary data structures and their ADT abstractions

### Procedural ADTs

* __procedural ADT__: a data structure that's defined w/o code or structure
  - it's a set of data w/ fixed access utilities, such as a set

##### Stack

* __L__ast-__I__n-__F__irst-__O__ut (LIFO)
* methods: `push()`, `pop()`, `peek()`, `isEmpty()`, `isFull()`

##### Queue

* __F__irst-__I__n-__F__irst-__O__ut
* methods: `enqueue()`, `dequeue()`

##### Double-ended queue

* deletion & insertion can occur at either end (both `front` or `back`)

##### Set

* methods: `insert()`, `delete()`, `boolean member()`, `union()` (join two sets), `find()` (find set containing that element)

##### Dictionary

* __dictionary__: records stored in sorted order, order is determined by the key fields
  * implemented using a binary search tree
* methods:
  * `search(key, D)` returns a pointer to the record(s) w/ _key_ in their key field
  * `successor(key, D)` returns a pointer to the record in dictionary D w/ the smallest key exceeding the value _key_.
  * `contains()`, `insert()`, `remove()`

##### Priority queue

* has `deleteMin()` or `deleteMax()` depending on whether it's a priority minqueue or maxqueue 
* methods:
  * `changePriority(record_name, new_key_value)` changes the priority of _record_name_ to _new_key_value_, which uses `reducePriority(record_name, new_key_value)` b/c the change is usually to a lower value

##### Mappings

* a renaming function

##### List

* procedural lists have the operations of the set, plus `successor()` & `predecessor()` operations, and maybe `insertAfter(y, x)` which inserts _y_ after _x_, `prepend(x, L)`, `append(x, L)`



### Structural ADTs

* __structural ADT__: physical models of data structures;; there's no code or fixed rules about how a structure should be built, accessed, or described

##### List

* can be implemented w/ single links, double links, circular linking, as an array, as a tuple, etc. Pretty much as anything. So versatile wow.

##### Bitmaps

* an array of bits
  
  * usually used to help you find out whether an integer is in a list
    
    ``` 
    S = (0, 4, 7, 8, 9, 10, 13, 14)
    corresponding_bitmap = BitMap[0..15]
    Bitmap[1] returns 0 b/c no 1 in the list
    Bitmap[0] returns 1 b/c 0 is in the list
    ```

##### Trees

* __free tree__, __unrooted tree__: a tree without a notion of a root; essentially a graph



## Data structures and the hazards of implementation

## Tarjan's abstractions and conceptualizations

## Tree traversal

### Depth-first tree traversal

### Iterative depth-first tree traversal

### Breadth-first tree traversal

## Stepping through recursive algorithms

## Tree-based DFS solution methods

### DFS traversal of implicitly defined trees

### Iterative deepening: using DFS to simulate BFS

