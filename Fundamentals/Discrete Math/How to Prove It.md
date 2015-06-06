# How to Prove It

#### Table of Contents
* [Chapter 1: Sentential Logic](https://github.com/)
    - Deductive Reasoning and Logical Connectives
    - Truth Tables: Logical Connectives
    - Truth Tables: Laws
        + DeMorgan's Laws
        + Commutative Laws
        + Associative Laws
        + Idempotent Laws
        + Distributive Laws
        + Absorption Laws
        + Double Negation Law
        + Tautology Laws
        + Contradiction Laws
    - Variables and Sets



# Chapter 1: Sentential Logic

### Deductive Reasoning and Logical Connectives

* __valid argument__: an argument in which the premises cannot be true without the conclusion being true as well
    - so an argument is valid if the premises are all true, and the conclusion is true, too.
* __well-formed formula__: an expression that doesn't violate any "grammatical" rules;
    - in other words: there shouldn't be adjacent logical connectives, just as how you wouldn't have 2*/10 in Algebra. 

### Truth Tables: Logical Connectives

* `+` means _exclusive or_
    - so `P+Q` represents the statement "P or Q, but not both"
* `↓` means _nor_
    - so `P↓Q` represents the statement "Neither P nor Q"
* `|` is called _nand_
    - so `P|Q` represents the statement "P and Q are not both true."

### Truth Tables: Laws

##### DeMorgan's Laws

* ¬(P∧Q) ≡ ¬P ¬Q
* ¬(PQ) ≡ ¬P∧¬Q

##### Commutative Laws

* P∧Q ≡ Q∧P
* P∨Q ≡ Q∨P

##### Associative Laws

* P∧(Q∧R) ≡ (P∧Q)∧R
* P∨(Q∨R) ≡ (P∨Q)∨R

##### Idempotent Laws

* P∧P ≡ P. The same applies for P∨P.

##### Distributive Laws

* P∧(Q∨R) ≡ (P∧Q)∨(P∧R)
* P∨(Q∧R) ≡ (P∨Q)∧(P∨R)

##### Absorption Laws

* P∨(P∧Q) ≡ P
* P∧(P∨Q) ≡ P

##### Double Negation Law

* ¬¬P ≡ P

##### Tautology Laws

* __tautology__: formulas that are always true
* P∧(a tautology) ≡ P
* P∨(a tautology) is a tautology.
* ¬(a tautology) is a contradiction.

* If the conclusion is a tautology, the validity of the argument relies on the truth values of the premises.
* If a premise is a tautology, the validity of the argument depends on the other premises.

##### Contradiction Laws

* __contradiction__: formulas that are always false
* P∧(a contradiction) is a contradiction.
* P∨(a contradiction) ≡ P
* ¬(a contradiction) is a tautology.

* If a conclusion is a contradiction, the argument is invalid.
* If a premise is a contradiction, the argument is invalid, unless the argument does not rely on the contradiction (eg. P∨(a contradiction).)

### Variables and Sets