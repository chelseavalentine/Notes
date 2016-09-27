# Cracking the Coding Interview

Findings noted while working through the book.

## Big O, Big Theta, & Big Omega

* __O__: Big O: the upperbound on time
  * therefore O(N) perf. can be described as N², N³, etc. (anything ≥ N)
* __Ω__: Big Omega: the lowerbound on performance
  * values ≤ the upperbound (eg. print array can be Ω(N), Ω(logN), or Ω(1))
* __Θ__: Big Theta: the upper & lower bound (same value)

* Best, expected, & worst cases describe big O & aren't related to Ω or Θ, which describe runtime bounds
* Drop lesser powers of the same variable
  * eg. O(N²), not O(N² + N); O(B² + A), not (B² + B + A)

#### Amortized time

* __amortized time__: the average time taken per operation, if you do many operations
  * eg. a resizing array that doubles @ powers of 2:
    * x + x/2 + x/4 + … + 1 ≈ 2x is O(1) amortized time

* logN: the problem space is cut in half each time

#### Recursive runtimes

* a recursive method w/ 2 branches has O(2ⁿ) performance
  * generally: O( branches^(depth) ) performance
* take into consideration the time for each method w/i a loop
  * (eg. compareTo takes O(string.length)
* You can simplify 2 raised to logN (base of 2) to just be N. Don't forget your highschool math lol.

#### Memoization

* __memoization__: storing previously computed values to optimize exponential time algorithms via lookups

* eg. O(2ⁿ) becomes O(N) performance via this memoization technique on fibonacci numbers

  ``` java
  void allFib(int n) {
    int[] memo = new int[n+1];
    for (int i = 0; i < n; i++)
      System.out.println(i + ": " + fib(i, memo));
  }

  int fib(int n, int[] memo) {
    if (n <= 0) return 0;
    else if (n == 1) return 1;
    else if (memo[n] > 0) return memo[n];

    memo[n] = fib(n-1, memo) + fib(n-2, memo);
    return memo[n];
  }
  ```

* you can change base for `log`, of course! (eg. performance of finding num digits of N is O(logN), with base 10 log

## Ch 1 – Arrays & Strings

* can use balanced binary tree to implement a hash table (no huge space, but O(logN) lookup time)
* strings require copying over all characters when appending any characters
  * therefore, for this: we have N _w_'s, _x_ chars added (depends on how far in the sequence) gives us O(N(x + 2x + .. + Nx)) => O(N²x) b/c drop coefficient
    * hence: use a StringBuilder!!! (implemented w/ a resizable array)

#### Bit vectors

* __bit-vector__: the sequence of bits in a string

#### Unicode vs. ASCII

* __Unicode__: its character set consists of all of the characters from different languages as well as symbols
  * number of characters: 2048 (2¹¹ characters)
* __ASCII__: character set for computers; surpassed by UTF-8
  * number of characters: 128 characters (2⁷ characters)

#### `break`

* you can label loops to choose which one to break out of:
  ``` java
  name:
  for (...) {
    for (...) {
      break name;
    }
  }
  ```

## Ch 2 – Linked Lists

Recursive algorithms take at least O(N), N = depth of the call.

#### The "Runner" Technique

Two pointers, 1 ahead of the other (either 1 ahead & they both move by 2, or moving at different intervals)

#### Hash sets

## Ch 3 – Stacks and Queues

## Ch 4 – Trees and Graphs

* 2 children → binary tree; 3 children → ternary tree

#### Tries (Prefix Trees)

* __trie__: _n_-ary tree w/ a character at each node; a path may lead to a word
  * `*` nodes/"null nodes" indicate a complete word
    * you can also do this with a TerminatingTrieNode
    * alternatively, use a boolean w/i the Node to indicate terminating node
  * can have 0–alphabetsize + 1 children
  * can look for a word in a trie in O(K) time, where K is the length of the word

#### Graphs

* of course, you can also represent graphs with an array, but it's less elegant than using nodes
* __tree__: a connected graph without cycles
* __graph__: a collection of nodes w/ edges between some of them
  * can have directed or undirected edges
  * __connected graph__: graph w/ edges between every pair of vertices
  * __acyclic graph__: graph w/o cycles
* __adjacency list__: every node has a list of adjacent nodes/vertices (eg. in an array, or linked list)

#### Adjacency matrix

* __adjacency matrix__: N x N boolean matrix where a `true` value at `matrix[i][j]` indicates an edge from node `i` to node `j`
  * an adjacency matrix is symmetric iff it's an undirected graph
    * not necessariyl so in a directed graph

#### Graph search

* __breadth-first search (BFS)__: start at root (some arbitrary node, if it's a graph), & explore the neighboring nodes first before moving onto the next level
  * good to find shortest path

  BFS pseudocode (***uses a queue!***):

  ``` java
  void search(Node root) {
    Queue queue = new Queue();
    root.marked = true;
    queue.enqueue(root); // add to end of queue

    while (!queue.isEmpty()) {
      Node r = queue.dequeue(); // Remove from front of queue
      visit(r);
      for each (Node n in r.adjacent) {
        n.marked = true;
        queue.enqueue(n);
    }
    }
  }
  ```

* __depth-first search (DFS)__: start at root (some arbitrary node, if it's a graph), & explore as far as possible along each branch before backtracking
  * good if want to visit all nodes

  DFS pseudocode:

  ``` java
  void search(Node root) {
    if (root == null) return;
    visit(root);
    root.visited = true;
    for each (Node n in root.adjacent) {
      if (n.visited == false) search(n);
    }
  }
  ```

* __bidirectional search__: used to find the shortest path between two nodes (source & destination)
  * do BFS on both of them @ the same time, and the collision of the searches is the path we want
