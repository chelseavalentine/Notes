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

### Chapter 5: Bend, or break (pp. 126-152)

### Chapter 6: While you are coding (pp. 152-176)

### Chapter 7: Before the project (pp. 176-194)

### Chapter 8: Pragmatic projects (pp. 194-222)
