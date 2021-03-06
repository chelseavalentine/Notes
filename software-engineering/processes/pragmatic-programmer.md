# Pragmatic Programmer

From: Andrew Hunt & David Thomas. “The Pragmatic Programmer: From Journeyman to Master.” iBooks. https://itun.es/us/GsI5x.l

* _kaizen_: Japanese business philosophy of continuously making small improvements

### Chapter 1: A pragmatic philosophy

* instead of excuses, provide options
* don't leave "broken windows" (bad designs, wrong decisions, poor code) unrepaired; otherwise -> a decline
* “[McC95] Jim McCarthy. Dynamics of Software Development. Microsoft Press, Redmond, WA, 1995.”
* “[Ber96] Albert J. Bernstein. Dinosaur Brains: Dealing with All Those Impossible People at Work. Ballantine Books, New York, NY, 1996.”


### Chapter 2: A pragmatic approach

> shortcuts make for long delays

* don't rely on the properties of things you can't control
  - eg. using phone numbers as a user ID (phone co. could reassign area codes)

* keep track of your estimates to gauge their accuracy;
* don't make estimates on the fly; get back to the person

### Chapter 3: The basic tools

* learn a text manipulation language (eg. Perl)

### Chapter 4: Pragmatic paranoia

* code defensively, even against yourself
* design with contracts, so "if all the routine's preconditions are met by the caller, the routine shall guarantee that all postconditions and invariants will be true when it completes."
  - if it doesn't, it's a bug
  ```java
    /**
     * @pre   f != null
     * @post getFont() == f
     */
     public void setFont(final Font f) {
       // ...”
  ```
* crash early, so you don't trash the system
* if it can't happen, use assertions to assure it won't
  - eg. saying the date can't be more than 2 digits, string can't be null
* leave assertions turned on
  - if there're performance issues, turn off the assertions producing the most hell

* when allocating the same set of resources in different places in your code, always allocate them in the same order
  - reduces deadlock
* can use wrappers for each resource in C (& prob other languages too), to ensure memory gets deallocated

### Chapter 5: Bend, or break

* __metaprogramming__: using configurations to generate code
  - eg. making the choice of database engine, UI style, etc. a configuration

* __temporal coding__: coding w/ concurrency & ordering in mind
  - leads to temporal coupling, meaning that timing matters

* design for concurrency; linear code => crappy assumptions made

### Chapter 6: While you are coding

* for routines you call, rely only on documented behavior. If you can't, then document your assumption well
* don't refactor and add functionality at the same time
* have good tests before refactoring
* refactor in small steps
* unit testing = testing against contract
* test your software, or your users will

### Chapter 7: Before the project

* requirements are ultimately an interpretation of the project, and shouldn't be too detailed or specific about implementation; abstract enough but not too abstract

### Chapter 8: Pragmatic projects

* documentation should be generated without human intervention
* resource exhaustion could include testing: memory, disk space, CPU bandwidth, wall-clock time, disk bandwidth, network bandwidth, color palette, & video resolution
* you'll be reading the source code 100s of times but only writing it a few times
