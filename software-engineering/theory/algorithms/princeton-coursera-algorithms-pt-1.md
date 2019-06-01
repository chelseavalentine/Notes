# Algorithms, pt 1

### Union-Find Problem

#### Dynamic connectivity

* __Problem__: given a set of _N_ objects,
  - _union command_: connect 2 objects
  - _find/connected query_: is there a path connecting the 2 objects?

##### Equivalence relations: RST & Modeling connections

* __R: Reflexive__: _p_ is connected to _q_
* __S: Systemmetric__: if _p_ is connected to _q_, then _q_ is connected to _p_
* __T: Transitive__: if _p_ is connected to _q_, and _q_ is connected to _r_, then _p_ is connected to _r_

* __connected components__: maximal set of objects that're mutually connected (can be sets of 1)

##### Quick-find implementation (1): See if 2 values are the same

* defects:
  - unions are too expensive, since N array accesses
  - trees are flat, but it's too expensive to keep them flat

```java
public class QuickFindUF {
  private int[] id;

  // O(N)
  public QuickFindUF(int N) {
    id = new int[N];

    for (int i = 0; i < N; i++) {
      id[i] = i;
    }
  }

  // O(1)
  public boolean connected(int p, int q) {
    return id[p] == id[q];
  }

  // O(N)
  // Change all element values to match if 2 elements
  // are in a connected component
  public void union(int p, int q) {
    int pid = id[p];
    int qid = id[q];

    for (int i = 0; i < id.length; i++) {
      if (id[i] == pid) {
        id[i] == qid;
      }
    }
  }
}
```

* as computers get faster & bigger, quadratic time algorithms get slower:
  - why? computers can do 10^9 ops/s, so 10^9 -> 10^18 operations = 30 years

##### Quick-union implementation

* defects:
  - trees can get tall
  - find (`connected`) is too expensive b/c N accesses in worst case

```java
public class QuickUnionUF {
  private int[] id;

  // O(N)
  public QuickUnionUF(int N) {
    id = new int[N];

    for (int i = 0; i < N; i++) {
      id[i] = 1;
    }
  }

  private int root(int i) {
    while (i != id[i]) {
      i = id[i];
    }

    return i;
  }

  // O(N)
  public boolean connected(int p, int q) {
    return root(p) == root(q);
  }

  // O(N) (includes cost of finding roots)
  public void union(int p, int q) {
    int i = root(p);
    int j = root(q);
    id[i] = j;
  }
}
```

##### Quick-union improvements

1. __weighting__
*   avoid tall long trees by keeping track of size of each tree, & linking the smaller tree to the root of the longer tree
*   code changes:
    - initialization: maintain `size[i]` array to count # objects in tree rooted @ `i`
    - find: same
    - union:
      ```java
      int i = root(p);
      int j = root(q);

      if (i == j) return;

      if (size[i] < size[j]) {
        id[i] = j;
        size[j] += size[i];
      } else {
        id[j] = i;
        size[i] += size[j];
      }
      ```
2. __path compression__ (almost linear)
*   after computing _p_'s root, make all nodes along the path to the root point directly to the root

*   code change:
    ```java
      private int root(int i) {
        while (i != id[i]) {
          id[i] = id[id[i]]; // additional line
          i = id[i]
        }

        return i;
      }
    ```

    _M_ union-find operations on a set of _N_ objects

    | Algorithm                      | Worst-case time |
    | ------------------------------ | :-------------: |
    | Quick-find                     |      M * N      |
    | Quick-union                    |      M * N      |
    | Weighted QU                    |   N + M logN    |
    | QU + Path compression          |   N + M logN    |
    | Weighted QU + Path compression |   N + M lg*N    |

    `*` <- iterative log function; # times you need to take logN to get 1; N = 2^65536, lg*N = 5; therefore < 5

##### Union-find application

* percolation: a system percolates iff top & bottom are connected by open sites (there is a path made via open sites)

* Monte Carlo method: doing repeated random sampling to obtain numerical results    ​

### Analysis of Algorithms

#### Mathematical models

- can test the efficiency of & time it takes for an algorithm via a mathematical model:

  1. plot points on a running time (_s_) vs. problem size (_N_) graph
  2. linearize it (usually a log-log plot linearization)
  3. slope of straight line gives you variable _b_ in the power law: a * N^b

  - can also get _b_ by taking the log of a ratio of N and 2N's time, it eventually converges to a constant

- not all triple loops have cubic running times

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

