# Software Development Process

Notes taken during Udacity's Software Development Process course.

## Introduction and overview

Software development process:

1. __requirements engineering__: talk to client and understand what system needs to be built
2. __design__: high-level structure of system
3. implementation
4. testing
5. maintenance

## Life cycle models

### 1. Requirements engineering

Is important because of the cost of __late correction__ (error in understanding requirements -> design decisions, etc. were based on incorrect assumptions)

Steps:

1. __elicitation__: collecting requirements from stakeholders
2. __analysis__: studying & understanding collective requirements
3. __specification of requirements__: requirements are organized & represented to be shared
4. __validation__: validate requirements for completion, consistency, lack of redundancy, etc.
5. __management__: changes to requirements during project's lifetime

### 2. Design

In order of high level to low level, __design activities__, and their products, include:

1. Architectural design -> System structure
2. Abstract specification -> Software specification
3. Interface design -> Interface specification
4. Component design -> Component specification
5. Data structures -> Data structure specification
6. Algorithm design -> Algorithm specification

### 3. Implementation

Four principles:

1. reduction of complexity
2. anticipation of diversity
3. structuring for validation (designing for testability)
4. use of (external) standards/regulation

### 4. Testing

Check software meets its specification.

* __validation__: did we build the right system _for the customers_?
* __verification__: did we _build the system right_? (implemented specifications)

### 5. Maintenance

Changing software to update to new technologies, complete users' feature requests, and fix bugs

Types of maintenance:

* __corrective maintenance__: code fixes
* __perfective maintenance__: feature requests
* __adaptive maintenance__: tech upgrades

There's a need to do __regression testing__ after software modifications, to ensure that software works as expected, and that there aren't regression errors

### Software processes / Life cycle models

Purposes:

* determine the order of steps in development
* establish the criteria that warrants a transition from one step to the next

#### __Waterfall model__

1. software concept
2. requirements analysis
3. architectural design
4. detailed design
5. coding & debugging
6. system testing

Waterfall is best used if there's a stable product definition, and we pretty much know how/what needs to be done to create the software. _Not flexible_.

#### __Spiral__

An incremental risk-oriented life cycle model.

Main phases include:

1. determine objectives
2. identify and resolve risks
3. development & tests
4. plan next iteration

_Advantages_:

* risk analysis reduces likelihood of getting requirements wrong
* functionality can be added at later phases
* software is produced early, -> can get customers' feedback

_Disadvantages_:

* risk analysis requires highly specific expertise
* model is complex
* highly dependent on risk analysis

#### __Evolutionary prototyping__

Phases:

1. initial concept
2. design and implement initial prototype
3. refine prototype until acceptable
4. complete and release prototype

It's ideal to use when all of the requirements aren't well-understood. Takes several iterations.

_Advantages_:

* immediate feedback

_Disadvantages_:

* difficult to plan how long development will take
* excuse to just hack things together, fix issues, & produce something mediocre

#### Rational Unified Process (RUP)

Is based on UML. An iterative solution with 4 phases. Set tasks done in each phase, w/ varying amounts of time depending on the phase.

Phases:

1. Inception
2. Elaboration
3. Construction
4. Transition

Activities:

1. Business modeling
2. Requirements
3. Analysis and design
4. Implementation
5. Testing
6. Deployment

#### Agile Software Developmental Process

Features highly iterative and incremental development.

__Test Driven Development (TDD)__:

1. Write tests that fail (RED)
2. Write enough code to make the test pass (GREEN)
3. Refactor: improve code quality & cohesiveness, maintainability, and readability

## Version Control Systems (VCSs)

Other VCSs: Subversion, CVS, ProForce (commercial), etc. vs. __Git__ (open source, distributed version control system)

* __distributed version control system__ tracks software revisions that don't have a central repository
  - can work separately w/o communicating w/ a central system

* VCSs use an efficient algorithm to store copies, so it doesn't take too much space
  - eg. Git hashes all the things

#### Git workflow

1. workspace (working directory): changes that haven't been committed
2. index (staging): tagged to be considered in the next commit
3. local repository (HEAD): committed stuff here
4. remote repository

* __`git fetch`__ gets files from a remote repository, and puts them in your local repository
  - only after a `git merge` do these files get put in your working directory
  - `git pull` combines `fetch` and `merge`
  - why `git fetch`, then?
    + can see file differences before merging by doing __`git diff head`__

## Requirements engineering (RE)

* __software requirements__: what the system must do to satisfy stakeholders
  - sometimes represented by 'shall' statements, user stories/cases, state transition diagrams, etc.

* __Software Requirements Specification__ (SRS): the result of RE; the 'what'

* software intensive system = software + hardware + context (eg. person using ATM at the bank)

* software quality is the function of the software and its purpose

* __requirements engineering__ (RE): set of activities to identify & communicate a software intensive system's purpose and context. Bridges the users' desires & the software's capabilities to come up with a feasible plan

* __system requirements__: the functional and non-functional requirements
  - __functional requirements__: functionality of a system (computation)
    + have a well-defined satisfaction criteria
  - __non-functional requirements__: qualities of a system
    + eg. security, accuracy, performance, usability, adaptability, interoperability, etc.
    + don't have a well-defined satisfaction criteria, so must refine them to make them verifiable (eg. < 30s)

### Requirements elicitation techniques

Traditionally:

1. background reading
2. hard data & samples
3. interviewing
4. surveys
5. meetings

But can also include:

1. collaborative techniques (eg. brainstorming)
2. social approaches (using social science methods, eg. anthropology to collect info)
3. cognitive techniques

### Modeling requirements

Ways to model requirements, with different focuses

* modeling enterprises
  - goals & objectives
  - organizational structure
  - tasks & dependencies
  - agents, roles, intentionality

* modeling information and behaviors
  - information structure
  - behavioral view: use cases, state machine models, sequence diagrams, information flow
  - time/sequencing requirements

* modeling non-functional requirements

### Analyzing requirements

1. __verification__: are the reqs. correct & fulfilling customer needs? complete? relevant? testable?
2. __validation__: do the reqs. satisfy what stakeholders want?
3. __risk analysis__: risks involved w/ developing the system

### Requirements prioritization

In cases of limited resources (ie. all cases, really), classify requirements into:

1. [*] mandatory
2. [•] nice to have
3. [?] superfluous

### Properties of requirements

* simple, not compound
* testable
* organized
* numbered, for traceability

## Object-oriented Engineering and UML

### Object orientation (OO)

* OO is/prioritizes: data over function; information hiding via data encapsulation; inheritance
* __object__: instance variables + operators/methods
* __class__: blueprint for new objects

Why use OO?

* reduces long-term maintenance costs b/c limits effects of change
* improves development process (b/c code & design reuse)
* enforces good design

### Object-oriented analysis

Real world objects --become--> requirements --contain--> our OO system.

Looking @ reqs, do these steps to get your system:

1. obtain/prepare textual description of problem
2. underline nouns => classes
3. underline objectives => classes'/objects' attributes
4. active verbs => methods

### Unified Modeling Language (UML): Structural diagrams

UML diagrams represent the static characteristics of a system.
