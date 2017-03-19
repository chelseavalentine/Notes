# Site Reliability Engineering

Notes taken reading Google's [Site Reliability Engineering](https://landing.google.com/sre/) book.

## Ch 17: Testing for reliability

> If you haven't tried it, assume it's broken.

### Types of software testing

* hierarchy of traditional tests (base to top): unit tests -> integration tests -> system tests
  - __unit test__: smallest & simplest form of software testing, limited to a separable unit of software (e.g. class/function)
  - __integration test__: testing the assembled software components, using dependency injection or what have you (example tool: Dagger)
  - __system tests__: largest scale test, run on an undeployed system, testing the end-to-end functionality of a system
    + __smoke tests__: testing very simple but critical behavior, also known as sanity testing
      - purpose: can short-circuit additional & more expensive testing
    + __performance tests__ serve to ensure that the system doesn't get much slower, use much more memory, etc. without notice
    + __regression tests__: testing against a gallery of rogue bugs that've historically plagued the system
  - __production tests__ interact w/ a live production system, are similar to black-box monitoring, and are sometimes collectively called black-box testing
  - __configuration tests__ test whether each configuration file by examining production to see how a particular binary is actually configured, and reports discrepancies against its config file
    + can be really simple, like a file diff of expected contents & file on production
    + config tests become more complex when the configuration does any of the following:
      - implicitly incorporates defaults that're built into the binary, so tests need to be versioned
      - passes through a preprocessor (e.g. bash into command-line flags), so the tests need to follow expansion rules
      - specifies behavioral context for a shared time, so tests depend on that runtime's release schedule
  - __stress test__: testing and finding the limits on a web service, e.g.:
    + how full can the database get before writes start to fail?
    + how many QPS can be sent to an app. server before it becomes overloaded, and requests fail? 
  - __canary test__: upgrading a subset of servers to a new config version, and then leaving them in an incubation period ("baking the binary"), and then progressively propagating that release to the rest of the servers, if there aren't unexpected variances

### Creating a test-and-build environment

* starting to write tests for a codebase without them is super overwhelming, so need to prioritize what can best benefit from testing, and which tests are easiest to write while providing the most value
  - e.g. test business-critical, mission-critical stuff, components that're in great use

### Testing at scale

* many tools that test for disaster, and are designed to operate offline, do the following:
  - compute a checkpoint state that's equivalent to cleanly stopping the service
  - push the checkpoint state to be loadable by existing nondisaster validation tools
  - support the usual barrier tools, which trigger the clean start procedure

* writing config files in interpreted languages is risky
  - more important to check for performance

* should write probe tests, split into 3 sets of requests:
  - known bad requests
  - known good requests that can be replayed against production
  - known good requests that can't be replayed against production

## Ch 18: Software engineering in SRE

### Why is software engineering within SRE important?

### Auxon case study: project background and problem space

#### Traditional capacity planning

1. collect demand forecasts
  - how many resources are needed; when and where they're needed
  - prediction for several quarters to years
2. device & build allocation plans
  - how much supply and in what locations?
3. review & sign off on a plan
  - is the forecast reasonable? does the plan line up with budgetary, product-level, & technical considerations?
4. deploy & configure resources

This plan, like most plans, should be adequately revised as the variables change.

### Intent-based capacity planning

* specifies the requirements, not the implementation

* __Intent-based Capacity Planning__ approaches this issue by programatically encoding the dependencies and parameters (__intent__) of a service's needs, & use that encoding to autogenerate an allocation plan that details which resources go to which service, in which cluster
  - allows autogeneration of a new plan when parameters change

* __intent__: the rational for how a service owner wants to run their service

* information we need in order to capture a service's intent:
  - dependencies
  - performance metrics
  - prioritization

* __Auxon__: Google's implementation of an intent-based capacity planning and resource allocation solution

* don't focus on perfection & purity of a solution, especially if the bounds of the problem aren't well known; launch and iterate
