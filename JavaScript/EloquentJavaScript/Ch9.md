# Chapter 9: Regular Expressions


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

* `.test()` will tell you whether the string contains a match of the pattern in the expression

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

* `\d` Any digit character
* `\w` Any alphanumeric character ("word character")
* `\s` Any whitespace character (space, tab, newline, and similar)
* `\D` Any character that is _not_ a digit
* `\W` Any nonalphanumeric character
* `\S` A nonwhitespace character
* `.` Any character except for newline

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

* Using a `+` sign after an element in a regular expression, it says the element can be repeated more than once.
    - Eg. (`/\d+/` matches one or more digit characters.)
* Using a `*` allows the pattern to match 0 times
    - doesn't prevent a pattern from repeating, it just matches 0 instances if it can't find a text match

```javascript
console.log(/'\d+'/.test("'123'")); // true
console.log(/'\d+'/.test("''")); // false
console.log(/\d*/.test("'123'")); // true
console.log(/'\d*'/.test("''")); // true
```

* Using a `?` says the pattern match is optional. So it can occur 0 or 1 time.

```javascript
var neighbor = /neighbou?r/;
console.log(neighbor.test("neighbour")); // true
console.log(neighbor.test("neighbor")); // true
```

* Repeating a set number of times:
    - when `{n}` (n is a positive integer) is put after an element, the element will occur n times
    - `{n1, n2}` specifies a range where the element must occur at least n1 times, and at most n2 times
    - `{, n}` means 0 to n times
    - `{n, }` means n or more times


### Grouping subexpressions

* use parentheses for clarity/precision when using `*`/`+` on 1+ element at a time
* `i` in an expression makes the expression case insensitive

```javascript
/boo+(hoo+)+/i
```


### Matches and groups

##### Matching

* `.exec([string])` will return the object w/ information about the match, if found
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

* parameters: `(year, month - 1, day, hours, minutes, seconds, miliseconds)`
* month numbers start at 0
* date numbers start at 1

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

* you can enforce that the match spans the whole string
    - `^`: must match from the beginning
    - `$`: must match the end

```javascript
/^\d+$/ // matches a string that has 1 or more digits, and only contains digits
/^!/ // matches strings starting w/ an exclamation mark
/x^/ // matches nothing b/c there can't be an x before a string
```

* Use `\b` when you want to make sure that the string starts & ends on a word boundary
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

* `$1` is replaced by the text that matched the first RegExp, & `$2` by the second.
* You can use `$n` up to `$9`

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


### The lastIndex property

* A regular expression's properties also include `lastIndex` and `.source`
    - `source` tells you the string that the expression was created from
    - `lastIndex` can control where the next match will start, in some circumstances
        + circumstances:
            * regular expression must have the `g` global option enabled
            * match must happen through the `.exec()` method

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
