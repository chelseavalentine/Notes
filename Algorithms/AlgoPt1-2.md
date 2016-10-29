# Algorithms, pt 1

### Analysis of Algorithms

#### Mathematical models

* can test the efficiency of & time it takes for an algorithm via a mathematical model:
  1. plot points on a running time (_s_) vs. problem size (_N_) graph
  2. linearize it (usually a log-log plot linearization)
  3. slope of straight line gives you variable _b_ in the power law: a * N^b
  - can also get _b_ by taking the log of a ratio of N and 2N's time, it eventually converges to a constant

* not all triple loops have cubic running times
  ```java
  for (int i = 0; i < N; i++) {
    for (int j = i + 1; j < N; j++) {
      for (int k = 1; k < N; k *= 2) {
        if (a[i] + a[j] >= a[k]) {
          sum++;
        }
      }
    }
  }
  ```

  ~(3/2)(N^2)logN array accesses

#### Order-of-growth classifications

* common order-of-growth classifications: 1, logN, N, NlogN, N^2, N^3, and 2^N (running times are proportional to one of these)

| Order of growth | Name         | Description        | Example           | T(2N)/T(N) |
| :-------------: | ------------ | ------------------ | ----------------- | :--------: |
|        1        | constant     | statement          | add 2 numbers     |     1      |
|      logN       | logarithmic  | divide in half     | binary search     |     ~1     |
|        N        | linear       | loop               | find the max      |     2      |
|      NlogN      | linearithmic | divide and conquer | merge sort        |     ~2     |
|       N^2       | quadric      | double loop        | check all pairs   |     4      |
|       N^3       | cubic        | triple loop        | check all triples |     8      |
|       2^N       | exponential  | exhaustive search  | check all subsets |    T(N)    |

##### Binary search

Assuming a sorted array,

