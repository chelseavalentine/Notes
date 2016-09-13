# Chapter 9: Unit Tests

### Three laws of TDD

1. You may not write production code until you've written a failing unit test
2. You may not write more of a unit test than is sufficient to fail, & not compiling is failing
3. You may not write more production code than is sufficient to pass the currently failing test.

* having no tests is <= having no tests;
  - need to modify tests as production code changes
  - test code is just as important as production code
   * must be as simple, succinct, & expressive, but doesn't need to be as efficient

* one assert per test

* can eliminate/reduce duplication by using the _template method_ pattern, where you create `given`/`when` formatted functions (eg. `whenRequestIsIssued`)

* single concept per test

* tests should be:
  - independent
  - repeatable in any environment (eg. prod, dev, local)
    * if your tests aren’t repeatable in any environment, then you’ll always have an excuse for why they fail."
  - self-validating (return a boolean)

# Chapter 10: Classes

* classes begin w/ a list of variables, ordered as such:
  - [1] public static constants, [2] private static variables, [3] private instance variables
  - functions: [1] public functions, [1.5] the related private functions]

* sometimes variables need to be made `protected`, rather than `private` so tests can access them

* classes need to be small (measured in _responsibilities_); classname describes its responsbilities
  - should be able to describe its functionality in <= 25 words, w/o using the words "if", "and", "or", or "but"

* __Single Responsibility Principle  (SRP)__: a class/module should have only 1 reason to change;

* cohesion means that there are as few instance variables as possible, and that instance variables (w/i reasonableness) are shared maximally amongst the methods

* Dependency Inversion Principle (DIP): a design principle saying that classes should depend upon abstractions, not concreate details
