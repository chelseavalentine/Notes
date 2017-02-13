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

#### Git work-flow

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
    + don't have a well-defined satisfaction criteria, so must refine them to make them verifiable (eg. < 30 seconds)

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

1. __verification__: are the requirements correct & fulfilling customer needs? complete? relevant? testable?
2. __validation__: do the requirements satisfy what stakeholders want?
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

* __class diagram__: a static structural view of a system, which describes the classes and their structure, and the relationships amongst classes
  - `+` indicates public visibility
  - `-` indicates only visible to the class

* attributes: representation of a class' structure, which're found by:
  - examining class definitions
  - studying requirements
  - applying domain knowledge

* relationships: descriptions of interactions between objects
  - 3 main types of relationships:
    1. __dependencies__: x uses y, indicated by a dashed arrow
      - changes in y can affect xx
    2. __associations/aggregations__: x has a y; x consists of y; "has-a" relationship
      - an association is indicated by a straight line
      - an aggregation is indicated by a straight line and a diamond at the end which aggregates
    3. __generalization__: x is a y
      - indicated by an arrow with its head unfilled

* __component diagram__: a static view of components and their relationships
  - each node/component defines a set of classes w/ a well-defined interface
  - each edge represents a relationship (ie. A "uses services of" B)
  - indicated symbolically by a straight line with a cupped circle (lol just look it up), to say "represents an interface provided by the component"

* __deployment diagram__: a static deployment view of a system
  - physical allocation of components to computational units
  - here, a node = computational unit (eg. server, client), and the edge = the communication

#### UML: Behavioral diagrams

Represents the dynamic aspects of a system.

* __use case diagram__ (a.k.a. scenarios, scripts, user stories) are a representation of:
  1. sequence of interactions of actors (outside entities) w/ the system
  2. system actions that yield observable results to the actors
  - basic notation:
    + the use case is indicated by a circle w/ text in it, defining the use case
    + a labeled stick figure indicates the actor (human/device)
    + a straight line to indicate "is the actor of"

* documenting use cases:
  - __documentation__: specification of a flow of events from an actor's point of view, which describes:
    + how the use case starts and ends
    + the normal flow of events
    + alternative flows of events
    + exceptional flows of events
  - can use informal/formal language, pseudocode, sequence diagrams, etc.

* Roles of use cases:
  - requirements elicitation
  - architectural analysis
  - user prioritization
  - planning
  - testing

* __sequence diagram__: an interaction diagram emphasizing the time ordering of messages

* __state transition diagram__: represents the possible lifecycle for a given class/object
  - describes possible states of the class as defined by attributes' values
  - describes events that cause state transitions
  - describes actions resulting from state change

## Software architecture

_What is it?_ Architectural design decisions are the decisions that can impact the success of a system, like a building's foundation

* __architectural erosion__: locally optimizing software via new features, platform upgrades, etc. resulting in the compromization of the system's behavior (esp. non-functional properties)

* __software architecture__ consists of the _elements_, _form_, and _rationale_
  - __elements__: processes, data, and connections
  - __form__: properties and relationships amongst elements
  - __rationale__: justification for the elements and their relationships

* __software architecture__: a set of principal design decisions about the system
  - the "blueprint" of the software = { structure, behavior, interactions, non-functional properties }

### Architectural degradation

* __architectural drift__: introducing architectural design decisions independent of a system's prescriptive (decided-on) architecture

* __architectural erosion__: introducing architectural design decisions that violate a system's prescriptive architecture

* __architectural recovery__: determining the software architecture from implementation and fixing it

* software architecture's elements include:
  - __processing elements__: implementers of the business logic & transformers of data
  - __data elements__ (aka information, state): containers of the info that processing elements use/transform
  - __interaction elements__: the glue  holding the architecture's pieces together

Components, connectors, and confirmation

* __software component__: an architectural entity that:
  - encapsulates a subset of the functionality and/or data
  - restricts access to the subset via an explicitly defined interface
  - can have explicitly defined dependencies on its execution environment

* __software connector__: an architectural entity affecting and regulating interaction

* __(architectural configuration)/topology__: the association between components and connectors of a SWA
  - __deployment architectural perspective__: mapping components and connectors to specific hardware elements

### Architectural styles

* __architectural style__: a named collection of architectural design decisions applicable in a given context

#### 1. Pipes and filters

A chain of processing elements (processes, threads, co-routines) are arranged so that the output of each element is the input of the next one
  - usually some buffering in-between

#### 2. Event-driven system

A system with event emitters and event consumers. Consumers are notified when events of interest occur.

#### 3. Publish-subscribe

Publishers send out messages w/o knowing the subscribers. Subscribes express interest in 1+ tags.

#### 4. Client-server

Server provides resources and functionality. Client contacts server & requests its resources/functionality.

#### 5. Peer-to-Peer (P2P)

A decentralized and distributed network system, where peers (individual nodes in the network) act as independent agents, supplying and consuming resources.

#### 6. Representational State Transfer (REST)

A hybrid architecture for distributed hypermedia systems.

## Design patterns