- common order-of-growth classifications: 1, logN, N, NlogN, N^2, N^3, and 2^N (running times are proportional to one of these)

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
   - if too small -> go left (divide array)
   - if too big -> go right
   - if equal -> found

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

- approaches to algorithm design:

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

- __bit__: 0 or 1
- __byte__: 8 bits
- __megabyte__ (MB): 2^20 bytes, or 1 million bytes
- __gigabyte__ (GB): 2^30 bytes, or 1 billion bytes

Primitive types' memory usage (in bytes):

- boolean = 1 byte
- byte = 1 byte
- char = 2 bytes
- int = 4 bytes
- float = 4 bytes
- long = 8 bytes
- double = 8 bytes

Some arrays:

- char[] = 2N + 24 bytes
- int[] = 4N + 24 bytes
- double[] = 8N + 24 bytes
- char[][] = ~2MN
- int[][] = ~4MN
- double[][] = ~8MN

Memory usage for objects:

- Object overhead (16 bytes) + Reference (8 bytes) + Padding (each object uses a multiple of 8 bytes) + any instance variables = memory usage
  - add another 8 if it's an inner class (for pointer to the enclosing class)

### Stacks and Queues

#### Stacks

- fundamental data types include the following methods: `insert`, `remove`, `iterate`, `isEmpty`
- __client__: a program using the operations defined in an interface
- __interface__: a description of a data type, and its basic operations

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

- every operation takes constant time in the worst case
- uses extra time & space to deal with the links

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

- every option takes constant amortized time
- less wasted space
- __amortized analysis__: average running time per operation over a worst-case sequence of operations

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

- benefits:
  - avoids making clients have to cast
  - discovers type mismatch errors at compile time, not runtime

##### Generic implementation of a stack

```java
public class Stack<Item> {
  private Node first = null;

  private class Node {
    Item item;
    Node next;
  }

  public boolean isEmpty() {
    return first == null;
  }

  public void push(Item item) {
    Node old = first;
    first = new Node();
    first.item = item;
    first.next = old;
  }

  public Item pop() {
    Item item = first.item;
    first = first.next;
    return item;
  }
}
```

##### Array implementation of a stack

```java
public class ArrayStack<Item> {
  private Item[] s;
  private int N = 0;

  public ArrayStack(int capacity) {
    s = (Item[]) new Object[capacity];
  }

  public boolean isEmpty() {
    return N == 0;
  }

  public void push(Item item) {
    S[N++] = item;
  }

  public Item pop() {
    return s[--N];
  }
}
```

#### Iterators

If you make stack implement the `Iterable` interface, then you can use the for-each statement

##### Linked list stack's iterator

```java
public Iterator<Item> iterator() {
  return new ListIterator();
}

private class ListIterator implements Iterator<Item> {
  private Node current = first;

  public boolean hasNext() {
    return current != null;
  }

  public void remove() { ... }

  public Item next() {
    Item item = current.item;
    current = current.next;
    return item;
  }
}
```

##### Array stack's iterator

```java
public Iterator<Item> iterator() {
  return new ReverseArrayIterator();
}

private class ReverseArrayIterator implements Iterator<Item> {
  private int i = N;

  public boolean hasNext() {
    return i > 0;
  }

  public void remove() { ... }

  public Item next() {
    return s[--i];
  }
}
```

- `Bag` is used when we're just adding to a collection, and order doesn't matter
  - is implemented as a stack w/o pop, or a queue w/o dequeue

### Elementary sorts

- callback: a reference to executable code
- __insertion sort__: iterate through an array, swapping array[i] with each larger entry to its left
  - is about 2x as fast as selection sort, because about 1/2 of the time, there aren't larger entries to its left
    - insertion sort: ~N²/4 vs. selection sort: ~N²/2
- __inversion__: a pair of keys that are out of order
  - an array is __partially sorted__ if inversions ≤ cN for some constant _c_

#### Shellsort

- __shellsort__: move entries more than 1 position at a time by h-sorting the array
  - where _h_ is the number of entries back that you can compare and move each element with
  - best numbers to use haven't been discovered; you can set h to 7, 3, and then 1, or make h = 3x + 1

```java
public class Shellsort {
  public static void sort(Comparable[] a) {
    int N = a.length;
    int h = 1;

    while (h < N/3) {
      h = 3 * h + 1; // 1, 4, 13, 40, ...
    }

    while (h >= 1) {
      for (int i = h; i < N; i++) {
        for (j = i; j <=  && less(a[j], a[j - h]); j = -h) {
          exchange(a, j, j-h);
        }
      }

      h /= 3;
    }
  }
}
```

