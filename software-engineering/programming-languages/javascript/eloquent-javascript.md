# Eloquent JavaScript

## Chapter 1: Values, Types, and Operators

- 6 value types: number, strings, Booleans, objects, functions, & undefined values.
  - numbers:
    - you can write them in scientific notation (eg. 3.1e10 = 3.1 x 10^10)
    - `-Infinity` & `Infinity` (special behavior)
    - `NaN` (Not a Number)

### Operators

- Binary operators vs unary operators
  - Binary operators use two values (eg. 2 < 3)
  - Unary operators only use one value
- `typeof ____` will return the type of whatever is placed in the blank
  - an example of a unary operator

##### `?`: The Conditional Operator

- `?` is the only ternary operator in JS. It operates on three values
  - it's format: `true ? 1 : 2`
- the Boolean value picks which value will be chosen;
  - if it's true, the value on the left is chosen (1)
  - if it's false, the value on the right is chosen (2)

### Automatic Type Conversion

- __type coercion__: when you use an operator on an incorrect value, or something like null/NaN/undefined, JS follows rules and returns a value that may be unexpected
- _false_: the following are considered equal to false when using operators resembling `==`
  - 0, NaN, '' (empty string)
- If you don't want automatic type conversion to occur, use `===`, `!==`, etc

## Chapter 2: Program Structure

### Variables

Define multiple variables like this:

```javascript
var x = 1, y = 2;
```

- __environment__: the collection of variables and their values that exist at a given time
  - never empty b/c there're always default variables that the browser uses

### Functions

- functions are invoked, called, applied, or executed.

##### `prompt` and `confirm`

Assign these to variables in order to get the value that the user inputs

`confirm` will return a Boolean whose value depends on whether the user clicks 'OK' or 'Cancel.'

```javascript
confirm("Do you want to continue?");
```

`prompt` takes in a string from the user. The second value is the text that'll sit in the input box.

```javascript
prompt("What is your name?", "[Name here]");
```

### Control flows

- Usually a program flows in one path
- You can have a conditional flow where flow changes based on if/else if/else statements, as well as while/do/for loops and switch statements.

## Chapter 3: Functions

### Defining a function

You can set a function to a variable. But you need to define the variable that holds the function prior to using the variable/function.

```javascript
var variabley = function(x) {
    return x;
}

console.log(variabley(10)); // 10
```

If you want to be define functions wherever you desire, you can use the function declaration that you find most familiar:

```javascript
function foo() {
    // bar
}
```

- a `return` statement w/o any expression will make the function return `undefined`

### Parameters and scopes

__lexical scoping__: setting a variable's scope so it can only be called/referenced from the code block in which it's defined

You can use free-standing blocks! They won't create a new scope. 

```javascript
var foo = 3;

{
    var bar = 0,
        bar0 = 1,
        bar1 = 2;
}
```

### Call stack

- When running through the "main program," the computer needs to jump from the main program to the functions being called. And it needs to remember its position.
  - __call stack__: the place where the computer stores this context

### Optional arguments

When you pass too many arguments into a function, it'll ignore the excess. When you pass too few, it'll assign `undefined` to the missing arguments.

You can leverage this behavior by writing something like this:

```javascript
function foo(base, multiple) {
    if (multiple == undefined)
        multiple = 2;
    return base * multiple;
}

foo(4); // 8
```

### Closure

- __closure__: being able to reference a specific instance of local variables in an enclosing function

Example the book gives:

```javascript
function wrapValue(n) {
    var localVariable = n;
    return function() {return localVariable};
}

var wrap1 = wrapValue(1); // console.log(wrap1()); --> 1
var wrap2 = wrapValue(2); // console.log(wrap2()); --> 2
```

You can utilize closures like this:

```javascript
function multiplier(factor) {
    return function(number) {
        return number * factor;
    };
}

var twice = multiplier(2);
console.log(twice(5)); // 10
```

### Recursion

Recursion with functions (which is 10x slower than a loop):

```javascript
function power(base, exponent) {
    if (exponent == 0)
        return 1;
    else
        return base * power(base, exponent - 1);
}

console.log(power(2, 3)); // 8
```

## Chapter 4: Data Structures: Objects and Arrays

### Properties

- dot operators, like `.length` access a property of a value

### Methods

- __method__: a property that contains a function
  - eg. `.toUpperCase()` is a method of a string

### Objects

- `delete` operator is a unary operator that can remove a property from an object

```javascript
var obj = {
    left: 1,
    right: 2
};

delete obj.left;
console.log(obj.left); // undefined
console.log("left" in obj); // false
```

### Mutability

- numbers, strings, & Booleans are immutable
- objects are mutable (you can modify it by changing its properties)

