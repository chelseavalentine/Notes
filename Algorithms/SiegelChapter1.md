# Ch 1 – Introduction & Overview

* __conflict graph__: a graph such that,
  * each vertex represents a different resource/tasks
  * edges connect resources that can't be used at the same time
* __greedy method__: executing a sequence of best single-step decisions based on the partial solution that's been found thus far
* ___k_-clique__: a graph w/ _k_ vertices that has an edge between each pair of vertices, thus, requires _k_ colors for a graph-coloring problem

### Intro to recursion

* __activation record__: aka stack frame, a data structure that makes up a call stack
  * composed of return addresses to the caller and the local variable values needed for the callee
* think about problems from the bottom up
  * eg. in context of Towers of Hanoi, what processing must have been completed at the step where ToH processes ring _n_? 

### Program invariants

* __program invariant__: rule; assertion about the data/program information that's true at specific locations of the problem
  * eg. at this point in the code, assertion A is true