- __h-sorted array__: _h_ interleaved sorted subsequences
  - you h-sort by using insertion sort, but go _h_ steps instead of 1

#### Shuffling

- __shuffle sort__ (linear time shuffling): shuffling a set/list/array elements. In iteration _i_ pick integer _r_, between 0 and _i_ uniformly and at random. Then swap `a[i]` and `a[r]`.

```java
public static void shuffle(Object[] a) {
  int N = a.length;

  for (int i = 0; i < N; i++) {
    int r = StdRandom.uniform(i + 1);
    exchange(a, i, r);
  }
}
```

#### Convex hull

- __convex hull__: for N points, the convex hull is the smallest perimeter fence enclosing the points

### Mergesort

- A Java sort for objects. An O(NlogN) algorithm, because divide takes logN and merging takes N.
- uses extra space proportional to N, because of the auxiliary array, vs. an in-place sort
  - __in-place sorting algorithm__: an algorithm using ≤ c*logN extra memory for some constant _c_

#### Mergesort

```java
public class Merge {
  private static void sort(Comparable[] a, Comparable[] aux, int lo, int hi) {
    if (hi <= lo) {
      return;
    }

    int mid = lo + (hi-lo)/2;

    sort(a, aux, lo, mid);
    sort(a, aux, mid + 1, hi);
    merge(a, aux, lo, mid, hi);
  }

  public static void sort(Comparable[] a) {
    aux = new Comparable[a.length];
    sort(a, aux, 0, a.length - 1);
  }

  private static void merge(Comparable[] a, Comparable[] aux, int lo, int mid, int hi) {
    assert isSorted(a, lo, mid);
    assert isSorted(a, mid + 1, hi);

    for (int k = 0 lo; k <= hi; k++) {
      aux[k] = a[k];
    }

    int i = lo;
    int j = mid + 1;

    for (int k = lo; k <= hi; k++) {
      if (i > mid) {
        a[k] = aux[j++];
      } else if (j > hi) {
        a[k] = aux[i++];
      } else if (less(aux[j], aux[i])) {
        a[k] = aux[j++];
      } else {
        a[k] = aux[i++];
      }
    }

    assert isSorted(a, lo, hi);
  }
}
```

#### Mergesort improvement 1

Switch to insertion sort for smaller arrays (eg. ≤ 7 items)

```java
private static void sort(Comparable[] a, Comparable[] aux, int lo, int hi) {
  if (hi <= lo + CUTOFF - 1) {
    Insertion.sort(a, lo, hi);
    return;
  }

  // The rest is the same as before
}
```

#### Mergesort improvement 2

Stop sorting if it's already sorted.

```java
private static void sort(Comparable[] a, Comparable[] aux, int lo, int hi) {
  if (hi <= lo) {
    return;
  }

  int mid = lo + (hi - lo)/2;

  sort(a, aux, lo, mid);
  sort(a, aux, mid + 1, hi);

  if (!less(a[mid + 1], a[mid])) {
    return;
  }

  merge(a, aux, lo, mi, hi);
}
```

#### Bottom-up Merge Sort

Pass through the array and merge sub-arrays of size 1. Repeat for size 2, 4, 8...

- Doesn't involve recursion
  - but uses extra space ∝ N for the auxiliary array
  - ~NlogN total, logN for pass over input, N compares

```java
public class MergeBU {
  private static Comparable[] aux;
  //...

  public static void sort(Comparable[] a) {
    int N = a.length;
    aux = new Comparable[N];

    // N
    for (int size = 1; size < N; size = size * 2) {
      // logN
      for (int lo = 0; lo < N - size; lo += 2 * size) {
        merge(a, lo, lo + size - 1, Math.min(lo + size + size - 1, N - 1));
      }
    }
  }
}
```

#### Comparators

- `Comparator` interface allows you to sort in an alternate order
- to extend sorting algorithm implementation to support comparators, use `Object[]` instead of `Comparable[]`

eg. insertion sort with comparators

```java
public static void sort(Object[] a, Comparator comparator) {
  int N = a.length;

  for (int i = 0; i < N; i++) {
    for (int j = i; j > 0 && less(comparator, a[j], a[j - 1]); j--) {
      exchange(a, j, j - 1);
    }
  }
}

private static boolean less(Comparator c, Object v, Object w) {
  return c.compare(v, w) < 0;
}

private static void exchange(Object[] a, int i, int j) {
  Object swap = a[i];
  a[i] = a[j];
  a[j] = swap;
}
```