1. compare key (value you're looking for) against middle entry

2. depending on the comparison:
   * if too small -> go left (divide array)
   * if too big -> go right
   * if equal -> found

Iterative implementation of binary search

```java
public static int binarySearch(int[] a, int key) {
  int low = 0;
  int high = a.length - 1;

  while (low <= high) {
    int mid = low + (high - low)/2;

    if (key < a[mid]) {
      high = mid - 1;
    } else if (key > a[mid]) {
      low = mid + 1;
    } else {
      return mid;
    }
  }

  return -1;
}
```

#### Theory of algorithms

* approaches to algorithm design:
  1. approach 1: design for worse-case scenario
  2. approach 2: randomize, depend on probabilistic guarantee
  - average case (expected cost)

Commonly used notations in theory of algorithms

| Notation  | Provides                   | Example | Used to                       |
| --------- | -------------------------- | :-----: | ----------------------------- |
| Big Theta | asymptotic order of growth |  Θ(N²)  | classify algorithms           |
| Big Oh    | Θ(N²) and smaller          |  O(N²)  | develop upper bounds          |
| Big Omega | Θ(N²) and larger           |  Ω(N²)  | develop lower bounds          |
| Tilde     | Leading term               |  10N²   | provides an approximate model |

#### Memory

##### Basics

* __bit__: 0 or 1
* __byte__: 8 bits
* __megabyte__ (MB): 2^20 bytes, or 1 million bytes
* __gigabyte__ (GB): 2^30 bytes, or 1 billion bytes

Primitive types' memory usage (in bytes):

* boolean = 1 byte
* byte = 1 byte
* char = 2 bytes
* int = 4 bytes
* float = 4 bytes
* long = 8 bytes
* double = 8 bytes

Some arrays:

* char[] = 2N + 24 bytes
* int[] = 4N + 24 bytes
* double[] = 8N + 24 bytes
* char[][] = ~2MN
* int[][] = ~4MN
* double[][] = ~8MN

Memory usage for objects:

* Object overhead (16 bytes) + Reference (8 bytes) + Padding (each object uses a multiple of 8 bytes) + any instance variables = memory usage
  - add another 8 if it's an inner class (for pointer to the enclosing class)

### Stacks and Queues

#### Stacks

* fundamental data types include the following methods: `insert`, `remove`, `iterate`, `isEmpty`
* __client__: a program using the operations defined in an interface
* __interface__: a description of a data type, and its basic operations

##### Linked list implementation

```java
public class LinkedStackOfStrings {
  private Node first = null;

  private class Node {
    String item;
    Node next;
  }

  public boolean isEmpty() {
    return first == null;
  }

  public void push(String item) {
    Node old = first;
    first = new Node();
    first.item = item;
    first.next = old;
  }

  public String pop() {
    String item = first.item;
    first = first.next;
    return item;
  }
}
```

* every operation takes constant time in the worst case
* uses extra time & space to deal with the links

##### Array implementation

```java
public class ArrayOfStrings {
  private String[] s;
  private int N = 0;

  public ArrayOfStrings(int capacity) {
    s = new String[capacity];
  }

  public boolean isEmpty() {
    return N == 0;
  }

  public void pop() {
    String item = S[--N];
    S[N] = null;

    if (N > 0 && N == s.length/4) {
      resize(s.length/2);
    }

    return item;
  }

  private void resize(int capacity) {
    String[] copy = new String[capacity];

    for (int i = 0; i < N; i++) {
      copy[i] = s[i];
    }

    s = copy;
  }

  public void push(String item) {
    if (N == s.length) {
      resize(2 * s.length);
    }

    S[N++] = item;
  }
}
```

* every option takes constant amortized time
* less wasted space
* __amortized analysis__: average running time per operation over a worst-case sequence of operations

#### Queues

```java
pubic class LinkedQueueOfStrings {
  private Node first;
  private Node last;

  private class Node {
    String item;
    Node next;
  }

  public void enqueue(String item) {
    Node oldLast = last;
    last = new Node();
    last.item = item;
    last.next = null;

    if (isEmpty()) {
      first = last;
    } else {
      oldLast.next = last;
    }
  }

  public void dequeue() {
    String item = first.item;
    first = first.next;

    if (isEmpty()) {
      last = null;
    }

    return item;
  }
}
```

#### Generics

* benefits:
  - avoids making clients have to cast
  - discovers type mismatch errors at compile time, not runtime









### Geometric applications of BSTs

#### 1d range search

* What is in a rectangle? How many rectangles intersect? etc.
  - used in games, movies, databases, and virtual reality

#### Linear segment intersection

#### k-d trees

* trees w/ _k_ dimensions
* 2d trees: networking, databases, circuit designs

#### Internal search trees

* data structures holding a set of overlapping intervals

### Hash tables

Implements an associative array w/ keys mapped to values.

#### Hash functions

* requirement: if x.equals(y), then x.hashCode() = y.hashCode()

##### Hash code design (user-defined)

Combine each significant field using the 31x + y rule

* if the field is a primitive type, use the wrapper's `hashCode()`
* if the field is null, return 0
* if the field is a reference type, use `hashCode()`
* if the field is an array, apply it to each entry

Hash functions return an int between 0 and M-1, where M is a prime power of 2, so:

```java
private int hash(Key key) {
  // 0x7fffffff makes the hash code positive
  return (Key.hashCode() & 0x7fffffff) % M;
}
```

A hash code is an integer between -2^31 and 2^31 - 1.

#### Separate chaining

* __problem__: 2 distinct keys hashing to the same index
* __solution__: separate chaining
  - use an array of M (< N) inked lists
    + hash: map keys to integer i, where 0 ≤ i ≤ M-1
    + insert: put @ front of i^th chain, if it's not already there
    + search: only need to search the i^th chain

```java
public class SeparateChainingHashST<Key, Value> {
  // Number of chains
  private int M = 97;

  // Array of chains (array doubling & halving code is omitted)
  private Node[] st = new Node[M];

  private static class Node {
    // declared object b/c no generic array creation allowed
    private Object key;
    private Object val;
    private Node next;
  }

  private int hash(Key key) {
    return (key.hashCode() & 0x7fffffff) % M;
  }

  public Value get(Key key) {
    int i = hash(key);

    for (Node x = st[i]; x != null; x = x.next) {
      if (key.equals(x.key)) {
        return (Value) x.val;
      }
    }

    return null;
  }

  public void put(Key key, Value val) {
    int i = hash(key);

    for (Node x = st[i]; x != null; x = x.next) {
      if (key.equals(x.key)) {
        x.val = val;
        return;
      }
    }

    st[i] = new Node(key, val, st[i]);
  }
}
```

* because we have uniform hashing, the number of keys per list is w/i a constant factor, `N/M`, which is extremely close to 1
  - so search & insert take proportional to N/M time (M times faster than sequential search)
    + if M is too large --> too many empty chains
    + if M is too small --> chains are too long
    + thus, optimal choice: M ~N/5 --> constant time operations

__Analysis__: separate chaining

* worst-case of search, insert, delete: logN
* average case: 3-5
* ordered iteration: No

#### Linear probing

* __problem__: we need to deal with hashing collisions
* __solution__: linear probing
  - uses __open addressing__: when a new key collides, find the next empty slot and put it there
    + therefore the size of the array is bigger than the expected keys (eg. M = 301, N = 150)

Linear probing algorithm:

* hash: map key to integer _i_ between 0 and `M-1`
* insert: put at table index _i_ if it's free, if not, try i+1, i+2, etc.
* search: search table index _i_, if occupied but no match, try i+1, i+2, etc.

It's especially important to keep the array at least half-empty.

* if M is too large --> too many empty arrays
* if M is too small --> search time balloons
* optimal: ∝ = N/M ~ 1/2 for a size of N=∝M
  - therefore, a search hit ~3/2 & miss is ~5/2

```java
public class LinearProbingHashSt<Key, Value> {
  private int M = 30001;
  private Value[] vals = (Value[]) new Object[M];
  private Key[] keys = (Key[]) new Object[M];

  private int hash(Key key) { ... }

  public void put(Key key, Value val) {
    int i;

    for (i = hash(key); keys[i] != null; i = (i + 1) % M) {
      if (keys[i].equals(key)) {
        break;
      }
    }

    key[i] = key;
    vals[i] = val;
  }

  public Value get(Key key) {
    for (int i = hash(key); keys[i] != null; i = (i + 1) % M) {
      if (key.equals(keys[i])) {
        return vals[i];
      }

      return null;
    }
  }
}
```

__Analysis__: Linear probing

* worst-case of search, insert, & delete: logN
* average case: 3-5
* ordered iteration: No

#### Hash table context

* Why is the uniform hashing assumption important?
  - DOS (denial-of-service attacks), because if they learn your hash function, they can pile up values in a single slot to halt performance

Hash tables vs. balanced search trees:

* hash tables
  - simpler to code
  - no effective solution for unordered keys
  - faster for simple keys
  - better system support in Java for strings

* balanced search trees
  - stronger performance guarantee
  - support for ordered ST operation
  - easier to implement `compareTo()` correctly than `equals()` and `hashCode()`
