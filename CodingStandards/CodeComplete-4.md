# Code Complete

## Chapter 23

### Finding a defect

* an error that doesn't occur predictably is usually an initialization error, timing issue, or dangling-pointer problem

* __stabilizing an error__: narrowing a test case to the simplest one that still produces the error

* set a maximum time for "quick and dirty" debugging, so you know whether it's a more complex issue than expected

### Fixing a defect

* understand the problem before you fix it, and also understand the program as well

* fix the problem, not the symptom

* make one change at a time

* add a unit test that exposes the defect

### Debugging tools

* set compiler's warning level to the highest, pickiest level possible

* treat warnings as errors

## Chapter 24: Refactoring

* small fixes (1-5 lines) are more likely to have errors than larger fixes, so don't treat them casually

* if a big refactor seems necessary, look at whether a redesign & reimplementation seems more appropriate

* times to check if you need to refactor
  - when you add a routine or class
  - when you fix a defect

## Chapter 25: Code-tuning strategies (p. 624)

## Chapter 26: Code-tuning techniques (p. 646)

## Chapter 27: How program size affects (p. 686)

## Chapter 28: Managing construction (p. 698)

## Chapter 29: Integration (p. 726)

## Chapter 30: Programming tools (p. 746)

## Chapter 31: Layout and style (p. 766)

## Chapter 32: Self-documenting code (p. 814)

## Chapter 33: Personal character (p. 856)

## Chapter 34: Themes in software craftsmanship (p. 874)

## Chapter 35: Where to find more information (p. 892)

(p. 899)