In your object that implements `Comparator`,

```java
public class Student {
  public static final Comparator<Student> BY_NAME = new ByName();
  private final String name;
  private final int section;
  // ...

  private static class ByName implements Comparator<Student> {
    public int compare(Student v, Student w) {
      return v.name.compareTo(w.name);
    }
  }
}
```

#### Stability

- __stable sort__ preserves the relative order of items with equal keys
  - eg. sort by name and then by age --> same age people will still be sorted by name
- stable sorts: insertion sort, merge sort
- unstable sorts: selection sort, shellsort
  - since they both more elements long-distances

### Quicksort

A sort for primitive types.

Algorithm:

1. Randomly shuffle the array
2. Partition the array, such that for some `j`,

- entry `a[j]` is in place
- there's no larger entry to the left of `j`
  - there's no smaller entry to the right of `j`

3. Sort each piece recursively

Properties:

- sorts in place
- shuffling is needed for performance guarantee against the worst case
- ~1.39NlogN average case
  - does > (+39%) compares than mergesort, but is faster in practice due to less data movement
- implementation can be quadratic if array is sorted/reverse sorted, or has many dupes

*depth of recursion: logarithmic extra space

#### Quicksort

Phase 1: repeat until `i` and `j` cross

- scan `i` from left to right, so long as `(a[i] < a[lo])`
- scan `j` from right to left, so long as `(a[j] > a[lo])`
- exchange `a[i]` with `a[j]` (implies that both elements `i` and `j` are on the wrong sides)

Phase 2: when the pointers cross

- exchange `a[lo]` (the chosen key) with `a[j]`

```java
public static void sort(Comparable[] a) {
  StdRandom.shuffle(a);
  sort(a, 0, a.length - 1);
}

private static int partition(Comparable[] a, int lo, int hi) {
  int i = lo;
  j = hi + 1;

  while (true) {
    // Find items on the left to swap
    while (less(a[++i], a[lo])) {
      if (i == hi) break;
    }

    // Find items on the right to swap
    while (less(a[lo], a[--j])) {
      if (j == lo) break;
    }

    if (i >= j) break;

    exchange(a, i, j);
  }

  // swap with the partitioning item
  exchange(a, lo, j);

  // return the index of the item that's now known to be in place
  return j;
}

private static void sort(Comparable[] a, int lo, int hi) {
  if (hi <= lo) return;

  int j = partition(a, lo, hi);

  sort(a, lo, j - 1);
  sort(a, j + 1, hi);
}
```

#### Quicksort improvement 1

Insertion sort small arrays (length of ~ ≤ 10-20).

```java
private static void sort(Comparable[] a, int lo, int hi) {
  if (hi <= lo + CUTOFF - 1) {
    Insertion.sort(a, lo, hi);
    return;
  }

  int j = partition(a, lo, hi);

  sort(a, lo, j - 1);
  sort(a, j - 1, hi);
}
```

#### Quicksort improvement 2

Choose the median of a random sample as a pivot.

```java
private static void sort(Comparable[] a, int lo, int hi) {
  if (hi <= lo) return;

  int m = medianOf3(a, lo, lo + (hi - lo) / 2, hi);

  swap(a, lo, m);
  // ...
}
```

#### Selection: Quick-select algorithm

Given an array of N items, it finds the kth smallest item, with an average of ~2N time.

Quick-select algorithm:

- entry `a[j]` is in place
- there's no larger entry to the left of `j`
- there's no smaller entry to the right of `j`

It repeats in one sub-array, depending on `j`, and it finishes when `j == k`

```java
public static Comparable select(Comparable[] a, int k) {
  StdRandom.shuffle(a);

  int lo = 0;
  int hi = a.length - 1;

  while (hi > lo) {
    int j = partition(a, lo, hi);

    if (j < k) {
      lo = j + 1;
    } else if (j > k) {
      hi = j - 1;
    } else {
      return a[k];
    }
  }

  return a[k];
}
```

#### Duplicate keys

In the context of Quicksort:

- problem: duplicate keys --> quadratic time if you partition equal keys, too
  - possible improvement: Dijkstra's 3-way partition
    - let `v` be the partitioning item `a[lo]`;
    - scan `i` from left to right:
      - `(a[i] < v)`: exchange `a[lt]` w/ `a[i]`; increment both `lt` and `i`
      - `(a[i] > v)`: exchange `a[gt]` w/ `a[i]`; decrement `gt`
      - `(a[i] == v)`: increment `i`
