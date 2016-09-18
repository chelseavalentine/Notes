# Continuous Integration

* advantages: rapid feedback & deployment, and repeatable automated testing, ensures the health of the software

* principles
  - all developers run private builds locally prior to committing
  - each developer integrates at least daily
  - 100% test pass rate
  - a product is generated (eg. executable) that can be tested functionally
  - build generates reports for developers to review (on coding standards, dependency analysis, etc.) for improvements

### Chapter 1: Getting Started

#### Build software at every change

* __build__: the compilation, testing, inspection, & deployment of code

* your build should answer these questions:
  - do the software components work together?
  - what's the code complexity?
  - are the coding standards being adhered to?
  - test coverage?
  - tests successful?
  - performance reqs met?
  - any problems with the last deployment?

* __build script__: 1 script/set of scripts to compile, test, inspect, & deploy software

* __integration build machine__: a separate machine whose sole responsibility is to integrate software (hosts the CI server, which polls the VC repo)

* features required for ___continuous integration___:
  * VC repo
  * build script
  * feedback mechanism (eg. email)
  * a process for integrating source code changes (manual or CI server)

* __inspection__: analysis of source code/bytecode for internal quality attributes
  - enforces rules, such as a class can't be longer than X lines of noncommented code
    + another example is amount of duplicate code

* you can augment your CI process to include building external documentation (eg. with Maven, Javadoc, NDoc)

### Chapter 2: Introducing Continuous Integration

* CI systems help you notice trends in build success/failure, quality, etc.

### Chapter 3: Reducing risks using CI

> Quality means doing it right when no one is looking.
-Henry Ford

* some documentation tools can create UML diagrams for you

* it's possible to enforce architectural standards by setting rules (eg. classes can't make direct calls to the data access objects)

### Chapter 4: Building software at every change (pp. 65-102)

### Chapter 5: Continuous database integration (pp. 107-128)

### Chapter 6: Continuous testing (pp. 129-159)

### Chapter 7: Continuous inspection (pp. 161-186)

### Chapter 8: Continuous deployment (pp. 189-200)

### Chapter 9: Continuous feedback (pp. 203-222)
