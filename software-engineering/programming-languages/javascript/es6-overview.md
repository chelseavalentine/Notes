# ES6 Features Overview

Notes taken on a [ECMAScript 6 features overview](https://github.com/lukehoban/es6features).

### Rest parameters and spread operators

MDN docs: [rest parameters](https://developer.mozilla.org/en-US/docs/Web/JavaScript/Reference/Functions/rest_parameters); [spread operator](https://developer.mozilla.org/en-US/docs/Web/JavaScript/Reference/Operators/Spread_operator);

```javascript
// Rest parameters
function restEx(x, ...y) {
  // y is an array of args w/ index 1 & beyond
}

// Spread operator
function spreadEx(x, y, z) {
  return x + y + z;
}

spreadEx(...[1, 2, 3]); // 6
```

### Iterators and `for ... of`

You can declare iterators, which allow you to do iterator-based iteration:

```javascript
for (let person of people) {
}
```

### Modules

* you can export things from other modules in a model with the `export` keyword

* you can load modules dynamically with `System.import('module').then((m) => {})`

### More data structures!

* `Map`, `Set`, `WeakMap`, `WeakSet`

### Symbols

Symbols are a new primitive type

### Etc

* `Array.of(arg1, arg2, ...)`
* `Array.from([some arg])` returns a real Array
* Array: `.fill`, `.find`, `.findIndex`, `.copyWithin`, `.entries()`, `.keys()`, `.values()`