- __entropy-optimal__: w/e the distribution of the equal keys is, a # of compares ∝ to the best you can possibly do is used
  - Quicksort w/ 3-way partitioning is both entropy-optimal, and often ~N

#### System sorts

| Sort        | In-place? | Stable? | Worst | Average | Best  | Remarks                                            |
| ----------- | :-------: | :-----: | :---: | :-----: | :---: | -------------------------------------------------- |
| Selection   |     √     |         | N²/2  |  N²/2   | N²/2  | N exchanges                                        |
| Insertion   |     √     |    √    | N²/2  |  N²/4   |   N   | use for small N, or partially ordered              |
| Shell       |     √     |         |   ?   |    ?    |   N   | tight code, subquadratic                           |
| Merge       |           |    √    | NlogN |  NlogN  | NlogN | NlogN guarantee, stable                            |
| Quick       |     √     |         | N²/2  | 2NlogN  | NlogN | NlogN probabilistic guarantee, fastest in practice |
| 3-way quick |     √     |         | N²/2  | 2NlogN  |   N   | good when duplicate keys                           |

### Priority queues

#### APIs and elementary implementations

- in priority queues, `remove()` would remove the largest (or smallest) item
  - therefore items must be `Comparable`

Unordered array implementation

```java
public class UnorderedMaxPQ<Key extends Comparable<Key>> {
  private Key[] pq;
  private N;

  public UnorderedMaxPQ(int capacity) {
    pq = (Key[]) new Comparable[capacity];
  }

  public boolean isEmpty() {
    return N == 0;
  }

  public void insert(Key x) {
    pq[N++] = x;
  }

  public Key delMax() {
    int max = 0;

    for (int i = 1; i < N; i++) {
      if (less(max, i)) {
        max = i;
      }
    }

    exchange(max, N - 1);

    // should null out this entry to prevent loitering
    return pq[--N];
  }
}
```

#### Binary heaps

- __binary tree__: empty or node w/ links with left and right binary trees
- __complete tree__: a perfectly balanced tree (all nodes have max children), except perhaps the bottom level
- __binary heap__: array representation (indices start at 1) of a heap-ordered complete binary tree
  - properties:
    - keys are in nodes
    - parent's key isn't smaller than its children's keys

Promotion in a heap

- occurs when there's a violation b/c the child's key > parent's
- __promotion in a heap__: a fix by exchanging the child's key with the parent's

```java
private void swim(int k) {
  while (k > 1 && less(k/2, k)) {
    // k/2 node is the parent of node k
    exchange(k, k/2);
    k = k/2;
  }
}
```

Insertion in a heap

- insert a node at the end of a heap, and then swim it up --> at most 1 + logN compares

```java
public insert(Key x) {
  pq[++N] = x;
  swim(N);
}
```

Demotion in a heap

- __demotion in a heap__ occurs when a parent's key becomes smaller than its child/ren
  - exchange the key with the larger child, and repeat until order is restored

```java
private void sink(int k) {
  while (2 * k <= N) {
    int j = 2 * k;

    if (j < N && less(j, j + 1)) j++;
    if (!less(k, j)) break;

    exchange(k, j);
    k = j;
  }
}
```

Delete maximum key in a heap

- exchange the root w/ the node at the end, and then sink it down; ≤ 2logN compares

```java
public Key delMax() {
  Key max = pq[1];
  exchange(1, N--);
  sink(1);

  // prevents loitering
  pq[N + 1] = null;

  return max;
}
```

So the larger class would look like,

```java
public final class MaxPQ<Key extends Comparable<Key>> {
  private final Key[] pq;
  private final int N;

  public MaxPQ(int capacity) {
    pq = (Key[]) new Comparable[capacity + 1];
  }

  // ...
}
```

#### Priority queues' implementation cost

| Implementation  | Insert  |   Del max   | Max  |
| --------------- | :-----: | :---------: | :--: |
| unordered array |    1    |      N      |  N   |
| ordered array   |    N    |      1      |  1   |
| binary heap     |  logN   |    logN     |  1   |
| d-ary heap      | logd(N) | d * logd(N) |  1   |

#### Considerations of binary heaps

- keys should be immutable once on the PQ
- in the case of underflow: throw exception if deleting from empty PQ
- in the case of overflow: add no-arg constructor and use resizing array