### Further arrayology

- `.shift(value)` allows you to add a value to the start of an array
- `.unshift()` returns & removes the first thing in an array
- `.indexOf()`
- `.lastIndexOf()`
- `.concat(anotherArray)`

### Strings and their properties

- `.trim()` remove whitespace (spaces, newlines, tabs, & similar characters) from the start & end of a string
- `.slice(start, end)`
- `.indexOf("string")`

### The arguments object

Functions have an `arguments` object that tells you how many arguments were passed to the function

```javascript
function countArguments() {
    console.log("You fed in", arguments.length, "arguments.");
}

console.log(countArguments("catch", "foo", "bar")); // You fed in 3 arguments.
```

### The global object

Any global variables are added to a global object. In browsers, they're stored in the `window` variable

```javascript
var foo = 20;
console.log(window.foo); // 20
```

## Chapter 5: Higher-Order Functions

### Abstraction

__abstraction__: hiding details, giving us the ability to talk about problems at a higher/more abstract level

`.forEach(function)`

```javascript
['array', 'values', 'here'].forEach(console.log);
```

### Higher-order functions

- __higher-order functions__: functions that operate on other functions
  - takes functions as arguments, or returns functions

```javascript
function greaterThan(n) {
    return function(m) {return m > n; };
}

var greaterThan10 = greaterThan(10);
console.log(greaterThan10(11)); // true
```

Create new flows:

```javascript
function repeat(times, body) {
    for (var i = 0; i < times; i++) body(i);
}

repeat(3, function() {console.log("hi");});
```

### Passing along arguments

