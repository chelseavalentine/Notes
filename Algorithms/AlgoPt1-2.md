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

| Notation  | Provides                   | Example | Used to |
| --------- | -------------------------- | :-----: | ------- |
| Big Theta | asymptotic order of growth |         |         |
| Big Oh    |                            |         |         |
| Big Omega |                            |         |         |
| Tilde     |                            |         |         |