#### Heap sort (in-place)

1. create a max-heap w/ all `N` keys
2. repeatedly remove the maximum key

```java
public class Heap {
  public static void sort(Comparable[] pq) {
    int N = pq.length;

    for (int k = N/2; k >= 1; k--) {
      sink(pq, k, N); // ≤ 2N compares & exchanges
    }

    // ≤ 2NlogN compares & exchanges;
    // O(NlogN) in the worst case
    while (N > 1) {
      exchange(pq, 1, N);
      sink(pq, 1, --N);
    }
  }

  // ..
}
```

Pros:

- optimal for time and space

Cons:

- inner loop is longer than quicksort's
- makes poor use of cache memory
  - references to memory are all over the place for large arrays
- not stable

Summary of heap sort:

- in place?: √
- stable?: X
- worst: 2NlogN
- average: NlogN
- best: NlogN
- remarks: NlogN

### Elementary Symbol Tables

- symbol tables hold key-value pairs
  - follows associative array abstraction (one value is associated with each key)

Conventions:

- values aren't null
- `get()` returns `null` if the key isn't present
- `put()` overwrites the old value
- assume keys are `Comparable` & are any generic type
- use immutable types for keys

##### `.equals()`

can use Array.equals(a, b) and Array.deepEquals(a, b).

```java
public boolean equals(Object y) {
  if (y == this) return true;
  if (y == null) return false;
  if (y.getClass() != this.getClass()) return false;

  Date that = (Date) y;
  // ... check for same value...

  return true;
}
```

#### Elementary implementations

1. unordered linked list of key-value pairs & sequentially search

2. ordered parallel arrays of key-value pairs & binary search

   ```java
   public Value get(Key key) {
     if (isEmpty()) return null;
   
     int i = rank(key);
   
     if (i < N && Keys[i].compareTo(key) == 0) {
       return vals[i];
     } else {
       return null;
     }
   }
   ```

##### Iterative binary search

- __rank__: the # of keys less than the current key

```java
private int rank(Key key) {
  int lo = 0;
  int hi = N - 1;

  while (lo <= hi) {
    int mid = lo + (hi - lo) / 2;
    int cmp = key.compareTo(keys[mid]);

    if (cmp < 0) hi = mid - 1;
    else if (cmp > 0) lo = mid + 1;
    else return mid;
  }

  return lo;
}
```

Con: insertion of keys requires shifting data (larger keys) when using binary search

#### Binary Search Trees (BSTs)

- a BST is a binary tree in symmetric order
  - binary trees are [1] empty, or [2] two disjoint binary trees (left & right)
    - each node is considered a sub-tree
  - each node has a key
    - keys are greater than all its keys in its left subtree, and smaller than keys in its right subtree
- tree shape depends on the insertion order
  - the flatter & more balanced the tree, the better
- if N keys are inserted into a BST in random order, it has the same O(N) as quicksort partitioning (~1.39logN)
- In BSTs, operations take time ∝ to _h_, which is the height of the BST. It's ∝ to logN if keys are randomly inserted

```java
public class BST<Key extends Comparable<Key>, Value> {
  private class Node {
    private Key key;
    private Value val;
    Node left;
    Node right;
    int count;

    public Node(Key key, Value val) {
      this.key = key;
      this.val = val;
    }
  }

  public Value get(Key key) {
    Node x = root;

    while (x != null) {
      int cmp = key.compareTo(x.key);

      if (cmp < 0) x = x.left;
      else if (cmp > 0) x = x.right;
      else return x.val;
    }

    return null;
  }

  public void put(Key key, Value val) {
    root = put(root, key, val);
  }

  private Node put(Node x, Key key, Value val) {
    if (x == null) return new Node(key, val);

    int cmp = key.compareTo(x.key);

    if (cmp > 0) {
      x.right = put(x.right, key, val);
    } else if (cmp < 0) {
      x.left = put(x.left, key, val);
    } else {
      x.val = val;
    }

    x.count = 1 + size(x.left) + size(x.right);

    return x;
  }

  public Key floor(Key key) {
    Node x = floor(root, key);

    if (x == null) return null;

    return x.key;
  }

  private Node floor(Node x, Key key) {
    if (x == null) return null;

    int cmp = key.compareTo(x.key);

    if (cmp == 0) {
      return x;
    } else if (cmp < 0) {
      return floor(x.left, key);
    }

    Node t = floor(x.right, key);

    if (t != null) return t;
    else return x;
  }

  public int size() {
    return size(root);
  }

  private int size(Node x) {
    if (x == null) return 0;
    return x.count;
  }

  public int rank(Key key) {
    return rank(key, root);
  }

  private int rank(Key key, Node x) {
    if (x == null) return 0;

    int cmp = key.compareTo(x.key);

    if (cmp < 0) {
      return rank(key, x.left);
    } else if (cmp > 0) {
      return 1 + size(x.left) + rank(key, x.right);
    } else {
      return size(x.left);
    }
  }

  public Iterable<Key> keys() {
    Queue<Key> q = new Queue<>();
    inorder(root, q);
    return q;
  }

  private void inorder(Node x, Queue<Key> q) {
    if (x == null) return;

    inorder(x.left, q);
    q.enqueue(x.key);
    inorder(x.right, q);
  }
}
```