- `.apply` all JS functions have this method; (I didn't understand this)

### JSON

- `JSON.stringify([])` converts a JS value and returns a JSON-encoded string
- `JSON.parse([])` takes a JSON-encoded string & converts it to the value it encodes
- `.filter()` filters an object for specified criteria & returns an array
- `.map()`

### Transforming with map

```javascript
function map(array, transform) {
    var mapped = [];
    for (var i = 0; i < array.length; i++)
        mapped.push(transform(array[i]));
    return mapped;
}

console.log(map(overNinety, function(person) {
    return person.name;
    }))
```

### Summarizing with reduce

- taking a lot of elements and computing a single value from them

### Associating properties with objects

```javascript
var byName = {};
ancestry.forEach(function(person) {
    byName[person.name] = person;
    })

console.log(byName["name"]); // returns object with this name
```

### Binding

- `.bind()` comes with all functions
  - creates a new function that calls the original function, but has some of the arguments already fixed

## Chapter 6: The Secret Life of Objects

### History

- _encapsulation_: distinguishing between internal complexity and external interface
  - in other words, you can use something w/o knowing every detail about it, or how functions work exactly

### Methods

- __method__: a property that holds a function value

```javascript
var rabbit = { descriptor: "pink"};
rabbit.speak = function(words) {
    console.log("The", this.descriptor, "rabbit says, '" + line + "'");
}

rabbit.speak("Yo."); // The pink rabbit says, 'Yo.'
```

- `.apply()` calls the function it's a method of, but takes its arguments normally, rather than as an array

```javascript
speak.apply(pink, ["Hi. I am pink."]);
// or
speak.apply({descriptor: "pink"}, "Hi. I am pink, too.");
```

### Prototypes

- __prototype__: another object that's used as a fall-back source of properties
  - `Object.getPrototypeOf([Object])` gives you the object's prototype

You can create new objects, too. Kind of like how you can create new HTML elements

```javascript
var protoRabbit = {
    speak: function (line) {
        console.log("The", this.type, "rabbit says, '" + line + "'");
    }
};

var bunny = Object.create(protoRabbit);
bunny.type = "little rabbit";
bunny.speak("Yo yo.");
```

### Constructors

- `new` creates a new instance of a constructor that's specified after new

```javascript
function Rabbit(type) {
    this.type = type;
}


var bunny = new Rabbit("bunny");
```

Constructors all have the `prototype` property, so we can have instances of a constructor all share a method by doing something like this:

```javascript
Rabbit.prototype.speak = function(line) { 
    console.log(this.type, "rabbit says, 'I'm tired of speaking already. Gosh.", line + "'");
};

bunny.speak("Can I go home now?");
```

- constructors are functions
- You can override properties that exist in functions by just reassigning it for a particular object

### Prototype interference

- JS distinguishes between enumerable & non-enumerable properties
  - enumerable: properties we create by assigning them
  - non-enumerable: standard properties in Object.prototype

You can define non-enumerable properties with `Object.defineProperty`.

```javascript
Object.defineProperty(Object.prototype, "boo", {enumerable: false, value: "wut"});

for (var x in [Object]) {
    console.log(x); // All properties except the non-enumerable ones will be listed
}
```

- `Object.hasOwnProperty(["propertyName"])` tells you whether an object has a property, _without looking at its prototypes_

### Prototype-less objects

- You can pass `null` as a prototype to create an object w/o any prototypes

```javascript
var bunny = Object.create(null);
bunny["cow"] = "what is going on";
console.log("toString" in bunny); // false
console.log("cow" in bunny); //true
```

### Polymorphism

- __polymorphism__: being able to add/override members, even after an object has been created. So objects can acquire/lose properties and behaviors over time

### Getters and setters

```javascript
var pile = {
    elements: ["ugh", "why", "what"],
    get height() {
        return this.elements.length;
    },
    set height(value) {
        console.log("lol you wish you could set the height value to", value);
    }
};

console.log(pile.height); // 3
pile.height = 100; // lol you wish you could set the height value to 100
```

- `Object.defineProperty()` can be used to create getters and setters, too
  - When a getter, but no setter, is written, writing to the property is ignored

```javascript
Object.defineProperty(TextCell.prototype, "heightProp", {
    get: function() {
        return this.text.length;
    }
});
var cell = new TextCell("I'm\nHungry.");
console.log(cell.heightProp); // 2
```

### Inheritance

- __inheritance__: basing a new constructor on an old one; you can let the new constructor inherit the old constructor's properties and methods

In this example, RTextCell inherits methods and properties from TextCell, and changes the draw method

```javascript
function RTextCell(text) {
    TextCell.call(this, text);
}

RTextCell.prototype = Object.create(TextCell.prototype);
RTextCell.prototype.draw = function(width, height) {
    var result = [];
    for (var i = 0; i < height; i++) {
        var line = this.text[i] || "";
        result.push(repeat(" ", width - line.length) + line);
    }

    return result;
};
```

### The `instanceof` operator

Can use `instanceof` with objects to verify which constructor it belongs to

```javascript
console.log(new Duckie("Yo yo duck") instanceof Duckie); // true
console.log(new RTextCell("A") instanceof TextCell); // true
console.log(new TextCell("A") instanceof RTextCell); // false
console.log([1] instanceof Array); // true
```

## Chapter 7: Project: Electronic Life

- each function call gets its own `this` binding, so the `this` of an inner function doesn't refer to the `this` of the outer function
  - You can work around it by assigning the outer `this` to a variable and passing it to the inner function

## Chapter 8: Bugs and Error Handling

### Strict mode

- JavaScript is quite lax. You can make it be more like Java by typing `"use strict"` at the top of a file or function

```javascript
function spotIssues() {
    "use strict";
    for (counter = 0; counter < 10; counter++)
        console.log("Yes");
}

spotIssues(); // ReferenceError: counter is not defined
```

_usually, when you use a variable w/o defining it with `var`, JS will create a global variable and use that_

- Strict mode doesn't allow you to give a function multiple parameters w/ the same name
  - it can also get rid of a lot of problematic language features

### Testing

Example:

```javascript
function testVector() {
    var p1 = new Vector(10, 20);
    var p2 = new Vector(-10, 5);
    var p3 = p1.plus(p2);

    if (p1.x !== 10) return "fail: x property";
    if (p1.y !== 20) return "fail: y property"; 
    // ... etc.
    return "Everything works.";
}

console.log(testVector()); // Everything works.
```

### Exceptions

- Exception handling lets you throw an exception when bad input is given to a function
  - __unwinding the stack__: when an exception is thrown, it'll jump back through all of the callers that called the function in which the exception was thrown 

```javascript
function promptDirection(question) {
    var result = prompt(question, "");
    if (result.toLowerCase() == "left") return "L";
    if (result.toLowerCase() == "right") return "R";
    throw new Error ("Invalid direction:", result);
}

function look() {
    if (promptDirection("Which way?") == "L")
        return "a duck";
    else
        return "a cow";
}

try {
    console.log("You see", look());
} catch (error) {
    console.log("Something went wrong:", error);
}
```

- After the `catch` block finishes, or if the `try` block successfully executes, the control proceeds beneath the entire try/catch statement

### Cleaning up after exceptions

Even if body() throws an exception, there'll be code that's run

- `finally {}` even if everything goes to crap, run this code.

```javascript
function withContext(newContext, body) {
    var oldContext = context;
    context = newContext;
    try {
        return body();
    } finally {
        context = oldContext();
    }
}
```

### Selective catching

- `for (;;)` creates a loop that doesn't end on its own. You need to put in a `break` statement somewhere.

### Assertions

```javascript
function AssertionFailed(message) {
    this.message = message;
}

AssertionFailed.prototype = Object.create(Error.prototype);

function assert(test, message) {
    if (!test)
        throw new AssertionFailed(message);
}

function lastElement(array) {
    assert(array.length > 0, "empty array in lastElement");
    return array[array.length - 1];
}
```

## Chapter 9: Regular Expressions

### Creating a regular expression

__regular expression__ is a type of object created as follows:

```javascript
var re1 = new RegExp("abc"); // here, the usual rules apply for ba]ckslashes; they're ignored unless part of a special character code like \n
// or
var re2 = /abc/; // here, you need to put a backslash before any forward slash that you want to be a part of the pattern, eg. /\//
```

Some characters have special meanings in RegEx, so you need to put a backslash before them if you want them to take on their character

```javascript
var eighteenPlus = /eighteen\+/;
```

### Testing for matches

- `.test()` will tell you whether the string contains a match of the pattern in the expression

```javascript
console.log(/abc/.test("abcde")); // true
```

### Matching a set of characters

If we want to match any number, we can test whether the string contains any of the numbers within the brackets, like so:

```javascript
console.log(/[0123456789]/.test("in 1992")); // true
// or
console.log(/[0-9]/.test("in 1992")); // true
// or
console.log(/\d/.test("in 1992")); // true
```

Here, the `-` covers the range of unicode characters between those two characters. Of course, [0, 1, 2, 3, 4, 5, 6, 7, 8, 9] are next to each other.

##### Common character groups

- `\d` Any digit character
- `\w` Any alphanumeric character ("word character")
- `\s` Any whitespace character (space, tab, newline, and similar)
- `\D` Any character that is _not_ a digit
- `\W` Any nonalphanumeric character
- `\S` A nonwhitespace character
- `.` Any character except for newline

These can be used between backslashes or square brackets

```javascript
/[\d.]/ // any digit or period character
```

##### Inverting a selection

To invert a set of characters (get any characters _except_ the ones in the set), use a `^` character after the opening bracket.

```javascript
var notBinary = /[^01]/;
console.log(notBinary.test(110010)); // false
console.log(notBinary.test(101020)); // true
```

### Repeating parts of a pattern

- Using a `+` sign after an element in a regular expression, it says the element can be repeated more than once.
  - Eg. (`/\d+/` matches one or more digit characters.)
- Using a `*` allows the pattern to match 0 times
  - doesn't prevent a pattern from repeating, it just matches 0 instances if it can't find a text match

```javascript
console.log(/'\d+'/.test("'123'")); // true
console.log(/'\d+'/.test("''")); // false
console.log(/\d*/.test("'123'")); // true
console.log(/'\d*'/.test("''")); // true
```

- Using a `?` says the pattern match is optional. So it can occur 0 or 1 time.

```javascript
var neighbor = /neighbou?r/;
console.log(neighbor.test("neighbour")); // true
console.log(neighbor.test("neighbor")); // true
```

- Repeating a set number of times:
  - when `{n}` (n is a positive integer) is put after an element, the element will occur n times
  - `{n1, n2}` specifies a range where the element must occur at least n1 times, and at most n2 times
  - `{, n}` means 0 to n times
  - `{n, }` means n or more times

### Grouping subexpressions

- use parentheses for clarity/precision when using `*`/`+` on 1+ element at a time
- `i` in an expression makes the expression case insensitive

```javascript
/boo+(hoo+)+/i
```

### Matches and groups

##### Matching

- `.exec([string])` will return the object w/ information about the match, if found
  - if not found, it returns null
  - the index tells you where the successful match begins

```javascript
var match = /\d+/.exec("one two 100");
console.log(match); // ["100"]
console.log(match.index); // 8
```

String methods can tell you what part of the string matches the regular expression:

```javascript
console.log("one two 100".match(/\d+/)); // ["100"]
```

##### Grouping subexpressions

If your regular expression has subexpressions grouped in parentheses, your array will return 2 things:

1. The whole text matched by the group (element 1)
2. The part matched by the first group (element 2)

```javascript
var quotedText = /'([^']*)'/;
console.log(quotedText.exec("she said 'hello'")); // ["'hello'", "hello"];
```

If there is no match, you'll get something like the following:

```javascript
console.log(/bad(ly)?//.exec("bad")); // ["bad", undefined]
```

When there's more than one match, only the last match is recorded:

```javascript
console.log(/(\d)+/.exec("123")); // ["123", "3"]
```

_Use case for grouping_: If you want to both [1] verify whether a string contains something, & [2] use the match to construct an object that represents it

### The date type

Current date and time:

```javascript
console.log(new Date());
```

Specific date:

- parameters: `(year, month - 1, day, hours, minutes, seconds, miliseconds)`
- month numbers start at 0
- date numbers start at 1

```javascript
console.log(new Date(2009, 11, 9)); // Wed Dec 09 2009 00:00:00 GMT+0100 (CET)
console.log(new Date(2009, 11, 9, 12, 59, 999)); // Wed Dec 09 2009 12:59:59 GMT+0100 (CET)
```

##### Timestamps

Get a timestamp using `.getTime()`
(_timestamps are the number of miliseconds since the start of 1970_)

```javascript
console.log(new Date(2009, 11, 19).getTime()); // # miliseconds since then
console.log(new Date(1387407600000)); // Thu Dec 19 2013 00:00:00 GMT+0100 (CET)
```

`Date.now()` will give you the current time in miliseconds.

##### Dates and regular expressions

```javascript
function findDate(string) {
    var dateTime = /(\d{1,2})-(\d{1,2})-(\d{4})/;
    var match = dateTime.exec(string);
    return new Date (Number(match[3]),
                     Number(match[2]) - 1,
                     Number(match[1]));
}

console.log(findDate("30-1-2003")); // Thu Jan 30 2003 00:00:00 GMT +0100 (CET)
```

### Word and string boundaries

- you can enforce that the match spans the whole string
  - `^`: must match from the beginning
  - `$`: must match the end

```javascript
/^\d+$/ // matches a string that has 1 or more digits, and only contains digits
/^!/ // matches strings starting w/ an exclamation mark
/x^/ // matches nothing b/c there can't be an x before a string
```

- Use `\b` when you want to make sure that the string starts & ends on a word boundary
  - `\b` doesn't represent a character; just enforces a condition
  - word boundary can be anywhere within the string that has a word character (as in `\w`) on one side, & a nonword character on the other side

```javascript
console.log(/cat/.test("concatenate")); // true
console.log(/\bcat\b/.test("concatenate")); // false
```

### Choice patterns

`|` can be used between expressions to match x or y or z

```javascript
var animalCount = /\b\d+ (pig|cow|chicken)s?\b/;
console.log(animalCount.test("15 pigs")); // true
console.log(animalCount.test("15 pigchickens")); // false
```

### The replace method

The string's `.replace([thisString], [thatString]` method can be used with regular expressions.

```javascript
console.log("papa".replace("p", "m")); // mapa
console.log("Borobudur".replace(/[ou]/, "a")); // Barobudur
console.log("Borobudur".replace(/[ou/g, "a")); // Barabadar
```

##### Referring to parenthesized groups

```javascript
console.log(
    "Hopper, Grace\nMcCarthy, John\nRitchie, Dennis"
        .replace(/([\w ]+), ([\w ]+)/g, "$2 $1")); // Grace Hopper
        // John McCarthy
        // Dennis Ritchie
```

`$1` & `$2` refer to each pair of parentheses.

- `$1` is replaced by the text that matched the first RegExp, & `$2` by the second.
- You can use `$n` up to `$9`

##### Some intense use of RegExp and functions

```javascript
var stock = "1 lemon, 2 cabbages, and 101 eggs";

function minusOne(match, amount, unit) {
    amount = Number(amount) - 1;
    if (amount === 1) // only one left, remove the 's'
        unit = unit.slice(0, unit.length - 1);
    else if (amount == 0)
        amount = "no";
    return amount + " " + unit;
}

console.log(stock.replace(/d(\d+) (\w+)/g, minusOne)); // no lemon, 1 cabbage, and 100 eggs
```

### Greed

`+`, `*`, `?` & `{}` are greedy b/c they match as many cases as they can.

You can make them less greedy by putting a question mark after them:
`+?`, `*?`, `??`, `{}?`

In this case, they match as little as possible, & only match more if the smallest pattern isn't a match

### Dynamically creating RegExp objects

```javascript
var name = "harry";
var text = "Harry is a suspicious character.";
var regexp = new RegExp("\\b(" + name + ")\\b", "gi");

console.log(text.replace(regexp, "_$1_")); // _Harry_ is a suspicious character.
```

### The search method

You can do the equivalent of what the String method, `.indexOf([string])` does with regular expressions, by using `.search([regexp])`

It'll give you the first index of the matched expression, or -1 if there's no match.

```javascript
console.log("  word".search(/\S/)); // 2
console.log("      ".search(/\S/)); // -1
```

### The `lastIndex` property

- A regular expression's properties also include `lastIndex` and `.source`
  - `source` tells you the string that the expression was created from
  - `lastIndex` can control where the next match will start, in some circumstances
    - circumstances:
      - regular expression must have the `g` global option enabled
      - match must happen through the `.exec()` method

```javascript
var pattern = /y/g;
pattern.lastIndex = 3;

var match = pattern.exec("xyzzy");
console.log(match.index); // 4
console.log(pattern.lastIndex); // 5
```

##### Looping over matches

Use `lastIndex` & `exec`

```javascript
var input = "A string with 3 numbers in it... 42 and 88.";
var number = /\b(\d+)\b/g;
var match;
while (match = number.exec(input))
    console.log("Found", match[1], "at", match.index); // Found 3 at 14
// Found 42 at 33
// Found 88 at 40
```

## Chapter 10: Modules

### Why modules help

##### Namespacing

- JS doesn't give you as much control as Java, when it comes to controlling visibility
  - everything that needs to be visible outside of the scope of a top-level function is visible everywhere
    - you can use objects to make publicly accessible subnamespaces
    - functions can be used to create isolated, private namespaces in a module

### Require

- the `require` function allows you to load a JS module into the file
  - it's built into Node.js, but not regular JS for the browser

### Slow-loading modules

- You can Browserify your code prior to loading it on a webpage

##### Asynchronous Module Definition (AMD) module system

- wrap code in a function so the module loader loads its dependencies in the background, and then calls the function to initialiez the module when the dependencies are loaded

eg. this. It's a part of a larger program, where functions like 'define' are already written

```javascript
define(["weekday", "today"], function(weekDay, today) {
    console.log(weekDay.name(today.dayNumber()));
    }); 
```

## Chapter 12: JavaScript and the Browser

### Networks and the Internet

- __TCP (Transmission Control Protocol) connection__
  - a computer waits/listens for other computers to talk to it
    - the _port_ is the listener
  - __server__: the listening computer
  - __client__: the connecting computer 

### The Web

- __HTTP (Hypertext Transfer Protocol)__ allows computers to request documents over the network
  - each document is named by a UniversalResourceLocator
  - eg. in http://google.com/design, http:// is the protocol, google.com is the server, and design is the path
- __sandboxing__: allowing enough free reign for a program to be useful, while also restricting it from doing anything harmful (eg. hacking your computer)

## Chapter 13: The Document Object Model

### Trees

- __tree__: a data structure that has a branching structure
  - is free of cycles (so a node can't contain itself, directly or indirectly)
- nodes of the DOM:
  - regular elements' (`<div>`, `<p>`, etc.) node is 1
    - `document.ELEMENT_NODE`
  - texts' node is 3
    - `document.TEXT_NODE`
  - comments' node is 8
    - `document.COMMENT_NODE`
- You can figure out an element's node type with the property `.nodeType`
- the DOM can be used with more than just HTML (eg. XML)

### Moving through the tree

- Elements have the following useful properties:
  - `firstChild`, `lastChild`
  - `previousSibling`, `nextSibling`
  - If the specified property is non-existent, you'll get `null`

### Changing the document

- many built-in methods to change the document:
  - `.appendChild(element)`
  - `.removeChild(element)`
  - `.insertBefore(element1, element2)`
    - inserts the new node, element1 before element2
  - `.replaceChild(element1, element2)`
    - element1 (the new node) will replace element2

### Creating nodes

- you can create nodes
  - `document.createTextNode`

### Attributes

- You can set any attribute on nodes that you want, and access them through the JS
  - literally anything. You could have `<p yo="hi">Uh...</p>`
  - it's recommended that you prefix made-up ones with 'data-', to avoid accidentally setting a real attribute

### Layout

- access more attributes of an element
  - the space the element takes up (px): `offsetWidth`, `offsetHeight`
  - the space inside of an element, ignoring border width: `clietWidth`, `clientHeight`
  - the precise position of an element is accessed by `.getBoundingClientRect()`
    - it returns an object w/ `top`, `bottom`, `left`, & `right` properties
    - to make it relative to the entire document, add the current scroll position
      - global variables `pageXOffset` & `pageYOffset` indicate the current scroll position

### Query selectors

Can use CSS attribute selectors in JS with `.querySelectorAll([selector])`, but it isn't live. The behavior of the selector doesn't change as the document is changed.

```javascript
document.querySelectorAll("p > .animal"); // direct child of <p> with class of animal
```

`document.querySelector([selector])` will return the first matching element, or null if no match.

### Positioning and animating

Can use `requestAnimationFrame(callback)` to create JS animations. It'll run when the browser is ready to repaint the screen. Note that `cancelAnimationFrame` also exists.

Code snippet of it being used: 

```javascript
function animate(time) {
    if (lastTime != null)
        angle += (time - lastTime) * 0.001;
    lastTime = time;
    cat.style.top = (Math.sin(angle) * 20) + "px";
    cat.style.left = (Math.cos(angle) * 200) + "px";
    requestAnimationFrame(animate);
}

requestAnimationFrame(animate);
```

## Chapter 14: Handling Events

### Propagation

- If both the parent and a child have event handlers (eg. one for 'click'), the child's function will be called before the parent's; this is called propagation
  - you can use the `stopPropagation` method if there are any parents to which you don't want this to occur

```javascript
var para = document.querySelector("p");
var button = document.querySelector("button");
para.addEventListener("mousedown", function() {
    console.log("Handler for paragraph.");
});
button.addEventListener("mousedown", function(event) {
    console.log("Handler for button");
    if (event.which == 3)
        event.stopPropagation();
});
```

##### Which one was targeted?

Can find out which one was clicked on, hovered over, etc. like this:

```javascript
document.body.addEventListener("click", function(event) {
    if (event.target.nodeName == "BUTTON")
        console.log("clicked", event.target.textContent);
});
```

### Default actions

- for most events, the event handler is called before the default behavior (eg. clicking on a link)
  - if you want to prevent the default behavior, use the `preventDefault` method

```javascript
var link = document.querySelector("a");
link.addEventListener("click", function(event) {
    console.log("Lol no");
    event.preventDefault();
});
```

### Mouse clicks

- `pageX` & `pageY` give you the location relative to the entire document
- `clientX` & `clientY` give you the location relative to the part of the document that's in view
- `innerHeight` & `innerWidth` give you the size of the window

### Debouncing

- __debouncing an event__: when you use `setTimeout` to ensure that you aren't executing a function in a handler too often

## Chapter 16: Drawing on Canvas

- canvas element converts shapes to pixels when they're drawn
  - the only way to move a shape on the canvas is to clear it and then redraw it

### The `canvas` element

##### Initializing it:

```javascript
var canvas = document.querySelector("canvas");
var context = canvas.getContext("2d");
context.fillStyle = "red";
context.fillRect(10, 10, 100, 50); // top, left, width, height
```

### Filling and stroking

```javascript
context.strokeStyle = "color"; // sets color of line stroke
context.lineWidth = 10; // width of line stroke
context.strokeRect(top, left, width, height); // draws outline of a rectangle
```

### Paths

You create paths by doing a sequence of method calls to describe its shape.

```javascript
context.beginPath();
for (var y = 10; y < 100; y+= 10) {
    context.moveTo(10, y);
    context.lineTo(90, y);
}
context.stroke(); // draws the path described by the for loop
```

### Curves

##### Quadratic curves

- `.quadraticCurveTo(controlPointX, controlPointY, x, y)` method
  - you go from a control point to the destination point
    - the control point attracts the line, which curves the line;
      - the line doesn't go through the control point

```javascript
// control = (60, 10); goal = (90, 90)
context.beginPath()
context.quadraticCurveTo(60, 10, 90, 90);
context.lineTo(60, 10);
context.closePath();
context.stroke();
```

##### Bezier curves

- `.bezierCurveTo(cp1x, cp1y, cp2x, cp2y, x, y)` method
  - you have 2 control points, and one destination point; the control points act as the line's end points
  - control points specify the direction at both ends of the curve, so the further from their corresponding point, the more the curve will be attracted in that dirrection

```javascript
context.beginPath();
// control1 = (10, 10); control2 = (90, 10); goal = (50, 90);
context.bezierCurveTo(10, 10, 90, 10, 50, 90);
context.lineTo(90, 10);
context.lineTo(10, 10);
context.closePath();
context.stroke();
```

##### Arcs

- `.arcTo(cpx, cpy, x, y, radius)` method
  - the round part of the arc is drawn, as well as a line from the starting position to the start of the rounded part
    - doesn't draw the line from the end of the rounded part to the goal; you'll need to use the `lineTo(goalx, goaly)` method to accomplish that 

```javascript
cx.beginPath();

// control = (90, 10); goal = (90, 90); radius = 20
cx.arcTo(90, 10, 90, 90, 20);
cx.stroke();
```

##### Drawing a circle

```javascript
cx.beginPath();

// center = (50, 50); radius = 40; angle = 0 to 7 radians
cx.arc(50, 50, 40, 0, 7);
cx.stroke();
```

### Text

- `.font = "[fontSize]px [font-style] [font-family]`
  - font-styles include things like italic/bold
- `.fillStyle = "[color]"`
- `.fillText("[text here]", x, y)`
  - x & y indicate the start of where the text stands;
  - you can change `textAlign`, `textBaseline`

### Images

- `.drawImage(elemnt, [width], [height])` method lets you draw pixel images on a canvas
  - if width & height aren't passed in, the original size will be drawn
  - use a 'load' event handler prior to drawing the picture, so you only draw it once it's loaded

```javascript
var img = document.createElement("img");
img.src = "image.png";
img.addEventListener("load", function() {
    for (var x = 10, x < 200; x += 30) {
        cx.drawImage(img, x, 10);
    }
});
```

##### Animating a picture on a canvas

- `.clearRect(x, y, )`

```javascript
var spriteW = 24, spriteH = 30;
img.addEventListener('load', function() {
    var cycle = 0;
    setInterval(function() {
        cx.clearRect(0, 0, spriteW, spriteH);
        cx.drawImage(img,
            // source rectangle
            cycle * spriteW, 0, spriteW, spriteH,
            // destination rectangle
            0, 0, spriteW, spriteH
        );
        cycle = (cycle + 1) % 8;
    }, 120);
});
```

### Transformation

- `.scale(factor of x, factor of y)`
- the order of transformations can change your result; so pay attention to that
- `.rotate([angle in radians])`
- `.translate(x, y)`

```javascript
function flipHorizontally(context, around) {
    context.translate(around, 0);
    context.scale(-1, 1);
    context.translate(-around, 0);
}
```

### Storing and clearing transformations

- storing it
- `.save()`
  - this will push a transformation onto the top of a stack;
- `.restore()`
  - this uses the transformation on the top of the stack

### Choosing a graphics interface

- with canvas, you can't register mouse event handlers on all parts of the canvas
  - you can do so with html/svg graphics, though
- canvas has a lower cost per shape, and is good for drawing pixel images

## Chapter 17: HTTP

### The protocol

- port 80 is the default port for HTTP traffic
- methods
  - `GET` want to get the specified resource
  - `DELETE` want to delete a resource
  - `POST` want to send information to a resource
- status codes
  - `2XX` request succeeded
  - `4XX` something was wrong w/ the request
  - `5XX` something was wrong w/ the server

### XMLHttpRequest

- `XMLHttpRequest`: the interface through which browser JS can make HTTP requests
  - eg. getting suggestions for searches as a user types

### Sending a request

- create a request object w/ `XMLHttpRequest` constructor, & call the `open` & `send` methods
  - `open` configures the request
  - `send` request body
    - is null for `GET` requests

```javascript
var req = new XMLHttpRequest();
req.open("GET", "example/data.txt", false);
req.send(null);
console.log(req.responseText); // This is the content of data.txt
// can also get .statusText, .status, .getResponseHeader
```

### Asynchronous Requests

- if the third argument passed to `.open()` is `true`, then the browser does an asynchronous request
  - but then it's important to listen for when the request object loads

```javascript
var req = new XMLHttpRequest();
req.open("GET", "example/data.txt", true);
req.addEventListener("load", function() {
    console.log("Done:", req.status);
});
req.send(null);
```

### Fetching XML Data

You can use things like .getElementsByClassName(), .querySelector(), etc. on requests, too...

And you can use JSON

```javascript
console.log(req.responseXML.querySelectorAll('idk').length);
console.log(JSON.parse(req.responseText));
```

### HTTP sandboxing

- browsers don't let scripts make HTTP requests to other domains, to keep things safe
  - servers can go around that by saying they'll allow requests from other domains (`Access-Control-Allow-Origin: *`)

### Promises

Promises wrap an asynchronous action in an object, which can be passed and told to do certain things when the action finishes or fails. (Promise.js)

Eg. a wrapper for `GET` requests

```javascript
// THIS 
function get(url) {
    return new Promise(function(succeed, fail) {
        var req = new XMLHttpRequest();
        req.open("GET", url, true);
        req.addEventListener("load", function() {
            if (req.status < 400)
                succeed(req.responseText);
            else
                fail(new Error("Request failed: " + req.statusText));
        });
        req.addEventListener("error", function() {
            fail(new Error("Network error"));
        });
        req.send(null);
    })
}

// WHEN USED WITH PROMISES, BECOMES:
get("example/data.txt").then(function(text) {
    console.log("data.txt: " + text);
}, function(error) {
    console.log("Failed to fetch data.txt: " + error);
});
```

## Chapter 18: Forms and Form Fields

### Focus

You can set focus in the HTML too. You can also change the order in which the `tab` key goes to things

```html
<input type="text" autofocus>
<input type="text" tabindex=1>
<button tabindex=2>Yo</button>
```

### The form as a whole

- `name` attribute: determines the way its value will be indentified by the server when the form is submitted

You don't need to use forms traditionally. You could just disable the form submission, and use JS to submit the form w/o refreshing the page, via XMLHttpRequest.

### Storing data client-side

Local storage persists until the user clears their local data, or you use `removeItem`

```javascript
localStorage.setItem("username", "ugh");
console.log(localStorage.getItem("username")); // ugh
localStorage.removeItem(username);
```

