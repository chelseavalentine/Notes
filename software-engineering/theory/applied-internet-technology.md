# Applied Internet Technology

Notes on the Applied Internet Technology course at NYU.

### `apply`, `call`, and `bind`

* `call`: calls a function with `(this, arg1, arg2, …, argN)`
* `apply`: calls a function with `(this, [arg1, … argN])`
* `bind`: returns a new function with `(this, setArgument, values, here)`

### Objects

* **polymorphism:** having the same interface to instances of different types
* global objects: `global` (node), `window` (browser)
* `in` picks up inherited properties, whereas `hasOwnProperty` does not
* loop over object properties with `for (variableName in [Object])`
* get an object's prototype with `.prototype` or `Object.getPrototypeOf(object)`

#### Node.js

* non-blocking iO and asynchronous events – everything runs in parallel, except for your code
* you can access `global`, `require`, `exports`, `module`, `__filename` and `__dirname` in Node

#### Arrays

* `splice(inclusiveStartIndex, deleteCount)`

### Functions

* functions are the only construct that create a new scope
* `.forEach`, `.filter`, `.map`, `.reduce(accumulatorValue, currentValue)`
* all instances share the prototype Object
  * inherit with `Inherited.prototype = Object.create(SuperObject.prototype)` and `Inherited.prototype.constructor = someFunction()`
* **IIFEE = Immediately Invoked Function Expression**
* invocations: function call, method call, call/apply

### HTTP

* **request:** [Method] [Path] HTTP/1.1
  * has a `Host` header
* **response:** HTTP/1.1 [status code] [description]
  * has a `Content-Type` header
* `netcat` (nc), `curl`

#### Cookies

* `HttpOnly` means JS can't read cookies; only done via HTTP (third-party javascript can read cookies otherwise)
* `Secure`: only send cookies for encrypted requests (HTTPS & SSL)

#### URL

* In `http://google.com:8080/pizza?count=4#menu`,
  * protocol/schema: HTTP
  * domain: google.com
  * port: 8080
  * path: pizza
  * query: count=4
  * fragment ID: #menu

#### HTTP, net module, cookies

* **XSS attack:** loading third-party JS, which can read the current domain's cookies
* `"Location"` header tells the browser where to redirect to
* `"Secure"` means things in `"Set-Cookie"` are only sent over SSL/HTTPS
* the server returns a response with the `"Set-Cookie"` header after the request with the same header is made
* the GET request is located in the URL of the HTTP request (queries)

### Databases

* relational vs. noSQL (non-relational) databases:
  * relational databases categorizes data into tables (relations), whereas noSQL databases comprise of the key-value, document, column, or graph databases
* databases should be **ACID-compliant**
  * Atomicity
  * Consistent: every transaction leads to a valid database state
  * Isolation: failed transactions don't affect others
  * Durability: once a transaction is complete, it should persist through crash, power loss, etc.

### Routers

* **routing:** the mechanism by which requests (specified by a URL and HTTP method) are routed to the code that handles the request

### Authentication

* **authentication:** the process of determining whether or not they're who they claim to be
* **authorization:** the rules that determine whether a user is allowed to perform an action that they're trying to perform
* TLS (Transport Layer Security)/SSL (Secure Sockets Layer) are cryptographic tools to encrypt the traffic between the server and client
* hashing is a one-way function, whereas encryption is a 2-way function
* **salting:** adding a (unique per user, per password) random string to the password before hashing
  * you also store the salt
* **serialization:** translating a data structure/object to a storable format

#### TLS/SSL (Cryptographic protocols)

* TLS and SSL sit between TCP/IP and HTTP to ensure private, trusted communication
* **symmetric cryptography:** the same key is used to encrypt to cipher text, as well as to decrypt
  * makes the connections via SSL/TLS private
* a message authentication code is a shared secret key to ensure messages are untampered when they're from trusted senders

### DOM

* the DOM (Document Object Model) is a fully object-oriented representation of a web page
* node types: `document.ELEMENT_NODE`, `document.TEXT_NODE`, `parentNode`, `childNodes`, `firstChild`, `previousSibling`, `nextSibling`
* modifying the DOM:
  * `.insertBefore(nodeToInsert, beforeThisNode)`
  * `.replaceChild(nodeToInsert, nodeToReplace)`
  * `createTextNode(text)`
* `window.requestAnimationFrame` is invoked prior to the screen being repainted

#### Bubbling/propagation

* use case: 2 elements are nested within each other and both have event listeners
  * the event handler of the more specific element (innermost) is called first, and then bubbles up through the parent elements
* you can prevent bubbling/propagation with `element.stopPropagation()`

#### CSS

* box-sizing determines the box-model used to calculate the width and height of an element
  * `content-box` (the default): calculates the width and height by content only (i.e. not padding, border, or margin)
  * `border-box`: includes the padding and border in size calculations (doesn't include the margin)
* position
  * `absolute`: positioned relative to the nearest positioned ancestor (or body)
* sizing units have relative (`em`, `rem`) and absolute (`px`) units
  * `em`: dynamic sizing unit relative to the font-size of the parent element
  * `rem`: font-size of the root (`<html>` element); doesn't stack for nested elements
* **pseudo-classes:** keywords added to a selector to specify the element

#### AJAX (Asynchronous JavaScript and XML)

* uses JS and `XMLHttpRequest` (or fetch API) to asynchronously exchange data between the browser and server
* **Same Origin Policy (SOP)** restricts how a thing from one origin can interact with a document/script/data from another origin
  * prevents scripting access to resources if it's on different sites
  * same origin if also the *same* protocol (HTTP, HTTPS), port, and host
* **Cross Site Request Forgery (CSRF):** making requests to and from another site a user is logged into
* **Cross Origin Resource Sharing (CORS)** allows JSON, fonts, etc. to be requested from another origin

#### Promises

* **promise:** a representation of an asynchronous action
  * states: pending, fulfilled, rejected

### Sockets and near-realtime communication

* techniques near real-time communication: AJAX polling, long polling, server-sent events, web sockets
  * **Server Side Events (SSEs):** client opens connection to server, which sends data, but client can't respond
  * WebSocket protocol (independent from HTTP): 2-way connection, allowing cross-origin communication (possibly at the cost of security)