#### Deletion in BSTs

1. lazy: set key's value to null
   - not too bad if there aren't too many deletions; otherwise -> memory overload

Delete minimum key:

```java
public void deleteMin() {
  root = deleteMin(root);
}

private Node deleteMin(Node x) {
  if (x.left == null) return x.right;

  x.left = deleteMin(x.left);
  x.count = 1 + size(x.left) + size(x.right);
  return x;
}
```

##### Hibbard deletion

Delete a node with key _k_ by searching for a node _t_ containing _k_. Find _t_'s successor, delete the minimum in _t_'s right subtree, and put the successor in _t_'s spot

```java
public void delete(Key key) {
  root = delete(root, key);
}

private Node delete(Node x, Key key) {
  if (x == null) return null;

  int cmp = key.compareTo(x.key);

  // Search for key
  if (cmp < 0) {
    x.left = delete(x.left, key);
  } else if (cmp > 0) {
    x.right = delete(x.right, key);
  } else {
    if (x.right == null) {
      return x.left; // no right child
    }

    if (x.left == null) {
      return x.right; // no left child
    }

    // replace with successor
    Node t = x;
    x = min(t.right);
    x.right = deleteMin(t.right);
    x.left = t.left;
  }

  // Update subtree counts
  x.count = size(x.left) + size(x.right) + 1;
  return x;
}
```

Analysis:

- lack of symmetry makes this solution unsatisfactory --> trees aren't random, and therefore => √(N) per delete operation (and also search and insert operations if delete is allowed)

### Balanced Search Trees (BSTs)

#### 2-3 Search Trees

You allow 1-2 keys/node, as a result you get:

- __2-node__: one key with 2 children
- __3-node__: two keys with 3 children

##### Other characteristics

- __perfect balance__: every path from the root --> null link has the same length
- __symmetric order__: in-order traversal yields keys in ascending order

##### Insertion

Inserting into a 2-node:

1. Search for the key
2. replace key w/ a 3-node after

Inserting into a 3-node:

1. Add new key to a 3-node to create a temporary 4-node
2. Mode the middle key in the 4-node into the parent

Note: splitting a 4-node is __local transformation__ (has a constant number of operations).

##### Performance

Guaranteed logarithmic performance for search and insert.

- if all nodes are 2-nodes --> log2(N) performance
- if all nodes are 3-nodes --> log3(N) performance

Each operation has c*logN performance, where _c_ depends on the implementation

#### Red-Black BSTs (left-leaning)

1. Represent 2-3 tree as a BST
2. Use "internal" left-leaning links as "glue" for 3-nodes

##### Properties of a left-leaning Red-Black BST

- No node has 2 red links connected to it
- every path from the root to null link has "perfect black balance" (same number of black links)
- red links lean left
- each LLRB corresponds to a 2-3 tree

##### Search in a LLRB BST

(ceiling and selection ops are similar)

- same as a BST (ignoring color), but is faster b/c better balance

```java
public Val get(Key key) {
  Node x = root;

  while (x != null) {
    int cmp = key.compareTo(x.key);

    if (cmp < 0) {
      x = x.left;
    } else if (cmp > 0) {
      x = x.right;
    } else {
      return x.val;
    }
  }

  return null;
}
```

##### Color representation: indicate when node is red

```java
private static final boolean RED = true;
private static final boolean BLACK = false;

private class Node {
  Key key;
  Value val;
  Node left;
  Node right;
  boolean color;
}

private boolean isRed(Node x) {
  if (x == null) return false;
  return x.color == RED;
}

```

##### Rotate a red link

Rotate left

