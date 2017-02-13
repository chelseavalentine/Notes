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