* __design patterns__: tried-and-true successful solutions to problems

### Five main classes of design patterns

1. Fundamental patterns (basic patterns)
2. Creational patterns (patterns supporting object creation)
3. Structural patterns (patterns that help compose and put objects together)
4. Behavioral patterns (patterns that realize interactions among objects)
5. Concurrency patterns (patterns supporting concurrency)

#### Factory method pattern

* intent: can create objects w/o specifying class by invoking a factory method (via interface)

* applicability:
  - class doesn't know the object types it'll create @ compile time (eg. frameworks)
  - class wants its subclasses to specify the object types it creates
  - class needs control over the creation of its objects

* participants:
  - __creator__ provides interface for factory method
  - __concrete creator__ provides a method for creating the actual object
  - __product__: the object made by the factory method

#### Strategy pattern

* intent: allows you to switch b/t different algorithms to accomplish a task

* applicability:
  - different variants of an algorithm
  - many related classes that only differ in their behavior

* participants:
  - __context__: an interface to the outside world; has reference to current algorithm, which can change at runtime
  - __algorithm (strategy)__: the common interface for the different algorithms
  - __concrete strategy__: the actual implementation of the algorithm

#### Other common design patterns

* __visitor pattern__: separating an algorithm from an object structure it operates on -> decoupled operation

* __decorator pattern__: a wrapper that adds functionality to a class

* __iterator pattern__: access elements of a collection w/o knowing how they're represented

* __observer pattern__: notify dependents when object changes

* __proxy pattern__: a surrogate controls access to an object

## Unified Software Process (USP)

* __Rational Unified Process__ (RUP): a software process model w/ following key features:
  1. order of phases in which things are done, which implies #2
  2. transition criteria to move between phases, which implies #3
  3. software built-in components, which implies #4
  4. well-defined interfaces

* distinguishing aspects of RUP:
  1. use-case driven
  2. architecture-centric
  3. iterative and incremental

### Features of RUP: Use-case driven

* perspective: system reacts to user inputs
  - what's the system supposed to do for each user? Use cases define a system's functions

### Features of RUP: Architecture-centric

* architecture defines how the system is structured to provide the functionality

1. create a rough draft of the system, independent of the use cases
2. use key use cases to create subsystems
3. refine the architecture by using additional use cases

### Features of RUP: Iterative and incremental

* there are many cycles of development (w/ all phases of development), w/ each resulting in a product release
  - each cycle has 4 phases (which may have multiple iterations within each)
    1. inception
    2. elaboration
    3. construction
    4. transition

* each cycle focusing on a small subset of the use cases, or a single use case. There could be some overlap between cycles

### Cycle phases: Phase 1 – Inception

Asks and answers the following questions:

* __Q__: what're the system's major users (actors), and what will the system do for them?
  - Use-case model.

* __Q__: What could the architecture for the system be?
  - tentative architecture w/ crucial subsystems

* __Q__: what's the plan and how much will it cost?
  - identification of main risks, and rough planning w/ estimates

Produces:

* a __vision document__: the project requirements, key features, and main constraints
* initial use-case model
* initial project glossary (the main terms in the project and their meanings), and initial business case (business context & success criteria)
* initial project plan & risk assessment
* (optional) 1+ prototype(s)

__Criteria for transition to phase 2 (elaboration)__:

* stakeholders concur on the scope, definition, and cost/schedule estimates
* requirements understanding
* credibility of estimates, risks, priorities, and development process
* depth and breadth of any prototype(s)

### Cycle phases: Phase 2 – Elaboration

Four main goals & activities of elaboration:

1. analyze problem domain
2. establish solid architectural foundation
3. eliminate highest risk elements (by addressing the most critical use-cases)
4. refine plan of activities and estimates

Outcomes/deliverables:

* almost complete use-case model, w/ all use-cases and actors defined
* supplementary requirements (eg. non-functional requirements)
* software architecture
* design model, test cases, executable prototype
* revised project plan and risk assessment
* preliminary user manual

__Criteria for transition to phase 3 (construction)__:

* stable vision and architecture
* prototype shows resolved/addressed risks
* detailed/accurate project plan
* stakeholders agree vision can be achieved with this plan
* acceptable actual resource vs. planned resource expenditure

### Cycle phases: Phase 3 – Construction

* What happens:
  - all considered features are developed
  - thoroughly test features

* Outcomes:
  - all use-cases are realized, w/ traceability info to the product
  - software is integrated on adequate platforms
  - user manual

__Criteria for transition to phase 4 (transition)__:

* stable/mature product for deployment
* are stakeholders ready to transition into user community?
* acceptable actual vs. planned resource expenditures

### Cycle phases: Phase 4 – Transition

1. issues after deployment -> new release (maintenance)
2. training customer service & providing help-line assistance

## General concepts

Clarification of terms:

* __failure__: observable incorrect behavior
* __fault/bug__: incorrect code
* __error__: cause of a fault

### Software verification approaches

1. Testing:
  - pro: no false positives
  - con: incomplete
