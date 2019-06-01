# ECMAScript 2015 (ES6)

### Named exports vs. default exports
[(source)](http://www.2ality.com/2014/09/es6-modules-final.html)

* __named export__: several exports per module
```js
// module.js
export const foo = "foo";
export function foo2(bar) { ... }
export function foo3(hi) { ... }
export function foo4(hello) { ... }

// where module is being used
import { foo2, foo3 }  from 'module'
```

* __default export__: one per module
```js
// module.js
export default function() {...};

/*Alternatively*/
var hehe = function() {...};
export {hehe as default};

// where module is being used
import whateverIWantToCallItNow from 'module';
import {default as whateverIWantToCallItNow} from 'module' // alternatively

whateverIWantToCallItNow();

```

* both named & default exports:
```js
// underscore.js
var _ = function(obj) {...};
var each = _.each = _.forEach = function(...) {...};

// main.js
var _ = require('underscore');
var each = _.each;
```

or

```js
// module.js
export default function() {...}
export function hehe() {...}

// main.js
import myChosenName, {hehe} from 'module';
```

#### More import examples
```js
// main.js
import * as myModule from 'module'; // imports all contents
```

# JSX

* __JSX__: an XML-like syntax extension to ES6, which is used to express the virtual DOM
    - JSX is interpreted & converted to virtual DOM (diffed vs. the real DOM, so only differences are applied)
