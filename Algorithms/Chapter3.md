# Ch 3 – Algorithmic analysis I

## Preliminaries

* __multiset__: a variant of a set where elements can appear more than once



## Asymptotic function growth

### Big omicron, omega, & theta, and little omicron

* __big theta__ (Θ): ignoring constant factors, the answer is this:
  * eg. run time of selection sort: T(n) = Θ(n²) 
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

* ​

## Analysis of algorithms & recurrences

### Performance of purely iterative programs

### Deriving recurrence equations