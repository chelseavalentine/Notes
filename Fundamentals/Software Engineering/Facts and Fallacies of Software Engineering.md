# Facts and Fallacies of Software Engineering

Facts from the book. Most of them are ones that I found interesting or want to verify. Either way, I wanted to have some kind of notes from this book.


### Facts: About Management

##### People

* "The most important factor in software work is _not_ the tools and techniques used by the programmers, but rather the quality of the programmers themselves."
    - "... projects succeed or fail primarily based on who does the work rather than how it's done."
* "The best programmers are up to 28 times better than the worst programmers"
* "The working environment has a profound impact on productivity and product quality"
    - "the hard drives out the soft"
        + "those things that are solidly measurable ('hard things') tend to take away from those that are not (the 'soft' ones)."

##### Tools & Learning Curves

* "Learning a new tool or technique actually lowers programmer productivity and product quality initially. The eventual benefit is achieved only after this learning curve is overcome. Therefore it is worth adopting new tools and techniques, but only (a) if their value is seen realistically and (b) if patience is used in measuring benefits."

##### Estimates

* "One of the two most common causes of runaway projects is poor estimation."
    - "Most of our estimates are more like wishes than realistic targets."
* "Most software estimates are performed at the beginning of the life cycle. This makes sense until we realize that estimates are obtained before the requirements are defined and thus before the problem is understood. Estimation, therefore, usually occurs at the wrong time."
* "Most software estimates are made either by upper management or by marketing, not by the people who will build the software or their managers. Estimation is, therefore, done by the wrong people."
* "Since estimates are so faulty, there is little reason to be concerned when software projects do not meet estimated targets. But everyone is concerned anyway."
* "The answer to a feasibility study is almost always 'yes.'"

##### Reuse

* "There are two 'rules of three' in reuse: (a) It is three times as difficult to build reusable components as single use components, and (b) a reusable component should be tried out in three different applications before it will be sufficiently general to accept into a reuse library."
* "Modification of reused code is particularly error-prone. If more than 20 to 25 percent of a component is to be revised, it is more efficient and effective to rewrite it from scratch."
* "Design pattern reuse is one solution to the problems inherent in code reuse."
    - "Designers rarely start from scratch."
    - Design pattern - "description of a problem that occurs over and over again, accompanied by a design solution to that problem"
        + elements: name, description of when solution should be applied, solution, & consequences of the solution
    - __Suggestion:__ read _Design Patterns_ by Erich Gamma

##### Complexity

* "For every 25% increase in problem complexity, there's a 100% increase in complexity of the software solution."


### Facts: About the Life Cycle

* __life cycle__: an organizing scheme for talking about the process of software construction
    - [1] requirements definition & development
        + define and analyze the "what" of the problem
    - [2] design
        + when & how the problem is to be solved
    - [3] coding
        + design is transformed into code that runs on a computer
    - [4] error removal
        + run & pass tests
    - [5] production & maintenance

##### Requirements

* "One of the two most common causes of runaway projects is unstable requirements."
* "Requirement errors are the most expensive to fix when found during production but the cheapest to fix early in development."
* "The most persistent software errors--those that escape the testing process and persist into the production version of the software--are errors of omitted logic. Missing requirements result in omitted logic."

##### Design

* "Design is a complex, iterative process. The initial design solution will likely be wrong and certainly not optimal."
    - "Probably the worst possible design approach, and yet one that is tempting to most design novices, is 'easy-part-first.'"

##### Error Removal

* "Error removal is the most time-consuming phase of the life cycle."

##### Testing

* "Software that a typical programmer believes to be thoroughly tested has often had only but 55-60% of its logic paths executed. Using automated support, such as coverage analyzers, can raise that roughly to 85-90%."
* Testing approaches:
    - Requirements-driven testing: testing to see if each requirement is met
    - Structure-driven testing: testing to see if all pieces of the as-built software function correctly
    - Statistics-driven testing: randomly testing to see how long/well the software can execute
    - Risk-driven testing: testing to see that the primary tasks are handled properly
* "Even if 100% test coverage were possible, that is not a sufficient criterion for testing. Roughly 35% of software defects emerge from missing logic paths, and another 40% from the execution of a unique combination of logic paths."
* "It's nearly impossible to do a good job of error removal without tools. Debuggers are commonly used, but others, such as coverage analyzers, are not."
* "Programmer-created built-in debug code, preferably optionally included in the object code based on compiler parameters, is an important supplement to testing tools."

##### Reviews and Inspections

* "In spite of the benefits of rigorous inspections, they cannot and should not replace testing."
* "Peer reviews are both technical and sociological. Paying attention to one without the other is a recipe for disaster."

##### Maintenance

* "Maintenance typically consumes 40-80% of software costs. Therefore, it is probably the most important life cycle phase of software."
    - maintenance: fixing errors (created when software was built or changed) as they're discovered & making those changes as they become necessary
* "Maintenance is a solution, not a problem."
    - "... 'we built this thing, but now we wish we had built something a little different.'"
* maintenance life cycle (Fjelsted & Hamlen):
    - defining & understanding the change [.15]
    - reviewing the documentation for the product [.05]
    - tracing logic [.25]
    - implementing the change [.2]
    - testing and debugging [.3]
    - updating the documentation [.05]
* "Better software engineering development leads to _more_ maintenance, not less."


### Facts: About Quality


### Facts: About Research


### Fallacies: About Management


### Fallacies: About the Life Cycle


### Fallacies: About Education

