# Ch 3 – Algorithmic analysis I

## Preliminaries

* __multiset__: a variant of a set where elements can appear more than once


## Asymptotic function growth

### Big omicron, omega, & theta, and little omicron

* __big theta__ (Θ): ignoring constant factors, the answer is this:
  * eg. run time of selection sort: T(n) = Θ(n²) 
  * is two-sided, meaning that T(n) is bound from above & below
* __big O__ (O): up to a constant factor, the performance is no worse than this
  * eg. selection sort would be T(n) = O(n²), so it's no worse than n², up to a constant factor
    * you can also say that selection sort's performance is anything ≥ O(n²), so you can also accurately say that it's O(n³)
* __little O__ (o): used for overestimates that become unreasonably greater as n increases
  * T(n) = o(f(n)) means lim (n→∞) T(n)/f(n) = 0
  * eg. n has performance o(n³) says that n grows slower than n³
* __big omega__ (Ω): gives the lower bound of the function's performance
  * if n is sufficiently large, the running time T(n) is no smaller than Ω(f(n))
  * eg. selection sort is Ω(n²)

### Knowing and using the performance notations

* in superiority of notations: o > O > Θ
* superpolynomial, but subexponential (p. 145)


## Analysis of algorithms & recurrences

* to find the recurrence equation T(n), estimate the operation counts for every line in the algorithm, and then you sum the line-by-line _repetitions x operations_
  * eg. bubblesort is T(n) = Θ(n) + T(n-1) = (repetitions) + (sum of ops)
* **recurrence equations** (aka difference equations) consist of at least two parts, the *initial condition* (non-recursive part), & then the *recursive formulation (*recursive part)
* recurrence relations: algorithmic analyses where initial conditions are for `n ≤ some constant` rather than `n = some constant`, where n may not be an integer
  * eg. { T(n) ≤ 1, if n = 1; T(n) ≤ 1 + 2T(n-1), if n > 1

#### Compound games

* for quicksort, every emlement has 1/n chance of being the pivot, so we can say the recurrence equation is:
  * { Q(n) = 0, for n = 0; Q(n) = 1, for n = 1, Q(n) = n - 1 + 2/n(Q(0) + Q(1) + … + Q(n - 1)), for n ≥ 2.

### Recursion trees

* _differencing_: cancelling out all but 1 or 2 terms in a sequence

### Transformational methods for solving recurrence equations

1. Telescoping (p. 199)

   1. this method works for expressions of the form expr(n) - expr(n-1) = c sub n

      eg. { T(0) = 1; T(n) = T(n-1) + 1, if n ≥ 1.

2. Domain & range transformations (pp. 200-202)

   1. domain transformations should be done before range transformations

3. Differencing (pp. 204-205)

