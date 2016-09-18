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

### Chapter 4: Building software at every change

* example build script structure: [1] clean, [2] compile source code, [3] integrate database, [4] run tests, [5] run inspections, [6] deploy software

* separate build scripts from your IDE; the success of your scripts should be independent

* create a consistent directory structure, such as having high lvl directories like: implementation, requirements, design, management, deployment, testing, tools

* fail should fail fast, suggested build execution order:
  1. integrate components
  2. run true unit tests (tests w/o database or other dependencies)
  3. run other automated processes (rebuild database, inspect, deploy)

* create configurable builds for different environments, specified by QA, local, prod

* build types: for the individual, the team, the users (customer)
  - private build: [1] get latest code, [2] run a build to execute all unit tests, [3] commit code
  - integration build (for the team)
    + no more than 10 minutes
  - release build (for users), usually occurs at end of an iteration or milestone

* speeding up your build
  * use mock/stubs for components that are too complex/difficult to use in unit-testing environments
  * separate long-running integration tests into separate specialized test suites
  * execute tests in parallel
  * separate tests by category: unit, component, system
  * remove unused & unnecessary inspections
  * reduce duplicate inspections
  * reduce frequency of certain inspections

### Chapter 5: Continuous database integration

* __Continuous Database Integration (CDBI)__: rebuilding your database & test data whenever a repo change occurs

* qualities to test for in data
  - efficient data performance by testing vs. target optimization for SQL queries
  - analyze data to ensure data integrity
  - use a SQL recorder to see which queries are being run the most
  - data naming convention & standards

### Chapter 6: Continuous testing

* system reliability is the product of the reliability of its components
  - a system w/ 100 components at 99% reliability has a reliability of 37%

* __unit tests__ verify the behavior of small elements; should only be for 1 class (unless tightly coupled :()
  - should take a fraction of a second; if it's longer, it may be a component test

* __component/subsystem tests__ verify portions of a system
  - may require a fully installed system or external dependencies

* __system tests__ require a fully installed system & associated database; testing for end-to-end functionality

* __functional tests__ / __acceptance tests__ tes tthe functionality from the viewpoint of the client
  - eg. testing titles, that text shows up, etc.

* a test directory could look like:
  ```
  test/
    unit/
    component/
    system/
  ```

* write tests for defects (defect-driven development lol)

* make component tests repeatable
  - either by mocking out as much as possible (easier), or control the database

* limit test cases to 1 assert

### Chapter 7: Continuous inspection

* __Cyclomatic Complexity Number (CCN)__: a plain integer measuring code complexity by counting the number of distinct paths through a method
  - higher CCN correlates with defects
  - reduce CCN w/ the __extract method technique__ (distribute complexity into smaller methods)

* determine overcoupling by looking at __afferent coupling__ & __efferent coupling__
  - highly afferent: an object has responsibility to too many other objects
  - highly efferent: the object isn't sufficiently independent of other objects

### Chapter 8: Continuous deployment

* example high level deployment steps
  - label a repo's assets
  - produce a clean environment, free of assumptions
  - generate & label a build directly from the repo & install it on the target machine
  - successfully run tests @ all levels in this clone of prod
  - create build feedback reports
  - if necessary, roll back the release