2. Static verification
  - pro: considers all program behaviors
  - con: considers some impossible behaviors, -> false positives
3. Inspections
  - manual group walk-through
4. Formal proofs of correctness
  - pro: strong guarantee
  - con: complex, expensive

#### 1. Testing

* __test case__: {i ϵ D, o ϵ O}, where D is the input domain, and O is the output domain.
* __test suite__: a set of test cases

Levels of testing:

1. __unit testing__: testing individual modules in isolation
2. __integration testing__: testing interactions among different modules (a subset of all)
  - _big bang integration testing_: testing interactions b/t all modules at once (yikes)
3. __system testing__: testing complete system's functional & non-functional requirements

Other testing:

* __acceptance testing__: validating software against customer requirements
* __regression testing__: a test performed after each system change to check for regression errors
* __alpha testing__: having users w/i organization test software
* __beta testing__: having a subset of users outside organization test software

### Black-box testing vs. white-box testing

* __black-box testing__: testing based on description of a software
  - covers as much specified behavior as possible
  - can't reveal errors due to implementation details

* __white-box testing__: testing based on the code
  - covers as much coded behavior as possible
  - can't reveal errors due to missing paths (parts of specification that aren't implemented)

## Black-box testing (a.k.a. functional testing)

Advantages:

* can focus on domain
* no need for code, thus can write tests early
* catches logic defects
* applicable at all granularity levels

### Systematic approach to black-box testing

1. the _functional specification_ identifies the
2. _independently testable features_, which identifies the
3. _relevant inputs_, through which you can derive the
4. _test cases' specifications_, to generate the
5. _test cases_

#### Test data selection: how to select good values

1. random testing
  * pros: pick inputs uniformly; no designer bias
  * cons: it's essentially looking for a needle in the hay sack

2. __partition testing__: using the fact that the input domain is naturally split into partitions by the software, & failures tend to be dense in particular partitions

3. __boundary values__: errors tend to occur @ the boundaries of (sub)domains, thus select inputs at the boundaries

### The Category-Partition Method

A specific black-box testing approach where, you use the specification to:

1. identify independently testable features
2. identify _categories_ (characteristics of each input element)
3. _partition_ categories into choices
4. identify constraints amongst choices
5. produce/evaluate test case specifications
6. generate test cases from test case specifications

* __test frame__: a specification of a test
  - eg. input string has length `size - 1`, content includes special chars; input size has value of `70`

TSLgenerator is a tool for this.

### Model-based testing

You take a specification, create a model (an abstract representation of the software being tested, eg. FSM), and then create test cases from that.

#### Finite State Machines (FSM)

* nodes = states of a system
* edges = transitions between states
  - edge labels = events/actions

Building an FSM involves:

1. identify system boundaries, and the input & output
2. identify relevant states & transitions
  - draw FSM & then the test cases are the paths you can take

About FSMs:

* they're just state diagrams
* you must find a suitable level of abstraction

## White-Box Testing (a.k.a. structural testing)

* Advantages:
  - based on code, thus can measure objectively & automatically
  - can be used to compare test suites
  - allows for covering coded behavior

* Types of white-box testing:
  - control-flow based
  - data-flow based
  - fault based

* Coverage criteria is defined in terms of test requirements, and it leads to the test specs & cases
  - _test requirements_ are the number of statements in the program
    + thus, the (statement coverage's coverage measure) = (number of executed statements) / (total number of statements)

* (branch coverage's coverage measure) = (number of executed branches) / (total number of branches)

* branch coverage subsumes statement coverage for any test suite, but branch coverage is more expensive

* (condition coverage's coverage measure) = (number of conditions that are both T and F) / (total number of conditions)

### Modified condition/Decision coverage (MC/DC)

This is required for safety-critical software. MC/DC subsumes branch coverage.

* purpose: test important conditions, & limit testing costs
  - by extending branch & decision coverage w/ requirement that each condition should affect the decision outcome independently

## Agile Development Methods (aka TDD)

The agile method mentality's principles:

* focus on code (≠ design, to avoid unnecessary changes)
* focus on people over process
* iterative approach
* customer involvement
* expectation that requirements will change
* simplicity

### Extreme Programming (XP)

A lightweight, humanistic discipline for software development.

Values and principles of XP:

* communication
  - elements: pair programming, user stories, customer involvement
* simplicity
* feedback
  - elements: test cases, estimating stories once getting feature from customer
* courage (to throw out code, make changes)

XP's practices:

1. incremental planning
2. small releases
3. simple design
4. test first
5. refactoring
6. pair programming
7. continuous integration
8. on-site customer

### Scrum

* actors:
  - product owner (customer): says what needs to be done & prioritizes them
  - team: ships
  - scrum master: person responsible for scrum process

## Software refactoring

Goal of refactoring: keep program readable, understandable, & maintainable, while preserving behavior.

Refactoring methods:

* collapse hierarchy
  - merging subclasses & super classes together when they're too similar
* consolidate conditional expressions
* extracting classes
* decomposing conditionals
* converting to in-line classes
  - for when a class doesn't do much
* extracting methods