```java
Node x = h.right;
h.right = x.left;
x.left = h;
x.color = h.color;
h.color = RED;

return x;
```

Rotate right (eg. if 2 red links in a row)

```java
Node x = h.left;
x.left = x.right;
x.right = h;
x.color = h.color;
h.color = RED;

return x;
```

##### Recolor to split temporary 4-nodes

```java
private void flipColors(Node h) {
  h.color = RED;
  h.right.color = BLACK;
  h.left.color = BLACK;
}
```

##### Add a node

```java
private Node put(Node h, Key key, Value val) {
  if (h == null) {
    return new Node(key, val, RED);
  }

  int cmp = key.compareTo(h.key);

  if (cmp < 0) {
    h.left = put(h.left, key, val);
  } else if (cmp > 0) {
    h.right = put(h.right, key, val);
  } else {
    h.val = val;
  }

  if (isRed(h.right) && !isRed(h.left)) {
    h = rotateLeft(h);
  }

  if (isRed(h.left) && isRed(h.left.left)) {
    // balance 4-node
    h = rotateRight(h);
  }

  if (isRed(h.left) && isRed(h.right)) {
    // split 4-node
    flipColors(h);
  }

  return h;
}
```

##### Properties: Search, insert, delete

- worst case: 2logN
- average case ≈ 1logN

### Geometric applications of BSTs

#### 1d range search

- What is in a rectangle? How many rectangles intersect? etc.
  - used in games, movies, databases, and virtual reality

#### Linear segment intersection

#### k-d trees

- trees w/ _k_ dimensions
- 2d trees: networking, databases, circuit designs

#### Internal search trees

- data structures holding a set of overlapping intervals

### Hash tables

Implements an associative array w/ keys mapped to values.

#### Hash functions

- requirement: if x.equals(y), then x.hashCode() = y.hashCode()

##### Hash code design (user-defined)

Combine each significant field using the 31x + y rule

- if the field is a primitive type, use the wrapper's `hashCode()`
- if the field is null, return 0
- if the field is a reference type, use `hashCode()`
- if the field is an array, apply it to each entry

Hash functions return an int between 0 and M-1, where M is a prime power of 2, so:

```java
private int hash(Key key) {
  // 0x7fffffff makes the hash code positive
  return (Key.hashCode() & 0x7fffffff) % M;
}
```

A hash code is an integer between -2^31 and 2^31 - 1.

#### Separate chaining

- __problem__: 2 distinct keys hashing to the same index
- __solution__: separate chaining
  - use an array of M (< N) inked lists
    - hash: map keys to integer i, where 0 ≤ i ≤ M-1
    - insert: put @ front of i^th chain, if it's not already there
    - search: only need to search the i^th chain

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

- because we have uniform hashing, the number of keys per list is w/i a constant factor, `N/M`, which is extremely close to 1
  - so search & insert take proportional to N/M time (M times faster than sequential search)
    - if M is too large --> too many empty chains
    - if M is too small --> chains are too long
    - thus, optimal choice: M ~N/5 --> constant time operations

__Analysis__: separate chaining

- worst-case of search, insert, delete: logN
- average case: 3-5
- ordered iteration: No

#### Linear probing

- __problem__: we need to deal with hashing collisions
- __solution__: linear probing
  - uses __open addressing__: when a new key collides, find the next empty slot and put it there
    - therefore the size of the array is bigger than the expected keys (eg. M = 301, N = 150)

Linear probing algorithm:

- hash: map key to integer _i_ between 0 and `M-1`
- insert: put at table index _i_ if it's free, if not, try i+1, i+2, etc.
- search: search table index _i_, if occupied but no match, try i+1, i+2, etc.

It's especially important to keep the array at least half-empty.

- if M is too large --> too many empty arrays
- if M is too small --> search time balloons
- optimal: ∝ = N/M ~ 1/2 for a size of N=∝M
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

- worst-case of search, insert, & delete: logN
- average case: 3-5
- ordered iteration: No

#### Hash table context

- Why is the uniform hashing assumption important?
  - DOS (denial-of-service attacks), because if they learn your hash function, they can pile up values in a single slot to halt performance

Hash tables vs. balanced search trees:

- hash tables
  - simpler to code
  - no effective solution for unordered keys
  - faster for simple keys
  - better system support in Java for strings
- balanced search trees
  - stronger performance guarantee
  - support for ordered ST operation
  - easier to implement `compareTo()` correctly than `equals()` and `hashCode()`